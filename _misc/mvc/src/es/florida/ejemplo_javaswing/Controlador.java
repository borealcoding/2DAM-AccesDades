/**
 * 
 */
package es.florida.ejemplo_javaswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Eduardo Rua Chamorro
 *
 */
public class Controlador {
	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerBoton1, actionListenerBoton2;
	
	Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}
	
	public void control() {
		actionListenerBoton1 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String mensaje1 = modelo.getMensaje1();
				vista.mostrarMensaje(mensaje1);
			} // end-actionPerformed()
		}; // end-ActionListener()
		vista.getBtn1().addActionListener(actionListenerBoton1);
		
		actionListenerBoton2 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String mensaje2 = modelo.getMensaje2();
				vista.mostrarMensaje(mensaje2);
			} // end-actionPerformed()
		}; // end-ActionListener()
		vista.getBtn2().addActionListener(actionListenerBoton2);
	} // end-control()
} // end-class
