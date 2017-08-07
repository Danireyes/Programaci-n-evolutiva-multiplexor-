package model.population;

public class FunctionOr extends Function {
	
	static FunctionOr or = null;
	
	public static Function getInstance() {
		if (or == null) {
			or = new FunctionOr();
		}
		return or;
	}
	
	@Override
	public Terminal calculate(Terminal op1, Terminal op2) {
		return new Terminal(op1.getValue() || op2.getValue());
	}
	
	@Override
	public String toString() {
		return " (OR ";
	}
	
}
