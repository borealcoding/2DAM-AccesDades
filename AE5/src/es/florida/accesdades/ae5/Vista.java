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
	
	private JButton btnMostrarBiblioteca, btnMostrarLlibre, btnAfegir, btnModificar, btnEsborrar;
	private JLabel tagTitol, tagAutor;
	private JPanel contentPane;
	static Session session;
	
	public Vista() {
		visualitzar();
		
	}
	
	public static void mostrarDialeg(int dialogId, String descripcio, String informacio) {
		try {
        	JDialog jd = new JDialog(new JFrame());
			jd.getContentPane().setBackground(new Color(3, 131, 135));
			
			JLabel tagTitolJd = new JLabel(descripcio);
	        tagTitolJd.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
	        tagTitolJd.setForeground(Color.WHITE);
	        
	        if(dialogId == 1) {
	        	jd.getContentPane().setLayout(new FlowLayout());
		        jd.setBounds(100, 100, 668, 445);
	        	
				JTextArea txtA;
		        txtA = new JTextArea();
				txtA.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				txtA.setLineWrap(true);
				txtA.setEditable(false);
				txtA.setBounds(10,47,632,500);
				txtA.setText(informacio);
				
		        jd.getContentPane().add(tagTitolJd);
		        jd.getContentPane().add(txtA);
		     
	        } // end-if
	        if(dialogId == 2) {
	        	jd.getContentPane().setLayout(null);
	        	jd.setBounds(100, 100, 585, 361);
	        	
	        	JTextField txtFIdLlibre = new JTextField();
	        	txtFIdLlibre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		txtFIdLlibre.setBounds(124, 66, 86, 28);
	    		jd.getContentPane().add(txtFIdLlibre);
	    		txtFIdLlibre.setColumns(8);
	    		
	    		JLabel tagIdLlibre = new JLabel("ID llibre");
	    		tagIdLlibre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		tagIdLlibre.setForeground(Color.WHITE);
	    		tagIdLlibre.setBounds(39, 69, 46, 14);
	    		jd.getContentPane().add(tagIdLlibre);
	    		
	    		JTextField txtFTitol = new JTextField();
	    		txtFTitol.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		txtFTitol.setEditable(false);
	    		txtFTitol.setBounds(124, 111, 397, 28);
	    		jd.getContentPane().add(txtFTitol);
	    		txtFTitol.setColumns(10);
	    		
	    		JLabel tagTitol = new JLabel("Titol");
	    		tagTitol.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		tagTitol.setForeground(Color.WHITE);
	    		tagTitol.setBounds(39, 114, 46, 14);
	    		jd.getContentPane().add(tagTitol);
	    		
	    		JTextField txtFAutor = new JTextField();
	    		txtFAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		txtFAutor.setEditable(false);
	    		txtFAutor.setBounds(124, 154, 397, 28);
	    		jd.getContentPane().add(txtFAutor);
	    		txtFAutor.setColumns(10);
	    		
	    		JLabel tagAutor = new JLabel("Autor");
	    		tagAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		tagAutor.setForeground(Color.WHITE);
	    		tagAutor.setBounds(39, 157, 46, 14);
	    		jd.getContentPane().add(tagAutor);
	    		
	    		JTextField txtFAnyN = new JTextField();
	    		txtFAnyN.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		txtFAnyN.setEditable(false);
	    		txtFAnyN.setBounds(124, 196, 86, 28);
	    		jd.getContentPane().add(txtFAnyN);
	    		txtFAnyN.setColumns(10);
	    		
	    		JLabel tagAnyN = new JLabel("Any Naixement");
	    		tagAnyN.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		tagAnyN.setForeground(Color.WHITE);
	    		tagAnyN.setBounds(39, 199, 102, 14);
	    		jd.getContentPane().add(tagAnyN);
	    		
	    		JLabel tagAnyP = new JLabel("Any Publicaci\u00F3");
	    		tagAnyP.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		tagAnyP.setForeground(Color.WHITE);
	    		tagAnyP.setBounds(220, 199, 86, 14);
	    		jd.getContentPane().add(tagAnyP);
	    		
	    		JTextField txtFAnyP = new JTextField();
	    		txtFAnyP.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		txtFAnyP.setEditable(false);
	    		txtFAnyP.setBounds(301, 196, 86, 28);
	    		jd.getContentPane().add(txtFAnyP);
	    		txtFAnyP.setColumns(10);
	    		
	    		JTextField txtFEditorial = new JTextField();
	    		txtFEditorial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		txtFEditorial.setEditable(false);
	    		txtFEditorial.setBounds(124, 239, 238, 28);
	    		jd.getContentPane().add(txtFEditorial);
	    		txtFEditorial.setColumns(10);
	    		
	    		JLabel tagEditorial = new JLabel("Editorial");
	    		tagEditorial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		tagEditorial.setForeground(Color.WHITE);
	    		tagEditorial.setBounds(39, 242, 46, 14);
	    		jd.getContentPane().add(tagEditorial);
	    		
	    		JLabel tagNumPag = new JLabel("N\u00BA p\u00E0gines");
	    		tagNumPag.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		tagNumPag.setForeground(Color.WHITE);
	    		tagNumPag.setBounds(382, 242, 67, 14);
	    		jd.getContentPane().add(tagNumPag);
	    		
	    		JTextField txtFNumPag = new JTextField();
	    		txtFNumPag.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		txtFNumPag.setEditable(false);
	    		txtFNumPag.setBounds(449, 239, 72, 28);
	    		jd.getContentPane().add(txtFNumPag);
	    		txtFNumPag.setColumns(10);
	    		
	    		JLabel tagDialog = new JLabel("Titol descriptiu");
	    		tagDialog.setHorizontalAlignment(SwingConstants.CENTER);
	    		tagDialog.setForeground(Color.WHITE);
	    		tagDialog.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
	    		tagDialog.setBounds(172, 11, 215, 28);
	    		jd.getContentPane().add(tagDialog);
	    		
	    		JButton tagGenerarInfo = new JButton("Generar informaci\u00F3 per l'ID");
	    		tagGenerarInfo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    		tagGenerarInfo.setBounds(231, 65, 171, 28);
	    		jd.getContentPane().add(tagGenerarInfo);
	        } // end-if
	        
	        jd.setVisible(true);
	        jd.setLocationRelativeTo(null);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex, "ERROR en la consulta!", JOptionPane.ERROR_MESSAGE);
		} // end-try-catch
	} // end-mostrarDialeg
	
	public static void mostrarBiblioteca(Session session) {	
		session.beginTransaction();
		String resultatSentencia = "", infoSentencia = "";
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		List biblioteca = new ArrayList();
		biblioteca = session.createQuery("FROM Llibre").list();
		//System.out.println("----- LLISTA DE LLIBRES -----");
		infoSentencia += "----- LLISTA DE LLIBRES -----";
		for (Object obj : biblioteca) {
			Llibre llibre = (Llibre) obj;
			//System.out.println(llibre.getIdentificador()+" - "+llibre.getTitol());
			resultatSentencia += llibre.getIdentificador()+" - "+llibre.getTitol()+"\n";
		} // end-for
		
		mostrarDialeg(2, infoSentencia, resultatSentencia);
		session.getTransaction().commit(); // Commit de la transaccio
	} // end-mostrarBiblioteca
	
	public static void mostrarLlibre(int idLlibre, Session session) {
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		Llibre llibre = (Llibre) session.get(Llibre.class, idLlibre);
		if(llibre==null) {
			System.err.println("ERROR! No s'ha trobat cap llibre amb l'ID: "+idLlibre);
		} else {
			System.out.println("----- DADES DEL LLIBRE -----"
					+ "\nTitol: "+llibre.getTitol()+
					"\nAutor: "+llibre.getAutor()+
					"\nAny Naixement: "+llibre.getAnyNaixement()+
					"\nAny Publicacio: "+llibre.getAnyPublicacio()+
					"\nEditorial: "+llibre.getEditorial()+
					"\nNum pagines: "+llibre.getNumPagines()
			);
		} // end-if-else
		
		session.getTransaction().commit(); // Commit de la transaccio
	} // end-mostrarLlibre
	
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
		
		tagTitol = new JLabel("Men\u00FA d'opcions d'Hibernate");
		tagTitol.setForeground(Color.WHITE);
		tagTitol.setHorizontalAlignment(SwingConstants.CENTER);
		tagTitol.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		tagTitol.setBounds(30, 11, 434, 25);
		contentPane.add(tagTitol);
		
		tagAutor = new JLabel("Alumne: Eduardo Ru\u00E1 Chamorro");
		tagAutor.setForeground(Color.WHITE);
		tagAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagAutor.setBounds(10, 372, 176, 23);
		contentPane.add(tagAutor);
		
		// MOSTRAR TOTS ELS TITOLS DE LA BIBLIOTECA
		btnMostrarBiblioteca = new JButton("Mostrar biblioteca");
		btnMostrarBiblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				mostrarBiblioteca(session);
				
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
				mostrarLlibre(1,session);
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
