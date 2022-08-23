package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import businessLogic.Unit;
import dataAccess.Reader;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;

public class Currencies extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JComboBox<String> comboBox;
	private JButton btnOk;
	private JButton btnCancel;
	
	private Unit unit;
	
	
	public Currencies(double value, String option, Menu menu) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		List<String> conversors = getListOfConvertions(option);
		setBounds(100, 100, 342, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Elije la moneda en la que deseas convertir tu dinero:");
		lblNewLabel.setBounds(10, 11, 328, 14);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox<String>(new Vector<String>(conversors));
		comboBox.setBounds(10, 36, 306, 22);
		contentPane.add(comboBox);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(47, 78, 89, 23);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(176, 78, 89, 23);
		contentPane.add(btnCancel);
	}
	
	private List<String> getListOfConvertions(String option){
		List<String> list;
		switch (option) {
			case "Conversor de Monedas": {
				list = getRates(Reader("rates.json")); 
				break;
			}
			default:
				list = new ArrayList<String>();
		}
		return list;
	}
	
	private List<String> getRates(JSONObject json){
		List<String> list = new ArrayList<>();
		for(Object key : json.keySet()) {
			JSONObject value = (JSONObject) json.get(key.toString());
			String baseName = value.get("name").toString();
			JSONObject rates = (JSONObject) value.get("rates");
			for(Object keyRate : json.keySet()) {
				JSONObject rate = (JSONObject) value.get(keyRate);
				list.add(baseName + " a " + rate.get("name").toString());
			}
		}
		return list;
	}

	private JSONObject Reader(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
