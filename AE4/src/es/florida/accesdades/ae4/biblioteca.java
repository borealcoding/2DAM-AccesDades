package es.florida.accesdades.ae4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author edvard
 * @version AE4 - 04.11.21
 */

public class biblioteca {
	public static void main(String[] args) {
		try {
			// Primerament tenim que llegir el fitxer CSV, per a poder treballar amb les dades d'aquest.
			String strFitxerCsv = "AE04_T1_4_JDBC_Dades.csv";
			BufferedReader br = new BufferedReader(new FileReader(strFitxerCsv));
			String linea = null;			
			int batchSize = 20;
			 
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","0000");

			String sentenciaInsercion = "INSERT INTO llibres (titol,autor,anyNaixement,anyPublicacio,editorial,numPagines) VALUES (?,?,?,?,?,?)";
			PreparedStatement psInsertar = null;
			
			br.readLine();
			
			/*
			 * SENTENCIES PER A PODER RESETEJAR LA TABLA NOMES INICIEM EL PROGRAMA
			 * AÇO ENS AJUDARA A EVITAR DUPLICITATS, JA QUE ES CREARIEN COPIES DEL CONTINGUT DEL CSV*/
			PreparedStatement psBorrar = con.prepareStatement("DROP TABLE llibres;");
			int resultadoBorrar = psBorrar.executeUpdate();
			PreparedStatement reset = null;
			reset = con.prepareStatement("CREATE TABLE llibres (\r\n"
					+ "  idLlibre INT AUTO_INCREMENT NOT NULL,\r\n"
					+ "  titol VARCHAR(50) NOT NULL,\r\n"
					+ "  autor VARCHAR(50) NOT NULL,\r\n"
					+ "  anyNaixement CHAR(4),\r\n"
					+ "  anyPublicacio CHAR(4) NOT NULL,\r\n"
					+ "  editorial VARCHAR(50) NOT NULL,\r\n"
					+ "  numPagines CHAR(30) NOT NULL,\r\n"
					+ "  PRIMARY KEY (idLlibre)\r\n"
					+ ");");
			reset.executeUpdate();

			/*
			 * ACI ENS ENCARREGUEM D'EXPORTAR LA INFORMACIO DEL CSV PER A INTRODUIRLA EN VARIABLES
			 * POSTERIORMENT, AFEGIREM AQUESTA INFORMACIO EN LA TABLA 'LLIBRES' DE LA BBDD 'BIBLIOTECA */
			while ((linea = br.readLine()) != null) {
				String[] camps = linea.split(";");
				String strTitol = camps[0];
				String strAutor = camps[1];
				String strAnyNaixement = camps[2];
				String strAnyPublicacio = camps[3];
				String strEditorial = camps[4];
				String strNumPagines = camps[5];
				
				psInsertar = con.prepareStatement(sentenciaInsercion);

				psInsertar.setString(1, strTitol);
				psInsertar.setString(2, strAutor);
				if(strAnyNaixement == "") {
					psInsertar.setString(3, "N.C");
				} else {
					psInsertar.setString(3, strAnyNaixement);
				}
				psInsertar.setString(4, strAnyPublicacio);
				psInsertar.setString(5, strEditorial);
				psInsertar.setString(6, strNumPagines);
				
				int resultadoInsertar = psInsertar.executeUpdate();
							
			} // end-while
			
			/*CONSULTES QUE ES DEUEN D'EXECUTAR PER DEFECTE*/
			
			Statement stmt1 = con.createStatement();
			System.out.println("\n ----- Dades de la tabla llibres ----- \n");
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM llibres");
			int nRegistres1 = rs1.getMetaData().getColumnCount();
			while(rs1.next()) {
				for(int i=1; i<=nRegistres1; i++) {
					System.out.print(rs1.getString(i));
					if(i<nRegistres1) {
						System.out.print(" - ");
					}
				}
				System.out.println();
			} // end-while
			
			Statement stmt2 = con.createStatement();
			System.out.println("\n ----- Llibres (titol, autor, i any de publicacio) dels autors nascuts abans de 1950 ----- \n");
			ResultSet rs2 = stmt2.executeQuery("SELECT titol,autor,anyPublicacio FROM llibres WHERE anyNaixement < 1950;");
			int nRegistres2 = rs2.getMetaData().getColumnCount();
			while(rs2.next()) {
				for(int i=1; i<=nRegistres2; i++) {
					System.out.print(rs2.getString(i));
					if(i<nRegistres2) {
						System.out.print(" - ");
					}
				}
				System.out.println();
			} // end-while
			
			Statement stmt3 = con.createStatement();
			System.out.println("\n ----- Editorials que hagen publicat almenys un llibre en el segle XXI ----- \n");
			ResultSet rs3 = stmt3.executeQuery("SELECT titol, editorial, anyPublicacio FROM llibres WHERE anyPublicacio > 2000;");
			int nRegistres3 = rs3.getMetaData().getColumnCount();
			while(rs3.next()) {
				for(int i=1; i<=nRegistres3; i++) {
					System.out.print(rs3.getString(i));
					if(i<nRegistres3) {
						System.out.print(" - ");
					}
				}
				System.out.println();
			} // end-while
			
			// TANQUEM STATEMENTS Y CONEXIONS
			br.close();
			rs1.close();
			stmt1.close();
			rs2.close();
			stmt2.close();
			rs3.close();
			stmt3.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} // end-try-catch
	} // end-main
} // end-class
