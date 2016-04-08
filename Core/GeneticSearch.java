package Core;

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
        
<<<<<<< HEAD
        private  Vector<CandidateSolution> matePopulation ( Vector<CandidateSolution> population, int numToMate) {
        	/*
        	 * Select members to mate  
        	 * choose mates,
        	 * produce offspring
        	 * mutate
        	 * */
        	Vector<CandidateSolution> alaphaMates = (Vector<CandidateSolution>) population.subList(0, numToMate) ;
        	for(CandidateSolution alpha : alaphaMates){
	        	for (CandidateSolution popMember : population){
	        		produceOffspring(alpha, popMember);
	        	}
        	}
        	
        	return null;
        }
        
        private CandidateSolution produceOffspring(CandidateSolution parent1,CandidateSolution  parent2 ){
        	return null;}

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
