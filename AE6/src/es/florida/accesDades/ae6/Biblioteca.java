package es.florida.accesDades.ae6;

import java.util.Scanner;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Biblioteca {
	// DECLARACIONS GENERALS
	public static void crear(MongoDatabase db, MongoCollection<Document> coleccio) {
		Document doc = new Document();
		doc.append("titulo", "Hunting High and Low");
		doc.append("artista", "a-ha");
		doc.append("anyo", "1985");
		doc.append("genero", "pop");
		coleccio.insertOne(doc);
		
		/* Tambe podem pasar-li una llista de Documents que vullgem crear!
		 * coleccio.insertMany(llista);*/
	} // end-crear
	
	public static void llegir(MongoDatabase db, MongoCollection<Document> coleccio) {
		/* Tambe podem crear un objecte BSON per a usar filtres
		 * > Bson query = Filters.eq("artista", "System Of A Down");*/
		MongoCursor<Document> cursor = coleccio.find().iterator();
		JSONObject obj = new JSONObject(cursor.next().toJson());
		while(cursor.hasNext() && obj != null) {
			System.out.println(obj.getString("Titol"));
		}
		
		/*System.out.println("\n> OBTENIM UNA DADA ESPECIFICA DEL DOCUMENT\n");
		JSONObject obj = new JSONObject(cursor.next().toJson());
		while(cursor.hasNext()) {
			System.out.println(obj.getString("anyo"));
		}*/
	} // end-llegir
	
	public static void modificar(MongoDatabase db, MongoCollection<Document> coleccio) {
		coleccio.updateOne(Filters.eq("artista", "a-ha"), new Document("$set", new Document("artista", "anonimo")));
		/* Tambe podem modificar mes d'un document a la vegada amb la seguent sentencia:
		 * > coleccio.updateMany(Filters.eq("formato", "WAV"), new Document("$set", new	Document("formato", "OGG")));*/
	} // end-modificar
	
	public static void esborrar(MongoDatabase db, MongoCollection<Document> coleccio) {
		coleccio.deleteMany(Filters.eq("artista", "a-ha"));
		/* Tambe podem esborrar mes d'un document amb:
		 * > coleccio.deleteMany(Filters.eq("artista", "a-ha"));
		 * Y si desitjem esborrar la coleccio sencera...
		 * > coleccio.drop();*/
	} // end-esborrar
	
	public static void main(String[] args) {
		// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n----- BENVINGUT AL SISTEMA DE MONGODB -----\n"
				+ "Selecciona alguna d'aquestes opcions!\n"
				+ "\t1: mostrar tots els documents\n"
				+ "\t2: crear un document nou\n"
				+ "\t3: modificar un document existent\n"
				+ "\t4: esborrar un document\n"
				+ "\t5: eixir del programa\n");
		System.out.print("> "+"\n");
		switch(sc.nextLine()) {
			case "1":
				llegir(mongoDb, coleccio);
				break;
			case "2":
				crear(mongoDb, coleccio);
				break;
			case "3":
				modificar(mongoDb, coleccio);
				break;
			case "4":
				esborrar(mongoDb, coleccio);
				break;
			case "5":
				System.out.println("Gracies per utilizar el nostre SW\nBon nadal i felis any nou! :D");
				System.exit(0);
			default:
				System.err.println("ERROR! :( Pareix que  l'opcio seleccionada no es correcta");
		} // end-switch
		sc.close(); // tanquem el teclat
		mongoClient.close(); // finalitzem conexio a mongodb
	} // end-main
}
