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

        private Vector<CandidateSolution> generatePopulation(int popSize) {
                Vector<CandidateSolution> population = new Vector<CandidateSolution>();
                while (population.size() < popSize) {
                        population.add(new CandidateSolution(prefTable));
                }

                return population;
        }

        private void mutate(CandidateSolution solution) {
                Random rng = new Random();
                while (rng.nextInt(100) < 50)
                        solution.getRandomAssignment().randomizeAssignment();

        }

        private void matePopulation(Vector<CandidateSolution> population, int numToMate) {
                Vector<CandidateSolution> children = new Vector<CandidateSolution>();
                Vector<CandidateSolution> alphaMates = new Vector<CandidateSolution>(population);
                alphaMates.setSize(numToMate);

                for (CandidateSolution alpha : alphaMates) {
                        for (CandidateSolution popMember : population) {
                                if (popMember != alpha) {
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
        	String assign;
        	for(int j = 0; j < parent1.solutionSize(); j++){
        		int x = rand.nextInt(1);
        		if(x == 0)
        			assign = parent1.getAssignmentAtIndex(j).getAssignment();
        		else
        			assign = parent2.getAssignmentAtIndex(j).getAssignment();
        		child.getAssignmentAtIndex(j).setAssignment(assign);
        	}
        	return child;
        }

        private void cullPopulation(Vector<CandidateSolution> population, int cut_off) {
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
                int populationSize = 1000, maxIterations = 100,numberOfMates = 10;
                Vector<CandidateSolution> population = generatePopulation(populationSize * 10);
                cullPopulation(population, populationSize);
                CandidateSolution bestSolution = population.firstElement();
                System.out.println(bestSolution.getFitness());

                for (int i = 0; i <maxIterations ; i++) {

                        matePopulation(population, numberOfMates);
                        cullPopulation(population, populationSize);
//        		if (bestSolution.getFitness() <  population.firstElement().getFitness())
//        			break;

                        bestSolution = population.firstElement();
                        System.out.println(bestSolution.getFitness() + " = best sol num ->" + i);
                }
                return bestSolution;

        }

        /**
         * Inspects current CandidateSolution for given SolutionType
         *
         * @return current CandidateSolutions energy
         */
        public int getBestSolutionEnergy() {
                return 0;

        }

        /**
         * Inspects current CandidateSolution for given SolutionType
         *
         * @return current CandidateSolutions fitness
         */
        public int getBestSolutionFitness() {
                return 0;

        }

        /**
         * @return running time to get best CandidateSolution
         */
        public int getTotalRunningTime() {
                return 0;

        }

}


