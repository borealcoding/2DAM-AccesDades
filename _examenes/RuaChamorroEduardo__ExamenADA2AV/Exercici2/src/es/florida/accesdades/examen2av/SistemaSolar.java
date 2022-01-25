package es.florida.accesdades.examen2av;

import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class SistemaSolar {
	
	public static void mostrarPlanetes(MongoDatabase db, MongoCollection<Document> coleccio) {
		MongoCursor<Document> cursor = coleccio.find().iterator();
		System.out.println("----- LLISTA DE PLANETES -----");
		while(cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			System.out.println(obj.getString("nomPlaneta"));
		}
	} // end-mostrarPlanetes
	
	public static void mostrarPlaneta(MongoDatabase db, MongoCollection<Document> coleccio) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Sobre quin planeta vols consultar?: ");
		String nomPlaneta = sc.nextLine();
		MongoCursor<Document> cursor = coleccio.find(Filters.eq("nomPlaneta", nomPlaneta)).iterator();
		while(cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}
	} // end-mostrarPlaneta
	
	public static void planetesAmbMesGravetat(MongoDatabase db, MongoCollection<Document> coleccio) {
		MongoCursor<Document> cursor = coleccio.find().iterator();
		while(cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			if(obj.getDouble("gravetat") > 9.8) {
				System.out.println(obj.getString("nomPlaneta")+" | "+obj.getDouble("gravetat"));
			}
		}
	} // end-planetesAmbMesGravetat
	
	public static void esborrarPlaneta(MongoDatabase db, MongoCollection<Document> coleccio) {
		Scanner sc = new Scanner(System.in);
		System.err.println("> ALERTA ALERTA! MALAURADAMENT PLUTO HA DEIXAT DE SER UN PLANETA");
		System.out.print("Confirma la seua eliminacio escrivint el seu nom: ");
		String nomPlaneta = sc.nextLine();
		coleccio.deleteOne(Filters.eq("nomPlaneta", nomPlaneta));
		System.out.println("Esborrat satisfactoriament! Ara pluto es un planeta enano :(");
	} // end-esborrarPlaneta
	
	public static void actualitzaPlaneta(MongoDatabase db, MongoCollection<Document> coleccio) {
		System.out.println("> S'HA ACTUALITZAT LA COLECCIO AL TROBAR-SE UNA NOVA LLUNA A JUPITER");
		MongoCursor<Document> cursor = coleccio.find(Filters.eq("nomPlaneta", "Jupiter")).iterator();
		JSONObject llunes = new JSONObject(cursor.next().toJson());
		int llunesJupiter = llunes.getInt("llunes");
		
		coleccio.updateOne(Filters.eq("nomPlaneta", "Jupiter"), 
				new Document("$set", new Document("llunes", llunesJupiter+1)));
		
	} // end-actualitzaPlaneta
	
	public static void tempsVoltaSol(MongoDatabase db, MongoCollection<Document> coleccio) {
		MongoCursor<Document> cursor = coleccio.find().iterator();
		while(cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			System.out.println(obj.getString("nomPlaneta")+" | "+obj.getInt("periodeOrbital")/365);
		}
	} // end-tempsVoltaSol

	public static void main(String[] args) {
		// SENTENCIES NECESSARIES PER A LA CONEXIO DE MONGODB
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDb = mongoClient.getDatabase("SistemaSolar");
		MongoCollection<Document> coleccio = mongoDb.getCollection("Planetes");
		
		//mostrarPlanetes(mongoDb, coleccio);
		//mostrarPlaneta(mongoDb, coleccio);
		//planetesAmbMesGravetat(mongoDb, coleccio);
		//esborrarPlaneta(mongoDb, coleccio);
		//actualitzaPlaneta(mongoDb, coleccio);
		//tempsVoltaSol(mongoDb, coleccio);

		mongoClient.close(); // finalitzem conexio a mongodb
	}
}
