package model.population;

public class FunctionNot extends Function{
	
	static FunctionNot not = null;
	
	public static Function getInstance() {
		if (not == null) {
			not = new FunctionNot();
		}
		return not;
	}
	
	@Override
	public Terminal calculate(Terminal op1, Terminal op2) {
		
		return null;
	}
	
	public Terminal calculate(Terminal op1) {
		return new Terminal(!op1.getValue());
	}
	
	@Override
	public String toString() {
		return " (NOT ";
	}
}
