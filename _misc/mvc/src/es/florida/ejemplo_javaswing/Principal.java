/**
 * 
 */
package es.florida.ejemplo_javaswing;

/**
 * @author Eduardo Rua Chamorro
 *
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);
		
	} // end-main
} // end-class
