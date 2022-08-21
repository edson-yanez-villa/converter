
public abstract class Unit {
	protected String initials;
	
	public Unit(String initials) {
		this.initials = initials;
	}
	
	public abstract double convertValue(double value, String initial);
}
