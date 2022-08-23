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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Menu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		String option = (String) comboBox.getSelectedItem();
		String dialogue = getDialogue(option);
		String input = JOptionPane.showInputDialog(dialogue);
		sendValueInput(input, option, e);
	}
	
	private String getDialogue(String option) {
		switch (option) {
		case "Conversor de Monedas": {
			return "Ingresa la cantidad de dinero que deseas convertir:";
		}
		default:
			return "";
		}
	}
	
	private void sendValueInput(String input, String option, ActionEvent e) {
	    if (input != null){
	    	try {
	    		double value = 0;
				value = Double.parseDouble(input);
				Currencies currencies = new Currencies(value, option, this);
				currencies.setVisible(true);
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Valor no Válido");
				this.actionPerformed(e);
			}
	    }
	    else{
	    	this.setVisible(true);
	    }
	}
}
