package es.florida.accesdades.ae5;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {
	// DECLARACIONS GENERALS
	private JButton btnMostrarBiblioteca, btnMostrarLlibre, btnAfegir, btnModificar, btnEsborrar;
	private JPanel contentPane;
	// DECLARACIONS STATIC (AQUESTES VARIABLES S'USEN EN DIVERSOS METODES)
	static Session session;
	//static String resultatSentencia = "", infoSentencia = "";
	//static JLabel tagIdLlibre;
	//static JTextField txtFIdLlibre, txtFTitol, txtFAutor, txtFAnyN, txtFAnyP, txtFEditorial, txtFNumPag;
	
	public Vista() {
		visualitzar();
	}
	
	@SuppressWarnings("null")
	public static void mostrarDialeg(int dialogId) {
		try {
        	JDialog jd = new JDialog(new JFrame());
			jd.getContentPane().setBackground(new Color(3, 131, 135));
			
			JLabel tagTitolJd = new JLabel("");
	        tagTitolJd.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
	        tagTitolJd.setForeground(Color.WHITE);
	       
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
    		
//    		JLabel tagDialog = new JLabel("Titol descriptiu");
//    		tagDialog.setHorizontalAlignment(SwingConstants.CENTER);
//    		tagDialog.setForeground(Color.WHITE);
//    		tagDialog.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
//    		tagDialog.setBounds(172, 11, 215, 28);
    		
	        
	        if(dialogId == 1) {
	        	session.beginTransaction();
	        	// CARACTERISTIQUES DEL DIALEG 1
	        	jd.getContentPane().setLayout(new FlowLayout()); // LAYOUT DEL DIALEG QUE MOSTRARA TOTS ELS TITOLS DE LA BIBLIOTECA
		        jd.setBounds(100, 100, 668, 445); // DIMENSIONS DEL JDIALOG
		        
				tagTitolJd.setText("----- LLISTA DE LLIBRES -----");
		        
		        JTextArea txtA;
		        txtA = new JTextArea();
				txtA.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				txtA.setLineWrap(true);
				txtA.setEditable(false);
				txtA.setBounds(10,47,632,500);
				
				
				System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
				String resultatSentencia = "";
				List biblioteca = new ArrayList();
				biblioteca = session.createQuery("FROM Llibre").list();
				for (Object obj : biblioteca) {
					Llibre llibre = (Llibre) obj;
					resultatSentencia += llibre.getIdentificador()+" - "+llibre.getTitol()+"\n";
				} // end-for
			
				txtA.setText(resultatSentencia);
				session.getTransaction().commit(); // Commit de la transaccio
	
		        // VISUALITZACIO DELS COMPONENTS DEL JDIALOG
		        jd.getContentPane().add(tagTitolJd);
		        jd.getContentPane().add(txtA);
	        } // end-if
	        if(dialogId == 2) {
	        	jd.getContentPane().setLayout(null); // LAYOUT DEL DIALEG PER AL METODE DE CONSULTAR LLIBRE
	        	jd.setBounds(100, 100, 585, 361); // DIMENSIONS DEL JDIALOG
	        	
	        	// MODIFICACIONS DE CARACTERISTIQUES ESPECIFIQUES PER A AQUEST DIALEG
				tagTitolJd.setText("----- DADES DEL LLIBRE -----");
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
	    	        	session.beginTransaction();
	    				System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
	    				
	    				int id = Integer.parseInt(txtFIdLlibre.getText());
	    				Llibre llibre = (Llibre) session.get(Llibre.class, id);
	    				if(llibre==null) {
	    					JOptionPane.showMessageDialog(new JFrame(), id, "ERROR! No s'ha trobat cap llibre", JOptionPane.ERROR_MESSAGE);
	    				} else {
	    					txtFTitol.setText(llibre.getTitol());
	    					txtFAutor.setText(llibre.getAutor());
	    					txtFAnyN.setText(llibre.getAnyNaixement());
	    					txtFAnyP.setText(llibre.getAnyPublicacio());
	    					txtFEditorial.setText(llibre.getEditorial());
	    					txtFNumPag.setText(llibre.getNumPagines());
	    					
	    				} // end-if-else
	    				session.getTransaction().commit(); // Commit de la transaccio
	    			}
	    		});
	    		btnExecucio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		btnExecucio.setBounds(231, 65, 171, 28);

		        // VISUALITZACIO DELS COMPONENTS DEL JDIALOG
	    		jd.getContentPane().add(tagTitolJd);
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
	        } // end-if
	        if(dialogId == 3) {
	        	jd.getContentPane().setLayout(null); // LAYOUT DEL DIALEG PER AL METODE DE CREAR LLIBRE
	        	jd.setBounds(100, 100, 585, 361); // DIMENSIONS DEL JDIALOG
	        	
	        	// MODIFICACIONS DE CARACTERISTIQUES ESPECIFIQUES PER A AQUEST DIALEG
	    		txtFTitol.setEditable(true);
	    		txtFAutor.setEditable(true);
	    		txtFAnyN.setEditable(true);
	    		txtFAnyP.setEditable(true);
	    		txtFEditorial.setEditable(true);
	    		txtFNumPag.setEditable(true);
	    		JButton btnExecucio = new JButton("Crear llibre");
	    		btnExecucio.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    			}
	    		});
	    		btnExecucio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		btnExecucio.setBounds(220, 63, 124, 28);
	    		
		        // VISUALITZACIO DELS COMPONENTS DEL JDIALOG
	    		jd.getContentPane().add(tagTitolJd);
	    		jd.getContentPane().add(txtFTitol);
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
	        } // end-if
	        if(dialogId == 4) {
	        	jd.getContentPane().setLayout(null); // LAYOUT DEL DIALEG PER AL METODE DE MODIFICAR LLIBRE
	        	jd.setBounds(100, 100, 585, 361); // DIMENSIONS DEL JDIALOG
	        	
	        	// MODIFICACIONS DE CARACTERISTIQUES ESPECIFIQUES PER A AQUEST DIALEG
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
	    				
	    			}
	    		});
	    		btnExecucio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		btnExecucio.setBounds(231, 65, 171, 28);
	        	
		        // VISUALITZACIO DELS COMPONENTS DEL JDIALOG
	    		jd.getContentPane().add(tagTitolJd);
	    		jd.getContentPane().add(txtFIdLlibre);
	    		jd.getContentPane().add(tagIdLlibre);
	    		jd.getContentPane().add(txtFIdLlibre);
	    		jd.getContentPane().add(tagIdLlibre);
	    		jd.getContentPane().add(txtFTitol);
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
	        } // end-if
	        if(dialogId == 5) {
	        	
	        }
	        // VISUALITZACIO DEL JDIALOG
	        jd.setVisible(true);
	        jd.setLocationRelativeTo(null);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex, "ERROR en la consulta!", JOptionPane.ERROR_MESSAGE);
		} // end-try-catch
	} // end-mostrarDialeg
	
//	public static void mostrarBiblioteca(Session session) {	
//		session.beginTransaction();
//		//String resultatSentencia = "", infoSentencia = "";
//		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
//		String infoSentencia = "", resultatSentencia = "";
//		List biblioteca = new ArrayList();
//		biblioteca = session.createQuery("FROM Llibre").list();
//		//System.out.println("----- LLISTA DE LLIBRES -----");
//		infoSentencia += "----- LLISTA DE LLIBRES -----";
//		for (Object obj : biblioteca) {
//			Llibre llibre = (Llibre) obj;
//			//System.out.println(llibre.getIdentificador()+" - "+llibre.getTitol());
//			resultatSentencia += llibre.getIdentificador()+" - "+llibre.getTitol()+"\n";
//		} // end-for
//		
//		mostrarDialeg(1);
//		session.getTransaction().commit(); // Commit de la transaccio
//	} // end-mostrarBiblioteca
	
//	public static void mostrarLlibre(Session session, int idLlibre) {
//		session.beginTransaction();
//		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
//		
//		Llibre llibre = (Llibre) session.get(Llibre.class, idLlibre);
//		if(llibre==null) {
//			//System.err.println("ERROR! No s'ha trobat cap llibre amb l'ID: "+idLlibre);
//			JOptionPane.showMessageDialog(new JFrame(), idLlibre, "ERROR! No s'ha trobat cap llibre", JOptionPane.ERROR_MESSAGE);
//		} else {
//			System.out.println("----- DADES DEL LLIBRE -----"
//					+ "\nTitol: "+llibre.getTitol()+
//					"\nAutor: "+llibre.getAutor()+
//					"\nAny Naixement: "+llibre.getAnyNaixement()+
//					"\nAny Publicacio: "+llibre.getAnyPublicacio()+
//					"\nEditorial: "+llibre.getEditorial()+
//					"\nNum pagines: "+llibre.getNumPagines()
//			);
//			txtFTitol.setText("hola");
////			txtFTitol.setText(llibre.getTitol());
////			txtFAutor.setText(llibre.getAutor());
////			txtFAnyN.setText(llibre.getAnyNaixement());
////			txtFAnyP.setText(llibre.getAnyPublicacio());
////			txtFEditorial.setText(llibre.getEditorial());
////			txtFNumPag.setText(llibre.getNumPagines());
//			
//		} // end-if-else
//		
//		session.getTransaction().commit(); // Commit de la transaccio
//	} // end-mostrarLlibre
	
	public static void afegirNouLlibre(Session session) {
		Scanner sc = new Scanner(System.in);
		String decisio = "";
		System.out.println("Benvingut al sistema de creacio de llibres!"
				+ "\nOmpli els seguents atributs per teclat...");
		do {
			session.beginTransaction();
			System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
			
			System.out.print("Titol: ");
			String strTitol = sc.nextLine();
			
			System.out.print("Autor: ");
			String strAutor = sc.nextLine();
			
			System.out.print("Any Naixement: ");
			String strAnyNaixement = sc.nextLine();
			
			System.out.print("Any Publicacio: ");
			String strAnyPublicacio = sc.nextLine();
			
			System.out.print("Editorial: ");
			String strEditorial = sc.nextLine();
			
			System.out.print("Num. Pagines: ");
			String strNumPagines = sc.nextLine();	
		
			Llibre llibreNou = new Llibre(strTitol,strAutor,strAnyNaixement,strAnyPublicacio,strEditorial,strNumPagines);
			
			System.out.println("----- DADES DEL NOU LLIBRE -----"
					+ "\nTitol: "+llibreNou.getTitol()+
					"\nAutor: "+llibreNou.getAutor()+
					"\nAny Naixement: "+llibreNou.getAnyNaixement()+
					"\nAny Publicacio: "+llibreNou.getAnyPublicacio()+
					"\nEditorial: "+llibreNou.getEditorial()+
					"\nNum pagines: "+llibreNou.getNumPagines()
			);
			
			Serializable id = session.save(llibreNou);
			session.getTransaction().commit(); // Commit de la transaccio
			
			System.out.print("Vols afegir altre llibre? (s/n): ");
			decisio = sc.nextLine();
			System.out.println();
		} while(decisio.equals("s"));
	} // end-afegirNouLlibre
	
	public static void actualitzarLlibre(Session session) {
		Scanner sc = new Scanner(System.in);
		String decisio = "";
		do {
			session.beginTransaction();
			System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
			
			System.out.print("Indica l'ID del llibre que vols actualitzar: ");
			Llibre llibreActualitzat = (Llibre) session.load(Llibre.class, Integer.parseInt(sc.nextLine()));
			
			System.out.print("Titol: ");
			llibreActualitzat.setTitol(sc.nextLine());
			
			System.out.print("Autor: ");
			llibreActualitzat.setAutor(sc.nextLine());
			
			System.out.print("Any Naixement: ");
			llibreActualitzat.setAnyNaixement(sc.nextLine());
			
			System.out.print("Any Publicacio: ");
			llibreActualitzat.setAnyPublicacio(sc.nextLine());
			
			System.out.print("Editorial: ");
			llibreActualitzat.setEditorial(sc.nextLine());
			
			System.out.print("Num. Pagines: ");
			llibreActualitzat.setNumPagines(sc.nextLine());
			
			session.update(llibreActualitzat); // Sentencia update
			session.getTransaction().commit(); // Commit de la transaccio
			
			System.out.print("Vols actualitzar altre llibre? (s/n): ");
			decisio = sc.nextLine();
			System.out.println();

		} while(decisio.equals("s"));
	} // end-actualitzarLlibre
	
	public static void esborrarLlibre(int idLlibre) {
		// Carrega la configuracio i crea una session factory
		System.err.println("\n> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		// Obri una nova sessio de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		Llibre llibreCremat = new Llibre();
		llibreCremat.setIdentificador(idLlibre);
		session.delete(llibreCremat);

		session.getTransaction().commit(); // Commit de la transaccio
		session.close(); // Tanca la sessio
	} // end-esborrarLlibre
	
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
				afegirNouLlibre(session);
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
				actualitzarLlibre(session);
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
				esborrarLlibre(18);
			}
		});
		btnEsborrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEsborrar.setBackground(new Color(239, 239, 239));
		btnEsborrar.setBorder(null);
		btnEsborrar.setFocusable(false);
		btnEsborrar.setBounds(174, 280, 147, 37);
		contentPane.add(btnEsborrar);
	} // end-visualitzar

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Carrega la configuracio i crea una session factory
		System.err.println("\n> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		session = sessionFactory.openSession(); // Obri una nova sessio de la session factory
		
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
	}
}
