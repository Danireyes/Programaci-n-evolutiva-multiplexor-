package model.population;


public abstract class Gene {//un gen puede ser tanto una funcion como un terminal.
	
	public abstract Gene cloneGene();
	
	public abstract String toString();
	
	public boolean equals (Gene g) {
		if (g.getClass().isInstance(this) && g.getClass() != Terminal.class)
			return true;
		else if (g.getClass().isInstance(this) && g.getClass() == Terminal.class){
			Terminal g1 = (Terminal) this;
			Terminal g2 = (Terminal) g;
			if (g1.getValue() == g2.getValue())
				return true;
		}
		return false;
		
	}
}
