package model.crossing;

import java.util.ArrayList;
import java.util.Random;

import model.population.Chromosome;
import model.population.Gene;
import model.population.Individuals;
import model.population.Terminal;
import model.population.TreeNode;

public class Crossing {
	
	protected double pCrossing;
	
	public Crossing (double p) {
		pCrossing = p;
	}
	
	protected void crossing(Chromosome ch1, Chromosome ch2) {
		Random rnd = new Random(System.currentTimeMillis());
		int depth1;
		int depth2;
		TreeNode<Gene> point1;// este sera el punto de cruce del cromosoma 1
		TreeNode<Gene> point2 = ch2.getGenes();// este sera el punto de cruce del cromosoma 2
		boolean accepted = false;
		
		do{
			depth1 = Math.abs(rnd.nextInt()) % (ch1.getDepth());//calculamos la profundidad de cruce del segundo cromosoma
			point1 = ch1.getGenes();
			if(!Terminal.class.isInstance(point1.getData())){// si el punto no es un terminal
				int child1 = -1;
				for (int i = 0; i <= depth1; i++) {// bajamos de profundidad eligiendo aleatoriamente uno de sus hijos
					if(!Terminal.class.isInstance(point1.getData())){
						child1 = Math.abs(rnd.nextInt()) % point1.getChildren().size();
						point1 = point1.getChildren().get(child1);//asi calculamos el punto de cruce
					}
					else{
						break;
					}
				}
				if(!Terminal.class.isInstance(point1.getData())) {//si es una funcion tenemos un 90% de probabilidad de que sea el punto definitivo
					if (Math.abs(rnd.nextDouble()) % 1 <= 0.9)
						accepted = true;
				}
				else {
					if (Math.abs(rnd.nextDouble()) % 1 <= 0.1)//y si es un terminal un 10%
						accepted = true;
				}
			}
			else{
				accepted = true;
			}
		}
		while(!accepted);
		
		do{//hacemos lo mismo para el segundo cromosoma
			depth2 = Math.abs(rnd.nextInt()) % (ch2.getDepth());//calculamos la profundidad de cruce del segundo cromosoma
			point2 = ch2.getGenes();
			if(!Terminal.class.isInstance(point2.getData())){
				int child2 = -1;
				for (int i = 0; i <= depth2; i++) {
					if(!Terminal.class.isInstance(point2.getData())){
						child2 = Math.abs(rnd.nextInt()) % point2.getChildren().size();
						point2 = point2.getChildren().get(child2);
					}
					else{
						break;
					}
				}
				if(!Terminal.class.isInstance(point1.getData())) {
					if (Math.abs(rnd.nextDouble()) % 1 <= 0.9)
						accepted = true;
				}
				else {
					if (Math.abs(rnd.nextDouble()) % 1 <= 0.1)
						accepted = true;
				}
			}
			else{
				accepted = true;
			} 
		}
		while(!accepted);
		

		point1.interchangeChildren(point2);//cuando tengamos los puntos de cruce los intercambiamos.
			
	}
	
	public void crossingPopulation(Individuals indiv) {//cruzamos todos los individuos con una probabilidad pCrossing
		ArrayList<Integer> indxCrossed = new ArrayList<Integer>();
		Random rnd = new Random();
		for (int i = 0; i < indiv.getIndividuals().length; i++) {
			if (Math.abs(rnd.nextDouble()) % 1 <= pCrossing) {
				indxCrossed.add(i);
			}
		}
		for (int i = 0; (i < indxCrossed.size()-1); i=i+2) {
			crossing(indiv.getIndividuals()[indxCrossed.get(i)], indiv.getIndividuals()[indxCrossed.get(i+1)]);
		}
	}	
	
}
