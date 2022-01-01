package es.florida.accesDades.ae6;
/*
 * @author Eduardo Rua Chamorro | 2. DAM - Florida Universitaria
 * @version AE6.0 - Acces a Dades
 * @description Aquest programa implementa la ampliacio indicada a l'activitat. Tracta de crear una interficie grafica que implemente les funcionalitats indicades al PDF.
 * */
// IMPORTACIO DE LLIBRERIES
import java.util.NoSuchElementException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Biblioteca extends JFrame {
	// DECLARACIONS GENERALS
	private JPanel contentPane;
	// DECLARACIONS STATIC (AQUESTES VARIABLES S'USEN EN DIVERSOS METODES)
	
	public Biblioteca() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ImageIcon unknownIcon = new ImageIcon(Biblioteca.class.getResource("/img/unknown.png"));
				Object [] opcions = {"Acceptar", "Cancelar"};
				int eleccion = JOptionPane.showOptionDialog(rootPane, "FELI\u00C7 ANY NOU 2022 !!! :D\nGracies per utilitzar el nostre SW\nque tinga un bon dia.","Easter Egg ;)",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, unknownIcon , opcions, "Acceptar");
				
				if (eleccion == JOptionPane.YES_OPTION)
				 System.exit(0);			
			} // end-felicitacio
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Biblioteca.class.getResource("/img/mongodbIcon.png"))); // ICONA DEL PROGRAMA
		visualitzar();
	} // end-constructor
	
	/*
	 * METODE: mostrarDialeg
	 * PARAMETRES: int dialogId -> identificara al JDIALOG que tindra que mostrar-se
	 * DEFINICIO: aquest metode es crida en cadascu dels events de cada boto del menu (VAR: contentPane)
	 * en funcio del parametre enviat, s'executara un JDIALOG amb les caracteristiques especifiques per a cada cas,
	 * adaptant-se a la seua problematica 
	 * */
	
	public static void mostrarDialeg(int dialogId) {
		try {
			// PROPIETATS GENERALS DEL JDIALOG
        	JDialog jd = new JDialog(new JFrame());
			jd.getContentPane().setBackground(new Color(0, 30, 43));
	        
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
	        	jd.getContentPane().setLayout(null); // LAYOUT DEL DIALEG PER AL METODE DE CONSULTAR LLIBRE
	        	jd.setBounds(100, 100, 585, 361); // DIMENSIONS DEL JDIALOG
	        	String resultat = "";
	        	
	        	// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
	    		MongoClient mongoClient = new MongoClient("localhost", 27017);
	    		MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    		MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
		   		MongoCursor<Document> cursor = coleccio.find().iterator();
		   		// BUCLE PER A MOSTRAR EL DOCUMENT LINIA A LINIA
		   		while(cursor.hasNext()) {
		   			resultat += cursor.next().toJson();
		   			resultat += "\n\n";
		   		} // end-while
		   		
		   		// PER DIVERSOS PROBLEMES, HE CREGUT CONVENIENT MOSTRAR LA INFORMACIO RECOLLIDA EN UN JFRAME AUXILIAR QUE TINDREM A LA CLASSE FrameAuxiliar
				FrameAuxiliar frameAux = new FrameAuxiliar();
				frameAux.getTextArea().setText(resultat);
				frameAux.setLocationRelativeTo(null);
				frameAux.setVisible(true);
				frameAux.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // AQUESTA INSTRUCCIO EVITA QUE AL TANCAR LA FINESTRA, ES TANQUE TAMBE LA DEL MENU
		        
		   		mongoClient.close(); // TANQUEM LA SESSIO DE MONGODB
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
	    				// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
	    				MongoClient mongoClient = new MongoClient("localhost", 27017);
	    				MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    				MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
	    				
	    				int idLlibre = Integer.parseInt(txtFIdLlibre.getText()); // RECOLLIM L'ID INDICAT EN EL TEXTFIELD
	    				try {
	    					Bson filtreId = Filters.eq("Id", idLlibre); // RECOLLIM EN UN OBJECTE BSON, EL FILTRE QUE FAREM SERVIR PER CERCAR L'ELEMENT DESITJAT
		    				MongoCursor<Document> cursor = coleccio.find(filtreId).iterator(); // CURSOR QUE SELECCIONARA EL DOCUMENT QUE COMPLIXGA ELS REQUISITS DEL FILTRE
		    				JSONObject document = new JSONObject(cursor.next().toJson()); // OBJECTE JSON QUE RECOLLEIX EL DOCUMENT PER A PODER TREBALLAR AMB ELL
		    				
		    				txtFTitol.setText(document.getString("Titol"));
	    					txtFAutor.setText(document.getString("Autor"));
	    					txtFAnyN.setText(document.getString("Any_naixement"));
	    					txtFAnyP.setText(document.getString("Any_publicacio"));
	    					txtFEditorial.setText(document.getString("Editorial"));
	    					txtFNumPag.setText(document.getString("Nombre_pagines"));
	    				} catch (NoSuchElementException nsee) {
	    					nsee.printStackTrace();
	    					JOptionPane.showMessageDialog(new JFrame(),"INTRODUEIX UN ID VALID!\nID INDICAT: "+idLlibre, "ERROR! :(", JOptionPane.ERROR_MESSAGE);
	    			    } // end try-catch
	    				mongoClient.close(); // TANQUEM LA SESSIO DE MONGODB
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
	        	tagDialog.setText("SISTEMA DE CREACIO DE LLIBRES");
	        	txtFIdLlibre.setEditable(true);
	    		txtFTitol.setEditable(true);
	    		txtFAutor.setEditable(true);
	    		txtFAnyN.setEditable(true);
	    		txtFAnyP.setEditable(true);
	    		txtFEditorial.setEditable(true);
	    		txtFNumPag.setEditable(true);
	    		JButton btnExecucio = new JButton("Crear llibre");
	    		btnExecucio.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
	    				MongoClient mongoClient = new MongoClient("localhost", 27017);
	    				MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    				MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
	    				
	    				String camp = "", valor = "", decisio = "";
	    				Document doc = new Document();
	    				int idLlibre = getIdRecent() + 1;
    					String strTitol = txtFTitol.getText(); String strAnyNaixement = txtFAnyN.getText();
    					String strAutor = txtFAutor.getText(); String strAnyPublicacio = txtFAnyP.getText();
    					String strEditorial = txtFEditorial.getText(); String strNumPagines = txtFNumPag.getText();
	    				try {    	    				
        					if(strTitol.equals("") || strAutor.equals("") || strAnyPublicacio.equals("") || strEditorial.equals("") || strNumPagines.equals("")) {
        						// PER A EVITAR CAMPS BUITS (QUE DE PER SI SON NOT NULL), COMPROBAREM SI ENS HEM DEICAT ALGUN CAMP SENSE OMPLIR
        						JOptionPane.showMessageDialog(new JFrame(), "Pareix que t'has oblidat d'omplir un camp de text!", "ERROR! :(", JOptionPane.ERROR_MESSAGE);
        					} else {
        						// NOMES EL ANY DE NAIXEMENT POT SER NULL, ALESHORES LI ASSIGNAREM UN N.C (NO CONSTA) SI RESULTA QUE NO LI HEM PASSAT CAP VALOR
        						strAnyNaixement = strAnyNaixement.equals("") ? "N.C" : strAnyNaixement;
        						
        						/*
        						 * MONGODB, A DIFERENCIA DELS LLENGUATGES SQL, TREBALLA AMB L'ESQUEMA CAMP:VALOR.
        						 * PER LO TANT, TANT PER A INSERTAR COM PER A MODIFICAR, SEGUIREM LES SEGÜENTS INSTRUCCIONS */
        						
        						camp = "Id";
        	    				doc.append(camp, idLlibre);
        	    				
        	    				camp = "Titol"; valor = strTitol;
        	    				doc.append(camp, valor);
        	    				
        	    				camp = "Autor"; valor = strAutor;
        	    				doc.append(camp, valor);
        	    				
        	    				camp = "Any_naixement"; valor = strAnyNaixement;
        	    				doc.append(camp, valor);
        	    				
        	    				camp = "Any_publicacio"; valor = strAnyPublicacio;
        	    				doc.append(camp, valor);
        	    				
        	    				camp = "Editorial"; valor = strEditorial;
        	    				doc.append(camp, valor);
        	    				
        	    				camp = "Nombre_pagines"; valor = strNumPagines;
        	    				doc.append(camp, valor);
        	    				
        	    				coleccio.insertOne(doc);

            					// PREGUNTEM AL USUARI SI VOLS AFEGIR MES LLIBRES
            					JOptionPane.showMessageDialog(new JFrame(), "Llibre afegit correctament amb l'ID "+idLlibre, "Avis", JOptionPane.INFORMATION_MESSAGE);	
            					decisio = JOptionPane.showInputDialog(null, "Vols afegir altre llibre? (s/n)").toLowerCase();
            					if(decisio.equals("s")) {
            						// RESSETEJEM LES DADES SI VOLEM AFEGIR ALTRE LLIBRE, PER A PODER AFEGIR NOVES DADES FACILMENT I EVITAR ACCIDENTS
            						txtFIdLlibre.setText("");
            						strTitol = ""; txtFTitol.setText("");
            						strAutor = ""; txtFAutor.setText("");
            						strAnyNaixement = ""; txtFAnyN.setText("");
            						strAnyPublicacio = ""; txtFAnyP.setText("");
            						strEditorial = ""; txtFEditorial.setText("");
            						strNumPagines = ""; txtFNumPag.setText("");
            					} else {
            						// SINO DESITJEM AFEGIR MES, FAREM QUE ES TANQUE EL JDIALOG
            						jd.dispose();
            					} // end-if 2
        					} // end-if 1
    					} catch (NoSuchElementException nsee) {
	    					nsee.printStackTrace();
	    					JOptionPane.showMessageDialog(new JFrame(),"INTRODUEIX UN ID VALID!\nID INDICAT: "+idLlibre, "ERROR! :(", JOptionPane.ERROR_MESSAGE);
	    			    } // end try-catch
	    				mongoClient.close(); // TANQUEM LA SESSIO DE MONGODB
	    			} // end-actionPerformed
	    		});
	    		btnExecucio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		btnExecucio.setBounds(220, 63, 124, 28);
	    		
	    		// VISUALITZACIO DELS COMPONENTS DEL JDIALOG
	    		jd.getContentPane().add(tagDialog);
	    		//jd.getContentPane().add(txtFIdLlibre);
	    		//jd.getContentPane().add(tagIdLlibre);
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
	    				// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB  				
	    				MongoClient mongoClient = new MongoClient("localhost", 27017);
	    				MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    				MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
	    				
	    				int idLlibre = Integer.parseInt(txtFIdLlibre.getText());
	    				Bson filtreId = Filters.eq("Id", idLlibre); // TORNEM A RECOLLIR EN UN OBJECTE BSON, EL FILTRE QUE FAREM SERVIR PER CERCAR L'ELEMENT DESITJAT
	    				String camp = "", valor = "", decisio = "";
    					String strTitol = txtFTitol.getText(); String strAnyNaixement = txtFAnyN.getText();
    					String strAutor = txtFAutor.getText(); String strAnyPublicacio = txtFAnyP.getText();
    					String strEditorial = txtFEditorial.getText(); String strNumPagines = txtFNumPag.getText();
	    				try {
	    					if(txtFTitol.getText().equals("") || txtFAutor.getText().equals("") || txtFAnyP.getText().equals("") || txtFEditorial.getText().equals("") || txtFNumPag.getText().equals("")) {
        						// PER A EVITAR CAMPS BUITS (QUE DE PER SI SON NOT NULL), COMPROBAREM SI ENS HEM DEICAT ALGUN CAMP SENSE OMPLIR
        						JOptionPane.showMessageDialog(new JFrame(), "Pareix que t'has oblidat d'omplir un camp de text!", "ERROR! :(", JOptionPane.ERROR_MESSAGE);
	    					} else {
	    						
        						/*
        						 * MONGODB, A DIFERENCIA DELS LLENGUATGES SQL, TREBALLA AMB L'ESQUEMA CAMP:VALOR.
        						 * PER LO TANT, TANT PER A INSERTAR COM PER A MODIFICAR, SEGUIREM LES SEGÜENTS INSTRUCCIONS */
	    						
	    						camp = "Titol"; valor = strTitol;
	    						coleccio.updateOne(filtreId, new Document("$set", new Document(camp, valor)));
	    						
	    						camp = "Autor"; valor = strAutor;
	    						coleccio.updateOne(filtreId, new Document("$set", new Document(camp, valor)));
	    						
	    						strAnyNaixement = strAnyNaixement.equals("") ? "N.C" : strAnyNaixement; // SINO INTRODUIM CAP MODIFICACIO EN EL CAMP DEL ANY DE NAIXEMENT, FAREM QUE EL SEU VALOR CONTINUE SIGUENT "N.C"
	    						camp = "Any_naixement"; valor = strAnyNaixement;
	    						coleccio.updateOne(filtreId, new Document("$set", new Document(camp, valor)));
	    						
	    						camp = "Any_publicacio"; valor = strAnyPublicacio;
	    						coleccio.updateOne(filtreId, new Document("$set", new Document(camp, valor)));
	    						
	    						camp = "Editorial"; valor = strEditorial;
	    						coleccio.updateOne(filtreId, new Document("$set", new Document(camp, valor)));
	    						
	    						camp = "Nombre_pagines"; valor = strNumPagines;
	    						coleccio.updateOne(filtreId, new Document("$set", new Document(camp, valor)));
	    						
			    				JOptionPane.showMessageDialog(new JFrame(), "Llibre amb ID "+idLlibre+" modificat correctament", "Avis", JOptionPane.INFORMATION_MESSAGE);
			    				decisio = JOptionPane.showInputDialog(null, "Vols modificar altre llibre? (s/n)").toLowerCase();
	        					if(decisio.equals("s")) {
	        						// RESSETEJEM LES DADES SI VOLEM MODIFICAR ALTRE LLIBRE, PER A PODER AFEGIR NOVES DADES FACILMENT I EVITAR ACCIDENTS
	        						txtFIdLlibre.setText("");
	        						txtFTitol.setText("");
	        						txtFAutor.setText("");
	        						txtFAnyN.setText("");
	        						txtFAnyP.setText("");
	        						txtFEditorial.setText("");
	        						txtFNumPag.setText("");
	        					} else {
	        						// SINO DESITJEM MODIFICAR MES, FAREM QUE ES TANQUE EL JDIALOG
	        						jd.dispose();
	        					} // end-if-else
		    				} // end-if-else
	    				} catch (NoSuchElementException nsee) {
	    					nsee.printStackTrace();
	    					JOptionPane.showMessageDialog(new JFrame(),"INTRODUEIX UN ID VALID!\nID INDICAT: "+idLlibre, "ERROR! :(", JOptionPane.ERROR_MESSAGE);
	    			    } // end try-catch
	    				mongoClient.close(); // TANQUEM LA SESSIO DE MONGODB
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
    		 * DIALEG QUE MOSTRARA EL PROCES, MITJANï¿½ANT INPUT DIALOGS, DE COM ESBORRAR UN LLIBRE SEGONS EL SEU ID
    		 * ELS PROCEDIMENTS EXECUTATS DINS DEL MATEIX ESTAN COMENTATS PER SEPARAT
    		 * */
	        if(dialogId == 5) {
	        	// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
	    		MongoClient mongoClient = new MongoClient("localhost", 27017);
	    		MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
	    		MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
	    		
	    		int idLlibre = Integer.parseInt(JOptionPane.showInputDialog(null, "Indica l'ID del llibre que desitjes esborrar")); // RECOLLIM L'ID INDICAT EN EL TEXTFIELD
    			try { 	
	    			Bson filtreId = Filters.eq("Id", idLlibre);
	    			String decisio = JOptionPane.showInputDialog(null, "Estas segur de que vols esborrar el llibre amb l'ID "+idLlibre+"? (s/n)").toLowerCase();
	    			if(decisio.equals("s")) {
	    				coleccio.deleteOne(filtreId); // EL PROCESSEM, OBTENINT EL LLIBRE QUE CORRESPON AMB AQUEST ID I L'ESBORREM
		    			JOptionPane.showMessageDialog(new JFrame(),"Llibre amb l'ID "+idLlibre+" esborrat correctament!", "Avis", JOptionPane.INFORMATION_MESSAGE);
		    		} else {
		    			jd.dispose();
		    		} // end-if-else 2
	        	} catch (NoSuchElementException nsee) {
					nsee.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(),"INTRODUEIX UN ID VALID!\nID INDICAT: "+idLlibre, "ERROR! :(", JOptionPane.ERROR_MESSAGE);
			    } catch (NumberFormatException nfe) {
			    	nfe.printStackTrace();
			    	JOptionPane.showMessageDialog(new JFrame(),"NO HAS INTRODUIT UN NUMERO!\nID INDICAT: "+idLlibre, "ERROR! :(", JOptionPane.ERROR_MESSAGE);
			    } // end try-catch
	    		mongoClient.close(); // TANQUEM LA SESSIO DE MONGODB
	        } // end-dialog5
		} catch (Exception ex) {
			System.err.println("\n> S'HA REGISTRAT UN ERROR, PERO AFORTUNADAMENT AQUEST NO AFECTA A LA CORRECTA EXECUCIO DEL PROGRAMA :)\n");
			ex.printStackTrace();
		} // end-try-catch
	} // end-mostrarDialeg
	
	/*
	 * METODE: visualitzar()
	 * PARAMETRES: cap
	 * DEFINICIO: s'encarrega de mostrar l'interficie grafica del programa i els elements basics d'aquesta
	 * */

	public void visualitzar() {
		setTitle("AE6 - MongoDB");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 512, 445);
		contentPane = new JPanel() {  
			 public void paintComponent(Graphics g) {
				 // METODE QUE TE COM A FUNCIO PINTAR UNA IMATGE AL JPANEL
			          Image img = Toolkit.getDefaultToolkit().getImage(  
			        		  Biblioteca.class.getResource("/img/appBackground3.jpg")); // PROVA A POSAR /img/appBackground2.jpg :)
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
	
	public static int getIdRecent() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
		MongoCursor<Document> cursor = coleccio.find().iterator();
		int ultimLlibre = 1;
		
		while(cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			ultimLlibre = obj.getInt("Id");
		} // end-while
		mongoClient.close();
		return ultimLlibre;
	} // end-getIdRecent

	/*
	 * METODE: main
	 * PARAMETRES: String[] args
	 * DEFINICIO: metode que inicia el programa aplicant per defecte a la UI un tema anomenat NIMBUS
	 * */
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Set cross-platform Java L&F (also called "Nimbus")
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end-try-catch
		Biblioteca frame = new Biblioteca();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	} // end-main
} // end-class