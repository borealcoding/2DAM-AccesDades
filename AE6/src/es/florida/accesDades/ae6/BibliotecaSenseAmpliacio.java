package es.florida.accesDades.ae6;

import java.util.Scanner;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class BibliotecaSenseAmpliacio {
	// DECLARACIONS GENERALS
	public static void crearLlibre(MongoDatabase db, MongoCollection<Document> coleccio) {
		Scanner sc = new Scanner(System.in);
		String camp = "", valor = "", decisio = "", pregunta = "Vols afegir altre llibre?: ";
		Document doc = new Document();
		
		camp = "Id";
		System.out.print(camp+": "); valor = sc.nextLine();
		doc.append(camp, valor);
		
		camp = "Titol";
		System.out.print(camp+": "); valor = sc.nextLine();
		doc.append(camp, valor);
		
		camp = "Autor";
		System.out.print(camp+": "); valor = sc.nextLine();
		doc.append(camp, valor);
		
		camp = "Any_naixement";
		System.out.print(camp+": "); valor = sc.nextLine();
		doc.append(camp, valor);
		
		camp = "Any_publicacio";
		System.out.print(camp+": "); valor = sc.nextLine();
		doc.append(camp, valor);
		
		camp = "Editorial";
		System.out.print(camp+": "); valor = sc.nextLine();
		doc.append(camp, valor);
		
		camp = "Nombre_pagines";
		System.out.print(camp+": "); valor = sc.nextLine();
		doc.append(camp, valor);
		
		coleccio.insertOne(doc);
//		doc.append("titulo", "Hunting High and Low");
//		doc.append("artista", "a-ha");
//		doc.append("anyo", "1985");
//		doc.append("genero", "pop");
//		coleccio.insertOne(doc);
		
		/* Tambe podem pasar-li una llista de Documents que vullgem crear!
		 * coleccio.insertMany(llista);*/
	} // end-crearLlibre
	
	public static void llegirBiblioteca(MongoDatabase db, MongoCollection<Document> coleccio) {
		/* Tambe podem crear un objecte BSON per a usar filtres
		 * > Bson query = Filters.eq("artista", "System Of A Down");*/
		MongoCursor<Document> cursor = coleccio.find().iterator();
		while(cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			System.out.println("Id: "+obj.getInt("Id")+" | Titol: "+obj.getString("Titol"));
		}
		
		/*System.out.println("\n> OBTENIM UNA DADA ESPECIFICA DEL DOCUMENT\n");
		JSONObject obj = new JSONObject(cursor.next().toJson());
		while(cursor.hasNext()) {
			System.out.println(obj.getString("anyo"));
		}*/
	} // end-llegirBiblioteca
	
	public static void llegirLlibre(String idLlibre, MongoDatabase db, MongoCollection<Document> coleccio) {
		MongoCursor<Document> cursor = coleccio.find(Filters.eq("Id", idLlibre)).iterator();
		while(cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}
	} // end-llegirLlibre
	
	public static void modificarLlibre(String idLlibre, MongoDatabase db, MongoCollection<Document> coleccio) {
		coleccio.updateOne(Filters.eq("Id", idLlibre), new Document("$set", new Document("artista", "anonimo")));
		/* Tambe podem modificar mes d'un document a la vegada amb la seguent sentencia:
		 * > coleccio.updateMany(Filters.eq("formato", "WAV"), new Document("$set", new	Document("formato", "OGG")));*/
	} // end-modificarLlibre
	
	public static void esborrarLlibre(String idLlibre, MongoDatabase db, MongoCollection<Document> coleccio) {
		coleccio.deleteMany(Filters.eq("Id", idLlibre));
		/* Tambe podem esborrar mes d'un document amb:
		 * > coleccio.deleteMany(Filters.eq("artista", "a-ha"));
		 * Y si desitjem esborrar la coleccio sencera...
		 * > coleccio.drop();*/
	} // end-esborrarLlibre
	
	public static void main(String[] args) {
		// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDb = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccio = mongoDb.getCollection("Llibres");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n----- BENVINGUT AL SISTEMA DE MONGODB -----\n"
				+ "Selecciona alguna d'aquestes opcions!\n"
				+ "\t1: mostrar la biblioteca\n"
				+ "\t2: llegir un llibre\n"
				+ "\t3: crear un nou llibre\n"
				+ "\t4: modificar un llibre ja existent\n"
				+ "\t5: esborrar un llibre\n"
				+ "\t6: eixir del programa\n");
		System.out.print("> "+"\n");
		switch(sc.nextLine()) {
			case "1":
				llegirBiblioteca(mongoDb, coleccio);
				break;
			case "2":
				System.out.print("Introdeix l'ID del llibre que vols consultar: ");
				llegirLlibre(sc.nextLine(), mongoDb, coleccio);
				break;
			case "3":
				crearLlibre(mongoDb, coleccio);
				break;
			case "4":
				System.out.print("Introdeix l'ID del llibre que vols modificar: ");
				modificarLlibre(sc.nextLine(), mongoDb, coleccio);
				break;
			case "5":
				System.out.print("Introdeix l'ID del llibre que vols esborrar: ");
				esborrarLlibre(sc.nextLine(), mongoDb, coleccio);
				break;
			case "6":
				System.out.println("Gracies per utilizar el nostre SW\nBon nadal i felis any nou! :D");
				System.exit(0);
			default:
				System.err.println("ERROR! :( Pareix que  l'opcio seleccionada no es correcta");
		} // end-switch
		sc.close(); // tanquem el teclat
		mongoClient.close(); // finalitzem conexio a mongodb
	} // end-main
}
