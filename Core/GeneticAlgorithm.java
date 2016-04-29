package Core;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import Candidate.CandidateSolution;
import Data.PreferenceTable;
import Interfaces.SolutionComparator;
import Interfaces.SolutionType;

public class GeneticAlgorithm implements SolutionType {
        private PreferenceTable prefTable = null;
        private CandidateSolution geneticSolution= null;

        public GeneticAlgorithm(PreferenceTable pt) {
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
                while (rng.nextInt(100) < 75)
                        solution.getRandomAssignment().randomizeAssignment();
        }

        private void matePopulation(Vector<CandidateSolution> population, int numToMate) {
                Vector<CandidateSolution> children = new Vector<CandidateSolution>();
                Vector<CandidateSolution> alphaMates = new Vector<CandidateSolution>(population);
                alphaMates.setSize(numToMate);
                	
	                for (CandidateSolution alpha : alphaMates) {
	                        for (CandidateSolution popMember : population) {
	                                children.add(produceOffspring(alpha, popMember));
	                        }
	                }
                population.addAll(0, children);
        }

        private CandidateSolution produceOffspring(CandidateSolution parent1,CandidateSolution  parent2 ){
        	CandidateSolution child = new CandidateSolution(prefTable);
        	Random rand = new Random();
        	String assign;
        	
        	for(int i = 0; i < parent1.solutionSize(); i++){
        		if(rand.nextInt(1) == 0)
        			assign = parent1.getAssignmentAtIndex(i).getAssignment();
        		else
        			assign = parent2.getAssignmentAtIndex(i).getAssignment();
        		
        		child.getAssignmentAtIndex(i).setAssignment(assign);
        	}
        	mutate(child);
        	return child;
        }

        private void cullPopulation(Vector<CandidateSolution> population, int cut_off) {
                Collections.sort(population, new SolutionComparator());
                population.setSize(cut_off);
        }

        public CandidateSolution generateSolution() {
                int populationSize = 1000, maxIterations = 150,numberOfMates = 10, count = 0;
                Vector<CandidateSolution> population = generatePopulation(populationSize * 10);
                cullPopulation(population, populationSize);
                geneticSolution = population.firstElement();
                System.out.println(geneticSolution.getFitness());
                
                for (int i = 0; i <maxIterations && count < 20 ; i++) {

                        matePopulation(population, numberOfMates);
                        cullPopulation(population, populationSize);
        		if (geneticSolution.getFitness() >=  population.firstElement().getFitness())
        			count ++;
        		else
        			count = 0;

        		geneticSolution = population.firstElement();
                        System.out.println(geneticSolution.getFitness() + " = best sol num ->" + i);
                }
                return geneticSolution;
        }

        /**
         * Inspects current CandidateSolution for given SolutionType
         *
         * @return current CandidateSolutions energy
         */
        public int getBestSolutionEnergy() {
                return geneticSolution.getEnergy();
        }

        /**
         * Inspects current CandidateSolution for given SolutionType
         *
         * @return current CandidateSolutions fitness
         */
        public int getBestSolutionFitness() {
                return geneticSolution.getFitness();
        }
}


