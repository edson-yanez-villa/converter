package businessLogic;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.json.simple.JSONObject;

import dataAccess.Reader;


public class Currency extends Unit {
	private HashMap<String, Double> rates;
	
	public Currency(String initial) {
		super(initial);
		this.initials = initial;
		this.rates = getRates(initials);
	}
	
	public HashMap<String, Double> getRates(String initials){
		Reader reader = new Reader("rates.json");
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
	
	@Override
	public double convertValue(double value, String initial) {
		DecimalFormat df = new DecimalFormat("#.##");
		double result = 0.0;
		if(this.rates.containsKey(initial)) {
			result = value / rates.get(initial);
		}
		return Double.valueOf(df.format(result));
	}
	
}
