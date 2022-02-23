package es.florida.accesdades.ae5;
/*
 * @author Eduardo Rua Chamorro | 2. DAM - Florida Universitaria
 * @version AE5.0 - Acces a Dades
 * @description Aquest programa no implementa l'ampliacio!
 * */
//IMPORTACIO DE LLIBRERIES
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class PrincipalSenseAmpliacio {
	public static void mostrarBiblioteca(Session session) {	
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		@SuppressWarnings("rawtypes")
		List biblioteca = new ArrayList();
		biblioteca = session.createQuery("FROM Llibre").list();
		System.out.println("----- LLISTA DE LLIBRES -----");
		for (Object obj : biblioteca) {
			Destinacio llibre = (Destinacio) obj;
			System.out.println(llibre.getIdentificador()+" - "+llibre.getTitol());
		} // end-for
		
		session.getTransaction().commit(); // Commit de la transaccio
	} // end-mostrarBiblioteca
	
	public static void mostrarLlibre(int idLlibre, Session session) {
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		Destinacio llibre = (Destinacio) session.get(Destinacio.class, idLlibre);
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
		
			Destinacio llibreNou = new Destinacio(strTitol,strAutor,strAnyNaixement,strAnyPublicacio,strEditorial,strNumPagines);
			
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
			Destinacio llibreActualitzat = (Destinacio) session.load(Destinacio.class, Integer.parseInt(sc.nextLine()));
			
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
		configuration.addClass(Destinacio.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		// Obri una nova sessio de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.err.println("> SESSIO INICIADA CORRECTAMENT\n");
		
		Destinacio llibreCremat = new Destinacio();
		llibreCremat.setIdentificador(idLlibre);
		session.delete(llibreCremat);

		session.getTransaction().commit(); // Commit de la transaccio
		session.close(); // Tanca la sessio
	} // end-esborrarLlibre
	
	
	public static void main(String[] args) {
		// Carrega la configuracio i crea una session factory
		System.err.println("\n> CONFIGURANT CONEXIO D'HIBERNATE");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Destinacio.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		System.err.println("> INICIANT SESSIO ...\n");
		Session session = sessionFactory.openSession(); // Obri una nova sessio de la session factory
				
		// Declaracions
		Scanner sc = new Scanner(System.in);
		System.out.println("----- BENVINGUT AL SISTEMA CRUD DE LA NOSTRA BIBLIOTECA -----");
		System.out.println("Els valors son assignats anteriorment en el codi, no pel usuari");
		boolean replay = true;
		
		// Aquest bucle te com a funcio la de seguir repetint el menu sempre i quant desitjem executar mes opcions
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
				mostrarBiblioteca(session);
				System.out.print("\nT'agradaria triar altra opcio?: (s/n): ");
				if(sc.nextLine().equals("s"))
					replay = true;
				else
					System.out.println("Gracies per usar el nostre SW, adeu! :D");
				break;
			case "2":
				System.out.print("Indica l'ID del llibre que vols consultar: ");
				mostrarLlibre(Integer.parseInt(sc.nextLine()), session);
				System.out.print("\nT'agradaria triar altra opcio?: (s/n): ");
				if(sc.nextLine().equals("s"))
					replay = true;
				else
					System.out.println("Gracies per usar el nostre SW, adeu! :D");
				break;
			case "3":
				afegirNouLlibre(session);
				System.out.print("\nT'agradaria triar altra opcio?: (s/n): ");
				if(sc.nextLine().equals("s"))
					replay = true;
				else
					System.out.println("Gracies per usar el nostre SW, adeu! :D");
				break;
			case "4":
				actualitzarLlibre(session);
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
				break;
			default:
				// Missatge de error en cas de que no s'hi indique una de les opcions nomenades al menu.
				System.err.println("ERROR! La opcio escollida no es correcta :(");
				replay = true; // estableix que el bucle es tornara a repetir degut a que ens hem equivocat amb l'opcio
			} // end-switch
		} // end-while
		sc.close(); // Tanca el teclat
		session.close(); // Tanca la sessio
		System.err.println("SESSIO TANCADA");
	} // end-main
} // end-class
