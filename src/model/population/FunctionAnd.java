package model.population;

public class FunctionAnd extends Function {
	
	 static FunctionAnd and = null;
	
	public static Function getInstance() {
		if (and == null) {
			and = new FunctionAnd();
		}
		return and;
	}
	@Override
	public Terminal calculate(Terminal op1, Terminal op2) {
		return new Terminal(op1.getValue() && op2.getValue());
	}

	@Override
	public String toString() {
		return " (AND ";
	}

}
