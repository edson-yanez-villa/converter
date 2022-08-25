package businessLogic;

import java.util.HashMap;

import org.json.simple.JSONObject;

import dataAccess.Reader;

public abstract class Unit {
	protected String initials;
	
	public Unit(String initials) {
		this.initials = initials;
	}
	
	protected HashMap<String, Double> getRates(String initials, String file){
		Reader reader = new Reader(file);
		HashMap<String, Double> rates = new HashMap<String, Double>();
		JSONObject jsonContent = reader.getJson();
		JSONObject JsonCurrency = (JSONObject) jsonContent.get(initials);
		JSONObject jsonRates = (JSONObject) JsonCurrency.get("rates");
		for(Object key : jsonRates.keySet()) {
			JSONObject value = (JSONObject) jsonRates.get(key.toString());
			rates.put(key.toString(), (double)value.get("rate"));
		}
		return rates;
	}
	
	public abstract double convertValue(double value, String initial);
}
