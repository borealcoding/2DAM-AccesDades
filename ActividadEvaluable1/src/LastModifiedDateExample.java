import java.io.File;
import java.text.SimpleDateFormat;
 // ignorar aquest fitxer. únicament és va usar per a probar la classe de SimpleDateFormat
public class LastModifiedDateExample {
    public static void main(String[] args) {
      
        File file = new File("c:\\test\\myfile.txt");
      
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
      
        System.out.println("Modified Date :- " + sdf.format(file.lastModified()));
    }
}