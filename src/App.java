import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import businessLogic.Currency;
import businessLogic.Unit;
import views.Menu;

public class App {

	public static void main(String[] args) {
		Unit currency = new Currency("EUR");
		double result = currency.convertValue(10, "USD");
		System.out.println(result);
		List<String> options = new ArrayList<String>();
		options.add("Conversor de Monedas");
		Menu frame = new Menu(options);
		frame.setVisible(true);
	}
}
