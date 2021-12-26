package es.florida.accesDades.ae6;
/*
 * @author Eduardo Rua Chamorro | 2. DAM - Florida Universitaria
 * @version [actividad] | [modulo]
 * @description [...]
 * */
// IMPORTACIO DE LLIBRERIES
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class VistaTemporal extends JFrame {
	// DECLARACIONS GENERALS
	private JPanel contentPane;
	// DECLARACIONS STATIC (AQUESTES VARIABLES S'USEN EN DIVERSOS METODES)
	
	public VistaTemporal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaTemporal.class.getResource("/img/mongodbIcon.png")));
		visualitzar();
	} // end-constructor
	
	/*
	 * METODE: 
	 * PARAMETRES:
	 * DEFINICIO:  
	 * */

	public void visualitzar() {
		setTitle("AE6 - MongoDB");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 445);
		contentPane = new JPanel() {  
			 public void paintComponent(Graphics g) {  
			          Image img = Toolkit.getDefaultToolkit().getImage(  
			        		  VistaTemporal.class.getResource("/img/appBackground3.jpg"));  
			          g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
			     }  
			};
		contentPane.setBackground(new Color(2, 52, 48));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel tagTitol = new JLabel("Men\u00FA d'opcions de MongoDB");
		tagTitol.setForeground(Color.WHITE);
		tagTitol.setHorizontalAlignment(SwingConstants.CENTER);
		tagTitol.setFont(new Font("Book Antiqua", Font.PLAIN, 23));
		tagTitol.setBounds(32, 6, 434, 43);
		contentPane.add(tagTitol);
		
		JLabel tagAutor = new JLabel("Alumne: Eduardo Ru\u00E1 Chamorro");
		tagAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		tagAutor.setForeground(Color.WHITE);
		tagAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagAutor.setBounds(300, 366, 176, 23);
		contentPane.add(tagAutor);

		JButton btnMostrarBiblioteca = new JButton("Mostrar la biblioteca");
		btnMostrarBiblioteca.setBackground(new Color(50, 205, 50));
		btnMostrarBiblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JDialog jd = new JDialog();
				jd.setBounds(0,0,100,100);
				jd.setLocationRelativeTo(null);
				jd.setVisible(true);
			} // end-actionPerformed
		});
		btnMostrarBiblioteca.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnMostrarBiblioteca.setBackground(new Color(239, 239, 239));
		btnMostrarBiblioteca.setBorder(null);
		btnMostrarBiblioteca.setFocusable(false);
		btnMostrarBiblioteca.setBounds(174, 78, 147, 37);
		contentPane.add(btnMostrarBiblioteca);

		JButton btnMostrarLlibre = new JButton("Llegir un llibre");
		btnMostrarLlibre.setBackground(new Color(50, 205, 50));
		btnMostrarLlibre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog jd = new JDialog();
				jd.setBounds(0,0,100,100);
				jd.setLocationRelativeTo(null);
				jd.setVisible(true);	
			} // end-actionPerformed
		});
		btnMostrarLlibre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnMostrarLlibre.setBackground(new Color(239, 239, 239));
		btnMostrarLlibre.setBorder(null);
		btnMostrarLlibre.setFocusable(false);
		btnMostrarLlibre.setBounds(174, 132, 147, 37);
		contentPane.add(btnMostrarLlibre);

		JButton btnAfegir = new JButton("Afegir nou llibre");
		btnAfegir.setBackground(new Color(50, 205, 50));
		btnAfegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog jd = new JDialog();
				jd.setBounds(0,0,100,100);
				jd.setLocationRelativeTo(null);
				jd.setVisible(true);
			} // end-actionPerformed
		});
		btnAfegir.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAfegir.setBackground(new Color(239, 239, 239));
		btnAfegir.setBorder(null);
		btnAfegir.setFocusable(false);
		btnAfegir.setBounds(174, 184, 147, 37);
		contentPane.add(btnAfegir);
		
		JButton btnModificar = new JButton("Modificar llibre existent");
		btnModificar.setBackground(new Color(50, 205, 50));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog jd = new JDialog();
				jd.setBounds(0,0,100,100);
				jd.setLocationRelativeTo(null);
				jd.setVisible(true);
			} // end-actionPerformed
		});
		btnModificar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnModificar.setBackground(new Color(239, 239, 239));
		btnModificar.setBorder(null);
		btnModificar.setFocusable(false);
		btnModificar.setBounds(174, 237, 147, 37);
		contentPane.add(btnModificar);

		JButton btnEsborrar = new JButton("Esborrar llibre");
		btnEsborrar.setBackground(new Color(50, 205, 50));
		btnEsborrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog jd = new JDialog();
				jd.setBounds(0,0,100,100);
				jd.setLocationRelativeTo(null);
				jd.setVisible(true);
			} // end-actionPerformed
		});
		btnEsborrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEsborrar.setBackground(new Color(239, 239, 239));
		btnEsborrar.setBorder(null);
		btnEsborrar.setFocusable(false);
		btnEsborrar.setBounds(174, 290, 147, 37);
		contentPane.add(btnEsborrar);
	} // end-visualitzar
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Set cross-platform Java L&F (also called "Nimbus")
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end-try-catch
		VistaTemporal frame = new VistaTemporal();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	} // end-main
} // end-class