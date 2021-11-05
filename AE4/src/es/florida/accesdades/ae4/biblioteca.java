package es.florida.accesdades.ae4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
			String linea = br.readLine(), titol = null, autor = null, anyNaixement = null, anyPublicacio = null, editorial = null;
			String [] arrayCsv = new String[6];
			int numPagines = 0;
			
			// 
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","0000");
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM llibres");
//			while(rs.next()) {
//				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8)+" "+rs.getString(9)+" "+rs.getString(10)+" "+rs.getString(11)+" "+rs.getString(12)+" "+rs.getString(13)+" "+rs.getString(14));
//			} // end-while
			
			while(linea != null) {
				arrayCsv = linea.split(";");
				titol = args[0];
				autor = args[1];
				anyNaixement = args[2];
				anyPublicacio = args[3];
				editorial = args[4];
				numPagines = Integer.parseInt(args[5]);
			}
			
			for(int i = 0; i<arrayCsv.length; i++) {
				System.out.println(titol);
				System.out.println(autor);
				System.out.println(anyNaixement);
				System.out.println(anyPublicacio);
				System.out.println(editorial);
				System.out.println(numPagines);
			}
			
			
			
//			rs.close();
//			stmt.close();
//			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} // end-try-catch
	} // end-main
} // end-class
