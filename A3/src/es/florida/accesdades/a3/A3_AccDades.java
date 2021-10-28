package es.florida.accesdades.a3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class A3_AccDades {
	/*
	 * 18: Realitza un programa que donat el fitxer creat en l’exercici anterior el mostre per pantalla línia a línia.
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
	 * 19: Crea un programa que implemente un parser per a gestionar el fitxer XML y torne per pantalla el nombre de nodes (objectes) que hi haja al fitxer
	 */
	public static void mostrarNumNodes() {
		try {
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            Element arrel = document.getDocumentElement();
            System.out.println("Contenido XML "+arrel.getNodeName()+": ");
            NodeList nodeList = document.getElementsByTagName("Llibre");
            
            int countNodes = 0;
            for(int i = 0; i < nodeList.getLength(); i++) {
            	Node node = nodeList.item(i);
            	System.out.println("");
            	if(node.getNodeType() == Node.ELEMENT_NODE) {
            		Element eElement = (Element) node;
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
	
	
	/*
	 * 21: Introdueix al programa anterior un mètode que implemente la classe objecte que has elegit per al XML. Pot ser un objecte Java comú (POJO, Plain Old Java Object) amb constructor,
setters i getters.
	 */
	public static void mostrarObjXML(Llibre llibre) {
		try {
			Llibre l = new Llibre(int identificador, int any_publicacio, int numPagines, String titol, String autor, String editorial);
			String strFitxerXML = "Llibres.xml";
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerXML));
            
            Element arrel = document.getDocumentElement();
            System.out.println("Contenido XML "+arrel.getNodeName()+": ");
            NodeList nodeList = document.getElementsByTagName("Llibre");
            
            
            
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException parserEx) {
        	parserEx.printStackTrace();
        } catch(SAXException saxEx) {
        	saxEx.printStackTrace();
        } // end-try-catch
	} // end-class
	
	/*
	 * 22: Per a provar que l’objecte funciona correctament, realitza una altra modificació que implemente que a mesura que es lligen els nodes del XML es vagen creant objectes i
guardant-los en una llista.
	 */
	

	/*
	 * 23: Crea una altra funcionalitat que permeta a un usuari introduir objectes nous en la llista. Per això se li han de demanar els valors dels atributs, posteriorment crear un objecte amb
eixos atributs i, finalment, afegir l’objecte a la llista.
	 */
	
	
	/*
	 * 24: Com a última funcionalitat, es demana que es guarde la llista completa d’objectes en un nou fitxer XML. S’ha de comprovar que el format del fitxer resultant es correspon a l’esperat per
a un fitxer XML (indent o sagnia adequats).
	 */
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//mostrarXML();
		//mostrarNumNodes();
		mostrarObjXML();
	}

}
