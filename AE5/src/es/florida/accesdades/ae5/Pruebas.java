package es.florida.accesdades.ae5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pruebas extends JFrame {

	private JPanel contentPane;
	private JTextField txtFTitol;
	private JTextField txtFAutor;
	private JTextField txtFAnyN;
	private JTextField txtFAnyP;
	private JTextField txtFEditorial;
	private JTextField txtFNumPag;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pruebas frame = new Pruebas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pruebas() {
		setTitle("Hibernate - AE5 (Acc\u00E8s a Dades)");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 361);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(3, 131, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtFTitol = new JTextField();
		txtFTitol.setEditable(false);
		txtFTitol.setBounds(124, 111, 397, 28);
		contentPane.add(txtFTitol);
		txtFTitol.setColumns(10);
		
		JLabel tagTitol = new JLabel("Titol");
		tagTitol.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagTitol.setForeground(Color.WHITE);
		tagTitol.setBounds(39, 114, 46, 14);
		contentPane.add(tagTitol);
		
		txtFAutor = new JTextField();
		txtFAutor.setEditable(false);
		txtFAutor.setBounds(124, 154, 397, 28);
		contentPane.add(txtFAutor);
		txtFAutor.setColumns(10);
		
		JLabel tagAutor = new JLabel("Autor");
		tagAutor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagAutor.setForeground(Color.WHITE);
		tagAutor.setBounds(39, 157, 46, 14);
		contentPane.add(tagAutor);
		
		txtFAnyN = new JTextField();
		txtFAnyN.setEditable(false);
		txtFAnyN.setBounds(124, 196, 86, 28);
		contentPane.add(txtFAnyN);
		txtFAnyN.setColumns(10);
		
		JLabel tagAnyN = new JLabel("Any Naixement");
		tagAnyN.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagAnyN.setForeground(Color.WHITE);
		tagAnyN.setBounds(39, 199, 102, 14);
		contentPane.add(tagAnyN);
		
		JLabel tagAnyP = new JLabel("Any Publicaci\u00F3");
		tagAnyP.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagAnyP.setForeground(Color.WHITE);
		tagAnyP.setBounds(220, 199, 86, 14);
		contentPane.add(tagAnyP);
		
		txtFAnyP = new JTextField();
		txtFAnyP.setEditable(false);
		txtFAnyP.setBounds(301, 196, 86, 28);
		contentPane.add(txtFAnyP);
		txtFAnyP.setColumns(10);
		
		txtFEditorial = new JTextField();
		txtFEditorial.setEditable(false);
		txtFEditorial.setBounds(124, 239, 238, 28);
		contentPane.add(txtFEditorial);
		txtFEditorial.setColumns(10);
		
		JLabel tagEditorial = new JLabel("Editorial");
		tagEditorial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagEditorial.setForeground(Color.WHITE);
		tagEditorial.setBounds(39, 242, 46, 14);
		contentPane.add(tagEditorial);
		
		JLabel tagNumPag = new JLabel("N\u00BA p\u00E0gines");
		tagNumPag.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagNumPag.setForeground(Color.WHITE);
		tagNumPag.setBounds(382, 242, 67, 14);
		contentPane.add(tagNumPag);
		
		txtFNumPag = new JTextField();
		txtFNumPag.setEditable(false);
		txtFNumPag.setBounds(449, 239, 72, 28);
		contentPane.add(txtFNumPag);
		txtFNumPag.setColumns(10);
		
		JLabel tagDialog = new JLabel("Sistema de creaci\u00F3 de llibres");
		tagDialog.setHorizontalAlignment(SwingConstants.CENTER);
		tagDialog.setForeground(Color.WHITE);
		tagDialog.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		tagDialog.setBounds(150, 11, 268, 28);
		contentPane.add(tagDialog);
		
		JButton tagGenerarInfo = new JButton("Crear llibre");
		tagGenerarInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tagGenerarInfo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tagGenerarInfo.setBounds(220, 63, 124, 28);
		contentPane.add(tagGenerarInfo);
	}
}
