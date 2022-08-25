package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import businessLogic.Currency;
import businessLogic.Temperature;
import businessLogic.Unit;
import dataAccess.Reader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;

public class Conversor extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JComboBox<String> comboBox;
	private JButton btnOk;
	private JButton btnCancel;
	private JFrame frame;
	
	private HashMap<String, List<String>> conversors;
	
	private double value;
	private String option;
	
	
	public Conversor(double value, String option, JFrame frame) {
		setTitle("Unidades");
		setResizable(false);
		this.frame = frame;
		this.value = value;
		this.option = option;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		conversors = getListOfConvertions(option);
		setBounds(100, 100, 342, 151);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(getStringType(option));
		lblNewLabel.setBounds(10, 11, 328, 14);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox<String>(new Vector<String>(conversors.keySet()));
		comboBox.setBounds(10, 36, 306, 22);
		contentPane.add(comboBox);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(47, 78, 89, 23);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(176, 78, 89, 23);
		contentPane.add(btnCancel);
	}
	
	private String getStringType(String option) {
		switch (option) {
			case "Conversor de Monedas": {
				return "Elije la moneda en la que deseas convertir tu dinero:";
			}
			case "Conversor de Temperatura": {
				return "Elije la unidad de temperatura a convertir: ";
			}
			default:
				return "";
		}
		
	}
	
	private HashMap<String, List<String>> getListOfConvertions(String option){
		HashMap<String, List<String>> list;
		switch (option) {
			case "Conversor de Monedas": {
				list = getRates(new Reader("rates.json")); 
				break;
			}
			case "Conversor de Temperatura": {
				list = getRates(new Reader("temperatura.json")); 
				break;
			}
			default:
				list = new HashMap<>();
		}
		return list;
	}
	
	private HashMap<String, List<String>> getRates(Reader reader){
		HashMap<String, List<String>> list = new HashMap<>();
		JSONObject content = reader.getJson();
		for(Object key : content.keySet()) {
			JSONObject jsonCurrency = (JSONObject) content.get(key.toString());
			String baseName = jsonCurrency.get("name").toString();
			JSONObject rates = (JSONObject) jsonCurrency.get("rates");
			for(Object keyRate : rates.keySet()) {
				JSONObject rate = (JSONObject) rates.get(keyRate);
				List<String> currencies = new ArrayList<>();
				currencies.add(key.toString());
				currencies.add(keyRate.toString());
				currencies.add(rate.get("name").toString());
				list.put(baseName + " a " + rate.get("name").toString(), currencies);
			}
		}
		return list;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		this.setVisible(false);
		Object source = e.getSource();
		if(source == btnCancel) {
			Continue continueFrame = new Continue(this);
			continueFrame.setLocationRelativeTo(null);
			continueFrame.setVisible(true);
		}
		if(source == btnOk) {
			String value = getConvertion((String) comboBox.getSelectedItem());
			JOptionPane.showMessageDialog(null, value);
			Continue continueFrame = new Continue(frame);
			continueFrame.setLocationRelativeTo(null);
			continueFrame.setVisible(true);
		}
	}
	
	
	private String getConvertion(String selectItem){
		List<String> currencies = conversors.get(selectItem);
		Unit unit = getUnit(currencies.get(0), this.option);
		if(unit != null && unit instanceof Currency) {
			return "Tienes $ " + String.valueOf(unit.convertValue(this.value, currencies.get(1))) + " " + currencies.get(2);
		}
		if(unit != null && unit instanceof Temperature) {
			return "Temperatura: " + String.valueOf(unit.convertValue(this.value, currencies.get(1))) + " °" + currencies.get(2);
		}
		return "";
	}
	
	private Unit getUnit(String initial, String option) {
		switch (option) {
			case "Conversor de Monedas": {
				return new Currency(initial);
			}
			case "Conversor de Temperatura": {
				return new Temperature(initial);
			}
			default:
				return null;
		}
	}
	
	
}
