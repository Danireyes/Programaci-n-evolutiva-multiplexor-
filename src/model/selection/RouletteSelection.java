package model.selection;

import java.util.Random;

public class RouletteSelection extends Selection{

	public int[] selected(double[] fitnessOfPopulation) {
		
		
		double totalFitness = 0;
		int length = fitnessOfPopulation.length;
		double segment[] = new double [length];
		
		for (int i =0;i<length;i++) { //Calcula el fitness total
			totalFitness += 1/fitnessOfPopulation[i];
		}
		
		double result = 0;
		for (int i = 0; i < length; i++) { //hacemos el segmento de probabilidades
			result += (1/fitnessOfPopulation[i])/totalFitness;
			segment[i] = result;
		}
		
		Random rand = new Random(System.currentTimeMillis());
		int selection[] = new int[length]; 
		double rndselect;
		int indexselected;
		for(int i=0;i<length; i++){ //seleccionamos el individuo en el que caiga rndselect hasta alcanzar tantos seleccionado como poblacion hubiera.
			rndselect=Math.abs(rand.nextDouble()%1);
			for(indexselected=0;indexselected<(segment.length-1);indexselected++){
				if(rndselect < segment[indexselected]){break;}
			}
			selection[i]=indexselected;
		}
		
		return selection;
	}
	
}
