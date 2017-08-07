package model.mutation;

import java.util.Random;

import model.population.Chromosome;
import model.population.Gene;
import model.population.Terminal;
import model.population.TreeNode;

public class SimpleTerminalMutation extends Mutation {

	public SimpleTerminalMutation(double p) {
		super(p);
	}

	@Override
	/*
	 * Se selecciona un nodo terminal y se cambia por uno nuevo al azar.
	 * */
	 protected void mutate(Chromosome chromosome) {
		  
		  Random rnd = new Random();
		  int election = Math.abs(rnd.nextInt()) % getTerminalsSize(chromosome.getGenes());
		  int terminal = Math.abs(rnd.nextInt())% 11;
		  Terminal newTerminal = null;
		  switch (terminal) {
		  case 0:
		   newTerminal = chromosome.getA0();
		   break;
		  case 1:
		   newTerminal = chromosome.getA1();
		   break;
		  case 2:
		   newTerminal = chromosome.getA2();
		   break;
		  case 3:
		   newTerminal = chromosome.getD0();
		   break;
		  case 4:
		   newTerminal = chromosome.getD1();
		   break;
		  case 5:
		   newTerminal = chromosome.getD2();
		   break;
		  case 6:
		   newTerminal = chromosome.getD3();
		   break;
		  case 7:
		   newTerminal = chromosome.getD4();
		   break;
		  case 8:
		   newTerminal = chromosome.getD5();
		   break;
		  case 9:
		   newTerminal = chromosome.getD6();
		   break;
		  case 10:
		   newTerminal = chromosome.getD7();
		   break;
		  }
		  changeTerminal(chromosome.getGenes(), election, newTerminal);
		  
		 }
	
	/*
	 * Se cambia el data del nodo terminal.
	 * */
	private void changeTerminal(TreeNode<Gene> genes, int election, Terminal newTerminal) {
		if (Terminal.class.isInstance(genes.getData())) {
			if (election == 0)
				genes.setData(newTerminal);
			else
				election--;
		}
		else {
			for (TreeNode<Gene> gene : genes.getChildren()) {
				changeTerminal(gene, election, newTerminal);
			}
		}
	}
	
	/*
	 * Devuelve el número de nodos terminales que hay en todo el cromosoma.
	 * */
	private int getTerminalsSize(TreeNode<Gene> genes) {
		if (Terminal.class.isInstance(genes.getData())) {
			return 1;
		}
		else {
			int size = 0;
			for (TreeNode<Gene> gene : genes.getChildren()) {
				size += getTerminalsSize(gene);
			}
			return size;
		}
	}
	
}
