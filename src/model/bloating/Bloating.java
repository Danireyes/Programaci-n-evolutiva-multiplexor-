package model.bloating;

import model.population.Chromosome;
import model.population.Individuals;

public abstract class Bloating {
	
	private int size;
	
	public Bloating(int s) {
		size = s;
	}
	public void bloatPopulation(Individuals ind) {
		Chromosome individuals[] = ind.getIndividuals();
		double averageSizeOrK = -1;
		if (TarpeianMethodBloating.class.isInstance(this))
			averageSizeOrK = getAverageSize(ind);
		else
			averageSizeOrK = Math.abs(getK(ind));
		for (int i = 0; i < individuals.length; i++) {//para cada individuo
			this.controlBloating(individuals[i], averageSizeOrK);
		}
	}

	public abstract void controlBloating(Chromosome chromosome, double averageSizeOrK);
	
	// Calcula el tamaño medio basandose en el numero de nodos o la profundidad ( depende del parametro size, que viene de la view).
	private double getAverageSize(Individuals ind) {
		int sumOfSizes = 0;
		for (Chromosome c : ind.getIndividuals()) {
			if (size == 0)
				sumOfSizes += c.getDepth();
			else
				sumOfSizes += c.getNodes();
		}
		return sumOfSizes / ind.getIndividuals().length;
	}
	
	// Calcula la media del fitness de la población.
	private double getAverageFitness(Individuals ind) {
		int sumOfFitnesses = 0;
		for (Chromosome c : ind.getIndividuals()) {
			sumOfFitnesses += c.getFitness();
		}
		return sumOfFitnesses / ind.getIndividuals().length;
	}
	
	//Calcula el parametro k basandose en la varianza y la covarianza de la poblacion.
	private double getK(Individuals ind) {
		double averageSize = getAverageSize(ind);
		double averageFitness = getAverageFitness(ind);
		double variance = 0;
		double covariance = 0;
		for (Chromosome c : ind.getIndividuals()) {
			variance += Math.pow((c.getFitness() - averageFitness),2);
			covariance += ((c.getFitness() - averageFitness) * (c.getNodes() - averageSize));
		}
		variance = variance / ind.getIndividuals().length;
		covariance = covariance / ind.getIndividuals().length;
		if (variance != 0) {
			return covariance / variance;
		}
		else {
			return 0;
		}
	}
	public int getSize() {
		return size;
	}
}
