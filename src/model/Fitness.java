package model;

import java.util.ArrayList;

import model.population.Chromosome;
import model.population.Individuals;
import model.population.Terminal;

public class Fitness {
	
	ArrayList<boolean[]> variablesCombinations;
	
	public Fitness() {
		variablesCombinations = getCombinations();
	}
	public int[] getFitnessIndividuals(Individuals population, boolean update) {//calcula el fitnes de cada individuo utilizando la funcion dada
		int fitness[] = new int[population.getIndividuals().length];
		int i = 0;
		for (Chromosome c : population.getIndividuals()) {
			fitness[i] = getFitnessOfChromosome(c, update);
			i++;
		}
		return fitness;
	}
	
	
	public int getFitnessOfChromosome(Chromosome chromosome, boolean update) {
		int fitness = 0;
		//calculamos todas las posibles combinaciones de valores para los terminales.
		
		Terminal solution;
		boolean combinations[];
				
		for (int j = 0; j < variablesCombinations.size(); j++) {
			//actualizamos los terminales con cada una de las combinaciones.
			combinations = variablesCombinations.get(j);
			chromosome.setA0(combinations[0]);
			chromosome.setA1(combinations[1]);
			chromosome.setA2(combinations[2]);
			chromosome.setD0(combinations[3]);
			chromosome.setD1(combinations[4]);
			chromosome.setD2(combinations[5]);
			chromosome.setD3(combinations[6]);
			chromosome.setD4(combinations[7]);
			chromosome.setD5(combinations[8]);
			chromosome.setD6(combinations[9]);
			chromosome.setD7(combinations[10]);

			//calculamos la solucion del cromosoma con esa combinacion de valores.
			solution = chromosome.calculate(chromosome.getGenes());
			chromosome.setD3(combinations[5]);
			//para cada combinacion miramos si falla (minimizacion)
			 if (chromosome.getA0().getValue() == false && chromosome.getA1().getValue() == false && chromosome.getA2().getValue() == false) {
			    if (!solution.equals(chromosome.getD0()))
			     fitness++;
			   }
			   else if (chromosome.getA0().getValue() == true && chromosome.getA1().getValue() == false && chromosome.getA2().getValue() == false) {
			    if (!solution.equals(chromosome.getD1()))
			     fitness++;
			   }
			   else if (chromosome.getA0().getValue() == false && chromosome.getA1().getValue() == true && chromosome.getA2().getValue() == false) {
			    if (!solution.equals(chromosome.getD2()))
			     fitness++;
			   }
			   else if (chromosome.getA0().getValue() == true && chromosome.getA1().getValue() == true && chromosome.getA2().getValue() == false) {
			    if (!solution.equals(chromosome.getD3()))
			     fitness++;
			   }
			   else if (chromosome.getA0().getValue() == false && chromosome.getA1().getValue() == false && chromosome.getA2().getValue() == true) {
			    if (!solution.equals(chromosome.getD4()))
			     fitness++;
			   }
			   else if (chromosome.getA0().getValue() == true && chromosome.getA1().getValue() == false && chromosome.getA2().getValue() == true) {
			    if (!solution.equals(chromosome.getD5()))
			     fitness++;
			   }
			   else if (chromosome.getA0().getValue() == false && chromosome.getA1().getValue() == true && chromosome.getA2().getValue() == true) {
			    if (!solution.equals(chromosome.getD6()))
			     fitness++;
			   }
			   else if (chromosome.getA0().getValue() == true && chromosome.getA1().getValue() == true && chromosome.getA2().getValue() == true) {
			    if (!solution.equals(chromosome.getD7()))
			     fitness++;
			   }
		}
		if (update)
			chromosome.setFitness(fitness);
		return fitness;
	}
	
	
	//http://stackoverflow.com/questions/27007752/creating-all-possible-ways-of-a-boolean-array-of-size-n
	private ArrayList<boolean[]> getCombinations() {
        int n = 11;// number of variables [a0, a1, a2, d0, d1, d2, d3, d4, d5, d6, d7, d8]
        ArrayList<boolean[]> combinations = new ArrayList<boolean[]>();
        for (int i = 0; i < Math.pow(2, n); i++) {// desde 0 hasta 2048
            String bin = Integer.toBinaryString(i);
            while (bin.length() < n)
                bin = "0" + bin;
            char[] chars = bin.toCharArray();
            boolean[] boolArray = new boolean[n];
            for (int j = 0; j < chars.length; j++) {
                boolArray[j] = chars[j] == '0' ? true : false;
            }
           combinations.add(boolArray);
        }
        return combinations;
    }

}
