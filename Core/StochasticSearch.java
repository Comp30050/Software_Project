package Core;

import java.util.Random;

import Candidate.CandidateAssignment;
import Interfaces.SolutionType;
import Candidate.CandidateSolution;
import Data.PreferenceTable;

public class StochasticSearch implements SolutionType {
        private int iterations;
        private PreferenceTable pt;
        private boolean flag = false;
        private int runningTime;        //Worry about this later
        private static double temperature;
        private double decrementVal;
        private CandidateSolution cs;

        public StochasticSearch() {
        }

        public StochasticSearch(PreferenceTable pt) {
                iterations = 100000;
                runningTime = 0;
                this.pt = pt;
                this.cs = new CandidateSolution(this.pt);

                temperature = 100;      //Edit the temperature here
                this.decrementVal = temperature/iterations;
        }

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
                        result = true;
                }
                double changeInEnergy = (this.cs.getEnergy() - prevEnergy);
                double changeEOverT = changeInEnergy/temperature;
                double probability = 1 / Math.exp(changeEOverT);
                probability = 100 - (probability * 100);

                if (rnd >= (int)probability) {
                        result = true;
                }
                return result;
        }

        
        public CandidateSolution generateSolution() {
                int prevEnergy;
                if (!flag) {
                        while (temperature > 0) {
                                prevEnergy = this.cs.getEnergy();
                                makeRandomChange(prevEnergy);
                                temperature= temperature - decrementVal;
                        }
                        flag = true;
                }
                return this.cs;
        }

        public CandidateSolution generateSolution(int maxIterations) {
                return null;
        }

        public int getBestSolutionEnergy() {
                return cs.getEnergy();
        }

        public int getBestSolutionFitness() {
                return cs.getFitness();
        }

        public int getTotalRunningTime() {
                return runningTime;
        }

}
