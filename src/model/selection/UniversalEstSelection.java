package model.selection;

import java.util.Random;

public class UniversalEstSelection extends Selection{
	
	
	@Override
	public int[] selected(double[] fitnessOfPopulation) {
		
		
		double totalFitness = 0;
		int length = fitnessOfPopulation.length;
		int N = length;
		
		double segment[] = new double [length];
		
		for (int i =0;i<length;i++) { //Calcula el fitness total
			totalFitness += 1/fitnessOfPopulation[i];
		}
		
		double result = 0;
		for (int i = 0; i < length; i++) {//se crea el segmento de probabilidades.
			result += (1/fitnessOfPopulation[i])/totalFitness;
			segment[i] = result;
		}
		
		Random rand = new Random(System.currentTimeMillis());
		int selection[] = new int[length]; 
		double rndselect=Math.abs(rand.nextDouble()%(1/N));
		int indexselected;
		for (int i = 0; i < length; i++) {//igual que la ruleta pero rndselected se va sumando 1/N en vez de ser siempre aleatorio
			for(indexselected=0;indexselected<segment.length-1;indexselected++){
				if(rndselect < segment[indexselected]){break;}
			}
			selection[i] = indexselected;
			rndselect += 1/N;	
		}
		return selection;
	}

}
