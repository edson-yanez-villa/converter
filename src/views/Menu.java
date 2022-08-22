package views;


import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.Currency;
import businessLogic.Unit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Menu extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnCancel;
	private JComboBox<String> comboBox;
	private JButton btnOk;
	private List<String> options;

	public Menu(List<String> options) {
		this.options = options;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 255, 142);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Seleccione una opción de conversión");
		title.setBounds(10, 11, 219, 14);
		contentPane.add(title);
		
		comboBox = new JComboBox<String>(new Vector<String>(this.options));
		comboBox.setBounds(10, 36, 219, 22);
		contentPane.add(comboBox);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		btnOk.setBounds(20, 69, 89, 23);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(125, 69, 89, 23);
		contentPane.add(btnCancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		Object source = e.getSource();
		if(source == btnCancel) {
			Continue continueFrame = new Continue(this);
			continueFrame.setVisible(true);
		}
		if(source == btnOk) {
			generateUnit(e);
		}
	}
	
	private void generateUnit(ActionEvent e) {
		Unit unit;
		String dialogue = "";
		String option = (String) comboBox.getSelectedItem();
		double value = 0;
		if(option.equals("Conversor de Monedas")) {
			dialogue = "Ingresa la cantidad de dinero que deseas convertir:";
		}
		String input = JOptionPane.showInputDialog(dialogue);
		if(input == null){
			   this.setVisible(true);
		}
		value = getValueInput(input, e);
	}
	
	private double getValueInput(String input, ActionEvent e) {
		double value = 0;
		try {
			value = Double.parseDouble(input);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Valor no Válido");
			this.actionPerformed(e);
		}
		return value;
	}
}
