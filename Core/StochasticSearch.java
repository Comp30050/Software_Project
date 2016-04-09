package Core;

import java.util.Random;
import Interfaces.SolutionType;
import Candidate.CandidateSolution;
import Data.PreferenceTable;

public class StochasticSearch implements SolutionType {
        private static int MAX_ALLOWED_ATTEMPTS;
        private int attempts = 0;
        private PreferenceTable pt;
        private boolean flag = false;
        private int runningTime;        //Worry about this later
        private int temperature;
        //this is the solution we will be modifying to eventually obtain a final solution
        private CandidateSolution cs;

        public StochasticSearch(PreferenceTable pt, int max_attempts) {
                MAX_ALLOWED_ATTEMPTS = max_attempts;
                runningTime = 0;
                this.pt = pt;
                this.cs = new CandidateSolution(this.pt);
                temperature = cs.getEnergy();

        }

        /**
         * Assign a new project to a random student within the HashMap in "cs" field
         */
        public void improveSolution() {
                CandidateSolution tmp;
                if (!flag) {
                        while (this.attempts < MAX_ALLOWED_ATTEMPTS) {
                                tmp = this.cs;
                                makeRandomChange(tmp);
                                attempts++;
                        }
                        flag = true;
                }
        }

        private void makeRandomChange(CandidateSolution tmp) {
                tmp.getRandomAssignment().randomizeAssignment();
                if (keepRandomChange(tmp)) {
                        System.out.println(cs.getEnergy() +": "+attempts);
                        this.cs = tmp;
                        this.temperature = cs.getEnergy();
                }
        }

        /**
         * 1) Keep the change immediately if the resultant energy is lower
         * 2) If energy is higher only accept with boltzmann function
         *
         * @return true to keep change, false to reject
         */
        private boolean keepRandomChange(CandidateSolution temp) {
                boolean result = false;
                Random generator = new Random();
                int rnd = generator.nextInt(100);

                if (temperature > temp.getEnergy()) {
                        result = true;
                } else {
                        double changeInEnergy = (temp.getEnergy() - temperature);
                        double changeEOverT = changeInEnergy/temperature;
                        double probability = 1 / Math.exp(changeEOverT);
                        probability = probability * 100;
                        
                        if (rnd <= (int)probability) {
                                result = true;
                        }
                }
                return result;
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
                StochasticSearch stoch = new StochasticSearch(pt, 1000);

                stoch.improveSolution();
        }
}
