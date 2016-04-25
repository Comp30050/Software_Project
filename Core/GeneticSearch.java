package Core;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import Candidate.CandidateSolution;
import Data.PreferenceTable;
import Interfaces.SolutionComparator;
import Interfaces.SolutionType;

public class GeneticSearch implements SolutionType {
		private PreferenceTable prefTable = null;
		
        public GeneticSearch(PreferenceTable pt) {
        		prefTable = pt;
        		
        }
        
        private  Vector<CandidateSolution> generatePopulation(int popSize) {
        	Vector<CandidateSolution> population =  new Vector<CandidateSolution>();
        	while(population.size() < popSize){
        		population.add(new CandidateSolution(prefTable));
        	}
        	
        	return population;
        }
        
        private void mutate(CandidateSolution solution){
        	Random rng = new Random();
        	while(rng.nextInt(100) < 50)
        		solution.getRandomAssignment().randomizeAssignment();
  	
        }
        
        private  void matePopulation ( Vector<CandidateSolution> population, int numToMate) {
        	Vector<CandidateSolution> children = new Vector<CandidateSolution>();
        	Vector<CandidateSolution> alphaMates = new Vector<CandidateSolution>(population) ;
        	alphaMates.setSize(numToMate);
        	
        	for(CandidateSolution alpha : alphaMates){
	        	for (CandidateSolution popMember : population){
	        		if(popMember != alpha){
	        			children.add(produceOffspring(alpha, popMember));
	        			mutate(children.lastElement());
	        		}
	        	}
        	}   	
        	population.addAll(0, children);
        }
        
        private CandidateSolution produceOffspring(CandidateSolution parent1,CandidateSolution  parent2 ){
        	CandidateSolution child = new CandidateSolution(prefTable);
        	Random rand = new Random();
        	int x = rand.nextInt(1);
        	x *= child.solutionSize()/2;
        	
        	for(int i = 0; i < x; i++){
        		//child.replaceAssignmentAt(i, parent1.getAssignmentAtIndex(i+x));
        		String assign = parent1.getAssignmentAtIndex(i+x).getAssignment();
        		child.getAssignmentAtIndex(i+x).setAssignment(assign);
        	}
        	for(int i = x; i < parent2.solutionSize(); i++){
        		//child.replaceAssignmentAt(i, parent2.getAssignmentAtIndex(i-x));
        		String assign = parent1.getAssignmentAtIndex(i-x).getAssignment();
        		child.getAssignmentAtIndex(i-x).setAssignment(assign);
        	}
        	return child;
        }

       private void cullPopulation(Vector<CandidateSolution> population, int cut_off){
    	   Collections.sort(population, new SolutionComparator());
//    	   System.out.println("-----------------------------------");
//    	   for(CandidateSolution q : population){
//    		   System.out.println(q.getFitness());
//    	   }
    	   population.setSize(cut_off);
    	   //System.out.println("-----------------------------------");
//    	   for(CandidateSolution q : population){
//    		   System.out.println(q.getFitness());
//    	   }
//    	   System.out.println("-----------------------------------");
        }
        
        public CandidateSolution generateSolution() {
        	int populationSize = 100;
        	int numberOfMates = 5;
        	Vector<CandidateSolution> population = generatePopulation(populationSize*10);
        	cullPopulation(population, populationSize);
        	CandidateSolution bestSolution = population.firstElement();
        	System.out.println(bestSolution.getFitness());
        	
        	for(int i = 0; i < 100; i++ ){
        		
        		matePopulation(population, numberOfMates);
        		cullPopulation(population, populationSize);
//        		if (bestSolution.getFitness() <  population.firstElement().getFitness())
//        			break;
        			
        		bestSolution = population.firstElement();	
        		System.out.println(bestSolution.getFitness() + " = best sol num ->"+ i);
        		System.out.println(bestSolution.getEnergy() + " = best sol num ->"+ i);
        	}
        	return bestSolution;
        	
        }
        /**
         *Inspects current CandidateSolution for given SolutionType
         * @return current CandidateSolutions energy
         */
        public int getBestSolutionEnergy() {
			return 0;
        	
        }

        /**
         *Inspects current CandidateSolution for given SolutionType
         * @return current CandidateSolutions fitness
         */
        public int getBestSolutionFitness() {
			return 0;
        	
        }

        /**
         *@return running time to get best CandidateSolution
         */
        public int getTotalRunningTime() {
			return 0;
        	
        }
        
}


