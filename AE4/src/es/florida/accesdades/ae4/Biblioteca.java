package es.florida.accesdades.ae4;

/**
 * @author Eduardo Rua Chamorro | Florida Universitaria - 2ºDAM
 * @version AE4 - 04.11.21
 * @description Aquesta aplicacio anomenada ExeSQL, te com a funcio la d'executar diverses consultes amb el llenguatge SQL.
 * Es fa servir una interficie grafica desde la qual fer totes les operacions anomenades a l'activitat. A lo llarg d'aquesta s'expliquen els metodes amb mes profunditat.
 * No s'ha fet servir el MVC per falta de temps, en aquest cas es fa servir unicament d'un unic fitxer java on, dividit per metodes, s'executara tota la app.
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Panel;

public class Biblioteca extends JFrame {
	// DECLARACIONS GLOBALS DE LES VARIABLES SWING
	private JButton btnSelectTabla, btnEditorials, btnLlibres, btnExecConsulta;
	private JLabel tagTitol, tagSubtitol1, tagSubtitol2, tagAutor;
	private JTextArea txtAConsulta;
	private JPanel contentPane;

	// CONSTRUCTOR PER A INICIALITZAR ELS METODES
	public Biblioteca() {
		visualitzar();
		exeSQL();
	} // end-Biblioteca
	
	// EL METODE VISUALITZAR TE COM A UNICA FUNCIO MOSTRAR L'INTERFICIE GRAFICA AMB ELS ELEMENTS NECESARIS
	public void visualitzar() {
		setTitle("ExeSQL - AE4 (Acc\u00E8s a Dades)");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 445);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tagTitol = new JLabel("Men\u00FA de consultes SQL");
		tagTitol.setHorizontalAlignment(SwingConstants.CENTER);
		tagTitol.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		tagTitol.setBounds(220, 11, 224, 25);
		contentPane.add(tagTitol);
		
		tagSubtitol1 = new JLabel("Consultes predefinides");
		tagSubtitol1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tagSubtitol1.setHorizontalAlignment(SwingConstants.CENTER);
		tagSubtitol1.setBounds(202, 58, 256, 30);
		contentPane.add(tagSubtitol1);
		
		tagSubtitol2 = new JLabel("Escriu la consulta que vullges fer");
		tagSubtitol2.setHorizontalAlignment(SwingConstants.CENTER);
		tagSubtitol2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tagSubtitol2.setBounds(195, 233, 278, 25);
		contentPane.add(tagSubtitol2);
		
		txtAConsulta = new JTextArea();
		txtAConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtAConsulta.setLineWrap(true);
		txtAConsulta.setBounds(131, 269, 411, 63);
		contentPane.add(txtAConsulta);
		
		tagAutor = new JLabel("Alumne: Eduardo Ru\u00E1 Chamorro");
		tagAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagAutor.setBounds(10, 372, 176, 23);
		contentPane.add(tagAutor);
	} // end-visualitzar
	
	/*
	 * AQUEST ES UN METODE QUE REUTILITZAREM PER A INVOCAR UN JDIALOG ALHORA DE MOSTRAR ELS RESULTATS DE LES CONSULTES REALITZADES.
	 * TAMBE S'USARA PER A MOSTRAR LA GUIA D'US DE LA APP. PER PARAMETRES PODREM ESPECIFICAR QUIN DIALEG VOLEM MOSTRAR:
	 * 1: DIALEG DE RESULTAT DE CONSULTA
	 * 2: DIALEG DE GUIA D'US
	 * PRIMERAMENT ES DECLAREN LES VARIABLES NECESARIES, I S'INICIALITZEN AMB ELS VALORS GENERALS. PERO, AMBDOS DIALEGS NO TINDRAN LES MATEIXES CARACTERISTIQUES.
	 * PER AIXO, HE AFEGIT UN IF QUE ASIGNARA LES PROPIETATS CORRESPONENTS EN FUNCIO DEL DIALEG INVOCAT.
	 * */
	public void mostrarDialeg(int dialogId, String descripcio, String informacio) {
		try {
			JDialog jd = new JDialog(new JFrame());
			JLabel tagTitolJd = new JLabel(descripcio);
			JTextArea txtA;
			
	        tagTitolJd.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
			
	        txtA = new JTextArea();
			txtA.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			txtA.setLineWrap(true);
			txtA.setEditable(false);
			txtA.setText(informacio);
	        
	        if(dialogId == 1) {
		        jd.getContentPane().setLayout(new FlowLayout());
		        jd.setBounds(100, 100, 668, 445);
				txtA.setBounds(10,47,632,500);
	        } // end-if
	        if(dialogId == 2) {
	        	jd.getContentPane().setLayout(new FlowLayout());
		        jd.setBounds(131,47,411,360);
				txtA.setBounds(10,47,380,280);
	        } // end-if
	        
	        jd.getContentPane().setBackground(new Color(153, 180, 209));
	        jd.getContentPane().add(tagTitolJd);
	        jd.getContentPane().add(txtA);
	        jd.setVisible(true);
	        jd.setLocationRelativeTo(null);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex, "ERROR en la consulta!", JOptionPane.ERROR_MESSAGE);
		} // end-try-catch
	} // end-mostrarDialeg
	
	// EL METODE exeSQL ES EL MES IMPORTANT, I EL ENCARREGAT D'EXECUTAR ELS SEGUENTS PROCESOS INDICATS MES ENDAVANT
	public void exeSQL() {
		try {
			// ----- PROCES DE MIGRACIO DE DADES DEL FITXER CSV A LA BBDD CREADA ANTERIORMENT, AIXI COM LA CONEXIO AMB AQUESTA. -----
			
			// A CONTINUACIO LLEGIM LES DADES DEL CSV
			String strFitxerCsv = "AE04_T1_4_JDBC_Dades.csv";
			BufferedReader br = new BufferedReader(new FileReader(strFitxerCsv));
			String linea = null;			
			 
			// LES SEGUENTS DOS SENTENCIES EFECTUARAN LA CONEXIO AMB LA BBDD
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","0000");

			// SENTENCIA QUE S'EFECTUARA PER DEFECTE PER A INTRODUIR LES DADES EXTRETES DEL CSV A LA BBDD
			String sentenciaInsercio = "INSERT INTO llibres (titol,autor,anyNaixement,anyPublicacio,editorial,numPagines) VALUES (?,?,?,?,?,?)";
			PreparedStatement psInsertar = null;
			
			/*
			 * COMPROVEM SI LA TABLA LLIBRES CONTE ELS 14 REGISTRES DEL CSV, SINO, ELS INTRODUIRA, I SI JA ELS TE, NO FARA RES.
			 * TAMBE TENIM LA FUNCIONALITAT DE CREAR LA TABLA DE NOU, PER SI VOLEM RESETEJAR-LA
			 * */

			Statement stmt = con.createStatement();
			ResultSet infoRegistres = stmt.executeQuery("SELECT COUNT(idLlibre) FROM llibres");
			int numRegistres;
			
			while (infoRegistres.next()) {
				numRegistres = infoRegistres.getInt(1);
				if(numRegistres < 1) {
					/*
					 * SENTENCIES PER A PODER RESETEJAR LA TABLA
					 * PER DEFECTE AQUESTA FUNCIONALITAT NO ESTA ACTIVADA, JA QUE TINDREM LA TABLA CREADA ANTERIORMENT
					 * NOMES DESCOMENTAR SI VOLEM REINICIAR LES DADES PREDETERMINADES
					 * */
//					PreparedStatement psBorrar = con.prepareStatement("DROP TABLE llibres;");
//					int resultatEsborrar = psBorrar.executeUpdate();
//					PreparedStatement reset = null;
//					reset = con.prepareStatement("CREATE TABLE llibres (\r\n"
//							+ "  idLlibre INT AUTO_INCREMENT NOT NULL,\r\n"
//							+ "  titol VARCHAR(50) NOT NULL,\r\n"
//							+ "  autor VARCHAR(50) NOT NULL,\r\n"
//							+ "  anyNaixement CHAR(4),\r\n"
//							+ "  anyPublicacio CHAR(4) NOT NULL,\r\n"
//							+ "  editorial VARCHAR(50) NOT NULL,\r\n"
//							+ "  numPagines CHAR(30) NOT NULL,\r\n"
//							+ "  PRIMARY KEY (idLlibre)\r\n"
//							+ ");");
//					reset.executeUpdate();
//					
					/*
					 * ACI ENS ENCARREGUEM D'EXPORTAR LA INFORMACIO DEL CSV PER A INTRODUIRLA EN VARIABLES
					 * POSTERIORMENT, AFEGIREM AQUESTA INFORMACIO EN LA TABLA 'LLIBRES' DE LA BBDD 'BIBLIOTECA 
					 * */
					
					br.readLine();
					
					while ((linea = br.readLine()) != null) {
						String[] camps = linea.split(";");
						String strTitol = camps[0];
						String strAutor = camps[1];
						String strAnyNaixement = camps[2];
						String strAnyPublicacio = camps[3];
						String strEditorial = camps[4];
						String strNumPagines = camps[5];
						
						psInsertar = con.prepareStatement(sentenciaInsercio);

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
						
						int resultatInsertar = psInsertar.executeUpdate();
					} // end-while 2		
				} // end-if				
			} // end-while

			// SELECT TOT
			btnSelectTabla = new JButton("Mostra tot el contingut de LLIBRES");
			btnSelectTabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			btnSelectTabla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Statement stmt = con.createStatement();
						String consulta = "SELECT * FROM llibres;";
						String descripcio = "Dades de la tabla llibres";
						ResultSet rs = stmt.executeQuery(consulta);
						int nRegistres = rs.getMetaData().getColumnCount();
						String resultatSentencia = "";
						while(rs.next()) {
							for(int i=1; i<=nRegistres; i++) {
								resultatSentencia += rs.getString(i);
								if(i<nRegistres) {
									resultatSentencia += " - ";
								} // end-if
							} // end-for
							resultatSentencia += "\n";
						} // end-while
						mostrarDialeg(1, descripcio, resultatSentencia);
						stmt.close();
						rs.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), ex, "ERROR en la consulta!", JOptionPane.ERROR_MESSAGE);
					} // end-try-catch
				} // end-actionPerformed
			}); // end-actionListener
			btnSelectTabla.setBounds(220, 99, 224, 30);
			contentPane.add(btnSelectTabla);
			// END SELECT TOT
			
			// LLIBRES
			btnLlibres = new JButton("Llibres (titol, autor i any de publicacio) dels autors nascuts abans de 1950");
			btnLlibres.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Statement stmt = con.createStatement();
						String consulta = "SELECT titol,autor,anyPublicacio FROM llibres WHERE anyNaixement < 1950;";
						String descripcio = "Llibres (titol, autor, i any de publicacio) dels autors nascuts abans de 1950";
						ResultSet rs = stmt.executeQuery(consulta);
						int nRegistres = rs.getMetaData().getColumnCount();
						String resultatSentencia = "";
						while(rs.next()) {
							for(int i=1; i<=nRegistres; i++) {
								resultatSentencia += rs.getString(i);
								if(i<nRegistres) {
									resultatSentencia += " - ";
								} // end-if
							} // end-for
							resultatSentencia += "\n";
						} // end-while
						mostrarDialeg(1, descripcio, resultatSentencia);
						stmt.close();
						rs.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), ex, "ERROR en la consulta!", JOptionPane.ERROR_MESSAGE);
					} // end-try-catch
				} // end-actionPerformed
			}); // end-actionListener
			btnLlibres.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			btnLlibres.setBounds(131, 140, 411, 30);
			contentPane.add(btnLlibres);
			// END LLIBRES
			
			// EDITORIALS
			btnEditorials = new JButton("Editorials que hagen publicat almenys un llibre en el segle XXI");
			btnEditorials.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Statement stmt = con.createStatement();
						String consulta = "SELECT titol, editorial, anyPublicacio FROM llibres WHERE anyPublicacio > 2000;";
						String descripcio = "Editorials que hagen publicat almenys un llibre en el segle XXI";
						ResultSet rs = stmt.executeQuery(consulta);
						int nRegistres = rs.getMetaData().getColumnCount();
						String resultatSentencia = "";
						while(rs.next()) {
							for(int i=1; i<=nRegistres; i++) {
								resultatSentencia += rs.getString(i);
								if(i<nRegistres) {
									resultatSentencia += " - ";
								} // end-if
							} // end-for
							resultatSentencia += "\n";
						} // end-while
						mostrarDialeg(1, descripcio, resultatSentencia);
						stmt.close();
						rs.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), ex, "ERROR en la consulta!", JOptionPane.ERROR_MESSAGE);
					} // end-try-catch
				} // end-actionPerformed
			});  // end-actionListener
			btnEditorials.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			btnEditorials.setBounds(158, 181, 355, 30);
			contentPane.add(btnEditorials);
			// END EDITORIALS
			
			// EN AQUESTA CONSULTA, L'USUARI PODRA INTRODUIR EN UN TEXTAREA, LA CONSULTA SQL QUE DESITJA EXECUTAR
			btnExecConsulta = new JButton("Executar consulta");
			btnExecConsulta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						// RECOLLIM LA PRIMERA PARAULA DE LA SENTENCIA, PER A IDENTIFICAR QUIN TIPUS DE SENTENCIA SQL ES (SELECT, INSERT, UPDATE, DELETE, ETC)
						String consulta = txtAConsulta.getText();
						StringTokenizer tokens = new StringTokenizer(consulta);
						String tipusConsulta = tokens.nextToken().toUpperCase();
						
						switch(tipusConsulta) {
							case "SELECT":
								Statement stmt = con.createStatement();
								ResultSet rs = stmt.executeQuery(consulta);
								int nRegistres = rs.getMetaData().getColumnCount();
								String descripcio = "Consulta personalitzada";
								String resultatSentencia = "";
								while(rs.next()) {
									for(int i=1; i<=nRegistres; i++) {
										resultatSentencia += rs.getString(i);
										if(i<nRegistres) {
											resultatSentencia += " - ";
										} // end-if
									} // end-for
									resultatSentencia += "\n";
								} // end-while
								mostrarDialeg(1, descripcio, resultatSentencia);
								break;
							case "INSERT":
							case "UPDATE":
							case "DELETE":
								PreparedStatement ps = con.prepareStatement(consulta);
								int resultat = ps.executeUpdate();
								JOptionPane.showMessageDialog(new JFrame(), tipusConsulta, "Consulta realitzada!", JOptionPane.INFORMATION_MESSAGE);
								break;
							default:
								JOptionPane.showMessageDialog(new JFrame(), tipusConsulta, "ERROR en la consulta!", JOptionPane.ERROR_MESSAGE);
						} // end-switch
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), ex, "ERROR en la consulta!", JOptionPane.ERROR_MESSAGE);
					} // end-try-catch
				}  // end-actionPerformed
			}); // end-actionListener
			btnExecConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			btnExecConsulta.setBounds(266, 343, 134, 23);
			contentPane.add(btnExecConsulta);
			// END CONSULTA PERSONALITZADA
			
			// GUIA D'US: COM A DETALL, HE CONSIDERAT UTIL AFEGIR UNA GUIA AMB ELS EXEMPLES DE LES SENTENCIES QUE CONSTEN AL PDF DE L'ACTIVITAT COM UNA AJUDA RAPIDA
			JButton btnGuia = new JButton("Guia d'\u00FAs");
			btnGuia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String descripcio = "GUIA D'US";
					String consells = "A mes de les consultes ja predefinides que pots executar, tens l'opcio de fer-ne personalitzades.\n"
							+ "Si no recordes com fer algunes consultes en llenguatge SQL, mes avall pots vore algunes de les mes usades.\n"
							+ "Qualsevol dubte que tingues, tambe el pots revisar la següent guia de SQL en: https://www.w3schools.com/MySQL/default.asp"
							+ "\n\n\n"
							+ "> Per a fer seleccions de dades:\n"
							+ "SELECT * FROM llibres;\n"
							+ "> Per a insertar dades:\n"
							+ "INSERT INTO llibres (titol,autor,anyNaixement,anyPublicacio,editorial,numPagines) VALUES (?,?,?,?,?,?);\n"
							+ "> Per a actualitzar dades:\n"
							+ "UPDATE llibres SET titol = 'valorTitol' WHERE idLlibre = 5;\n"
							+ "> Per a esborrar dades:\n"
							+ "DELETE FROM llibres WHERE idLlibre = 5;\n";
					
					mostrarDialeg(2, descripcio, consells);
				} // end-actionPerformed
			}); // end-actionListener
			btnGuia.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			btnGuia.setBounds(550, 372, 92, 23);
			contentPane.add(btnGuia);
			// END GUIA D'US
			
			// TANQUEM BUFFEREDREADER
			br.close();
		} catch (Exception e) {
				System.out.println(e);
		} // end-try-catch
	} // end-exeSQL

	// METODE MAIN DESDE EL QUAL LLANÇAREM EL PROGRAMA
	public static void main(String[] args) {
		Biblioteca frame = new Biblioteca();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	} // end-main
} // end-class
