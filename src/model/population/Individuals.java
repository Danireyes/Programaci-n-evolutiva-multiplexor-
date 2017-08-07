package model.population;

import java.util.Random;

public class Individuals {
	
	private Chromosome individuals[];
	
	public Individuals (int population, int seed, int depthChromosome, boolean useIf, String initializationType) {
		this.individuals = new Chromosome[population];
		//Inicializamos los terminales.
		Terminal [] listaterminals = new Terminal[11];
		  listaterminals[0] = new Terminal ("a0");
		  listaterminals[1] =  new Terminal ("a1");
		  listaterminals[2] =  new Terminal ("a2");
		  listaterminals[3] =  new Terminal ("d0");
		  listaterminals[4] =  new Terminal ("d1");
		  listaterminals[5] =  new Terminal ("d2");
		  listaterminals[6] =  new Terminal ("d3");
		  listaterminals[7] =  new Terminal ("d4");
		  listaterminals[8] =  new Terminal ("d5");
		  listaterminals[9] =  new Terminal ("d6");
		  listaterminals[10] =  new Terminal ("d7");
		Random rnd = new Random(seed); // utilizamos la semilla para crear la poblacion inicial. Vamos pasando el objeto Random para aprovecharla.
		if (initializationType.equals("Ramped and half")) {// si es ramped and half tenemos que elegir entre completa o creciente.
			int groupMembers = population / (depthChromosome);
			int depth = 0;
			for (int i = 0; i < population; i++) {//creamos cada cromosoma pasandole el largo de cada uno de sus genes y el objeto Random
				if (i % groupMembers == 0) {// usamos una profundidad diferente para cada grupo.
					if (depth < depthChromosome)
						depth++;
				}
				if (i % 2 == 0)
					this.individuals[i] = new Chromosome(rnd, depth, useIf, "Inicializacion completa",listaterminals);
				else
					this.individuals[i] = new Chromosome(rnd, depth, useIf, "Inicializacion creciente",listaterminals);
			}
		} else {
			for (int i = 0; i < population; i++) {//creamos cada cromosoma pasandole el largo de cada uno de sus genes y el objeto Random
				this.individuals[i] = new Chromosome(rnd, depthChromosome, useIf, initializationType,listaterminals);
			}
		}
	}
	
	public Chromosome[] getIndividuals() {
		return individuals;
	}
	
	public void setIndividuals(Chromosome [] newpopu){
		this.individuals=newpopu;
	}
	
}

