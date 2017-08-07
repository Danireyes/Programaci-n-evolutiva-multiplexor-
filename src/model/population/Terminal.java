package model.population;

public class Terminal extends Gene{
	
	private boolean value;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Terminal (String name){
		this.name = name;
	}
	public Terminal(boolean v) {
		value = v;
	}
	
	public Terminal(Terminal t) {
		this.value = t.getValue();
		this.name = t.getName();
	}

	public boolean getValue() {
		return value;
	}
	
	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public Gene cloneGene() {
		return new Terminal(this);
	}

	@Override
	public String toString() {
		return name;
	}
	
	public boolean equals(Terminal t){
		return this.getValue()== t.getValue();
	}
	
}
