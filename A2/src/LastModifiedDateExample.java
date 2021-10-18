import java.io.File;
import java.text.SimpleDateFormat;
 
/*
	Aquesta es una clase d'exemple de com fer us de la classe SimpleDateFormat.
	Per a fer-la funcionar en el nostre programa, hem de seguir els següents passos.
*/
public class LastModifiedDateExample {
    public static void main(String[] args) {
    	// 1. Tenim que crear el arxiu file des-de el qual anem a treballar
        File file = new File("c:\\test\\myfile.txt");
        // 2. Declarem la classe SimpleDateFormat i com a arguments especifiquem el format de data i hora que obtindrem al realitzar la conversio
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        // 3. Per ultim, tindrem que fer us del metode sdf.format(ací s'hi indica el objecte amb el que extraguem les dades que volem convertir).
        System.out.println("Modified Date :- " + sdf.format(file.lastModified()));
    }
}