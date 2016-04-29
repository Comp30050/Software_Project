package Interfaces;

import Candidate.CandidateSolution;

public interface SolutionType {
        /**
         *Generates a candidate solution for the running algorithm.
         * @return The final candidate solution
         */
        CandidateSolution generateSolution();
        /**
         *Inspects current CandidateSolution for given SolutionType
         * @return current CandidateSolutions energy
         */
        int getBestSolutionEnergy();

        /**
         *Inspects current CandidateSolution for given SolutionType
         * @return current CandidateSolutions fitness
         */
        int getBestSolutionFitness();
}
