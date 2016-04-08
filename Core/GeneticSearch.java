package Core;

import java.util.Random;
import java.util.Vector;

import Candidate.CandidateSolution;
import Data.PreferenceTable;
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
        	if(rng.nextInt(1000) < 10)
        		solution.getRandomAssignment().randomizeAssignment();
  	
        }
        
        private  Vector<CandidateSolution> matePopulation ( Vector<CandidateSolution> population, int numToMate) {
        	Vector<CandidateSolution> children = new Vector<CandidateSolution>();
        	Vector<CandidateSolution> alaphaMates = (Vector<CandidateSolution>) population.subList(0, numToMate) ;
        	for(CandidateSolution alpha : alaphaMates){
	        	for (CandidateSolution popMember : population){
	        		children.add(produceOffspring(alpha, popMember));
	        		mutate(children.lastElement());
	        	}
        	}
        	population.addAll(0, children);
        	return population;
        }
        
        private CandidateSolution produceOffspring(CandidateSolution parent1,CandidateSolution  parent2 ){
        	CandidateSolution child = new CandidateSolution(prefTable);
        	Random rand = new Random();
        	int x = rand.nextInt(1);
        	x *= parent2.solutionSize()/2;
        	
        	for(int i = 0; i < x; i++){
        		child.replaceAssignmentAt(i, parent1.getAssignmentAtIndex(i+x));
        	}
        	for(int i = x; i < parent2.solutionSize(); i++){
        		child.replaceAssignmentAt(i, parent2.getAssignmentAtIndex(i-x));
        	}
        	return child;
        }

        public Vector<CandidateSolution> evaluateSolution(Vector<CandidateSolution> initial, int cut_off){
        	for(CandidateSolution cand: initial){
        		for(CandidateSolution cand2: initial){
        			if(cand2.getFitness() > cand.getFitness()){
        				CandidateSolution temp;
        				temp = cand;
        				cand = cand2;
        				cand2 = temp;
        			}
        		}
        	}
        	return (Vector<CandidateSolution>)initial.subList(0, cut_off);
        }
        
        public void improveSolution() {
        	
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
