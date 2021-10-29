package es.florida.accesdades.ae3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
	public static int crearLlibre(Llibre l) throws ParserConfigurationException, TransformerException {
		
		ArrayList<Llibre> llibres = recuperarTots();
		llibres.add(l);
		String id = null;
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
		}
		
		TransformerFactory tranF = TransformerFactory.newInstance();
		Transformer aTransFormer = tranF.newTransformer();
		aTransFormer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
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
		}
		
		int idLlibre = Integer.valueOf(id);
		return idLlibre;
		
	}

	/*
	 * Metode: recuperarLlibre(int identificador)
	 * Funcio: torna un objecte llibre a partir d'un identificador
	 */
	public static Llibre recuperarLlibre(int identificador) {
		
		Llibre l = null;
		try {
			
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            Element arrel = document.getDocumentElement();
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
	}
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
	}
	/*
	 * Metode: borrarLlibre(int identificador)
	 * Funcio: borra un objecte llibre a partir d'un identificador
	 */
	public static void borrarLlibre(int identificador) {
		// cargamos el documento xml
		String strFitxerXML = "Llibres.xml";
		File fitxerXML = new File(strFitxerXML);
		try {
			
			DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
	        Document document = dBuilder.parse(fitxerXML);
	        
	        // buscamos entre los nodos Llibre, y eliminamos aquel que contenga el atributo id_llibre deseado
	        NodeList items = document.getElementsByTagName("Llibre");
	        for(int i=0; i < items.getLength(); i++) {
	        	Element element = (Element) items.item(i);
	        	if(element.getAttribute("id_llibre").equalsIgnoreCase(String.valueOf(identificador))) {
	        		element.getParentNode().removeChild(element);
	        	}
	        }
	        
	        // transformamos la informacion a documento xml
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
		}	
	}
	/*
	 * Metode: actualitzaLlibre(int identificador)
	 * Funcio: actualitza (modifica) la informacio d'un objecte llibre a partir d'un identificador
	 */
	public static void actualitzaLlibre(int identificador) {
		
		ArrayList<Llibre> llibres = recuperarTots();
		Scanner sc = new Scanner(System.in);
			for(Llibre l : llibres) {
				if(identificador == l.getId()) {
					System.out.print("Indica el titol del llibre: ");
		        	llibres.get(identificador-1).setTitol(sc.nextLine());
		        	System.out.print("De quin autor?: ");
		        	llibres.get(identificador-1).setAutor(sc.nextLine());
		        	System.out.print("En quin any es va publicar?: ");
		        	llibres.get(identificador-1).setAnyPublicacio(Integer.valueOf(sc.nextInt()));
		        	System.out.print("Nombra la editorial: ");
		        	llibres.get(identificador-1).setEditorial(sc.nextLine());
		        	System.out.print("I quantes pagines te?: ");
		        	llibres.get(identificador-1).setNumPagines(Integer.valueOf(sc.nextInt()));
				}
			}

	}
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
            
            Element arrel = document.getDocumentElement();
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
        }
		
		return llibres;
		
	}
	/*
	 * Metode: main 
	 * Funcio: mostra un menu amb diverses accions disponibles, el usuari podra seleccionar la que desitje
	 */
	
	
	public static void writeXmlFile() {
		try {
		DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = dFact.newDocumentBuilder();
		Document doc = build.newDocument();
		Element raiz = doc.createElement("canciones");
		doc.appendChild(raiz);
		for (Cancion can : lista.getListaCanciones()) {
		Element cancion = doc.createElement("cancion");
		String id = String.valueOf(can.getId());
		cancion.setAttribute("id",id); raiz.appendChild(cancion);
		Element titulo = doc.createElement("titulo");
		titulo.appendChild(doc.createTextNode(String.valueOf(can.getTitulo())));
		cancion.appendChild(titulo);
		Element artista = doc.createElement("artista");
		artista.appendChild(doc.createTextNode(String.valueOf(can.getArtista())));
		cancion.appendChild(artista);
		Element anyo = doc.createElement("anyo");
		anyo.appendChild(doc.createTextNode(String.valueOf(can.getAnyo())));
		cancion.appendChild(anyo);
		Element formato = doc.createElement("formato");
		formato.appendChild(doc.createTextNode(String.valueOf(can.getFormato())));
		cancion.appendChild(formato);
		}
		TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
		Transformer aTransformer = tranFactory.newTransformer();
		aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
		aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		try {
		FileWriter fw = new FileWriter("canciones2.xml"); // Definir el nombre del fichero y guardar
		StreamResult result = new StreamResult(fw);
		aTransformer.transform(source, result);
		fw.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		} catch (TransformerException ex) {
		System.out.println("Error escribiendo el documento");
		} catch (ParserConfigurationException ex) {
		System.out.println("Error construyendo el documento");
		}
		}
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException, ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int opcio = 0, idLlibre;
		
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
			ArrayList<Llibre> llibres = recuperarTots();
			System.out.println("\t----- Contingut biblioteca -----");
    		for(Llibre l : llibres) {
    			System.out.println("ID_llibre: "+l.getId());
        		System.out.println("Titol: "+l.getTitol());
        		System.out.println("Autor: "+l.getAutor());
        		System.out.println("Any de publicacio: "+l.getAnyPublicacio());
        		System.out.println("Editorial: "+l.getEditorial());
        		System.out.println("Num.º pagines: "+l.getNumPagines()+"\n");
    		}
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
			Scanner sc4 = new Scanner(System.in);
			System.out.print("Introdueix el ID del llibre que vols modificar: ");
			idLlibre = sc4.nextInt();
			actualitzaLlibre(idLlibre);
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
	} // end-main
} // end-class
