package model.population;

import java.util.Random;

public class Chromosome{
	private TreeNode<Gene> genes;
	private int typesOfGenes = 5;
	private double fitness;
	private Terminal a0;
	private Terminal a1;
	private Terminal a2;
	private  Terminal d0;
	private  Terminal d1;
	private  Terminal d2;
	private  Terminal d3;
	private  Terminal d4;
	private  Terminal d5;
	private  Terminal d6;
	private  Terminal d7;
	
	
	public Chromosome (Random rnd, int depthChromosome, boolean useIf, String initializationType,Terminal[] lista) {
		//inicializamos los terminales
		a0 = lista[0];
		a1 = lista[1];
		a2 = lista[2];
		d0 = lista[3];
		d1 = lista[4];
		d2 = lista[5]; 
		d3 = lista[6];
		d4 = lista[7]; 
		d5 = lista[8]; 
		d6 = lista[9]; 
		d7 = lista[10]; 
		if (!useIf)//si no se usa el if tenemos un tipo de gen menos
			typesOfGenes--;
		if (initializationType.equals("Inicializacion completa"))//inicializamos el cromosoma dependiendo del tipo de inicializacion.
			genes = createGeneCI(rnd, depthChromosome);
		else if(initializationType.equals("Inicializacion creciente"))
			genes = createGeneGI(rnd, depthChromosome,false);	
	}
	
	public Chromosome (Chromosome ch) {// esto sirve para clonar el objeto
		typesOfGenes = ch.getTypesOfGenes();
		genes = ch.getGenes().clone();		
		a0 = ch.getA0();
		a1 = ch.getA1();
		a2 = ch.getA2();
		d0 = ch.getD0();
		d1 = ch.getD1();
		d2 = ch.getD2();
		d3 = ch.getD3();
		d4 = ch.getD4();
		d5 = ch.getD5();
		d6 = ch.getD6();
		d7 = ch.getD7();
		fitness = ch.getFitness();
	}
	


	@Override
	public Chromosome clone() {
		return new Chromosome(this);
	}
	
	public boolean equals(Chromosome ch) {
		return ((typesOfGenes == ch.getTypesOfGenes()) && (genes.equals(ch.getGenes())));
	}
	
	private TreeNode<Gene> createGeneCI(Random rnd, int remainingDepth) {
		if (remainingDepth > 0) {// si todavia hay espacio metemos funciones.
			int election = (Math.abs(rnd.nextInt()) % (typesOfGenes-1));//elegimos que tipo de gen es.
			TreeNode<Gene> arbol;
			switch (election) {//aleatoriamente el gen puede ser AND, OR, NOT o IF.
			case 0: 
				arbol = new TreeNode<Gene>(FunctionAnd.getInstance());
				arbol.addChild(createGeneCI(rnd,remainingDepth-1));
				arbol.addChild(createGeneCI(rnd,remainingDepth-1));
				return arbol;
			case 1:
				arbol = new TreeNode<Gene>(FunctionOr.getInstance());
				arbol.addChild(createGeneCI(rnd,remainingDepth-1));
				arbol.addChild(createGeneCI(rnd,remainingDepth-1));
				return arbol;
			case 2:
				arbol = new TreeNode<Gene>(FunctionNot.getInstance());
				arbol.addChild(createGeneCI(rnd,remainingDepth-1));
				return arbol;
			case 3:
				arbol = new TreeNode<Gene>( FunctionIf.getInstance());
				arbol.addChild(createGeneCI(rnd,remainingDepth-1));
				arbol.addChild(createGeneCI(rnd,remainingDepth-1));
				arbol.addChild(createGeneCI(rnd,remainingDepth-1));
				return arbol;
			}
		}
		else {// si hemos llegado al final metemos los terminales.
			int election = Math.abs(rnd.nextInt())% 11;
			switch (election) {
			case 0:
				return new TreeNode<Gene>(a0);
			case 1:
				return new TreeNode<Gene>(a1);
			case 2:
				return new TreeNode<Gene>(a2);
			case 3:
				return new TreeNode<Gene>(d0);
			case 4:
				return new TreeNode<Gene>(d1);
			case 5:
				return new TreeNode<Gene>(d2);
			case 6:
				return new TreeNode<Gene>(d3);
			case 7:
				return new TreeNode<Gene>(d4);
			case 8:
				return new TreeNode<Gene>(d5);
			case 9:
				return new TreeNode<Gene>(d6);
			case 10:
				return new TreeNode<Gene>(d7);
			}
		}
		return null;
	}
	
	private TreeNode<Gene> createGeneGI(Random rnd, int remainingDepth, boolean rootInserted) {
		if (remainingDepth > 0) {//si aun hay profundidad.
			int election;
			election = (Math.abs(rnd.nextInt()) % typesOfGenes);//elegimos que tipo de gen es si terminal o función (y que tipo de función).
			TreeNode<Gene> arbol;
			switch (election) {//elegimos el tipo de gen
			case 0:
				if (!rootInserted) {//un terminal no puede ser la raíz del arbol.
					election = Math.abs(rnd.nextInt())% 11;
					switch (election) {
					case 0:
						return new TreeNode<Gene>(a0);
					case 1:
						return new TreeNode<Gene>(a1);
					case 2:
						return new TreeNode<Gene>(a2);
					case 3:
						return new TreeNode<Gene>(d0);
					case 4:
						return new TreeNode<Gene>(d1);
					case 5:
						return new TreeNode<Gene>(d2);
					case 6:
						return new TreeNode<Gene>(d3);
					case 7:
						return new TreeNode<Gene>(d4);
					case 8:
						return new TreeNode<Gene>(d5);
					case 9:
						return new TreeNode<Gene>(d6);
					case 10:
						return new TreeNode<Gene>(d7);
					}
				}
				else {
					return createGeneGI(rnd,remainingDepth,false);//si no hay raiz volvemos a intentarlo.
				}
			case 1: 
				arbol = new TreeNode<Gene>( FunctionAnd.getInstance());
				arbol.addChild(createGeneGI(rnd,remainingDepth-1,true));
				arbol.addChild(createGeneGI(rnd,remainingDepth-1,true));
				return arbol;
			case 2:
				arbol = new TreeNode<Gene>(FunctionOr.getInstance());
				arbol.addChild(createGeneGI(rnd,remainingDepth-1,true));
				arbol.addChild(createGeneGI(rnd,remainingDepth-1,true));
				return arbol;
			case 3:
				arbol = new TreeNode<Gene>(FunctionNot.getInstance());
				arbol.addChild(createGeneGI(rnd,remainingDepth-1,true));
				return arbol;
			case 4:
				arbol = new TreeNode<Gene>( FunctionIf.getInstance());
				arbol.addChild(createGeneGI(rnd,remainingDepth-1,true));
				arbol.addChild(createGeneGI(rnd,remainingDepth-1,true));
				arbol.addChild(createGeneGI(rnd,remainingDepth-1,true));
				return arbol;
			}
		}
		else {//si no queda profundidad metemos un terminal.
			int election = Math.abs(rnd.nextInt())% 11;
			switch (election) {
			case 0:
				return new TreeNode<Gene>(a0);
			case 1:
				return new TreeNode<Gene>(a1);
			case 2:
				return new TreeNode<Gene>(a2);
			case 3:
				return new TreeNode<Gene>(d0);
			case 4:
				return new TreeNode<Gene>(d1);
			case 5:
				return new TreeNode<Gene>(d2);
			case 6:
				return new TreeNode<Gene>(d3);
			case 7:
				return new TreeNode<Gene>(d4);
			case 8:
				return new TreeNode<Gene>(d5);
			case 9:
				return new TreeNode<Gene>(d6);
			case 10:
				return new TreeNode<Gene>(d7);
			}
		}
		return null;
	}
	
	public int getDepth() {
		return genes.getDepth();
	}
	
	public int getNodes() {
		return genes.getNodes();
	}

	public TreeNode<Gene> getGenes() {
		return genes;
	}

	public void setGenes(TreeNode<Gene> genes) {
		this.genes = genes;
	}
	
	public int getTypesOfGenes() {
		return typesOfGenes;
	}
	
	public void print() {
		System.out.println();
		System.out.println(toString());
	}
	
	@Override
	public String toString(){
		return  toString(this.genes,"");
	}
	
	public String toString(TreeNode<Gene> g, String s){
		if(g == null)
			return s;
		else if(Terminal.class.isInstance(g.data))
			return s + ((Terminal) g.data).toString();
		else if(FunctionAnd.class.isInstance(g.data)) {
			Function f = (FunctionAnd) g.data;
			return s + f.toString() + toString(g.getChildren().get(0), s) + toString(g.getChildren().get(1), s) + ")";
		}
		else if(FunctionOr.class.isInstance(g.data)) {
			Function f = (FunctionOr) g.data;
			return s + f.toString() + toString(g.getChildren().get(0), s) + toString(g.getChildren().get(1), s) + ")";
		}
		else if(FunctionNot.class.isInstance(g.data)) {
			FunctionNot fNot = (FunctionNot) g.data;
			return s + fNot.toString() + toString(g.getChildren().get(0), s) + ")";
		}
		else if(FunctionIf.class.isInstance(g.data)) {
			FunctionIf fIf = (FunctionIf) g.data;
			return s + fIf.toString() + toString(g.getChildren().get(0), s) + toString(g.getChildren().get(1), s) + toString(g.getChildren().get(2), s) + ")";
		}
		return s;
	}
	
	//recorremos el arbol ejecutando sus nodo.
	public Terminal calculate(TreeNode<Gene> g) {
		if(g == null)
			return null;
		else if(Terminal.class.isInstance(g.data))
			return (Terminal) g.data;//retornamos el valor del terminal.
		else if(FunctionAnd.class.isInstance(g.data)) {
			Function f = (FunctionAnd) g.data;
			//retornamos el resultado de la funcion AND.
			return f.calculate(this.calculate(g.getChildren().get(0)), this.calculate(g.getChildren().get(1)));
		}
		else if(FunctionOr.class.isInstance(g.data)) {
			Function f = (FunctionOr) g.data;
			//retornamos el resultado de la funcion OR.
			return f.calculate(this.calculate(g.getChildren().get(0)), this.calculate(g.getChildren().get(1)));
		}
		else if(FunctionNot.class.isInstance(g.data)) {
			FunctionNot fNot = (FunctionNot) g.data;
			//retornamos el resultado de la funcion NOT.
			return fNot.calculate(this.calculate(g.getChildren().get(0)));
		}
		else if(FunctionIf.class.isInstance(g.data)) {
			FunctionIf fIf = (FunctionIf) g.data;
			//retornamos el resultado de la funcion IF.
			return fIf.calculate(this.calculate(g.getChildren().get(0)), this.calculate(g.getChildren().get(1)), this.calculate(g.getChildren().get(2)));
		}
		return null;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double d) {
		this.fitness = d;
	}
	public Terminal getA0() {
		return a0;
	}

	public void setA0(boolean a0) {
		this.a0.setValue(a0);
	}

	public Terminal getA1() {
		return a1;
	}

	public void setA1(boolean a1) {
		this.a1.setValue(a1);
	}
	
	public Terminal getA2() {
		return a2;
	}
	
	public void setA2(boolean a2) {
		this.a2.setValue(a2);
	}
	
	public Terminal getD0() {
		return d0;
	}

	public void setD0(boolean d0) {
		this.d0.setValue(d0);
	}

	public Terminal getD1() {
		return d1;
	}

	public void setD1(boolean d1) {
		this.d1.setValue(d1);
	}

	public Terminal getD2() {
		return d2;
	}

	public void setD2(boolean d2) {
		this.d2.setValue(d2);
	}

	public Terminal getD3() {
		return d3;
	}

	public void setD3(boolean d3) {
		this.d3.setValue(d3);
	}
	
	public Terminal getD4() {
		return d4;
	}

	public void setD4(boolean d4) {
		this.d4.setValue(d4);
	}
	
	public Terminal getD5() {
		return d5;
	}

	public void setD5(boolean d5) {
		this.d5.setValue(d5);
	}
	
	public Terminal getD6() {
		return d6;
	}

	public void setD6(boolean d6) {
		this.d6.setValue(d6);
	}
	
	public Terminal getD7() {
		return d7;
	}

	public void setD7(boolean d7) {
		this.d7.setValue(d7);
	}
	
	public Terminal [] getTerminals(){
		Terminal [] listaterminals = new Terminal[11];
		listaterminals[0] = a0;
		listaterminals[1] = a1;
		listaterminals[2] = a2;
		listaterminals[3] = d0;
		listaterminals[4] = d1;
		listaterminals[5] = d2; 
		listaterminals[6] = d3;
		listaterminals[7] = d4; 
		listaterminals[8] = d5; 
		listaterminals[9] = d6; 
		listaterminals[10] = d7; 
		return listaterminals;
	}

	
	
}
