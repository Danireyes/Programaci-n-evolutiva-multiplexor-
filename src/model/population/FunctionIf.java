package model.population;

public class FunctionIf extends Function{
	
	static FunctionIf iff = null;

	public static Function getInstance() {
		if (iff == null) {
			iff = new FunctionIf();
		}
		return iff;
	}
	
	@Override
	public Terminal calculate(Terminal op1, Terminal op2) {
		
		return null;
	}
	
	public Terminal calculate(Terminal op1, Terminal op2, Terminal op3) {
		if (op1.getValue())
			return new Terminal(op2.getValue());
		
		return new Terminal(op3.getValue());
	}
	
	@Override
	public String toString() {
		return " (IF ";
	}
	
}
