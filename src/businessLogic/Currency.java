package businessLogic;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Currency extends Unit {
	private HashMap<String, Double> rates;
	
	public Currency(String initial) {
		super(initial);
		this.initials = initial;
		this.rates = getRates(initials);
	}
	
	private  HashMap<String, Double> getRates(String initials){
		HashMap<String, Double> rates = new HashMap<String, Double>();
		JSONObject jsonRates = (JSONObject) getJson().get("rates");
		for(Object key : jsonRates.keySet()) {
			double value = (double)jsonRates.get(key.toString());
			rates.put(key.toString(), value);
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
	
	private JSONObject getJson() {
		JSONParser parser = new JSONParser();
		JSONObject jsonContent = null;
		try {
			Object obj = parser.parse(new FileReader("rates.json"));
			JSONObject jsonObject = (JSONObject) obj;
			jsonContent = (JSONObject) jsonObject.get(initials);
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (ParseException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
		
		return jsonContent;
	}
}
