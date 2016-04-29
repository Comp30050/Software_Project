package Core;

import java.util.Random;

import Candidate.CandidateAssignment;
import Interfaces.SolutionType;
import Candidate.CandidateSolution;
import Data.PreferenceTable;

public class StochasticAlgorithm implements SolutionType {
        private int iterations;
        private PreferenceTable pt;
        private static double temperature;
        private double decrementVal;
        private CandidateSolution cs;

        public StochasticAlgorithm() {
        }

        public StochasticAlgorithm(PreferenceTable pt) {
                iterations = 100000;    //Decides how many iterations are done, i.e how many random changes are done
                this.pt = pt;
                this.cs = new CandidateSolution(this.pt);
                temperature = 100;
                this.decrementVal = temperature/iterations;
        }

        public CandidateSolution generateSolution() {
                int prevEnergy;
                while (temperature > 0) {
                        prevEnergy = this.cs.getEnergy();
                        makeRandomChange(prevEnergy);   //Makes a random change to the current CandidateSolution
                        temperature= temperature - decrementVal;
                }
                return this.cs;
        }

        /**
         * Randomly gets an assignment from the candidate solution and changes the current students assigned project.
         * If the call to the KeepRandomChange() method yields false then the change is reverted.
         * @param prevEnergy Energy of previous candidate solution.
         */
        private void makeRandomChange(int prevEnergy) {
                CandidateAssignment randomlyGottenAssignment = this.cs.getRandomAssignment();
                this.cs.getAssignmentFor(
                        randomlyGottenAssignment.getStudent().getStudentName() ).randomizeAssignment();

                //If change is rejected, revert it
                if (!keepRandomChange(prevEnergy)) {
                        this.cs.getAssignmentFor(
                                randomlyGottenAssignment.getStudent().getStudentName() ).undoChange();
                }
        }

        /**
         * 1) Keep the change immediately if the resultant energy is lower
         * 2) If energy is higher only accept with boltzmann function
         *
         * @return true to keep change, false to reject
         */
        private boolean keepRandomChange(int prevEnergy) {
                boolean result = false;
                Random generator = new Random();
                int rnd = generator.nextInt(100);

                if (this.cs.getEnergy() < prevEnergy) {
                        return true;
                }
                double changeInEnergy = (this.cs.getEnergy() - prevEnergy);
                double changeEOverT = changeInEnergy / temperature;
                double probability = 1 / Math.exp(changeEOverT);
                probability = 100 - (probability * 100);

                if (rnd >= (int) probability) {
                        result = true;
                }
                return result;
        }

        public int getBestSolutionEnergy() {
                return cs.getEnergy();
        }

        public int getBestSolutionFitness() {
                return cs.getFitness();
        }
}
