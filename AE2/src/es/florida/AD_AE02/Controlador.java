package es.florida.AD_AE02;

import java.awt.event.*;
import java.util.ArrayList;

public class Controlador {
	private Modelo model;
	private Vista vista;
	private ActionListener eventoBuscar, eventoReemplazar;
	private String fitxerLectura, fitxerEscriptura;


	public Controlador(Modelo model, Vista vista) {
		this.model = model;
		this.vista = vista;
		controlar();
		
	}
	
	public void controlar() {
		this.fitxerLectura = model.getFitxerLectura();
		this.fitxerEscriptura = model.getFitxerEscriptura();
		mostrarFitxer(fitxerLectura, 1);
		buscarParaula();
		reemplazarParaula();
	}
	
	public void mostrarFitxer(String fitxer, int numTxtArea) {
		ArrayList<String> llistatText = model.mostrarContingut(fitxer);
		for(String linea : llistatText) {
			if(numTxtArea == 1)
				vista.getTextAreaOriginal().append(linea+"\n");
			else
				vista.getTextAreaModificado().append(linea+"\n");
		}
	}
	
	public void buscarParaula() {
		eventoBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent actEv) {
				String paraula = vista.getTextFieldBuscar().getText();
				model.buscar(paraula);
			} // end-actionPerformed
		}; // end-eventoReemplazar
		vista.getBtnBuscar().addActionListener(eventoBuscar);
	} // end-buscarParaula
	
	public void reemplazarParaula() {
		eventoReemplazar = new ActionListener() {
			public void actionPerformed(ActionEvent actEv) {
				String paraulaTrobada = vista.getTextFieldBuscar().getText();
				String paraulaNova = vista.getTextFieldReemplazar().getText();
				model.reemplazar(paraulaTrobada, paraulaNova);
				mostrarFitxer(fitxerEscriptura, 2);
			} // end-actionPerformed
		}; // end-eventoReemplazar
		vista.getBtnReemplazar().addActionListener(eventoReemplazar);
	} // end-buscarParaula
}