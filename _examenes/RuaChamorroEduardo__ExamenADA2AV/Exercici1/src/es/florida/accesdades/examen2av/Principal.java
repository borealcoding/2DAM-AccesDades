package es.florida.accesdades.examen2av;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Principal {
	
	public static void afegirDestinacio(Session session) {
		Scanner sc = new Scanner(System.in);
		String decisio = "";
		
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		Destinacio destinacio = new Destinacio("Londres", 1000, 900, "Si");
		Serializable id = session.save(destinacio);
		
		destinacio = new Destinacio("Tokio", 1800, 1500, "Si");
		id = session.save(destinacio);
		
		destinacio = new Destinacio("Rio de Janeiro", 1500, 1100, "No");
		id = session.save(destinacio);
		
		session.getTransaction().commit(); // Commit de la transaccio
		System.out.println("REGISTRES INSERTATS SATISFACTORIAMENT");
		System.out.print("Desitjes introduir mes destinacions? (s/n): ");
		decisio = sc.nextLine();

		while(decisio.equals("s")) {
			session.beginTransaction();
			
			System.out.print("Lloc: ");
			String strLloc = sc.nextLine();
			
			System.out.print("Preu: ");
			int iPreu = Integer.parseInt(sc.nextLine());
			
			System.out.print("Preu oferta: ");
			int iPreuOferta = Integer.parseInt(sc.nextLine());
			
			System.out.print("Passaport COVID: ");
			String strPassaportCOVID = sc.nextLine();
		
			Destinacio novaDestinacio = new Destinacio(strLloc, iPreu, iPreuOferta, strPassaportCOVID);
			id = session.save(novaDestinacio);
			session.getTransaction().commit(); // Commit de la transaccio
			
			System.out.print("Desitjes introduir mes destinacions? (s/n): ");
			decisio = sc.nextLine();
		}
	} // end-afegirDestinacio
	
	public static void mostrarDestinacions(Session session) {	
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		@SuppressWarnings("rawtypes")
		List destinacions = new ArrayList();
		destinacions = session.createQuery("FROM Destinacio").list();
		System.out.println("----- LLISTA DE DESTINACIONS -----");
		for (Object obj : destinacions) {
			Destinacio destinacio = (Destinacio) obj;
			System.out.println(destinacio.getId()+" - "+destinacio.getLloc());
		} // end-for
		
		session.getTransaction().commit(); // Commit de la transaccio
	} // end-mostrarDestinacions
	
	public static void mostrarDestinacio(Session session) {
		Scanner sc = new Scanner(System.in);
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		System.out.print("Introdueix l'id de la destinacio que vols consultar: ");
		int id = Integer.parseInt(sc.nextLine());
		Destinacio destinacio = (Destinacio) session.get(Destinacio.class, id);
		if(destinacio==null) {
			System.err.println("ERROR! No s'ha trobat cap destinacio amb l'ID: "+id);
		} else {
			System.out.println("----- DADES DE LA DESTINACIO -----"
					+ "\nId: "+destinacio.getId()+
					"\nLloc: "+destinacio.getLloc()+
					"\nPreu: "+destinacio.getPreu()+
					"\nPreu oferta: "+destinacio.getPreuOferta()+
					"\nPassaport COVID: "+destinacio.getPassaportCOVID()
			);
		} // end-if-else
		
		session.getTransaction().commit(); // Commit de la transaccio
	} // end-mostrarDestinacio
	
	public static void actualitzarDestinacio(Session session) {
		Scanner sc = new Scanner(System.in);
		String decisio = "";
		do {
			session.beginTransaction();
			System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
			
			System.out.print("Indica l'ID de la destinacio que vols actualitzar: ");
			int id = Integer.parseInt(sc.nextLine());
			Destinacio destinacio = (Destinacio) session.load(Destinacio.class, id);
			
			if(destinacio==null) {
				System.err.println("ERROR! No s'ha trobat cap destinacio amb l'ID: "+id);
			} else {
				System.out.print("Lloc: ");
				destinacio.setLloc(sc.nextLine());
				
				System.out.print("Preu: ");
				destinacio.setPreu(Integer.parseInt(sc.nextLine()));
				
				System.out.print("Preu oferta: ");
				destinacio.setPreuOferta(Integer.parseInt(sc.nextLine()));
				
				System.out.print("Passaport COVID: ");
				destinacio.setPassaportCOVID(sc.nextLine());
				
				session.update(destinacio); // Sentencia update
				session.getTransaction().commit(); // Commit de la transaccio
				
				System.out.print("Vols actualitzar altra destinacio? (s/n): ");
				decisio = sc.nextLine();
				System.out.println();
			}
		} while(decisio.equals("s"));
	} // end-actualitzarDestinacio
	
	public static void esborrarDestinacio() {
		// Carrega la configuracio i crea una session factory
		System.err.println("\n> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Destinacio.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		// Obri una nova sessio de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Indica l'id de la destinacio que vols esborrar de la llista: ");
		int id = Integer.parseInt(sc.nextLine());
		//Destinacio destinacio = new Destinacio();
		Destinacio destinacio = (Destinacio) session.load(Destinacio.class, id);
		if(destinacio==null) {
			System.err.println("ERROR! No s'ha trobat cap destinacio amb l'ID: "+id);
		} else {
			destinacio.setId(id);
			session.delete(destinacio);
		}

		session.getTransaction().commit(); // Commit de la transaccio
		session.close(); // Tanca la sessio
	} // end-esborrarDestinacio
	
	
	public static void main(String[] args) {
		// Carrega la configuracio i crea una session factory
		System.err.println("\n> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Destinacio.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		Session session = sessionFactory.openSession(); // Obri una nova sessio de la session factory
		//afegirDestinacio(session);
		//mostrarDestinacions(session);
		//mostrarDestinacio(session);
		actualitzarDestinacio(session);
		//esborrarDestinacio();
		
		session.close(); // Tanca la sessio
	}

}
