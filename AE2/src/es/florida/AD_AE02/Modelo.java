package es.florida.AD_AE02;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {
	private String fitxerLectura, fitxerEscriptura;
	/*
	 * Constructor Modelo()
	 * Descripcio: Inicialitza el fitxer de lectura i de escriptura que s'utilitzaran per a realitzar les funcions
	 */
	public Modelo() {
		fitxerLectura = "AE02_T1_2_Streams_Groucho.txt";
		fitxerEscriptura = "AE02_T1_2_Streams_Groucho_copia.txt";

	} // end-CONSTR-modelo()
	
	/*
	 * metode mostrarContingut() de tipus ArrayList amb valors String
	 * Descripcio: Aquest metode actuara com a llista de valors de tipus String, passant-li com a parametre el fitxer amb el que anem a treballar.
	 * El fitxer indicat per parametre es llegira per a poder mostrar-lo quan ho necessitem.
	 * En el cas del fitxer de lectura, el mostrarem inicialment al obrir el programa, i el d'escriptura nomes apareixera quan treballem amb el metode de reemplazar()
	 * Lo que obtingam d'aquest metode, ho recollira la classe Controlador que sera la que mostre el contingut en el primer textarea.
	 */
	public ArrayList<String> mostrarContingut(String fitxer) {
		ArrayList<String> llistat = new ArrayList<String>();
		File mostrarFitxer = new File(fitxer);
		try {
			FileReader fr = new FileReader(mostrarFitxer);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			
			while(linea != null) {
				llistat.add(linea);
				linea = br.readLine();
			}
			br.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		return llistat;
	} // end-ArrayList<String>mostrarContingut()
	
	/*
	 * metode buscar()
	 * Descripcio: El metode buscar s'encarregara unicament de buscar la paraula indicada per parametre en el fitxer de lectura.
	 * Per a poder fer aço, tindrem que llegir el fitxer de lectura, separar cada paraula del text en un array de paraules, i buscar la paraula desitjada en aquest array.
	 * Una vegada la trobem, el contador registrara el nombre de vegades que s'ha trobat.
	 * La classe Controlador s'encarregara de activar el event de polsar en el boto de buscar, per a poder executar el proces corresponent.
	 */
	public void buscar(String buscaTxt) {
		File fitxerL = new File(getFitxerLectura());
		try {
			FileReader fr = new FileReader(fitxerL);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			int contador = 0;
			while(linea != null) {
				String [] paraules = linea.split(" ");
				for(int i = 0; i < paraules.length; i++) {
					if(paraules[i].equals(buscaTxt)) {
						contador++;
					}
				}
				linea = br.readLine();
			}
			br.close();
			if(contador==1 || contador<1)
				JOptionPane.showMessageDialog(new JFrame(),"S'ha trobat "+contador+" vegada.");
			else
				JOptionPane.showMessageDialog(new JFrame(),"S'ha trobat "+contador+" vegades.");
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
	} // end-buscar()
	
	/*
	 * metode reemplazar()
	 * Descripcio: Ultim metode amb el que treballarem a la classe Modelo. Aquest te com a funcio recollir per parametre la paraula buscada anteriorment, aixi com la paraula amb la que voldrem reemplazar la paraula trobada amb el metode de buscar().
	 * Per lo tant, una vegada recollim les dades necessaries, fem us del metode replaceAll per a canviar cada ocurrencia de la paraula trobada per la paraula nova.
	 * Al mateix temps, el text generat despres dels canvis, s'escriura a un fitxer nou.
	 */
	public void reemplazar(String paraulaTrobada, String paraulaNova) {
		File fitxerL = new File(getFitxerLectura());
		File fitxerE = new File(getFitxerEscriptura());
		try {
			FileReader fr = new FileReader(fitxerL);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(fitxerE);
			BufferedWriter bw = new BufferedWriter(fw);
			String linea = br.readLine();
			
			while(linea != null) {
				linea = linea.replaceAll("\\b"+paraulaTrobada+"\\b", paraulaNova);
				bw.write(linea);
				bw.newLine();
				linea = br.readLine();
			}
			br.close();
			fr.close();
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
	} // end-reemplazar()
	
	// GETTERS
	
	public String getFitxerLectura() {
		return fitxerLectura;
	} // end-getFitxerLectura()
	
	public String getFitxerEscriptura() {
		return fitxerEscriptura;
	} // end-getFitxerEscriptura
	
} // end-class
