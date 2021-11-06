package es.florida.accesdades.ae4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JOptionPane;

public class Vista extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
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
	public Vista() {
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 425);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Men\u00FA de consultes SQL");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(220, 11, 224, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Consultes predefinides");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(202, 58, 256, 30);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Mostra tot el contingut de LLIBRES");
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(), "Hello there! How are you today?");
			}
		});
		btnNewButton.setBounds(220, 99, 224, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Editorials que hagen publicat almenys un llibre en el segle XXI");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(), "Hello there! How are you today?");
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_1.setBounds(158, 181, 355, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Llibres (titol, autor i any de publicacio) dels autors nascuts abans de 1950");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(), "Hello there! How are you today?");
			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_2.setBounds(131, 140, 411, 30);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Escriu la consulta que vullges fer");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(195, 233, 278, 25);
		contentPane.add(lblNewLabel_2_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textArea.setLineWrap(true);
		textArea.setBounds(131, 269, 411, 63);
		contentPane.add(textArea);
		
		JButton btnNewButton_3 = new JButton("Executar consulta");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(), "Hello there! How are you today?");
			}
		});
		btnNewButton_3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_3.setBounds(266, 343, 134, 23);
		contentPane.add(btnNewButton_3);
	}
}
