package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Currencies extends JFrame {

	private JPanel contentPane;
	
	public Currencies(double value) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Elije la moneda en la que deseas convertir tu dinero:");
		lblNewLabel.setBounds(10, 11, 328, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 306, 22);
		contentPane.add(comboBox);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(47, 78, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(176, 78, 89, 23);
		contentPane.add(btnCancel);
	}
}
