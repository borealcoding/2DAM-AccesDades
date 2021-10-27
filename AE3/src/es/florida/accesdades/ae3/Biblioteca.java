package es.florida.accesdades.ae3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Biblioteca {
	/*
	 * Metode: crearLlibre(Llibre llibre)
	 * Funcio: crear un nou llibre com a XML a partir de les dades proporcionades per l'usuari, torna l'identificador del llibre
	 */
	public int crearLlibre(Llibre llibre) {
		return 0;
		
	}
	/*
	 * Metode: recuperarLlibre(int identificador)
	 * Funcio: torna un objecte llibre a partir d'un identificador
	 */
	public Llibre recuperarLlibre(int identificador) {
		return null;
	}
	/*
	 * Metode: mostrarLlibre(Llibre llibre)
	 * Funcio: mostra els atributs del llibre per pantalla
	 */
	public void mostrarLlibre(Llibre llibre) {
		
	}
	/*
	 * Metode: borrarLlibre(int identificador)
	 * Funcio: borra un objecte llibre a partir d'un identificador
	 */
	public void borrarLlibre(int identificador) {
		
	}
	/*
	 * Metode: actualitzaLlibre(int identificador)
	 * Funcio: actualitza (modifica) la informacio d'un objecte llibre a partir d'un identificador
	 */
	public void actualitzaLlibre(int identificador) {
		
	}
	/*
	 * Metode: ArrayList<Llibre> recuperarTots()
	 * Funcio: torna una llista amb tots els llibres de la biblioteca
	 */
	public ArrayList<Llibre> recuperarTots() {
		return null;
	}
	/*
	 * Metode: main 
	 * Funcio: mostra un menu amb diverses accions disponibles, el usuari podra seleccionar la que desitje
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int opcio = 0;
		try {
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException parserEx) {
        	parserEx.printStackTrace();
        } catch(SAXException saxEx) {
        	saxEx.printStackTrace();
        }
		
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
			System.out.println("1");
			break;
		case 2:
			System.out.println("2");
			break;
		case 3:
			System.out.println("3");
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
			
		}
	} // end-main
} // end-class
