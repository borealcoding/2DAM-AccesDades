import java.io.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Eduardo Ru� Chamorro | Florida Universit�ria 2�DAM
 * @version 1.0 - 29/09/2021
 */
public class Ej1Tema1 {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
// 1. Realitza un programa que reba com a par�metre d�entrada un directori i el mostre per pantalla.
		/*File fichero = new File(args[0]);
		System.out.println("Ruta del directorio recibido: "+fichero.getAbsolutePath());*/

// 2. Ampliar el programa anterior per tal que mostre totes les caracter�stiques d�inter�s de directori, prenent com a refer�ncia la informaci� que proporciona la classe File
		/*File fichero = new File("prueba.txt");
		System.out.println("Nombre del archivo "+fichero.getName());
		System.out.println("Informacion del fichero");
		System.out.println("Nombre: "+fichero.getName());
		System.out.println("Ruta relativa: "+fichero.getPath());
		System.out.println("Ruta absoluta: "+fichero.getAbsolutePath());
		System.out.println("Se puede escribir?: "+fichero.canWrite());
		System.out.println("Se puede leer?: "+fichero.canRead());*/

// Ejemplo 3. Introduir una comprobaci� en el programa anterior per determinar si el directori existeix
		/*File fichero = new File("prueba.txt");
		try {
			if(fichero.exists()) {
				System.out.println("El archivo "+fichero.getName()+" si existe o acaba de ser creado.");
				System.out.println("Informacion del fichero");
				System.out.println("Nombre: "+fichero.getName());
				System.out.println("Ruta relativa: "+fichero.getPath());
				System.out.println("Ruta absoluta: "+fichero.getAbsolutePath());
				System.out.println("Se puede escribir?: "+fichero.canWrite());
				System.out.println("Se puede leer?: "+fichero.canRead());
				// mostrar informacion del archivo
			} else {
				System.out.println("El archivo "+fichero.getName()+" no existe. Se creara.");
				fichero.createNewFile();
			}
		} catch(IOException e) {
			System.out.println(e.toString());
		}/*
// Ejemplo 4: Realitzar un programa que donat un directori, comprove si existeix i torne un missatge de
		//confirmaci� si existeix o una alerta en cas contrari.
		// note: printStackTrace(); muestra excepcion por pantallla
		/*File directorio3 = new File("./carpeta");
		if (directorio3.exists()) {
			System.out.println("El directorio "+directorio3+" si existe.");
		} else {
			System.out.println("El directorio "+directorio3+" no existe. Se crear�!");
			directorio3.mkdir();
		}*/

/* 5. Realitza un programa que reba com a par�metres d�entrada un directori i una extensi� de fitxer (por exemple .txt) i torne per pantalla tots els fitxers del directori que complisquen el criteri. */
/*		String strDirectorio2 = args[0];
		String strExtension = args[1];
		File directorio2 = new File(strDirectorio2);
		FiltroExtension filtro = new FiltroExtension(strExtension);
		String [] listaArchivos = directorio2.list(filtro);
		for (int i = 0; i<listaArchivos.length; i++) {
			System.out.println(listaArchivos[i]);
		}*/
		//System.out.println("No existen archivos con la extension "+strExtension);

/*6. Modifica el programa anterior per que tinga en compte que si no se li passa ninguna extensi� com a par�metre, mostre tot el contingut del directori.*/
		/*String strDirectorio4 = args[0];
		String strExtension4 = "";
		File directorio4 = new File(strDirectorio4);
		FiltroExtension filtro = new FiltroExtension(strExtension4);
		String [] listaArchivos = directorio4.list(filtro);
		if (strExtension4 == "") {
			for (int i = 0; i<listaArchivos.length; i++) {
				System.out.println("No se indico ninguna extension de archivo. Se mostrara todo el contenido.");
				System.out.println(listaArchivos[i]);
			}
		} else {
			for (int i = 0; i<listaArchivos.length; i++) {
				System.out.println("Extension indicada "+strExtension4+". Archivos encontrados: ");
				System.out.println(listaArchivos[i]);
			}
		}*/

/*7. Modifica el programa anterior per tal que admeta com a par�metres d�entrada un nombre qualsevol d�extensions, tornant despr�s per pantalla tots els fitxers del directori que tinguen alguna de les extensions indicades.*/
		/*String strDirectorio5 = args[0];
		String strExtension5 = "";
		File directorio5 = new File(strDirectorio5);
		for(int i = 1; i<args.length; i++) {
			strExtension5 = args[i];
			FiltroExtension filtro = new FiltroExtension(strExtension5);
			String [] listaArchivos = directorio5.list(filtro);
			for (int j = 0; j<listaArchivos.length; j++) {
				System.out.println(listaArchivos[j]);
			} // end-for2
		} // end-for1*/

//8. Desenvolupa un programa que donat un fitxer, realitze una c�pia del mateix (en el mateix directori i canviant-li el nom) i el borre despr�s. Mostra una tra�a per pantalla de les accions per a veure que es realitzen.*/
		
		String strDirectorio6 = args[0];
		File directorio = new File(strDirectorio6);
		File f_origen = new File("E:\\usb_coding\\software_developing\\florida_universitaria\\OneDrive - Florida Centre de Formaci� Coop.V\\acceso_datos\\ejercicios\\proyectosEntregados\\2DAM-AccesDades\\Actividad1\\prueba.txt");
		File f_destino = new File("E:\\usb_coding\\software_developing\\florida_universitaria\\OneDrive - Florida Centre de Formaci� Coop.V\\acceso_datos\\ejercicios\\proyectosEntregados\\2DAM-AccesDades\\Actividad1\\copia_prueba.txt");
		FileReader r_fitxerOrigen = new FileReader(f_origen);
		BufferedReader bfr_fitxerOrigen = new BufferedReader(r_fitxerOrigen);
		FileWriter w_fitxerDestino = new FileWriter(f_destino);
		BufferedWriter bfw_fitxerDestino = new BufferedWriter(w_fitxerDestino);
		String lineas = bfr_fitxerOrigen.readLine();
		while(lineas != null) {
			bfw_fitxerDestino.write(lineas);
			lineas = bfr_fitxerOrigen.readLine();
		} // end-while
		bfr_fitxerOrigen.close();
		bfw_fitxerDestino.close();
		r_fitxerOrigen.close();
		w_fitxerDestino.close();

		System.out.println("Copia realitzada del fitxer "+f_origen+" a "+f_destino);
		// he comentant les seg�ents linees per a poder provar si la copia es realitza correctament. descomenta si desitges borrar el fitxer anteriorment copiat.
		/*System.out.println("Esborrant copia realitzada anteriorment...");
		f_destino.delete();*/
	} // end-main
} // end-class
