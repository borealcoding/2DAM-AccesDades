package es.florida.accesDades.ae6;
/*
 * @author Eduardo Rua Chamorro | 2. DAM - Florida Universitaria
 * @version [actividad] | [modulo]
 * @description [...]
 * */
// IMPORTACIO DE LLIBRERIES
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class VistaTemporal extends JFrame {
	// DECLARACIONS GENERALS
	private JPanel contentPane;
	// DECLARACIONS STATIC (AQUESTES VARIABLES S'USEN EN DIVERSOS METODES)
	
	public VistaTemporal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaTemporal.class.getResource("/img/mongodbIcon.png")));
		visualitzar();
	} // end-constructor
	
	public static void mostrarDialeg(int dialogId) {
		try {
			// PROPIETATS GENERALS DEL JDIALOG
        	JDialog jd = new JDialog(new JFrame());
			jd.getContentPane().setBackground(new Color(3, 131, 135));
	        
    		JLabel tagDialog = new JLabel("Titol descriptiu");
    		tagDialog.setHorizontalAlignment(SwingConstants.CENTER);
    		tagDialog.setForeground(Color.WHITE);
    		tagDialog.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
    		tagDialog.setBounds(10, 11, 549, 28);

			JTextField txtFIdLlibre = new JTextField();
        	txtFIdLlibre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		txtFIdLlibre.setBounds(124, 66, 86, 28);
    		txtFIdLlibre.setColumns(8);
    		
    		JLabel tagIdLlibre = new JLabel("ID llibre");
    		tagIdLlibre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		tagIdLlibre.setForeground(Color.WHITE);
    		tagIdLlibre.setBounds(39, 69, 46, 14);
    		
    		JTextField txtFTitol = new JTextField();
    		txtFTitol.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		txtFTitol.setBounds(124, 111, 397, 28);
    		txtFTitol.setColumns(10);
    		
    		JLabel tagTitol = new JLabel("Titol");
    		tagTitol.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		tagTitol.setForeground(Color.WHITE);
    		tagTitol.setBounds(39, 114, 46, 14);
    		
    		JTextField txtFAutor = new JTextField();
    		txtFAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		txtFAutor.setBounds(124, 154, 397, 28);
    		txtFAutor.setColumns(10);
    		
    		JLabel tagAutor = new JLabel("Autor");
    		tagAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		tagAutor.setForeground(Color.WHITE);
    		tagAutor.setBounds(39, 157, 46, 14);
    		
    		JTextField txtFAnyN = new JTextField();
    		txtFAnyN.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		txtFAnyN.setBounds(124, 196, 86, 28);
    		txtFAnyN.setColumns(10);
    		
    		JLabel tagAnyN = new JLabel("Any Naixement");
    		tagAnyN.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		tagAnyN.setForeground(Color.WHITE);
    		tagAnyN.setBounds(39, 199, 102, 14);
    		
    		JLabel tagAnyP = new JLabel("Any Publicaci\u00F3");
    		tagAnyP.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		tagAnyP.setForeground(Color.WHITE);
    		tagAnyP.setBounds(220, 199, 86, 14);
    		
    		JTextField txtFAnyP = new JTextField();
    		txtFAnyP.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		txtFAnyP.setBounds(301, 196, 86, 28);
    		txtFAnyP.setColumns(10);
    		
    		JTextField txtFEditorial = new JTextField();
    		txtFEditorial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		txtFEditorial.setBounds(124, 239, 238, 28);
    		txtFEditorial.setColumns(10);
    		
    		JLabel tagEditorial = new JLabel("Editorial");
    		tagEditorial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		tagEditorial.setForeground(Color.WHITE);
    		tagEditorial.setBounds(39, 242, 46, 14);
    		
    		JLabel tagNumPag = new JLabel("N\u00BA p\u00E0gines");
    		tagNumPag.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		tagNumPag.setForeground(Color.WHITE);
    		tagNumPag.setBounds(382, 242, 67, 14);
    		
    		JTextField txtFNumPag = new JTextField();
    		txtFNumPag.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		txtFNumPag.setBounds(449, 239, 72, 28);
    		txtFNumPag.setColumns(10);
    		
    		/*
    		 * DIALEG PER A MOSTRAR TOTS ELS TITOLS DE LA BIBLIOTECA
    		 * ELS PROCEDIMENTS EXECUTATS DINS DEL MATEIX ESTAN COMENTATS PER SEPARAT
    		 * */
	        if(dialogId == 1) {
	        	// codi que es executara
	        	jd.getContentPane().setLayout(null); // LAYOUT DEL DIALEG PER AL METODE DE CONSULTAR LLIBRE
	        	jd.setBounds(100, 100, 585, 361); // DIMENSIONS DEL JDIALOG
	        	String resultat = "";
	        	
	        	// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
	    		MongoClient mongoClient = new MongoClient("localhost", 27017);
	    		MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    		MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
		    	/* Tambe podem crear un objecte BSON per a usar filtres
		   		 * > Bson query = Filters.eq("artista", "System Of A Down");*/
		   		MongoCursor<Document> cursor = coleccio.find().iterator();
		   		while(cursor.hasNext()) {
		   			//System.out.println(cursor.next().toJson());
		   			resultat += cursor.next().toJson();
		   			resultat += "\n\n";
		   		}
		   		
		   		// PER DIVERSOS PROBLEMES, HE CREGUT CONVENIENT MOSTRAR LA INFORMACIO RECOLLIDA EN UN JFRAME AUXILIAR QUE TINDREM A LA CLASSE FrameAuxiliar
				FrameAuxiliar frameAux = new FrameAuxiliar();
				frameAux.getTextArea().setText(resultat);
				frameAux.setLocationRelativeTo(null);
				frameAux.setVisible(true);
				frameAux.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // AQUESTA INSTRUCCIO EVITA QUE AL TANCAR LA FINESTRA, ES TANQUE TAMBE LA DEL MENU
		        
		   		mongoClient.close();
	        } // end-if
	        
	        /*
    		 * DIALEG PER A MOSTRAR LA DESCRIPCIO D'UN LLIBRE SEGONS EL SEU ID
    		 * ELS PROCEDIMENTS EXECUTATS DINS DEL MATEIX ESTAN COMENTATS PER SEPARAT
    		 * */ 
	        if(dialogId == 2) {
	        	jd.getContentPane().setLayout(null); // LAYOUT DEL DIALEG PER AL METODE DE CONSULTAR LLIBRE
	        	jd.setBounds(100, 100, 585, 361); // DIMENSIONS DEL JDIALOG
	        	
	        	// MODIFICACIONS DE CARACTERISTIQUES ESPECIFIQUES PER A AQUEST DIALEG
	        	tagDialog.setText("DADES DEL LLIBRE");
	        	txtFIdLlibre.setEditable(true);
	    		txtFTitol.setEditable(false);
	    		txtFAutor.setEditable(false);
	    		txtFAnyN.setEditable(false);
	    		txtFAnyP.setEditable(false);
	    		txtFEditorial.setEditable(false);
	    		txtFNumPag.setEditable(false);

	    		JButton btnExecucio = new JButton("Generar informaci\u00F3 per l'ID");
	    		// AQUEST BOTO S'ENCARREGA DE, UNA VEGADA S'HAJA INDICAT EL ID DEL LLIBRE QUE VOLEM CONSULTAR, MOSTRAR LA INFORMACIO DE TOTS ELS SEUS ATRIBUTS
	    		btnExecucio.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				// codi que es executara
	    				// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
	    				Scanner sc = new Scanner(System.in);
	    				MongoClient mongoClient = new MongoClient("localhost", 27017);
	    				MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    				MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
	    				
	    				System.out.print("Indica l'ID del llibre que vols consultar: "); String idLlibre = sc.nextLine();
	    				MongoCursor<Document> cursor = coleccio.find(Filters.eq("Id", idLlibre)).iterator();
	    				while(cursor.hasNext() ) {
	    					System.out.println(cursor.next().toJson());
	    				}
	    				mongoClient.close();
	    			} // end-actionPerformed
	    		});
	    		btnExecucio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		btnExecucio.setBounds(231, 65, 171, 28);

		        // VISUALITZACIO DELS COMPONENTS DEL JDIALOG
	    		jd.getContentPane().add(tagDialog);
	    		jd.getContentPane().add(txtFIdLlibre);
	    		jd.getContentPane().add(tagIdLlibre);
	    		jd.getContentPane().add(txtFTitol);
	    		jd.getContentPane().add(tagTitol);
	    		jd.getContentPane().add(txtFAutor);
	    		jd.getContentPane().add(tagAutor);
	    		jd.getContentPane().add(txtFAnyN);
	    		jd.getContentPane().add(tagAnyN);
	    		jd.getContentPane().add(txtFAnyP);
	    		jd.getContentPane().add(tagAnyP);
	    		jd.getContentPane().add(txtFEditorial);
	    		jd.getContentPane().add(tagEditorial);
	    		jd.getContentPane().add(tagNumPag);
	    		jd.getContentPane().add(txtFNumPag);
	    		jd.getContentPane().add(btnExecucio);
	    		
	    		// VISUALITZACIO DEL JDIALOG
		        jd.setVisible(true);
		        jd.setLocationRelativeTo(null);
	        } // end-if
	        
	        /*
    		 * DIALEG PER A CREAR UN LLIBRE SEGONS ELS SEUS ATRIBUTS
    		 * ELS PROCEDIMENTS EXECUTATS DINS DEL MATEIX ESTAN COMENTATS PER SEPARAT
    		 * */
	        if(dialogId == 3) {
	        	jd.getContentPane().setLayout(null); // LAYOUT DEL DIALEG PER AL METODE DE CREAR LLIBRE
	        	jd.setBounds(100, 100, 585, 361); // DIMENSIONS DEL JDIALOG
	        	
	        	// MODIFICACIONS DE CARACTERISTIQUES ESPECIFIQUES PER A AQUEST DIALEG
	        	tagDialog.setText("SISTEMA DE CREACIÓ DE LLIBRES");
	    		txtFTitol.setEditable(true);
	    		txtFAutor.setEditable(true);
	    		txtFAnyN.setEditable(true);
	    		txtFAnyP.setEditable(true);
	    		txtFEditorial.setEditable(true);
	    		txtFNumPag.setEditable(true);
	    		JButton btnExecucio = new JButton("Crear llibre");
	    		btnExecucio.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
    					// codi que es te que executar
	    				// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
	    				MongoClient mongoClient = new MongoClient("localhost", 27017);
	    				MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    				MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
	    				
	    				Scanner sc = new Scanner(System.in);
	    				String camp = "", valor = "", decisio = "", pregunta = "Vols afegir altre llibre?: ";
	    				Document doc = new Document();
	    				
	    				camp = "Id";
	    				System.out.print(camp+": "); valor = sc.nextLine();
	    				doc.append(camp, valor);
	    				
	    				camp = "Titol";
	    				System.out.print(camp+": "); valor = sc.nextLine();
	    				doc.append(camp, valor);
	    				
	    				camp = "Autor";
	    				System.out.print(camp+": "); valor = sc.nextLine();
	    				doc.append(camp, valor);
	    				
	    				camp = "Any_naixement";
	    				System.out.print(camp+": "); valor = sc.nextLine();
	    				doc.append(camp, valor);
	    				
	    				camp = "Any_publicacio";
	    				System.out.print(camp+": "); valor = sc.nextLine();
	    				doc.append(camp, valor);
	    				
	    				camp = "Editorial";
	    				System.out.print(camp+": "); valor = sc.nextLine();
	    				doc.append(camp, valor);
	    				
	    				camp = "Nombre_pagines";
	    				System.out.print(camp+": "); valor = sc.nextLine();
	    				doc.append(camp, valor);
	    				
	    				coleccio.insertOne(doc);
//	    				doc.append("titulo", "Hunting High and Low");
	    				
	    				/* Tambe podem pasar-li una llista de Documents que vullgem crear!
	    				 * coleccio.insertMany(llista);*/
	    				mongoClient.close();
	    			} // end-actionPerformed
	    		});
	    		btnExecucio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		btnExecucio.setBounds(220, 63, 124, 28);
	    		
	    		// VISUALITZACIO DELS COMPONENTS DEL JDIALOG
	    		jd.getContentPane().add(tagDialog);
	    		jd.getContentPane().add(txtFTitol);
	    		jd.getContentPane().add(tagTitol);
	    		jd.getContentPane().add(txtFAutor);
	    		jd.getContentPane().add(tagAutor);
	    		jd.getContentPane().add(txtFAnyN);
	    		jd.getContentPane().add(tagAnyN);
	    		jd.getContentPane().add(txtFAnyP);
	    		jd.getContentPane().add(tagAnyP);
	    		jd.getContentPane().add(txtFEditorial);
	    		jd.getContentPane().add(tagEditorial);
	    		jd.getContentPane().add(tagNumPag);
	    		jd.getContentPane().add(txtFNumPag);
	    		jd.getContentPane().add(btnExecucio);
	    		
	    		// VISUALITZACIO DEL JDIALOG
		        jd.setVisible(true);
		        jd.setLocationRelativeTo(null);
	        } // end-if
	        
	        /*
    		 * DIALEG PER A MODIFICAR ELS ATRIBUTS D'UN LLIBRE SEGONS EL SEU ID
    		 * ELS PROCEDIMENTS EXECUTATS DINS DEL MATEIX ESTAN COMENTATS PER SEPARAT
    		 * */
	        if(dialogId == 4) {
	        	jd.getContentPane().setLayout(null); // LAYOUT DEL DIALEG PER AL METODE DE MODIFICAR LLIBRE
	        	jd.setBounds(100, 100, 585, 361); // DIMENSIONS DEL JDIALOG
	        	
	        	// MODIFICACIONS DE CARACTERISTIQUES ESPECIFIQUES PER A AQUEST DIALEG
	        	tagDialog.setText("ACTUALITZACIO DE DADES");
	        	txtFIdLlibre.setEditable(true);
	    		txtFTitol.setEditable(true);
	    		txtFAutor.setEditable(true);
	    		txtFAnyN.setEditable(true);
	    		txtFAnyP.setEditable(true);
	    		txtFEditorial.setEditable(true);
	    		txtFNumPag.setEditable(true);
	    		
	    		JButton btnExecucio = new JButton("Modificar llibre");
	    		btnExecucio.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				// codi que es te que executar
	    				// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
	    				Scanner sc = new Scanner(System.in);
	    				MongoClient mongoClient = new MongoClient("localhost", 27017);
	    				MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    				MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
	    				
	    				System.out.print("Indica l'ID del llibre que vols modificar: "); String idLlibre = sc.nextLine();
	    				coleccio.updateOne(Filters.eq("Id", idLlibre), new Document("$set", new Document("artista", "anonimo")));
	    				/* Tambe podem modificar mes d'un document a la vegada amb la seguent sentencia:
	    				 * > coleccio.updateMany(Filters.eq("formato", "WAV"), new Document("$set", new	Document("formato", "OGG")));*/
	    				mongoClient.close();
	    			} // end-actionPerformed
	    		});
	    		btnExecucio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		btnExecucio.setBounds(231, 65, 171, 28);
	        	
		        // VISUALITZACIO DELS COMPONENTS DEL JDIALOG
	    		jd.getContentPane().add(tagDialog);
	    		jd.getContentPane().add(txtFIdLlibre);
	    		jd.getContentPane().add(tagIdLlibre);
	    		jd.getContentPane().add(txtFTitol);
	    		jd.getContentPane().add(tagTitol);
	    		jd.getContentPane().add(txtFAutor);
	    		jd.getContentPane().add(tagAutor);
	    		jd.getContentPane().add(txtFAnyN);
	    		jd.getContentPane().add(tagAnyN);
	    		jd.getContentPane().add(txtFAnyP);
	    		jd.getContentPane().add(tagAnyP);
	    		jd.getContentPane().add(txtFEditorial);
	    		jd.getContentPane().add(tagEditorial);
	    		jd.getContentPane().add(tagNumPag);
	    		jd.getContentPane().add(txtFNumPag);
	    		jd.getContentPane().add(btnExecucio);
	    		
	    		// VISUALITZACIO DEL JDIALOG
		        jd.setVisible(true);
		        jd.setLocationRelativeTo(null);
	        } // end-dialog4
	        
	        /*
    		 * DIALEG QUE MOSTRARA EL PROCES, MITJANÇANT INPUT DIALOGS, DE COM ESBORRAR UN LLIBRE SEGONS EL SEU ID
    		 * ELS PROCEDIMENTS EXECUTATS DINS DEL MATEIX ESTAN COMENTATS PER SEPARAT
    		 * */
	        if(dialogId == 5) {
	        	// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
	        	Scanner sc = new Scanner(System.in);
	    		MongoClient mongoClient = new MongoClient("localhost", 27017);
	    		MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    		MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
	    		
	    		System.out.print("Indica l'ID del llibre que vols esborrar: "); String idLlibre = sc.nextLine();
	    		coleccio.deleteMany(Filters.eq("Id", idLlibre));
	    		/* Tambe podem esborrar mes d'un document amb:
	    		 * > coleccio.deleteMany(Filters.eq("artista", "a-ha"));
	    		 * Y si desitjem esborrar la coleccio sencera...
	    		 * > coleccio.drop();*/
	    		mongoClient.close();
	        } // end-dialog5
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "ALGO VA MAL... @.@\nPER RAONS DE SEGURETAT ES TANCARA EL PROGRAMA\nADEU :)","ERROR! :(" , JOptionPane.ERROR_MESSAGE);
		} // end-try-catch
	} // end-mostrarDialeg
	
	/*
	 * METODE: 
	 * PARAMETRES:
	 * DEFINICIO:  
	 * */

	public void visualitzar() {
		setTitle("AE6 - MongoDB");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 445);
		contentPane = new JPanel() {  
			 public void paintComponent(Graphics g) {  
			          Image img = Toolkit.getDefaultToolkit().getImage(  
			        		  VistaTemporal.class.getResource("/img/appBackground3.jpg"));  
			          g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
			     }  
			};
		contentPane.setBackground(new Color(2, 52, 48));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel tagTitol = new JLabel("Men\u00FA d'opcions de MongoDB");
		tagTitol.setForeground(Color.WHITE);
		tagTitol.setHorizontalAlignment(SwingConstants.CENTER);
		tagTitol.setFont(new Font("Book Antiqua", Font.PLAIN, 23));
		tagTitol.setBounds(32, 6, 434, 43);
		contentPane.add(tagTitol);
		
		JLabel tagAutor = new JLabel("Alumne: Eduardo Ru\u00E1 Chamorro");
		tagAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		tagAutor.setForeground(Color.WHITE);
		tagAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagAutor.setBounds(300, 366, 176, 23);
		contentPane.add(tagAutor);

		JButton btnMostrarBiblioteca = new JButton("Mostrar la biblioteca");
		btnMostrarBiblioteca.setBackground(new Color(50, 205, 50));
		btnMostrarBiblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				mostrarDialeg(1);
			} // end-actionPerformed
		});
		btnMostrarBiblioteca.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnMostrarBiblioteca.setBackground(new Color(239, 239, 239));
		btnMostrarBiblioteca.setBorder(null);
		btnMostrarBiblioteca.setFocusable(false);
		btnMostrarBiblioteca.setBounds(174, 78, 147, 37);
		contentPane.add(btnMostrarBiblioteca);

		JButton btnMostrarLlibre = new JButton("Llegir un llibre");
		btnMostrarLlibre.setBackground(new Color(50, 205, 50));
		btnMostrarLlibre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDialeg(2);
			} // end-actionPerformed
		});
		btnMostrarLlibre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnMostrarLlibre.setBackground(new Color(239, 239, 239));
		btnMostrarLlibre.setBorder(null);
		btnMostrarLlibre.setFocusable(false);
		btnMostrarLlibre.setBounds(174, 132, 147, 37);
		contentPane.add(btnMostrarLlibre);

		JButton btnAfegir = new JButton("Afegir nou llibre");
		btnAfegir.setBackground(new Color(50, 205, 50));
		btnAfegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDialeg(3);
			} // end-actionPerformed
		});
		btnAfegir.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAfegir.setBackground(new Color(239, 239, 239));
		btnAfegir.setBorder(null);
		btnAfegir.setFocusable(false);
		btnAfegir.setBounds(174, 184, 147, 37);
		contentPane.add(btnAfegir);
		
		JButton btnModificar = new JButton("Modificar llibre existent");
		btnModificar.setBackground(new Color(50, 205, 50));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDialeg(4);
			} // end-actionPerformed
		});
		btnModificar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnModificar.setBackground(new Color(239, 239, 239));
		btnModificar.setBorder(null);
		btnModificar.setFocusable(false);
		btnModificar.setBounds(174, 237, 147, 37);
		contentPane.add(btnModificar);

		JButton btnEsborrar = new JButton("Esborrar llibre");
		btnEsborrar.setBackground(new Color(50, 205, 50));
		btnEsborrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDialeg(5);
			} // end-actionPerformed
		});
		btnEsborrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEsborrar.setBackground(new Color(239, 239, 239));
		btnEsborrar.setBorder(null);
		btnEsborrar.setFocusable(false);
		btnEsborrar.setBounds(174, 290, 147, 37);
		contentPane.add(btnEsborrar);
	} // end-visualitzar
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Set cross-platform Java L&F (also called "Nimbus")
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end-try-catch
		VistaTemporal frame = new VistaTemporal();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	} // end-main
} // end-class