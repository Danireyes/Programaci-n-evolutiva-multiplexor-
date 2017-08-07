package model.selection;

import java.util.Random;

public class TourProbSelection extends Selection {
	
	private final double p;
	private final int contestants;
	
	public TourProbSelection(double p,int contestants){
		this.p=p;
		this.contestants=contestants;
	}
	
	
	@Override
	public int[] selected(double[] fitnessOfPopulation) {
		
		
		int length = fitnessOfPopulation.length;
		int selection[] = new int[length];
		Random rand = new Random(System.currentTimeMillis());
		int rnd;
		double rndselect;
		int mejor,peor;
		
		for(int i=0;i<length;i++){
			
			mejor=0;peor=0;		
			for(int j=0;j<contestants;j++){//cojemos el mejor y el peor de los participantes en el torneo.
				rnd=Math.abs(rand.nextInt()%length);
				if(fitnessOfPopulation[rnd] < fitnessOfPopulation[mejor]){
					mejor=rnd;
				}
				else if(fitnessOfPopulation[rnd] > fitnessOfPopulation[peor]){
					peor=rnd;
				}
			}
			// si rndselect > p gana el mejor sino  el peor
			rndselect=Math.abs(rand.nextDouble()%1);
			if(rndselect <= p){
				selection[i]=mejor;
			}
			else{
				selection[i]=peor;
			}
		}
		return selection;
	}

}
