/**
 * @author Eduardo Ruá Chamorro - 2º DAM | Florida Universitària
 * @version 1.0 - 29/09/2021
 * Recursos:
https://docs.oracle.com/javase/7/docs/api/java/io/File.html
https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.htm
> DESCRIPCIÓ DE L'ACTIVITAT
En aquest primer projecte es va a treballar amb la funcionalitat de la classe File de Java. S’ha de desenvolupar una aplicació en Java que tinga com a paràmetre d’entrada un directori de treball en el qual hi haja almenys un subdirectori i un fitxer i que implemente els següents mètodes:
- getInformacio -
 * Mostrar el nom, tipus (si és fitxer o directori), la ubicació (ruta completa), la data de l’última modificació en format data i si és ocult o no
 * Si és un fitxer haurà de mostrar la seua grandària en bytes.
 * Si és un directori haurà de mostrar el nombre d’elements que conté, espai lliure, espai disponible i espai total
- creaCarpeta -
 * Crear una carpeta en directori local
- creaFitxer -
 * Crear un fitxer en la carpeta creada
- elimina -
 * Eliminar fitxers/carpetes
- reanomenar -
 * Reanomena fitxers/carpetes

Haurà d’haver-hi una gestió d’errors (com a mínim, controlar que existisca i que es tenen permisos). Hauràs de tenir especial precaució quan implementes la gestió d’excepcions, ja que has de decidir sobre quin element has de comprovar els permisos: sobre el fitxer en si o sobre la carpeta que el conté.
Hauràs de localitzar els mètodes de la classe File que permeten realitzar la funcionalitat desitjada a través de l'API de Java. El resultat de les cridades als distints mètodes es pot concatenar en forma d’un objecte String, que s’utilitzarà per a mostrar la informació des de la interfície.

Com a suggeriment es proposa comentar una funcionalitat una vegada que s’haja provat que funciona, per a així no interferir amb el desenvolupament de la resta de funcionalitats. Com a ampliació (es tindrà en compte a la nota) es proposa implementar un xicotet menú de text que permeta a l’usuari elegir quina funcionalitat vol utilitzar i sol·licitar-li (si és procedent) la informació necessària per a la seua execució (per exemple, nom del fitxer que es vol crear).

Hauràs d’entregar el teu projecte pujant a la plataforma el teu repositori Github.
 */
// importació de llibreres
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class AE1_AccDatos {
	// declaracions generals
    public static void getInformacio() {
        Scanner sc = new Scanner(System.in);
        // la classe de SimpleDateFormat s'encarrega de convertir a format data el resultat obtingut per el mètode .lastModified()
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        String strDirectoriArrel = "E:\\usb_coding\\software_developing\\florida_universitaria\\OneDrive - Florida Centre de Formació Coop.V\\acceso_datos\\ejercicios\\proyectosEntregados\\2DAM-AccesDades\\ActividadEvaluable1\\src\\directori_de_treball";
        //String strFitxer = "fitxer.txt";
        File f_element = new File(strDirectoriArrel);
        File [] f_Lista = f_element.listFiles();
        if(f_element.exists()) {
            System.out.println("Nom: "+f_element.getName());
            System.out.println("Tipus d'element: "+(f_element.isDirectory()?"Directori":"Fitxer"));
            System.out.println("Ubicacio: "+f_element.getAbsolutePath());
            System.out.println("Data modificacio: "+sdf.format(f_element.lastModified()));
            System.out.println("Visibilitat: "+(f_element.isHidden()?"Ocult":"Visible"));
            if (f_element.isDirectory()) {
                assert f_Lista != null; // -assert- actuarà com una mena de 'requisit' que deurà complir-se per a seguir executant el codi. En aquest cas, per a poder comprovar quants elements té una llista, aquesta deu existir.
                System.out.println("Nombre d'elements que conte: "+f_Lista.length);
                System.out.println("Espai lliure: "+ ((f_element.getFreeSpace())/1e+6) +" mb");
                System.out.println("Espai total: "+ ((f_element.getTotalSpace())/1e+6) +" mb");
            } // end-if2
            if (f_element.isFile()) {
                System.out.println("Grandaria (en bytes): "+f_element.length()+" bytes");
            } // end-if3
        } // end-if1
    } // end-getInformacio()

    public static void creaCarpeta() {
        Scanner sc = new Scanner(System.in);
        String strDirectoriArrel = "E:\\usb_coding\\software_developing\\florida_universitaria\\OneDrive - Florida Centre de Formació Coop.V\\acceso_datos\\ejercicios\\proyectosEntregados\\2DAM-AccesDades\\ActividadEvaluable1\\src\\directori_de_treball";
        File f_element;
        String strDirectoriNou;

        System.out.print("Com vols nombrar al directori?: ");
        strDirectoriNou = sc.nextLine();
        f_element = new File(strDirectoriArrel+"\\"+strDirectoriNou);
        if (!f_element.exists()) {
            if (f_element.mkdir()) {
                System.out.println("Directori creat satisfactoriament amb el nom de "+f_element.getName());
            } // end-if2
        } else {
            System.out.println("El directori anteriorment citat ja existeix.");
        } // end-if1
    } // end-creaCarpeta()

    public static void creaFitxer() throws IOException {
        Scanner sc = new Scanner(System.in);
        String strDirectoriArrel = "E:\\usb_coding\\software_developing\\florida_universitaria\\OneDrive - Florida Centre de Formació Coop.V\\acceso_datos\\ejercicios\\proyectosEntregados\\2DAM-AccesDades\\ActividadEvaluable1\\src\\directori_de_treball";
        String strNomFitxer, strExtension, strFitxer;
        File f_element;

        System.out.print("Quin nom vols possar-li al fitxer?: ");
        strNomFitxer = sc.nextLine();
        System.out.print("I amb quina extensio? (sempre comença amb un punt): ");
        strExtension = sc.nextLine();
        strFitxer = strNomFitxer+"\\"+strExtension;
        f_element = new File(strDirectoriArrel+strFitxer);
        System.out.println(f_element);
        if (!f_element.exists()) {
            if (f_element.createNewFile()){
                System.out.println("Fitxer creat satisfactoriament amb el nom de "+f_element.getName());
            } // end-if2
        } else {
            System.out.println("El fitxer anteriorment citat ja existeix.");
        } // end-if1

    } // end-creaFitxer()

    public static void elimina() {
        Scanner sc = new Scanner(System.in);
        String strDirectoriArrel = "E:\\usb_coding\\software_developing\\florida_universitaria\\OneDrive - Florida Centre de Formació Coop.V\\acceso_datos\\ejercicios\\proyectosEntregados\\2DAM-AccesDades\\ActividadEvaluable1\\src\\directori_de_treball";
        String strElement;
        File f_element;

        System.out.print("Bon dia! Indica'm el nom del element que vols esborrar! (si és un fitxer, indica l'extensió): ");
        strElement = sc.nextLine();
        f_element = new File(strDirectoriArrel+"\\"+strElement);
        if (f_element.delete()) {
            System.out.println("El element amb nom "+strElement+" ha sigut esborrat!");
        } else {
            System.out.println("De quin element em parles? No puc eliminar algo que no existeix :(");
        } // end-if
    } // end-elimina()

    public static void reanomena() {
        Scanner sc = new Scanner(System.in);
        String strDirectoriArrel = "E:\\usb_coding\\software_developing\\florida_universitaria\\OneDrive - Florida Centre de Formació Coop.V\\acceso_datos\\ejercicios\\proyectosEntregados\\2DAM-AccesDades\\ActividadEvaluable1\\src\\directori_de_treball";
        String strElement, strNouElement;
        File f_element, f_NouElement;

        System.out.print("Bon dia! Indica'm quin element vols renombrar (si és un fitxer, indica l'extensió): ");
        strElement = sc.nextLine();
        f_element = new File(strDirectoriArrel+"\\"+strElement);
        System.out.print("Quin nom vols possar-li?: ");
        strNouElement = sc.nextLine();
        f_NouElement = new File(strDirectoriArrel+"\\"+strNouElement);
        if (f_element.renameTo(f_NouElement)) {
            System.out.println("Element renombrat satisfactoriament!");
        } else {
            System.out.println("No s'ha pogut canviar el nom del element :(");
        } // end-if
    } // end-reanomena()

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("BENVINGUT A LA MEUA APP!");
        Thread.sleep(500);
        System.out.println("Funcionalitats disponibles:\n" +
                "\t0. Em sap greu però, si vols eixir de l'app, polsa aquesta opció.\n" +
                "\t1. Obté informació del directori/fitxer de treball\n" +
                "\t2. Crea un directori nou\n" +
                "\t3. Crea un fitxer nou\n" +
                "\t4. Elimina directoris/fitxers\n" +
                "\t5. Reanomena directoris/fitxers\n");
        Thread.sleep(500);
        System.out.print("Elegeix la teua preferida: ");
        int opcio = sc.nextInt();
        switch(opcio) {
            case 0:
                System.out.println("Gracies per utilitzar l'App! Fins ara :)");
                System.exit(0);
            break;
            case 1:
                getInformacio();
            break;
            case 2:
                creaCarpeta();
            break;
            case 3:
                creaFitxer();
            break;
            case 4:
                elimina();
            break;
            case 5:
                reanomena();
            break;
            default:
                System.out.println("ERROR! No s'ha seleccionat cap opció! :(");
        } // end-switch
    } // end-main
} // end-class
