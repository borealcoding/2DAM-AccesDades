package es.florida.accesdades.ae5;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Pruebas extends JFrame {

	private JPanel contentPane;
	public JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public Pruebas() {
		visualitzar2();
		
	}
	
	public void visualitzar2() {
		setTitle("Hibernate - AE5 (Acc\u00E8s a Dades)");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 454);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(3, 131, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	

        JLabel totsTitols = new JLabel("Tots el titols de la biblioteca");
        totsTitols.setBounds(24, 30, 634, 36);
        totsTitols.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
        totsTitols.setForeground(Color.WHITE);
        totsTitols.setHorizontalAlignment(SwingConstants.CENTER);
        Border margin = new EmptyBorder(20, 0, 10, 0);
        Border border = totsTitols.getBorder();
        contentPane.setLayout(null);
        totsTitols.setBorder(new CompoundBorder(border, margin));
        contentPane.add(totsTitols);

        textArea = new JTextArea();
        textArea.setBounds(1, 438, 563, 695);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBorder(null);
        textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(), BorderFactory.createEmptyBorder(15, 15, 5, 5)));
        textArea.setVisible(true);
        textArea.setText("");
        contentPane.add(textArea);

        JScrollPane scrollPane = new JScrollPane (textArea);
        scrollPane.setBounds(24, 100, 634, 290);
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setVisible(true);
        contentPane.add(scrollPane);
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
}
