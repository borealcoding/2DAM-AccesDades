/*
 * @author Eduardo Rua Chamorro | Florida Universitaria
 * @version 3.0 A3 - 29-10-21
 */
package es.florida.accesdades.a3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class A3_AccDades {
	/*
	 * 18: Realitza un programa que donat el fitxer creat en l’exercici anterior el mostre per pantalla línia a línia.
	 */
	
	
	/*
	 * 19: Crea un programa que implemente un parser per a gestionar el fitxer XML y torne per pantalla el nombre de nodes (objectes) que hi haja al fitxer
	 */
	public static void mostrarNumNodes() {
		try {
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            Element arrel = document.getDocumentElement();
            System.out.println("Contingut XML "+arrel.getNodeName()+": ");
            NodeList nodeList = document.getElementsByTagName("Llibre");
            
            int countNodes = 0;
            for(int i = 0; i < nodeList.getLength(); i++) {
            	Node node = nodeList.item(i);
            	System.out.println("");
            	if(node.getNodeType() == Node.ELEMENT_NODE) {
            		countNodes++;
            	} // end-if
            } // end-for
            System.out.println("Num de nodes: "+countNodes);
            
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException parserEx) {
        	parserEx.printStackTrace();
        } catch(SAXException saxEx) {
        	saxEx.printStackTrace();
        } // end-try-catch
	}
	
	/*
	 * 20: Ampllia el programa anterior per a que, a mes, recorrega els nodes un a un i mostre per pantalla els seus atributs
	 */
	public static void mostrarXML() {
		try {
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            Element arrel = document.getDocumentElement();
            System.out.println("Contenido XML "+arrel.getNodeName()+": ");
            NodeList nodeList = document.getElementsByTagName("Llibre");
            
            for(int i = 0; i < nodeList.getLength(); i++) {
            	Node node = nodeList.item(i);
            	System.out.println("");
            	if(node.getNodeType() == Node.ELEMENT_NODE) {
            		Element eElement = (Element) node;
            		System.out.println("ID_llibre: "+eElement.getAttribute("id_llibre"));
            		System.out.println("Titol: "+eElement.getElementsByTagName("titol").item(0).getTextContent());
            		System.out.println("Autor: "+eElement.getElementsByTagName("autor").item(0).getTextContent());
            		System.out.println("Any de publicacio: "+eElement.getElementsByTagName("any_publicacio").item(0).getTextContent());
            		System.out.println("Editorial: "+eElement.getElementsByTagName("editorial").item(0).getTextContent());
            		System.out.println("Num.º pagines: "+eElement.getElementsByTagName("num_pagines").item(0).getTextContent());
            	} // end-if
            } // end-for
            
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException parserEx) {
        	parserEx.printStackTrace();
        } catch(SAXException saxEx) {
        	saxEx.printStackTrace();
        } // end-try-catch
	} // end-class
	
	/*
	 * 21: Introdueix al programa anterior un mètode que implemente la classe objecte que has elegit per al XML. Pot ser un objecte Java comú (POJO, Plain Old Java Object) amb constructor, setters i getters.
	 */
//	public class Llibre {
//		
//		// identificador, titol, autor, any publicacio, editorial, num pagines
//		int identificador, any_publicacio, numPagines;
//		String titol, autor, editorial;
//		
//		public Llibre(int identificador, int any_publicacio, int numPagines, String titol, String autor, String editorial) {
//			super();
//			this.identificador = identificador;
//			this.any_publicacio = any_publicacio;
//			this.numPagines = numPagines;
//			this.titol = titol;
//			this.autor = autor;
//			this.editorial = editorial;
//		}
//
//		// ---- SETTERS
//		public void setId(int identificador) {
//			this.identificador = identificador;
//		}
//		
//		public void setTitol(String titol) {
//			this.titol = titol;
//		}
//		
//		public void setAutor(String autor) {
//			this.autor = autor;
//		}
//		
//		public void setAnyPublicacio(int any_publicacio) {
//			this.any_publicacio = any_publicacio;
//		}
//		
//		public void setEditorial(String editorial) {
//			this.editorial = editorial;
//		}
//		
//		public void setNumPagines(int numPagines) {
//			this.numPagines = numPagines;
//		}
//		
//		// ---- GETTERS
//		public int getId() {
//			return this.identificador;
//		}
//		
//		public String getTitol() {
//			return this.titol;
//		}
//		
//		public String getAutor() {
//			return this.autor;
//		}
//		
//		public int getAnyPublicacio() {
//			return this.any_publicacio;
//		}
//		
//		public String getEditorial() {
//			return this.editorial;
//		}
//		
//		public int getNumPagines() {
//			return this.numPagines;
//		}
//
//		@Override
//		public String toString() {
//			return "Llibre [identificador=" + identificador + ", any_publicacio=" + any_publicacio + ", numPagines="
//					+ numPagines + ", titol=" + titol + ", autor=" + autor + ", editorial=" + editorial + "]";
//		}

//	} // end-class
	
	/*
	 * 22: Per a provar que l’objecte funciona correctament, realitza una altra modificació que implemente que a mesura que es lligen els nodes del XML es vagen creant objectes i guardant-los en una llista.
	 */
	public static void llegirObjecte() {
		
		try {
			//Llibre l = new Llibre(identificador, any_publicacio, numPagines, titol, autor, editorial);
			ArrayList<Llibre> llibres = new ArrayList<Llibre>();
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            Element arrel = document.getDocumentElement();
            System.out.println("Contenido XML "+arrel.getNodeName()+": ");
            NodeList nodeList = document.getElementsByTagName("Llibre");
            
            for(int i = 0; i < nodeList.getLength(); i++) {
            	Node node = nodeList.item(i);
            	System.out.println("");
            	if(node.getNodeType() == Node.ELEMENT_NODE) {
            		Element eElement = (Element) node;
            		int identificador = Integer.parseInt(eElement.getAttribute("id_llibre")); 
            		String titol = eElement.getElementsByTagName("titol").item(0).getTextContent();
            		String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
            		int any_publicacio = Integer.parseInt(eElement.getElementsByTagName("any_publicacio").item(0).getTextContent());
            		String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
            		int numPagines = Integer.parseInt(eElement.getElementsByTagName("num_pagines").item(0).getTextContent());
            		Llibre l = new Llibre(identificador, any_publicacio, numPagines, titol, autor, editorial);
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
	}

	/*
	 * 23: Crea una altra funcionalitat que permeta a un usuari introduir objectes nous en la llista. Per això se li han de demanar els valors dels atributs, posteriorment crear un objecte amb eixos atributs i, finalment, afegir l’objecte a la llista.
	 */
	public static ArrayList<Llibre> llegirObjecteUsuari() {
		Scanner sc = new Scanner(System.in);
		ArrayList<Llibre> llibres = new ArrayList<Llibre>();
		try {
			//Llibre l = new Llibre(identificador, any_publicacio, numPagines, titol, autor, editorial);
			
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            Element arrel = document.getDocumentElement();
            System.out.println("Contenido XML "+arrel.getNodeName()+": ");
            NodeList nodeList = document.getElementsByTagName("Llibre");
            
            for(int i = 0; i < nodeList.getLength(); i++) {
            	Node node = nodeList.item(i);
            	System.out.println("");
            	if(node.getNodeType() == Node.ELEMENT_NODE) {
            		Element eElement = (Element) node;
            		int identificador = Integer.parseInt(eElement.getAttribute("id_llibre")); 
            		String titol = eElement.getElementsByTagName("titol").item(0).getTextContent();
            		String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
            		int any_publicacio = Integer.parseInt(eElement.getElementsByTagName("any_publicacio").item(0).getTextContent());
            		String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
            		int numPagines = Integer.parseInt(eElement.getElementsByTagName("num_pagines").item(0).getTextContent());
            		
            		Llibre l = new Llibre(identificador, any_publicacio, numPagines, titol, autor, editorial);
            		llibres.add(l);
 
            	} // end-if
           
            } // end-for
            
            System.out.println("Benvingut al sistema de creacio de llibres!");
        	Thread.sleep(200);
        	System.out.println("Introdueix les dades que se te indiquen.");
        	Thread.sleep(200);
        	System.out.print("Dime el ID del llibre (assegurat de que l'ID indicat, no esta assignat a cap llibre): ");
        	int id = Integer.parseInt(sc.nextLine());
        	System.out.print("Indica el titol del llibre: ");
        	String titolNou = sc.nextLine();
        	System.out.print("De quin autor?: ");
        	String autorNou = sc.nextLine();
        	System.out.print("En quin any es va publicar?: ");
        	int nouAnyPublicacio = Integer.parseInt(sc.nextLine());
        	System.out.print("Nombra la editorial: ");
        	String editorialNova = sc.nextLine();
        	System.out.print("I quantes pagines te?: ");
        	int nouNumPagines = Integer.parseInt(sc.nextLine());
        	
        	Llibre l = new Llibre(id, nouAnyPublicacio, nouNumPagines, titolNou, autorNou, editorialNova);
        	llibres.add(l);
        	
        
        
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException parserEx) {
        	parserEx.printStackTrace();
        } catch(SAXException saxEx) {
        	saxEx.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
		sc.close();
		return llibres;
	}
	
	/*
	 * 24: Com a última funcionalitat, es demana que es guarde la llista completa d’objectes en un nou fitxer XML. S’ha de comprovar que el format del fitxer resultant es correspon a l’esperat per a un fitxer XML (indent o sagnia adequats).
	 */
	public static void escriureXML(ArrayList<Llibre> llibres) {
		try {
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = dbFact.newDocumentBuilder();
			Document doc = build.newDocument();
			Element arrel = doc.createElement("Llibres");
			doc.appendChild(arrel);
			
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
			}
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
			}
			
		} catch (TransformerException ex) {
			System.err.println("ERROR escrivint el document");
		} catch (ParserConfigurationException ex) {
			System.err.println("ERROR construint el document");
		}
	}
	
	/*
	 * Metode: borrarLlibre()
	 * Obs: El afegixc per a poder donar-li mes funcionalitat a l'aplicacio, ja que si creem un llibre nou, almenys tenim que tindre la opcio de poder esborrar-lo
	 * */
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
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Selecciona una de les opcions disponibles d'aquest menu:"
				+ "\n\t1. Mostrar tots els titols de la biblioteca"
				+ "\n\t2. Mostrar el numero de nodes que conte cada llibre"
				+ "\n\t3. Crear un nou llibre"
				+ "\n\t4. Borrar llibre"
				+ "\n\t5. Tanca la biblioteca");
		System.out.print("> ");
		String opcioMenu = sc.nextLine();
		
		switch(Integer.parseInt(opcioMenu)) {
			case 1:
				mostrarXML();
				break;
			case 2:
				mostrarNumNodes();
				break;
			case 3:
				escriureXML(llegirObjecteUsuari());
				break;
			case 4:
				System.out.print("Indica el ID del llibre que vols eliminar: ");
				borrarLlibre(Integer.parseInt(sc.nextLine()));
				break;
			case 5:
				System.out.println("Gracies per consultar la nostra biblioteca. Adeu!");
				System.exit(0);
				break;
			default:
				System.err.println("ERROR! La opcio sel·leccionada no existeix");
		} // end-switch
		sc.close();
	} // end-main
} // end-class
