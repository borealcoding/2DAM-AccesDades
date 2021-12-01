package es.florida.accesdades.ae5;

/*
 * @author Eduardo Rua Chamorro | 2. DAM - Florida Universitaria
 * @version AE5.0 - Acces a Dades
 * @description 
 * */

// llibreries importades
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
	public static void mostrarBiblioteca() {
		// Carrega la configuracio i crea una session factory
		System.err.println("> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		// Obri una nova sessio de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		List biblioteca = new ArrayList();
		biblioteca = session.createQuery("FROM Llibre").list();
		System.out.println("----- LLISTA DE LLIBRES -----");
		for (Object obj : biblioteca) {
			Llibre llibre = (Llibre) obj;
			System.out.println(llibre.getIdentificador()+" - "+llibre.getTitol());
		}
		
		// Commit de la transaccio i tanca de sessio
		session.getTransaction().commit();
		session.close();
	} // end-mostrarBiblioteca
	
	public static void mostrarLlibre(int idLlibre) {
		System.err.println("> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		// Obri una nova sessio de la session factory
		Session session = sessionFactory.openSession();
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
		
		// Commit de la transaccio i tanca de sessio
		session.getTransaction().commit();
		session.close();
	} // end-mostrarLlibre
	
	public static void afegirNouLlibre() {
		// Carrega la configuracio i crea una session factory
		System.err.println("> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		// Obri una nova sessio de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		Scanner sc = new Scanner(System.in);
		String decisio = "";
		System.out.println("Benvingut al sistema de creacio de llibres!"
				+ "\nOmpli els seguents atributs per teclat...");
		do {
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
			session.getTransaction().commit();
			
			System.out.print("Vols afegir altre llibre? (s/n): ");
			decisio = sc.nextLine();
			System.out.println();
		} while(decisio.equals("s"));
		// Commit de la transaccio i tanca de sessio
		session.close();
	} // end-afegirNouLlibre
	
	public static void actualitzarLlibre() {
		// Carrega la configuracio i crea una session factory
		System.err.println("> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		// Obri una nova sessio de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		Llibre llibreActualitzat = (Llibre) session.load(Llibre.class, 15);
		llibreActualitzat.setAnyPublicacio("2019");
		llibreActualitzat.setEditorial("Sargantana");
		session.update(llibreActualitzat);
		
		// Commit de la transaccio i tanca de sessio
		session.getTransaction().commit();
		session.close();
	} // end-actualitzarLlibre
	
	public static void esborrarLlibre(int idLlibre) {
		// Carrega la configuracio i crea una session factory
		System.err.println("> CONFIGURANT CONEXIO D'HIBERNATE");
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
		
		// Commit de la transaccio i tanca de sessio
		session.getTransaction().commit();
		session.close();
	} // end-esborrarLlibre
	
	
	public static void main(String[] args) {
		// declaracions
		Scanner sc = new Scanner(System.in);
		System.out.println("----- BENVINGUT AL SISTEMA CRUD DE LA NOSTRA BIBLIOTECA -----");
		System.out.println("Els valors son assignats anteriorment en el codi, no pel usuari");
		boolean replay = true;
		
		while(replay) {
			replay = false; // per defecte estara en false 
			System.out.println("Elegeix alguna de les seguents opcions!");
			System.out.println("1: Mostrar biblioteca"
					+ "\n2: Llegir un llibre"
					+ "\n3: Crear un nou llibre"
					+ "\n4: Actualitzar un llibre ja existent"
					+ "\n5: Esborrar un llibre"
					+ "\n6: Eixir de l'App");
			System.out.print("\t> ");
			switch(sc.nextLine()) {
			case "1":
				mostrarBiblioteca();
				System.out.print("\nT'agradaria triar altra opcio?: (s/n): ");
				if(sc.nextLine().equals("s"))
					replay = true;
				else
					System.out.println("Gracies per usar el nostre SW, adeu! :D");
				break;
			case "2":
				System.out.print("Indica l'ID del llibre que vols consultar: ");
				mostrarLlibre(sc.nextInt());
				System.out.print("\nT'agradaria triar altra opcio?: (s/n): ");
				if(sc.nextLine().equals("s"))
					replay = true;
				else
					System.out.println("Gracies per usar el nostre SW, adeu! :D");
				break;
			case "3":
				afegirNouLlibre();
				System.out.print("\nT'agradaria triar altra opcio?: (s/n): ");
				if(sc.nextLine().equals("s"))
					replay = true;
				else
					System.out.println("Gracies per usar el nostre SW, adeu! :D");
				break;
			case "4":
				actualitzarLlibre();
				System.out.print("\nT'agradaria triar altra opcio?: (s/n): ");
				if(sc.nextLine().equals("s"))
					replay = true;
				else
					System.out.println("Gracies per usar el nostre SW, adeu! :D");
				break;
			case "5":
				System.out.print("Indica l'ID del llibre que vols esborrar: ");
				esborrarLlibre(Integer.parseInt(sc.nextLine()));
				System.out.print("\nT'agradaria triar altra opcio?: (s/n): ");
				if(sc.nextLine().equals("s"))
					replay = true;
				else
					System.out.println("\nGracies per usar el nostre SW, adeu! :D");
				break;
			case "6":
				System.out.println("Gracies per usar el nostre SW, adeu! :D");
				sc.close();
				System.exit(0);
				break;
			default:
				// Missatge de error en cas de que no s'hi indique una de les opcions nomenades al menu.
				System.err.println("ERROR! La opcio escollida no es correcta :(");
				replay = true; // estableix que el bucle es tornara a repetir degut a que ens hem equivocat amb l'opcio
			} // end-switch
		} // end-while
		sc.close();
	} // end-main
} // end-class
