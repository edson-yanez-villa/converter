package businessLogic;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Temperature extends Unit {
private HashMap<String, Double> rates;
	
	public Temperature(String initial) {
		super(initial);
		this.rates = getRates(initials, "temperatura.json");
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
