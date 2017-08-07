package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AG;
import model.Fitness;
import model.bloating.Bloating;
import model.bloating.TarpeianMethodBloating;
import model.bloating.WellFundamentedPenalizationBloating;
import model.crossing.Crossing;
import model.mutation.Mutation;
import model.mutation.Permutation;
import model.mutation.SimpleFunctionalMutation;
import model.mutation.SimpleTerminalMutation;
import model.mutation.TreeMutation;
import model.population.Chromosome;
import model.population.Individuals;
import model.selection.RouletteSelection;
import model.selection.Selection;
import model.selection.TourDetSelection;
import model.selection.TourProbSelection;
import model.selection.UniversalEstSelection;
import view.View;

public class Control {
	private View view;
	private AG algoritmoGen;
	private int iteraciones;
	private double media;
	private double maxaptitud;
	//private double minaptitud;
	
	public Control(View v) {
		view = v;
		media=0;
		maxaptitud=0;
		//minaptitud=9999999;
		onAttach();//le pasamos los listeners a la view.
	}
	
	public void onAttach() {
		view.setSeleccionCBListener(getSeleccionActionListener());
		view.setLanzaButtonListener(getLanzaButtonActionListener());
		view.setEliminaButtonListener(getEliminaGrafActionListener());
		view.setRelanzaButtonListener(getRelanzaActionListener());
	}
	
	

	private ActionListener getLanzaButtonActionListener() {
		return new ActionListener() {	//cuando lanzamos el AG cogemos los datos necesarios para crear el objeto AG y correrlo. Tambien dejamos relanzar AG.	
			@Override
			public void actionPerformed(ActionEvent e) {
				int entre = view.getEntreTextField();
				double mejor = view.getMejorTextField();
				int poblacion = view.getPoblacionText();
				iteraciones =view.getIteracionesText();
				double pCruce = view.getCrucesText();
				double pMutacion = view.getMutacionText();
				int semilla = view.getSemillaText();
				int indexSelection = view.getSeleccionCBIndex();
				boolean elitismo = view.getElitismoButton();
				boolean useOfIF = view.getIFButton();
				String initializationType = view.getInitializationTextField();
				int maxDepth = view.getProfundidadText();
				
				
			    Fitness fitness= new Fitness();
			    Individuals individuals = new Individuals(poblacion, semilla, maxDepth, useOfIF, initializationType);
			    fitness.getFitnessIndividuals(individuals, true);//SE HACE PORQUE NECESITAMOS TENERLO INICIALIZADO
				
				Selection selection = null;
			      switch(indexSelection) {
				      case 0:
				       selection = new TourProbSelection(mejor, entre);
				       break;
				      case 1:
				    	  selection = new TourDetSelection();
				       break;
				      case 2:
				    	  selection = new UniversalEstSelection();
				       break;
				      case 3:
				       selection = new RouletteSelection();
				       break;
			    }
		      
			      Crossing crossing = new Crossing(pCruce);
			      
			      int indexMutation = view.getMutationCBIndex();
			      
			      Mutation mutation = null;
			      switch(indexMutation) {
					case 0:
						mutation = new SimpleTerminalMutation(pMutacion);
						break;
					case 1:
						mutation = new SimpleFunctionalMutation(pMutacion);
						break;
					case 2:
						mutation = new TreeMutation(pMutacion, initializationType);
						break;
					case 3:
						mutation = new Permutation(pMutacion);
			      }
			      
			      int indexBloating = view.getBloatingCBIndex();
			      
			      Bloating bloating = null;
			      switch(indexBloating) {
					case 0:
						bloating = new TarpeianMethodBloating(view.getCalcTamCBIndex());
						break;
					case 1:
						bloating = new WellFundamentedPenalizationBloating(-1);
			      }
			      
				algoritmoGen = new AG(individuals,mutation,fitness,crossing, selection, bloating, elitismo);
				view.setVisibilityRelanza(true);
				run();
			}
		};
		
	}
	
	public ActionListener getRelanzaActionListener() {
		  return new ActionListener() {
		     
		     @Override
		     public void actionPerformed(ActionEvent e) {
		    	 run();//corremos el mismo objeto AG creado en lanza dando igual los datos que toque el usuario.
		     }
		  };
	}
	
	
	public ActionListener getSeleccionActionListener() {
		  return new ActionListener() {
		     
		     @Override
		     public void actionPerformed(ActionEvent e) {//el torneo probabilistico necesita saber la probabilidad de coger el mejor y entre cuantos.
		      int index = view.getSeleccionCBIndex();
		      switch(index) {
			      case 0:
			       view.setVisibilityTorneo(true);
			       break;
			      case 1:
			       view.setVisibilityTorneo(false);
			       break;
			      case 2:
			       view.setVisibilityTorneo(false);
			       break;
			      case 3:
			       view.setVisibilityTorneo(false);
			       break;
			       
		      }
		     }
		  };
	}
	
	public ActionListener getEliminaGrafActionListener() {
		  return new ActionListener() {
		     
		     @Override
		     public void actionPerformed(ActionEvent e) {
		    	 view.borrarGrafActivo();//se borra el grafico seleccionado en el TabbedPane
		     }
		  };
	}
	
	public void run(){//aqui se corre el objeto AG y con los datos devueltos se hace la grafica.
		
		int fitness[];
		double mediait[] = new double [iteraciones];
		int mejorit[] = new int [iteraciones];
		int mejorabsoluto[]= new int [iteraciones];
		Chromosome mejorabs=null;
		int mejorfitness=0,peorfitness=0;
		int mejorIt,peorit;
		
		for(int i=0;i<iteraciones;i++){ 
			fitness = algoritmoGen.runAG();
			adaptarFitness(fitness);
			mejorIt = getBestfitness(fitness);
			if(i==0){
				mejorabs= new Chromosome (algoritmoGen.getMejorchr());
				mejorfitness= mejorIt;
			}
			else if(mejorIt > mejorfitness){
				mejorabs.setGenes(algoritmoGen.getMejorchr().getGenes());
				mejorfitness=mejorIt;
			}
			
			mediait[i] = calculamedia(fitness);
			mejorit[i]	= mejorIt;
			peorit = buscapeor(fitness);
			
			if(i==0){
				mejorabsoluto[i]=mejorIt;
			}
			else if( mejorIt > mejorabsoluto[i-1]){
				mejorabsoluto[i]=mejorIt;
			}	
			else{
				mejorabsoluto[i]=mejorabsoluto[i-1];
			}
			if(peorit < peorfitness){
				peorfitness = peorit;
			}
		}	
		view.addGraphPanel(mediait, mejorit,mejorabsoluto,mejorabs.toString());
		for(int i=0;i<mediait.length;i++){
			media+=mediait[i];
		}
		media= media/mediait.length;
		
		System.out.println("=========================================================================");
		System.out.println("Mejor chromosoma : " + mejorabs);
		System.out.println("Aptitud MEJOR : "+ mejorfitness);
		System.out.println("Profundidad  : "+ mejorabs.getDepth());
		System.out.println("Numero de nodos   : "+ mejorabs.getNodes());
		//System.out.println("Aptitud PEOR : "+ peorfitness);
		//System.out.println("Media total: " +media);
		//System.out.println("Total Cruces : " + algoritmoGen.getTotalcruces());
		//System.out.println("Total Mutaciones : " + algoritmoGen.getTotalmutaciones());
		//System.out.println("Total Inversiones: " + algoritmoGen.getTotalinversiones());
	
		
	}
	
	private int getBestfitness(int fitness[]) {
		int best = fitness[0];
		for(int i = 1; i < fitness.length; i++) {
			if (fitness[i] > best)
				best = fitness[i];
			if(fitness[i]>maxaptitud){
				maxaptitud=fitness[i];
			}
		}
		return best;
	}

	private void adaptarFitness(int[] fitness) {
		for (int i = 0; i < fitness.length; i++)
			fitness[i] = 2048 - fitness[i];
		
	}

	public int buscapeor(int [] fitness){
		int peor=0;
		for(int i=0;i<fitness.length;i++){
			if( peor < fitness[i]){
				peor=fitness[i];
			}
		}
		return peor;
		
	}
	public double calculamedia( int [] fitness){ //media del fitness
		double total=0;
		for(int i=0;i<fitness.length;i++){
			total+=fitness[i];
		}
		return total/fitness.length;
	}
}
