import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author edvard
 *
 */
public class App {
	
	public static void llegirXML() {
		String strFitxerConfiguracio = "config.xml";
		try {
            DocumentBuilderFactory dBuilderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderF.newDocumentBuilder();
            Document document = dBuilder.parse(new File(strFitxerConfiguracio));
            
            NodeList nodeList = document.getElementsByTagName("config1");
            
            for(int i = 0; i < nodeList.getLength(); i++) {
            	Node node = nodeList.item(i);
            	System.out.println("");
            	if(node.getNodeType() == Node.ELEMENT_NODE) {
            		Element eElement = (Element) node;
        			 String etiquetaUrl = eElement.getElementsByTagName("url").item(0).getTextContent();
            		 String etiquetaUser = eElement.getElementsByTagName("user").item(0).getTextContent();
            		 String etiquetaPassword = eElement.getElementsByTagName("password").item(0).getTextContent();
            		 
            		 sql(etiquetaUrl,etiquetaUser,etiquetaPassword);
            	} // end-if
            } // end-for
		} catch(IOException e) {
            e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	} // end-llegirXML
	
	public static void sql(String url, String user, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM preus");
			int nRegistres = rs.getMetaData().getColumnCount();
			
			while(rs.next()) {
				for(int i=1; i<=nRegistres; i++) {
					System.out.print(rs.getString(i));
					if(i<nRegistres) {
						System.out.print(" - ");
					} // end-if
				} // end-for
				System.out.println();
			} // end-while
			
			stmt.close();
			rs.close();
			
			Scanner sc = new Scanner(System.in);
			System.out.print("\nSelecciona el id de la destinacio que vols consultar-hi: ");
			String idDestinacio = sc.next();
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT nom, preu FROM preus WHERE id = "+idDestinacio);
			int nRegistres2 = rs2.getMetaData().getColumnCount();
			
			String data = LocalDateTime.now().toString();
			String nomFitxer = data.replaceAll("[A-Z]","");
			nomFitxer = "consulta_"+nomFitxer.substring(0, nomFitxer.indexOf(".", 0)).replaceAll("[-:]", "")+".txt";
			File fitxer = new File(nomFitxer);
			FileWriter fw = new FileWriter(fitxer);
			BufferedWriter bw = new BufferedWriter(fw);
			
			System.out.println();
			while(rs2.next()) {
				for(int i=1; i<=nRegistres2; i++) {
					System.out.print(rs2.getString(i));
					bw.write(rs2.getString(i));
					if(i<nRegistres2) {
						System.out.print(" - ");
						bw.write(" - ");
					} // end-if
				} // end-for
				System.out.println();
				bw.newLine();
			} // end-while
			
			bw.close();
			stmt2.close();
			rs2.close();
			sc.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // end-conexio
	
	public static void main(String[] args) {
		llegirXML();
	} // end-main
} // end-class
