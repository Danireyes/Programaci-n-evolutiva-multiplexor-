package model.mutation;

import java.util.Random;

import model.population.Chromosome;
import model.population.Gene;
import model.population.TreeNode;

public class Permutation extends Mutation{
	
	
	public Permutation(double p) {
		super(p);
	}

	@Override
	/*
	 * Selecciona un nodo e intercambia el orden de sus hijos. Los ultimos pasan a ser los primeros.
	 * */
	protected void mutate(Chromosome chromosome) {
		Chromosome mayBeBetter = chromosome.clone();
		Random rnd = new Random(System.currentTimeMillis());
		int depth = Math.abs(rnd.nextInt()) % (mayBeBetter.getDepth()+1);
		TreeNode<Gene> point = mayBeBetter.getGenes();
		int child = -1;
		
		if (!point.getChildren().isEmpty()) {
			for (int i = 0; i <= depth; i++) {
				child = Math.abs(rnd.nextInt()) % point.getChildren().size();
				if (point.getChildren().get(child).getChildren().isEmpty())
					break;
				point = point.getChildren().get(child);
			}
			
			point.mutateChildrenPositions();
		}
	}
}
