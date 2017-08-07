package model.selection;

import java.util.Random;

public class TourDetSelection extends Selection{
	

	@Override
	public int[] selected(double[] fitnessOfPopulation) {
		
		int length = fitnessOfPopulation.length;
		int selection[] = new int[length];
		Random rand = new Random(System.currentTimeMillis());
		int rnd1,rnd2;
		
		for(int i=0;i<length;i++){//Hemos puesto que Z sea igual a 2; para cada vez seleccionamos quien haya ganado el torneo.
			rnd1=Math.abs(rand.nextInt()%length);
			rnd2=Math.abs(rand.nextInt()%length);
			if(fitnessOfPopulation[rnd1]<fitnessOfPopulation[rnd2]){ selection[i]= rnd1;}
			else{selection[i]= rnd2;}
		}
		return selection;
	}
}
