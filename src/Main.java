public class Main {

	public static void main(String[] args) {
		Unit currency = new Currency("EUR");
		double result = currency.convertValue(10, "USD");
		System.out.println(result);
	}

}
