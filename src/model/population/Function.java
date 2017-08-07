package model.population;

public abstract class Function extends Gene {
	
	public abstract Terminal calculate(Terminal op1, Terminal op2);
	
	public abstract String toString();
	
	
	@Override
	public Gene cloneGene() {
		return null;
	}
}
