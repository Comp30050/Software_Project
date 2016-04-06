package Interfaces;

public interface SolutionType {
        /**
         *
         */
        void improveSolution();
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

        /**
         *@return running time to get best CandidateSolution
         */
        int getTotalRunningTime();
}
