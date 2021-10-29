/*
 * @author Eduardo Rua Chamorro | Florida Universitaria
 * @version 3.0 AE3 - 29-10-21
 */
package es.florida.accesdades.ae3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Biblioteca {
	
	/*
	 * Metode: crearLlibre(Llibre llibre)
	 * Funcio: crear un nou llibre com a XML a partir de les dades proporcionades per l'usuari, torna l'identificador del llibre
	 */
	
	public static int crearLlibre(Llibre l) throws ParserConfigurationException, TransformerException {
		
		ArrayList<Llibre> llibres = recuperarTots();
		llibres.add(l);
		String id = null;
		
		try {
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = dbFact.newDocumentBuilder();
			Document doc = build.newDocument();
			Element arrel = doc.createElement("Llibres");
			doc.appendChild(arrel);
			
			for(Llibre l1 : llibres) {
				Element llibre = doc.createElement("Llibre");
				
				id = String.valueOf(l1.getId());
				llibre.setAttribute("id_llibre", id);
				arrel.appendChild(llibre);
				
				Element titol = doc.createElement("titol");
				titol.appendChild(doc.createTextNode(String.valueOf(l1.getTitol())));
				llibre.appendChild(titol);
				
				Element autor = doc.createElement("autor");
				autor.appendChild(doc.createTextNode(String.valueOf(l1.getAutor())));
				llibre.appendChild(autor);
				
				Element anyPublicacio = doc.createElement("any_publicacio");
				anyPublicacio.appendChild(doc.createTextNode(String.valueOf(l1.getAnyPublicacio())));
				llibre.appendChild(anyPublicacio);
				
				Element editorial = doc.createElement("editorial");
				editorial.appendChild(doc.createTextNode(String.valueOf(l1.getEditorial())));
				llibre.appendChild(editorial);
				
				Element numPagines = doc.createElement("num_pagines");
				numPagines.appendChild(doc.createTextNode(String.valueOf(l1.getNumPagines())));
				llibre.appendChild(numPagines);
			} // end-for
			
			TransformerFactory tranF = TransformerFactory.newInstance();
			Transformer aTransFormer = tranF.newTransformer();
			aTransFormer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			aTransFormer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
			aTransFormer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			
			try {
				FileWriter fw = new FileWriter("Llibres.xml");
				StreamResult result = new StreamResult(fw);
				aTransFormer.transform(source, result);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			} // end-try-catch
		} catch (TransformerException ex) {
			System.err.println("Error escribiendo el documento");
		} catch (ParserConfigurationException ex) {
			System.err.println("Error construyendo el documento");
		}

		
		
		int idLlibre = Integer.valueOf(id);
		return idLlibre;
		
	} // end-crearLlibre

	/*
	 * Metode: recuperarLlibre(int identificador)
	 * Funcio: torna un objecte llibre a partir d'un identificador
	 * per a treballar amb aquest llibre l'identificarem amb un id indicat per parametre
	 * aleshores, crearem de nou l'estructura del mateix, i recorreguent els nodes de Llibre (sempre i quant el id del llibre corresponga amb el id indicat)) anirem asignant els valors.
	 * finalment, el tornarem amb un return, i farem us d'aquest metode quan el usuari vullga visualitzar l'informacio detallada de un llibre en funcio del id que ell ens indique per teclat
	 */
	
	public static Llibre recuperarLlibre(int identificador) {
		
		Llibre l = null;
		try {
			
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            NodeList nodeList = document.getElementsByTagName("Llibre");
            
            for(int i = 0; i < nodeList.getLength(); i++) {
            	Node node = nodeList.item(i);
            	System.out.println("");
            	if(node.getNodeType() == Node.ELEMENT_NODE) {
            		Element eElement = (Element) node;
            		if(Integer.valueOf(String.valueOf(eElement.getAttribute("id_llibre"))) == identificador) {
            			int idLlibre = Integer.parseInt(eElement.getAttribute("id_llibre")); 
                		String titol = eElement.getElementsByTagName("titol").item(0).getTextContent();
                		String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
                		int any_publicacio = Integer.parseInt(eElement.getElementsByTagName("any_publicacio").item(0).getTextContent());
                		String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
                		int numPagines = Integer.parseInt(eElement.getElementsByTagName("num_pagines").item(0).getTextContent());
                		
                		l = new Llibre(idLlibre, any_publicacio, numPagines, titol, autor, editorial);
            		} // end-if2
            	} // end-if
            } // end-for
            
            } catch(IOException e) {
                e.printStackTrace();
            } catch(ParserConfigurationException parserEx) {
            	parserEx.printStackTrace();
            } catch(SAXException saxEx) {
            	saxEx.printStackTrace();
            }
    		
		return l;
	} // end-recuperarLlibre
	
	/*
	 * Metode: mostrarLlibre(Llibre llibre)
	 * Funcio: mostra els atributs del llibre per pantalla
	 */
	
	public static void mostrarLlibre(Llibre llibre) {
		
		System.out.println("ID: "+llibre.getId());
		System.out.println("Titol: "+llibre.getTitol());
		System.out.println("Autor: "+llibre.getAutor());
		System.out.println("Any publicacio: "+llibre.getAnyPublicacio());
		System.out.println("Editorial: "+llibre.getEditorial());
		System.out.println("Nº pagines: "+llibre.getNumPagines());
	} // end-mostrarLlibre
	
	/*
	 * Metode: borrarLlibre(int identificador)
	 * Funcio: borra un objecte llibre a partir d'un identificador
	 */
	
	public static void borrarLlibre(int identificador) {
		// carreguem el fitxer XML
		String strFitxerXML = "Llibres.xml";
		File fitxerXML = new File(strFitxerXML);
		try {
			
			DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
	        Document document = dBuilder.parse(fitxerXML);
	        
	        // busquem entre els nodes Llibre, i eliminem aquells que continguen el atribut id_llibre indicat per parametre
	        NodeList items = document.getElementsByTagName("Llibre");
	        for(int i=0; i < items.getLength(); i++) {
	        	Element element = (Element) items.item(i);
	        	if(element.getAttribute("id_llibre").equalsIgnoreCase(String.valueOf(identificador))) {
	        		element.getParentNode().removeChild(element);
	        	} // end-if
	        } // end-for
	        
	        // transformem l'informacio a document xml
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result result = new StreamResult(fitxerXML);
			Source source = new DOMSource(document);
			transformer.transform (source, result);
	        
		} catch(IOException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException parserEx) {
        	parserEx.printStackTrace();
        } catch(SAXException saxEx) {
        	saxEx.printStackTrace();
        } catch (TransformerException e) {
			e.printStackTrace();
		} // end-try-catch	
	} // end-borrarLlibre
	
	/*
	 * Metode: actualitzaLlibre(int identificador)
	 * Funcio: actualitza (modifica) la informacio d'un objecte llibre a partir d'un identificador
	 */
	
	public static void actualitzaLlibre(int identificador) {
		
		ArrayList<Llibre> llibres = recuperarTots();
		Scanner sc = new Scanner(System.in);
			for(Llibre l : llibres) {
				if(identificador == l.getId()) {
					System.out.println("Indica el titol del llibre: ");
					String nouTitol = sc.nextLine();
		        	llibres.get(identificador-1).setTitol(nouTitol);
		        	
		        	System.out.println("De quin autor?: ");
		        	String nouAutor = sc.nextLine();
		        	llibres.get(identificador-1).setAutor(nouAutor);
		        	
		        	System.out.println("En quin any es va publicar?: ");
		        	String nouAnyPublicacio = sc.nextLine();
		        	llibres.get(identificador-1).setAnyPublicacio(Integer.parseInt(nouAnyPublicacio));
		        	
		        	System.out.println("Nombra la editorial: ");
		        	String novaEditorial = sc.nextLine();
		        	llibres.get(identificador-1).setEditorial(novaEditorial);
		        	
		        	System.out.println("I quantes pagines te?: ");
		        	String numPagines = sc.nextLine();
		        	llibres.get(identificador-1).setNumPagines(Integer.parseInt(numPagines));
				} // end-if
			} // end-for
		writeXmlFile();
		sc.close();
	} // end-actualitzaLlibre
	
	/*
	 * Metode: ArrayList<Llibre> recuperarTots()
	 * Funcio: torna una llista amb tots els llibres de la biblioteca
	 */
	
	static ArrayList<Llibre> llibres = new ArrayList<Llibre>();
	
	public static ArrayList<Llibre> recuperarTots() {
		int identificador = 0, any_publicacio = 0, numPagines = 0;
		String titol = "", autor = "", editorial = "";
		Scanner sc = new Scanner(System.in);
		
		
		Llibre l = new Llibre(identificador, any_publicacio, numPagines, titol, autor, editorial);
		
		try {
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
 
            NodeList nodeList = document.getElementsByTagName("Llibre");
            
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
		} catch(IOException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException parserEx) {
        	parserEx.printStackTrace();
        } catch(SAXException saxEx) {
        	saxEx.printStackTrace();
        } // end-try-catch
		sc.close();
		return llibres;
	} // end-recuperarTots()
	
	/*
	 * Metode: writeXmlFile
	 * Funcions: conte l'estructura necesaria per a construir el document XML
	 * a mes, en base a la informacio recopilada del llibre, l'escriu a un nou fitxer
	 * 
	 */
	
	public static void writeXmlFile() {
			try {
				// construim el document XML creant-lo en base als nodes del seu arbre DOM
				DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
				DocumentBuilder build = dbFact.newDocumentBuilder();
				Document doc = build.newDocument();
				Element arrel = doc.createElement("Llibres");
				doc.appendChild(arrel);
				
				// recorreguem el llibre, asignant-li els nodes a cada Llibre, amb els seus valors corresponents
				for(Llibre l : llibres) {
					Element llibre = doc.createElement("Llibre");
					
					String id = String.valueOf(l.getId());
					llibre.setAttribute("id_llibre", id);
					arrel.appendChild(llibre);
					
					Element titol = doc.createElement("titol");
					titol.appendChild(doc.createTextNode(String.valueOf(l.getTitol())));
					llibre.appendChild(titol);
					
					Element autor = doc.createElement("autor");
					autor.appendChild(doc.createTextNode(String.valueOf(l.getAutor())));
					llibre.appendChild(autor);
					
					Element anyPublicacio = doc.createElement("any_publicacio");
					anyPublicacio.appendChild(doc.createTextNode(String.valueOf(l.getAnyPublicacio())));
					llibre.appendChild(anyPublicacio);
					
					Element editorial = doc.createElement("editorial");
					editorial.appendChild(doc.createTextNode(String.valueOf(l.getEditorial())));
					llibre.appendChild(editorial);
					
					Element numPagines = doc.createElement("num_pagines");
					numPagines.appendChild(doc.createTextNode(String.valueOf(l.getNumPagines())));
					llibre.appendChild(numPagines);
				} // end-for
				
				// transformem l'informacio en un document
				TransformerFactory tranF = TransformerFactory.newInstance();
				Transformer aTransFormer = tranF.newTransformer();
				aTransFormer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
				aTransFormer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
				aTransFormer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				
				// finalment, l'escriguem en un nou fitxer
				try {
					FileWriter fw = new FileWriter("Llibres.xml");
					StreamResult result = new StreamResult(fw);
					aTransFormer.transform(source, result);
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // end-try-catch
				
			} catch (TransformerException ex) {
				System.err.println("Error escribiendo el documento");
			} catch (ParserConfigurationException ex) {
				System.err.println("Error construyendo el documento");
			} // end-try-catch
	} // end-writeXmlFile
	
	/*
	 * Metode: main 
	 * Funcions: mostra un menu amb diverses accions disponibles, el usuari podra seleccionar la que desitje
	 */
	
	public static void main(String[] args) throws InterruptedException, ParserConfigurationException, TransformerException {
		Scanner sc = new Scanner(System.in);
		int opcioMenu = 0, idLlibre;
		
		// MENU 
		System.out.println("Selecciona una de les opcions disponibles d'aquest menu:"
				+ "\n\t1. Mostrar tots els titols de la biblioteca"
				+ "\n\t2. Mostrar informacio detallada d'un llibre"
				+ "\n\t3. Crear nou llibre"
				+ "\n\t4. Actualitzar llibre"
				+ "\n\t5. Borrar llibre"
				+ "\n\t6. Tanca la biblioteca");
		System.out.print("> ");
		opcioMenu = sc.nextInt();
		
		switch(opcioMenu) {
		case 1:
			ArrayList<Llibre> llibres = recuperarTots();
			System.out.println("\t----- Contingut biblioteca -----");
    		for(Llibre l : llibres) {
    			System.out.println("ID_llibre: "+l.getId());
        		System.out.println("Titol: "+l.getTitol());
        		System.out.println("Autor: "+l.getAutor());
        		System.out.println("Any de publicacio: "+l.getAnyPublicacio());
        		System.out.println("Editorial: "+l.getEditorial());
        		System.out.println("Num.º pagines: "+l.getNumPagines()+"\n");
    		} // end-for
			break;
		case 2:
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Indica el ID del llibre que vols consultar: ");
			idLlibre = sc2.nextInt();
			mostrarLlibre(recuperarLlibre(idLlibre));
			sc2.close();
			break;
		case 3:
			Scanner sc3 = new Scanner(System.in);
			System.out.println("Benvingut al sistema de creacio de llibres!");
        	Thread.sleep(300);
        	System.out.println("Introdueix les dades que se te indiquen.");
        	Thread.sleep(300);
        	
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
			Scanner sc4 = new Scanner(System.in);
			System.out.print("Introdueix el ID del llibre que vols modificar: ");
			idLlibre = sc4.nextInt();
			actualitzaLlibre(idLlibre);
			sc4.close();
			break;
		case 5:
			Scanner sc5 = new Scanner(System.in);
			System.out.print("Indica el ID del llibre que vols eliminar: ");
			idLlibre = sc5.nextInt();
			borrarLlibre(idLlibre);
			sc5.close();
			break;
		case 6:
			System.out.println("Gracies per consultar la nostra biblioteca. Adeu!");
			System.exit(0);
			break;
		default:
			System.err.println("ERROR! La opcio sel·leccionada no existeix");
		} // end-switch
		sc.close();
	} // end-main
} // end-class
