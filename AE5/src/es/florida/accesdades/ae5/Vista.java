package es.florida.accesdades.ae5;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@SuppressWarnings("serial")
public class Vista extends JFrame {
	// DECLARACIONS GENERALS
	private JButton btnMostrarBiblioteca, btnMostrarLlibre, btnAfegir, btnModificar, btnEsborrar;
	private JPanel contentPane;
	// DECLARACIONS STATIC (AQUESTES VARIABLES S'USEN EN DIVERSOS METODES)
	static Session session;
	
	public Vista() {
		visualitzar();
	} // end-constructor
	
	public static void mostrarDialeg(int dialogId) {
		try {
        	JDialog jd = new JDialog(new JFrame());
			jd.getContentPane().setBackground(new Color(3, 131, 135));
	        
    		JLabel tagDialog = new JLabel("Titol descriptiu");
    		tagDialog.setHorizontalAlignment(SwingConstants.CENTER);
    		tagDialog.setForeground(Color.WHITE);
    		tagDialog.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
    		tagDialog.setBounds(10, 11, 549, 28);
	       
			// CARACTERISTIQUES COMUNES DELS DIALEGS 2, 3 I 4

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
    		
    		// DIALEG DE MOSTRAR TOTS ELS TITOLS
	        if(dialogId == 1) {
	        	session.beginTransaction();
				
				System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
				String resultatSentencia = "";
				@SuppressWarnings("rawtypes")
				List biblioteca = new ArrayList();
				biblioteca = session.createQuery("FROM Llibre").list();
				for (Object obj : biblioteca) {
					Llibre llibre = (Llibre) obj;
					resultatSentencia += llibre.getIdentificador()+" - "+llibre.getTitol()+"\n";
				} // end-for
				Pruebas visualitzar2 = new Pruebas();
				visualitzar2.getTextArea().setText(resultatSentencia);
				visualitzar2.setVisible(true);
				
				//txtA.setText(resultatSentencia);
				session.getTransaction().commit(); // Commit de la transaccio
	        } // end-if
	        
	        // 
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
	    		btnExecucio.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				try {
		    				session.beginTransaction();
		    				System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
	    					int id = Integer.parseInt(txtFIdLlibre.getText());
		    				Llibre llibre = (Llibre) session.get(Llibre.class, id);
		    				
		    				if(llibre==null) {
		    					JOptionPane.showMessageDialog(new JFrame(), "El ID "+id+" no es valid! :(", "ERROR!", JOptionPane.ERROR_MESSAGE);
		    				} else {
		    					txtFTitol.setText(llibre.getTitol());
		    					txtFAutor.setText(llibre.getAutor());
		    					txtFAnyN.setText(llibre.getAnyNaixement());
		    					txtFAnyP.setText(llibre.getAnyPublicacio());
		    					txtFEditorial.setText(llibre.getEditorial());
		    					txtFNumPag.setText(llibre.getNumPagines());
		    					
		    				} // end-if-else
		    				session.getTransaction().commit(); // Commit de la transaccio
	    				} catch(NumberFormatException nfe){
	    					JOptionPane.showMessageDialog(new JFrame(), nfe+"\nTENS QUE INDICAR UN ID! :(", "ERROR!", JOptionPane.ERROR_MESSAGE);
	    			    	session.close();
	    			    	configuracio();
	    				} catch (IllegalStateException ise) {
	    					JOptionPane.showMessageDialog(new JFrame(), ise+"\nTENS QUE INDICAR UN ID! :(", "ERROR!", JOptionPane.ERROR_MESSAGE);
	    					session.close();
	    					configuracio();
	    			    } // end try-catch
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
    					session.beginTransaction();
    					System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
	    				String decisio = "";

    					String strTitol = txtFTitol.getText();
    					String strAutor = txtFAutor.getText();
    					String strAnyNaixement = txtFAnyN.getText();
    					String strAnyPublicacio = txtFAnyP.getText();
    					String strEditorial = txtFEditorial.getText();
    					String strNumPagines = txtFNumPag.getText();
    					
    					if(strTitol.equals("") || strAutor.equals("") || strAnyPublicacio.equals("") || strEditorial.equals("") || strNumPagines.equals("")) {
    						// per a evitar camps buits (que de per si son NOT NULL), comprobarem si ens hem deixat algun camp sense omplir
    						JOptionPane.showMessageDialog(new JFrame(), "Pareix que t'has oblidat d'omplir un camp de text :(", "ERROR!", JOptionPane.ERROR_MESSAGE);
    						session.close();
	    			    	configuracio();
    					} else {
    						strAnyNaixement = strAnyNaixement.equals("") ? "N.C" : strAnyNaixement; // nomes el any de naixement pot ser null, aleshores li assignarem un N.C (No consta) si resulta que no li hem passat cap valor
    						Llibre llibreNou = new Llibre(strTitol,strAutor,strAnyNaixement,strAnyPublicacio,strEditorial,strNumPagines);
        					@SuppressWarnings("unused")
							Serializable id = session.save(llibreNou);
        					session.getTransaction().commit(); // Commit de la transaccio
        					
        					JOptionPane.showMessageDialog(new JFrame(), "Llibre afegit correctament amb l'ID "+llibreNou.getIdentificador(), "Avis", JOptionPane.INFORMATION_MESSAGE);	
        					decisio = JOptionPane.showInputDialog(null, "Vols afegir altre llibre? (s/n)");
        				
        					if(decisio.equals("s")) {
        						// ressetejem les dades si volem afegir altre llibre, per a poder afegir noves dades facilment i evitar accidents
        						strTitol = ""; txtFTitol.setText("");
        						strAutor = ""; txtFAutor.setText("");
        						strAnyNaixement = ""; txtFAnyN.setText("");
        						strAnyPublicacio = ""; txtFAnyP.setText("");
        						strEditorial = ""; txtFEditorial.setText("");
        						strNumPagines = ""; txtFNumPag.setText("");
        					} else {
        						// sino desitjem afegir mes llibres, farem que es tanque el jdialog.
        						jd.dispose();
        					} // end-if 2
    					} // end-if 1
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
	    				try {
	    					session.beginTransaction();
	    					String decisio = "";
	    					int id = Integer.parseInt(txtFIdLlibre.getText());
		    				Llibre llibreModificat = (Llibre) session.get(Llibre.class, id);
		    				
		    				if(llibreModificat==null) {
		    					JOptionPane.showMessageDialog(new JFrame(), "El ID "+id+" no es valid! :(", "ERROR!", JOptionPane.ERROR_MESSAGE);
		    				} else {
		    					
		    					llibreModificat.setTitol(txtFTitol.getText());
		    					llibreModificat.setAutor(txtFAutor.getText());
		    					llibreModificat.setAnyNaixement(txtFAnyN.getText());
		    					llibreModificat.setAnyPublicacio(txtFAnyP.getText());
		    					llibreModificat.setEditorial(txtFEditorial.getText());
		    					llibreModificat.setNumPagines(txtFNumPag.getText());

		    				} // end-if-else
		    				session.update(llibreModificat); // Sentencia update
		    				session.getTransaction().commit(); // Commit de la transaccio
		    				
		    				JOptionPane.showMessageDialog(new JFrame(), "Llibre amb ID "+llibreModificat.getIdentificador()+" modificat correctament", "Avis", JOptionPane.INFORMATION_MESSAGE);
		    				
		    				decisio = JOptionPane.showInputDialog(null, "Vols modificar altre llibre? (s/n)");
	        				
        					if(decisio.equals("s")) {
        						// ressetejem les dades si volem afegir altre llibre, per a poder afegir noves dades facilment i evitar accidents
        						txtFIdLlibre.setText("");
        						txtFTitol.setText("");
        						txtFAutor.setText("");
        						txtFAnyN.setText("");
        						txtFAnyP.setText("");
        						txtFEditorial.setText("");
        						txtFNumPag.setText("");
        					} else {
        						// sino desitjem afegir mes llibres, farem que es tanque el jdialog.
        						jd.dispose();
        					} // end-if 2
	    				} catch(NumberFormatException nfe){
	    					JOptionPane.showMessageDialog(new JFrame(), nfe+"\nTENS QUE INDICAR UN ID! :(", "ERROR!", JOptionPane.ERROR_MESSAGE);
	    			    	session.close();
	    			    	configuracio();
	    				} catch (IllegalStateException ise) {
	    					JOptionPane.showMessageDialog(new JFrame(), ise+"\nTENS QUE INDICAR UN ID! :(", "ERROR!", JOptionPane.ERROR_MESSAGE);
	    					session.close();
	    					configuracio();
	    			    } // end try-catch
	    			} // end-actionPerformed
	    		});
	    		btnExecucio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		btnExecucio.setBounds(231, 65, 171, 28);
	        	
		        // VISUALITZACIO DELS COMPONENTS DEL JDIALOG
	    		jd.getContentPane().add(tagDialog);
	    		jd.getContentPane().add(txtFIdLlibre);
	    		jd.getContentPane().add(tagIdLlibre);
	    		jd.getContentPane().add(txtFIdLlibre);
	    		jd.getContentPane().add(tagIdLlibre);
	    		jd.getContentPane().add(txtFTitol);
	    		jd.getContentPane().add(tagTitol);
	    		jd.getContentPane().add(txtFAutor);
	    		jd.getContentPane().add(tagAutor);
	    		jd.getContentPane().add(txtFAnyN);
	    		jd.getContentPane().add(tagAnyN);
	    		jd.getContentPane().add(txtFAnyP);
	    		jd.getContentPane().add(txtFEditorial);
	    		jd.getContentPane().add(tagEditorial);
	    		jd.getContentPane().add(tagNumPag);
	    		jd.getContentPane().add(txtFNumPag);
	    		jd.getContentPane().add(btnExecucio);
	    		
	    		// VISUALITZACIO DEL JDIALOG
		        jd.setVisible(true);
		        jd.setLocationRelativeTo(null);
	        } // end-dialog4
	        
	        if(dialogId == 5) {
	        	try {
	        		session.beginTransaction();
		    		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		    		
		    		int idLlibre = Integer.parseInt(JOptionPane.showInputDialog(null, "Indica l'ID del llibre que desitjes esborrar"));
		    		Llibre llibreCremat = (Llibre) session.get(Llibre.class, idLlibre);
		    		if(llibreCremat == null) {
		    			JOptionPane.showMessageDialog(new JFrame(), "El ID "+idLlibre+" no es valid! :(", "ERROR!", JOptionPane.ERROR_MESSAGE);
		    		} else {
		    			String decisio = JOptionPane.showInputDialog(null, "Estas segur de que vols esborrar el llibre amb l'ID "+idLlibre+"?");
		    			if(decisio.equals("s")) {
			    			llibreCremat.setIdentificador(idLlibre);
			    			session.delete(llibreCremat);
			    			session.getTransaction().commit(); // Commit de la transaccio
			    			session.close();
			    			JOptionPane.showMessageDialog(new JFrame(),"Llibre amb l'ID "+idLlibre+" esborrar correctament!", "Avis", JOptionPane.INFORMATION_MESSAGE);
			    		} else {
			    			session.close();
			    			configuracio();
			    		} // end-if 2
		    		} // end-if 1
	        	} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(new JFrame(), nfe, "ERROR!", JOptionPane.ERROR_MESSAGE);
			    	session.close();
			    	configuracio();
				} catch (IllegalStateException ise) {
					JOptionPane.showMessageDialog(new JFrame(), ise, "ERROR!", JOptionPane.ERROR_MESSAGE);
					session.close();
					configuracio();
			    } // end try-catch
	        } // end-dialog5
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex, "ERROR en la consulta!", JOptionPane.ERROR_MESSAGE);
		} // end-try-catch
	} // end-mostrarDialeg
	
	public void visualitzar() {
		setTitle("Hibernate - AE5 (Acc\u00E8s a Dades)");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 445);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(3, 131, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tagTitol = new JLabel("Men\u00FA d'opcions d'Hibernate");
		tagTitol.setForeground(Color.WHITE);
		tagTitol.setHorizontalAlignment(SwingConstants.CENTER);
		tagTitol.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		tagTitol.setBounds(30, 11, 434, 25);
		contentPane.add(tagTitol);
		
		JLabel tagAutor = new JLabel("Alumne: Eduardo Ru\u00E1 Chamorro");
		tagAutor.setForeground(Color.WHITE);
		tagAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagAutor.setBounds(10, 372, 176, 23);
		contentPane.add(tagAutor);
		
		// MOSTRAR TOTS ELS TITOLS DE LA BIBLIOTECA
		btnMostrarBiblioteca = new JButton("Mostrar biblioteca");
		btnMostrarBiblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				mostrarDialeg(1);
			}
		});
		btnMostrarBiblioteca.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnMostrarBiblioteca.setBackground(new Color(239, 239, 239));
		btnMostrarBiblioteca.setBorder(null);
		btnMostrarBiblioteca.setFocusable(false);
		btnMostrarBiblioteca.setBounds(174, 68, 147, 37);
		contentPane.add(btnMostrarBiblioteca);
		
		// MOSTRAR INFORMACIO D'UN LLIBRE PEL SEU ID
		btnMostrarLlibre = new JButton("Mostrar llibre");
		btnMostrarLlibre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCIONS QUE S'EXECUTARAN AL PRESSSIONAR SOBRE AQUEST BOTO
				mostrarDialeg(2);	
			}
		});
		btnMostrarLlibre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnMostrarLlibre.setBackground(new Color(239, 239, 239));
		btnMostrarLlibre.setBorder(null);
		btnMostrarLlibre.setFocusable(false);
		btnMostrarLlibre.setBounds(174, 122, 147, 37);
		contentPane.add(btnMostrarLlibre);
		
		// AFEGIR UN LLIBRE A LA BIBLIOTECA INDICANT ELS SEUS ATRIBUTS
		btnAfegir = new JButton("Afegir llibre");
		btnAfegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCIONS QUE S'EXECUTARAN AL PRESSSIONAR SOBRE AQUEST BOTO
				mostrarDialeg(3);
			}
		});
		btnAfegir.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAfegir.setBackground(new Color(239, 239, 239));
		btnAfegir.setBorder(null);
		btnAfegir.setFocusable(false);
		btnAfegir.setBounds(174, 174, 147, 37);
		contentPane.add(btnAfegir);
		
		// MODIFICAR ELS ATRIBUTS D'UN LLIBRE JA EXISTENT
		btnModificar = new JButton("Modificar llibre");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCIONS QUE S'EXECUTARAN AL PRESSSIONAR SOBRE AQUEST BOTO
				mostrarDialeg(4);
			}
		});
		btnModificar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnModificar.setBackground(new Color(239, 239, 239));
		btnModificar.setBorder(null);
		btnModificar.setFocusable(false);
		btnModificar.setBounds(174, 227, 147, 37);
		contentPane.add(btnModificar);
		
		// ESBORRRAR UN LLIBRE INDICANT EL SEU ID
		btnEsborrar = new JButton("Esborrar llibre");
		btnEsborrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCIONS QUE S'EXECUTARAN AL PRESSSIONAR SOBRE AQUEST BOTO
				mostrarDialeg(5);
			}
		});
		btnEsborrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEsborrar.setBackground(new Color(239, 239, 239));
		btnEsborrar.setBorder(null);
		btnEsborrar.setFocusable(false);
		btnEsborrar.setBounds(174, 280, 147, 37);
		contentPane.add(btnEsborrar);
	} // end-visualitzar
	
	public static void configuracio() {
		// Carrega la configuracio i crea una session factory
		System.err.println("\n> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		session = sessionFactory.openSession(); // Obri una nova sessio de la session factory
	} // end-configuracio

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		configuracio();
		
		// Mostra la interficie i li aplica un tema per defecte a la UI que s'anomena Nimbus
		// Set cross-platform Java L&F (also called "Nimbus")
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vista frame = new Vista();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	} // end-main
} // end-class
