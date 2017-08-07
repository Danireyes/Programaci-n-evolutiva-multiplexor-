package model.mutation;

import java.util.Random;

import model.population.Chromosome;
import model.population.Gene;
import model.population.TreeNode;

public class TreeMutation extends Mutation{
	
	public TreeMutation(double p, String initializationType) {
		super(p);
		}

	@Override
	/*
	 * Selecciona un nodo del arbol al azar y crea un nuevo árbol el cual sustituye al anterior.
	 * */
	protected void mutate(Chromosome chromosome) {
		Random rnd = new Random();
		int depth = Math.abs(rnd.nextInt()) % (chromosome.getDepth());
		TreeNode<Gene> point = chromosome.getGenes();
		int child = -1;
		for (int i = 0; i < depth; i++) {
			if(point.getChildren().size() == 0) break;
			child = Math.abs(rnd.nextInt()) % point.getChildren().size();
			point = point.getChildren().get(child);
		}
		
		
		Chromosome aux = new Chromosome(new Random(System.currentTimeMillis()), chromosome.getDepth()-depth, chromosome.getTypesOfGenes()== 5,"Inicializacion creciente",chromosome.getTerminals());
		point.interchangeChildren(aux.getGenes());
	}
}
