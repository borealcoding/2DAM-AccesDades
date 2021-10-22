package es.florida.ejemplo_javaswing;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Vista {

	private JFrame frame;
	private JButton btn1, btn2;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btn1 = new JButton("Boton 1");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn1.setBounds(109,52,196,43);
		frame.getContentPane().add(btn1);
		
		btn2 = new JButton("Boton 2");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn2.setBounds(109,117,196,43);
		frame.getContentPane().add(btn2);
		
		btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(189, 198, 47, 23);
		frame.getContentPane().add(btnNewButton);
		
		frame.setTitle("Hello World");
		frame.setVisible(true);
	}
	
	public JButton getBtn1() {
		return btn1;
	} // end-getBtn1
	public JButton getBtn2() {
		return btn2;
	} // end-getBtn2
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}
}
