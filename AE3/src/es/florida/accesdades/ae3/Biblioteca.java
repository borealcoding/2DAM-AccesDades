package es.florida.accesdades.ae3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.florida.accesdades.ae3.Llibre;

public class Biblioteca {
	/*
	 * Metode: crearLlibre(Llibre llibre)
	 * Funcio: crear un nou llibre com a XML a partir de les dades proporcionades per l'usuari, torna l'identificador del llibre
	 */
	public static int crearLlibre(Llibre l) {
		
		System.out.println("Llibre creat amb el ID "+l.getId()+" en el fitxer Llibres.xml");
		return l.getId();
	}
	/*
	 * Metode: recuperarLlibre(int identificador)
	 * Funcio: torna un objecte llibre a partir d'un identificador
	 */
	public static Llibre recuperarLlibre(int identificador) {
		
		//ArrayList<Llibre> llibre = recuperarTots();
		
		if(identificador == 1) {
			System.out.println("1");
		}
		if(identificador == 2) {
			System.out.println("2");
		}
		if(identificador == 3) {
			System.out.println("3");
		}
		if(identificador == 4) {
			System.out.println("4");
		}
		if(identificador == 5) {
			System.out.println("5");
		}
		
		
		
		return null;
	}
	/*
	 * Metode: mostrarLlibre(Llibre llibre)
	 * Funcio: mostra els atributs del llibre per pantalla
	 */
	public static void mostrarLlibre(Llibre llibre) {
		
	}
	/*
	 * Metode: borrarLlibre(int identificador)
	 * Funcio: borra un objecte llibre a partir d'un identificador
	 */
	public static void borrarLlibre(int identificador) {
		
	}
	/*
	 * Metode: actualitzaLlibre(int identificador)
	 * Funcio: actualitza (modifica) la informacio d'un objecte llibre a partir d'un identificador
	 */
	public static void actualitzaLlibre(int identificador) {
		
	}
	/*
	 * Metode: ArrayList<Llibre> recuperarTots()
	 * Funcio: torna una llista amb tots els llibres de la biblioteca
	 */
	public static ArrayList<Llibre> recuperarTots() {
		int identificador = 0, any_publicacio = 0, numPagines = 0;
		String titol = "", autor = "", editorial = "";
		Scanner sc = new Scanner(System.in);
		ArrayList<Llibre> llibres = new ArrayList<Llibre>();
		Llibre l = new Llibre(identificador, any_publicacio, numPagines, titol, autor, editorial);
		
		try {
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            Element arrel = document.getDocumentElement();
            NodeList nodeList = document.getElementsByTagName("Llibre");
            System.out.println("Contenido XML "+arrel.getNodeName()+": ");
            
            for(int i = 0; i < nodeList.getLength(); i++) {
            	Node node = nodeList.item(i);
            	System.out.println("");
            	if(node.getNodeType() == Node.ELEMENT_NODE) {
            		Element eElement = (Element) node;
            		identificador = Integer.parseInt(eElement.getAttribute("id_llibre")); 
            		titol = eElement.getElementsByTagName("titol").item(0).getTextContent();
            		autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
            		any_publicacio = Integer.parseInt(eElement.getElementsByTagName("any_publicacio").item(0).getTextContent());
            		editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
            		numPagines = Integer.parseInt(eElement.getElementsByTagName("num_pagines").item(0).getTextContent());
            		
            		l = new Llibre(identificador, any_publicacio, numPagines, titol, autor, editorial);
            		llibres.add(l);
 
            	} // end-if
           
            } // end-for
            
            for(int i = 0; i < llibres.size(); i++) {
                System.out.println("ID: "+llibres.get(i).getId());
                System.out.println("Titol: "+llibres.get(i).getTitol());
                System.out.println("Autor: "+llibres.get(i).getAutor());
                System.out.println("Any de publicacio: "+llibres.get(i).getAnyPublicacio());
                System.out.println("Editorial: "+llibres.get(i).getEditorial());
                System.out.println("Nº pagines: "+llibres.get(i).getNumPagines());
            } // end-for
            
		} catch(IOException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException parserEx) {
        	parserEx.printStackTrace();
        } catch(SAXException saxEx) {
        	saxEx.printStackTrace();
        }
		
		return llibres;
		
	}
	/*
	 * Metode: main 
	 * Funcio: mostra un menu amb diverses accions disponibles, el usuari podra seleccionar la que desitje
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int opcio = 0;
		
		// MENU 
		System.out.println("Selecciona una de les opcions disponibles d'aquest menu:"
				+ "\n\t1. Mostrar tots els titols de la biblioteca"
				+ "\n\t2. Mostrar informacio detallada d'un llibre"
				+ "\n\t3. Crear nou llibre"
				+ "\n\t4. Actualitzar llibre"
				+ "\n\t5. Borrar llibre"
				+ "\n\t6. Tanca la biblioteca");
		System.out.print("> ");
		opcio = sc.nextInt();
		switch(opcio) {
		case 1:
			recuperarTots();
			break;
		case 2:
			Scanner sc2 = new Scanner(System.in);
			int idLlibre;
			System.out.print("Indica el ID del llibre que vols consultar: ");
			idLlibre = sc2.nextInt();
			recuperarLlibre(idLlibre);
			sc2.close();
			break;
		case 3:
			Scanner sc3 = new Scanner(System.in);
			System.out.println("Benvingut al sistema de creacio de llibres!");
        	Thread.sleep(200);
        	System.out.println("Introdueix les dades que se te indiquen.");
        	Thread.sleep(200);
        	System.out.print("Dime el ID del llibre (a partir del 6 en amunt): ");
        	int nouId = Integer.parseInt(sc3.nextLine());
        	System.out.print("Indica el titol del llibre: ");
        	String titolNou = sc3.nextLine();
        	System.out.print("De quin autor?: ");
        	String autorNou = sc3.nextLine();
        	System.out.print("En quin any es va publicar?: ");
        	int nouAnyPublicacio = Integer.parseInt(sc3.nextLine());
        	System.out.print("Nombra la editorial: ");
        	String editorialNova = sc3.nextLine();
        	System.out.print("I quantes pagines te?: ");
        	int nouNumPagines = Integer.parseInt(sc3.nextLine());
        	
        	Llibre l = new Llibre(nouId, nouAnyPublicacio, nouNumPagines, titolNou, autorNou, editorialNova);
        	crearLlibre(l);
        	sc3.close();
			break;
		case 4:
			System.out.println("4");
			break;
		case 5:
			System.out.println("5");
			break;
		case 6:
			System.out.println("6");
			break;
		default:
			System.err.println("ERROR! La opcio sel·leccionada no existeix");
			
		} // end-switch
	} // end-main
} // end-class
