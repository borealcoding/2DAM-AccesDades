/**
 * @author Eduardo Rua Chamorro - 2º DAM | Florida Universitaria
 * @version 2.0 - 10.07.2021
 */
// llibreries importades
import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat; // llibreria necessaria per a poder aplicar formateig a dates i hores.

public class A2_AccDadesERCH {
	// Programa que rep com a parametre d'entrada la ruta d'un fitxer per a despres poder llegir el seu contingut i mostrar-lo per pantalla caracter a caracter
	public static void exercici9(String[] args) throws IOException {
		File fitxer = new File(args[3]);
		FileReader r = new FileReader(fitxer);
		BufferedReader br = new BufferedReader(r);
		int lletra = br.read(); // variable de tipus INT que recollira els caracters que es vajen llegint del fitxer (fora del bucle, ho fara nomes una vegada)
		
		while(lletra != -1) {
			System.out.println((char)lletra); // se anira mostrant cada caracter que es va llegint fins que no hi queden mes al fitxer. tindrem que convertir-lo a char per a poder mostrarlo, ja que de br.read() obtenim un valor INT.
			lletra = br.read();
		} // end-while
		br.close();
	} // end-exercici9
	
	// Modificant el programa anterior, s'afegeix un parametre d'entrada que indica la velocitat a la qual es mostraran els caracters
	public static void exercici10(String[] args, int velocitat) throws IOException, InterruptedException {
		File fitxer = new File(args[3]);
		FileReader r = new FileReader(fitxer);
		BufferedReader br = new BufferedReader(r);
		int lletra = br.read();
		
		while(lletra != -1) {
			System.out.println((char)lletra);
			Thread.sleep(velocitat); // Thread.sleep s'encarregara de crear una pausa entre les dos sentencies (la darrera, i la seguent), tindrem que especificar la velocitat en mil·lisegons
			lletra = br.read();
		} // end-while
		br.close();
	} // end-exercici10
	
	// Programa que mostrara un determinat nombre de caracters per pantalla. El usuari tindra que polsar alguna tecla per a que el programa puga seguir llegint altre bloc de caracters, aquest proces es repeteix fins que s'ha mostrat tot el contingut.
	public static void exercici11(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		String opcio = "";
		File fitxer = new File(args[3]);
		FileReader r = new FileReader(fitxer);
		BufferedReader br = new BufferedReader(r);
		int contador = 0, limit = 0;
		int lletra = br.read();
		
		// el usuari tindra que especificar per teclat el nombre de caracters que vols llegir
		System.out.print("Quants caracters vols llegir?: ");
		limit = sc.nextInt();
		
		while(lletra != -1) {
			// el seguent if anira mostrant els caracters que es van llegint sempre i quant no s'hi aplegue al limit establert anteriorment per teclat.
			if(contador <= limit) {
				System.out.println((char)lletra);
				Thread.sleep(200);
				lletra = br.read();
				contador++;
			} // end-if1
			// una vegada s'aplegue al limit, el usuari tindra que escriure qualsevol caracter per a eixir del programa
			if(contador==limit) {
				System.out.print("S'han llegit "+limit+" caracters, presiona qualsevol lletra per a seguir: ");
				opcio = sc.next();
				if(opcio!="") {contador=0;} else {sc.close();System.exit(0);}
			} // end-if2
		} // end-while
		System.out.println("S'ha llegit tot el document!");
		br.close();
	} // end-exercici11
	
	// Programa que mostra el contingut d'un fitxer de text linia a linia.
	public static void exercici12(String[] args) throws IOException, InterruptedException {
		File fitxer = new File(args[3]);
		FileReader r = new FileReader(fitxer);
		BufferedReader br = new BufferedReader(r);
		String lineas = br.readLine(); // en diferencia al metode read(), readLine() no llegira caracter per caracter sino, linia per linia, en format STRING (no INT)
		
		// fins que lineas no siga null, es a dir, mentre el text encara tinga paraules per a poder llegir, es seguira executant el bucle
		while(lineas != null) {
			System.out.println(lineas);
			Thread.sleep(200);
			lineas = br.readLine();
		} // end-while
		br.close();
	} // end-exercici12
	
	// Programa que mostrara les linees del fitxer de text a la velocitat indicada com a parametre d'entrada.
	public static void exercici13(String[] args, int velocitat) throws IOException, InterruptedException {
		File fitxer = new File(args[3]);
		FileReader r = new FileReader(fitxer);
		BufferedReader br = new BufferedReader(r);
		String lineas = br.readLine();
		
		while(lineas != null) {
			System.out.println(lineas);
			Thread.sleep(velocitat); // en aquest cas la velocitat en ms no s'indica directament, sino com a parametre d'entrada.
			lineas = br.readLine();
		} // end-while
		br.close();
	} // end-exercici13
	
	// Aquest programa es una replica del anterior, aleshores apart de mostrar el contingut per consola, l'escriura en un altre fitxer del mateix directori.
	public static void exercici14(String[] args, int velocitat) throws IOException, InterruptedException {
		File fitxer = new File(args[3]), fitxerNou = new File(args[4]);
		FileReader r = new FileReader(fitxer);
		BufferedReader br = new BufferedReader(r);
		FileWriter w = new FileWriter(fitxerNou);
		BufferedWriter bw = new BufferedWriter(w);
		String lineas = br.readLine();

		while(lineas != null) {
			System.out.println(lineas);
			bw.write(lineas); // write(); escriu el string indicat com a parametre
			bw.newLine(); // newLine(); introdueix una nova linea (retorno de carro)
			Thread.sleep(velocitat);
			lineas = br.readLine();
		} // end-while
		br.close();
		bw.close();
	} // end-exercici14
	
	// En aquest programa l'usuari podra decidir que esciure en un fitxer de text mentre no escriga la paraula "exit" que actuara com a condicio de finalitzacio.
	public static void exercici15(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		File fitxerNou = new File(args[4]);
		FileWriter w = new FileWriter(fitxerNou);
		BufferedWriter bw = new BufferedWriter(w);
		String lineas = "";
		
		System.out.println("Escriu tot el text que vullges, i quan et canses escriu 'exit'");
		// el bucle seguira executant-se mentre la string lineas no haja obtingut com a valor la paraula 'exit', farem us del metode .equals() que compara strings amb objectes
		while(!lineas.equals("exit")) {
			System.out.print("\t--> ");
			lineas = sc.nextLine();
			
			if(!lineas.equals("exit")) {
				bw.write(lineas);
				bw.newLine();
			} // end-if
		} // end-while
		bw.close();
	} // end-exercici15

	// Modificacio del programa anterior que afig la data i hora de creacio del fitxer.
	public static void exercici16(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss"); // especifiquem el format de data/hora que desitjem
		File fitxerNou = new File(args[4]);
		FileWriter w = new FileWriter(fitxerNou);
		BufferedWriter bw = new BufferedWriter(w);
		String lineas = "";
		
		System.out.println("Escriu tot el text que vullges, i quan et canses escriu 'exit'");
		while(!lineas.equals("exit")) {
			System.out.print("\t--> ");
			lineas = sc.nextLine();
			if(!lineas.equals("exit"))
				bw.write(lineas+"\r");
		}
		bw.write("\r[Data i hora de creacio: "+sdf.format(fitxerNou.lastModified())+"]"); // convertim el valor long retornat pel metode .lastModified al format de data/hora indicat en la declaracio del SimpleDateFormat
		bw.close();
	} // end-exercici16
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// Aquest es un menu desde el qual pots seleccionar el exercici que desitjes executar.
		// declaracions
		Scanner sc = new Scanner(System.in);
		int opcio, velocitat;
		
		System.out.print("Quin exercici vols executar? (del 9 al 16): ");
		opcio = sc.nextInt();
		
		switch(opcio) {
		case 9:
			exercici9(args);
			break;
		case 10:
			System.out.print("Especifica la velocitat de lectura(en ms): ");
			velocitat = sc.nextInt();
			exercici10(args, velocitat);
			sc.close();
			break;
		case 11:
			exercici11(args);
			break;
		case 12:
			exercici12(args);
			break;
		case 13:
			System.out.print("Especifica la velocitat de lectura(en ms): ");
			velocitat = sc.nextInt();
			exercici13(args, velocitat);
			sc.close();
			break;
		case 14:
			System.out.print("Especifica la velocitat de lectura(en ms): ");
			velocitat = sc.nextInt();
			exercici14(args, velocitat);
			sc.close();
			break;
		case 15:
			exercici15(args);
			break;
		case 16:
			exercici16(args);
			break;
		default:
			System.out.println("El exercici seleccionat no es correcte. Torna a provar! :(");
		} // end-switch
		sc.close();
	} // end-main
} // end-class
