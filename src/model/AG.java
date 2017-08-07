package model;

import model.bloating.Bloating;
import model.crossing.Crossing;
import model.mutation.Mutation;
import model.population.Chromosome;
import model.population.Individuals;
import model.selection.Selection;

public class AG {
	
	private Individuals individuals;
	private Mutation mutation;
	private Fitness fitness;
	private Crossing crossing;
	private Selection selection;
	private Bloating bloating;
	private boolean elitismo;
	private final double prcelite=0.02;
	private Chromosome mejorchr;
	private double bestfitness;
	private long totalcruces=0,totalmutaciones=0,totalinversiones=0;

	
	public AG(Individuals i, Mutation m,Fitness f,Crossing c,Selection s, Bloating b, boolean e) {
		individuals=i;
		mutation=m;
		fitness=f;
		crossing=c;
		selection=s;
		elitismo=e;
		bloating = b;
	}
	
	
	public int[] runAG(){
		double [] fitness = getFitness();//creamos los individuos y calculamos los fitness dependiendo de la funcion
		Chromosome[]eliteSel = null;
		int tamelite = (int)((fitness.length)*prcelite);
		if(tamelite==0)tamelite=1;
		
		//para coger a los mejores lo hacemos sin tener en cuenta el control del bloating.
		if(elitismo){//si hay elitismo guardamos tantos individuos como los calculados en tamelite
			int[] fitnessElite = this.fitness.getFitnessIndividuals(individuals, false);
			int [] best = new int[tamelite];
			elite(fitnessElite,best);
			eliteSel = new Chromosome[tamelite];
			for (int i = 0; i <tamelite; i++) {
				eliteSel[i] = new Chromosome(individuals.getIndividuals()[best[i]]);
			}
		}

		selection.getnewpopulation(selection.selected(fitness), individuals);//seleccionamos
		crossing.crossingPopulation(individuals);//cruzamos
		mutation.mutatePopulation(individuals);//mutamos
		this.fitness.getFitnessIndividuals(individuals, true);//actualizamos el fitness para hacer el bloating (bien fundamentado)
		bloating.bloatPopulation(individuals);//control del bloating.
		fitness = getFitness();
		// para elegir a los peores lo hacemos teniendo en cuenta el control del bloating
		if(elitismo){//si hay elitismo mantenemos a los mejores
			//fitnessElite = this.fitness.getFitnessIndividuals(individuals, false);
			int[] peores =  new int[tamelite];
			worstIndexes(fitness,peores);
			for(int i=0;i<eliteSel.length;i++){
				individuals.getIndividuals()[peores[i]]=eliteSel[i];
			}		
		}
		fitness = getFitness();
		int mejoridx = 0;
		for (int i = 1; i < fitness.length; i++) {
			if (fitness[mejoridx] > fitness[i])
				mejoridx = i;
		}
		
		mejorchr=new Chromosome(individuals.getIndividuals()[mejoridx]);
		bestfitness=fitness[mejoridx];
		
		return this.fitness.getFitnessIndividuals(individuals, false);
	}
	
	private double[] getFitness() {
		int length = individuals.getIndividuals().length;
		double [] fitness = new double[length];
		for (int i = 0; i < length; i++)
			fitness[i] = individuals.getIndividuals()[i].getFitness();
		return fitness;
	}
	
	public long getTotalcruces() {
		return totalcruces;
	}


	public long getTotalmutaciones() {
		return totalmutaciones;
	}


	public long getTotalinversiones() {
		return totalinversiones;
	}


	public Chromosome getMejorchr() {
		return mejorchr;
	}


	public double getBestfitness() {
		return bestfitness;
	}


	//El elitismo se hace en el bucle principal.
	private void elite ( int[] fitnessElite, int[]best){
		
		int elite =  (int) (fitnessElite.length*prcelite);
		if(elite==0)elite=1;
		int peor = 0;//indice que apunta al peor de los mejores
		int j;
		
		for(j=0;j<elite;j++){ // Seleciona la posicion de best[] que tiene el indice con peor fitness de fitnessOfPopulation
			best[j]=j;
			if(fitnessElite[j] > fitnessElite[best[peor]]){peor=j;}
		}
		
		for(int i=j;i<fitnessElite.length;i++){
			
			if(fitnessElite[i] < fitnessElite[best[peor]]){
				best[peor]=i;
				for(int u=0;u<elite;u++){// Seleciona la posicion de best[] que tiene el indice con peor fitness de fitnessOfPopulation
					if(fitnessElite[best[u]] > fitnessElite[best[peor]]){peor=u;}
				}
			}				
		}				
	}

	
	//El elitismo se hace en el bucle principal.
private void worstIndexes(double[] fitness2, int [] worst){		
		
		int peores =  (int) (fitness2.length*prcelite);
		if(peores==0)peores=1;
		int mejor = 0;//indice que apunta al mejor de los peores
		int j;
		
		for(j=0;j<peores;j++){ // Seleciona la posicion de worst[] que tiene el indice con mejor fitness de fitnessOfPopulation
			worst[j]=j;
			if(fitness2[j] < fitness2[worst[mejor]]){mejor=j;}
		}
		
		for(int i=j;i<fitness2.length;i++){
			
			if(fitness2[i] > fitness2[worst[mejor]]){
				worst[mejor]=i;
				for(int u=0;u<peores;u++){// Seleciona la posicion de worst[] que tiene el indice con mejor fitness de fitnessOfPopulation
					if(fitness2[worst[u]] < fitness2[worst[mejor]]){mejor=u;}
				}
			}				
		}				
	}
	
}
