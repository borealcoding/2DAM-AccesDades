/**
 * @author Eduardo Rua Chamorro - 2ºDAM | Florida Universitaria
 * @version 2.0 - AE2 | 4.10.21
 * @description Es demana desenvolupar una xicoteta aplicacio amb interficie grafica seguint un patro d'arquitectura MVC
 * L'aplicacio haura de tenir les seguents funcionalitats:
 * 	- Mostrar un text en la interficie grafica obtingut a partir d'un fitxer TXT que es proporcionara per a l'activitat
 * 	- Funció Buscar: Buscar una paraula en el text i contar quantes vegades apareix. La paraula es proporcionarà a través de la interfície gràfica per l’usuari. El número de vegades que apareix la paraula es pot mostrar com una finestra de tipus popup (popup message) o dins del mateix camp de text on s’introdueix la paraula a buscar.
 * 	-  Funció Reemplaçar: Reemplaçar en el text la paraula buscada per una altra especificada en un altre camp de text. El text resultant es mostrarà en un altre textArea de la interfície i es guardarà automàticament com un fitxer nou al mateix directori on està el fitxer original.
 */
package es.florida.AD_AE02;

import java.io.IOException;

/**
 * @author edvard
 *
 */
public class Principal {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Modelo model = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(model, vista);
	}
}
