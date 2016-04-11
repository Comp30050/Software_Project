package Core;

import java.util.Random;

import Candidate.CandidateAssignment;
import Interfaces.SolutionType;
import Candidate.CandidateSolution;
import Data.PreferenceTable;

public class StochasticSearch implements SolutionType {
        private PreferenceTable pt;
        private boolean flag = false;
        private int runningTime;        //Worry about this later
        private static int temperature;
        private CandidateSolution cs;

        public StochasticSearch(PreferenceTable pt) {
                runningTime = 0;
                this.pt = pt;
                this.cs = new CandidateSolution(this.pt);

                temperature = 1000;      //Edit the temperature here
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
                } else {
                        double changeInEnergy = (this.cs.getEnergy() - prevEnergy);
                        double changeEOverT = changeInEnergy/temperature;
                        double probability = 1 / Math.exp(changeEOverT);
                        probability = 100 - (probability * 100);

                        if (rnd >= (int)probability) {
                                result = true;
                        }
                }
                return result;
        }

        //Ben maxIterations isn't needed, figure out a way to fix this
        public CandidateSolution generateSolution(int maxIterations) {
                int prevEnergy;
                if (!flag) {
                        while (temperature > 0) {
                                prevEnergy = this.cs.getEnergy();
                                makeRandomChange(prevEnergy);
                                temperature--;
                                System.out.println("Curr Energy: "+this.cs.getEnergy());
                        }
                        flag = true;
                }
                return this.cs;
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

        public static void main(String[] args) {
                PreferenceTable pt = new PreferenceTable("Project allocation data.txt");
                StochasticSearch stoch = new StochasticSearch(pt);

                stoch.generateSolution(50); // maxIterations is now redundant, talk to james about this
        }
}
