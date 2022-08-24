import java.util.ArrayList;
import java.util.List;

import views.Menu;

public class App {

	public static void main(String[] args) {
		List<String> options = new ArrayList<String>();
		options.add("Conversor de Monedas");
		Menu frame = new Menu(options);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
