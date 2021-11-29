package es.florida.accesdades.a5;

/*
 * @author Eduardo Rua Chamorro | 2. DAM - Florida Universitaria
 * @version A5.0 - Acces a Dades
 * @description Exercicis basics per a compendre el funcionament d'Hibernate. 
 * */

// llibreries importades
import java.io.Serializable;
import java.sql.Statement;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Principal {
	
	public static void main(String[] args) {
		// Carrega la configuracio i crea una session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		// Obri una nova sessio de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// Sentencies CRUD (Create, Read, Update, Delete)
		Scanner sc = new Scanner(System.in);
		System.out.println("----- BENVINGUT AL SISTEMA CRUD DE LA NOSTRA BIBLIOTECA -----");
		System.out.println("Els valors son assignats anteriorment en el codi, no pel usuari");
		System.out.println("Elegeix alguna de les seguents opcions!");
		System.out.println("1: Llegir un llibre"
				+ "\n2: Crear un nou llibre"
				+ "\n3: Actualitzar un llibre ja existent"
				+ "\n4: Esborrar un llibre"
				+ "\n5: Eixir de l'App");
		System.out.print("\t> ");
		switch(sc.next()) {
			case "1":
				/*
				 * Recupera un objecte Llibre a partir d'un ID. Posteriorment obtindrem una descripcio d'aquest com si d'una sentencia SQL es tractara. El metode GET de session obte un objecte de la classe Llibre que tinga com a ID 14*/
				Llibre llibre = (Llibre) session.get(Llibre.class, 14);
				if(llibre==null) {
					System.err.println("ERROR! No s'ha trobat cap llibre amb l'ID: 14");
				} else {
					System.out.println("----- Dades del llibre -----"
							+ "\nTitol: "+llibre.getTitol()+
							"\nAutor: "+llibre.getAutor()+
							"\nAny Naixement: "+llibre.getAnyNaixement()+
							"\nAny Publicacio: "+llibre.getAnyPublicacio()+
							"\nEditorial: "+llibre.getEditorial()+
							"\nNum pagines: "+llibre.getNumPagines()
					);
				} // end-if-else
				break;
			case "2":
				/* Crea un nou objecte Llibre amb els parametres indicats. Serializable facilita l'emmagatzematge del objecte, mitjançant el metode SAVE de session.*/
				Llibre llibreNou = new Llibre("a","a","a","a","a","a");
				Serializable id = session.save(llibreNou);
				break;
			case "3":
				/* Actualitzara un objecte Llibre amb les dades indicades mitjançant setters. El metode UPDATE s'encarrega de actualitzar la nova informacio a l'objecte.*/
				Llibre llibreActualitzat = (Llibre) session.load(Llibre.class, 15);
				llibreActualitzat.setAnyPublicacio("2019");
				llibreActualitzat.setEditorial("Sargantana");
				session.update(llibreActualitzat);
				break;
			case "4":
				/* Esborrara un objecte Llibre donat el seu ID. El metode DELETE de session preparara a l'objecte nomenat per a ser eliminat. */
				Llibre llibreCremat = new Llibre();
				llibreCremat.setIdentificador(16);
				session.delete(llibreCremat);
				break;
			case "5":
				// Ix del programa
				System.out.println("Gracies per usar el nostre SW, adeu! :D");
				sc.close();
				System.exit(0);
				break;
			default:
				// Missatge de error en cas de que no s'hi indique una de les opcions nomenades al menu.
				System.err.println("ERROR! La opcio escollida no es correcta :(");	
		} // end-switch
		
		// Commit de la transaccio i tanca de sessio
		session.getTransaction().commit();
		session.close();
	} // end-main
} // end-class
