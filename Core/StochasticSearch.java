package Core;

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

        public StochasticSearch(PreferenceTable pt, CandidateSolution cs, int max_attempts) {
                MAX_ALLOWED_ATTEMPTS = max_attempts;
                runningTime = 0;
                this.pt = pt;
                this.cs = cs;
                temperature = cs.getEnergy();

        }

        /**Assign a new project to a random student within the HashMap in "cs" field
         */
        public void improveSolution() {
                CandidateSolution tmp;
                if(!flag) {
                        while (this.attempts < MAX_ALLOWED_ATTEMPTS) {
                                tmp = this.cs;
                                makeRandomChange(tmp);
                        }
                        flag = true;
                }
        }

        private void makeRandomChange(CandidateSolution tmp) {
                tmp.getRandomAssignment().randomizeAssignment();
                if(keepRandomChange(tmp)) {
                        this.cs = tmp;
                        this.temperature = cs.getEnergy();
                }
        }


        /**1) Keep the change immediately if the resultant energy is lower
         * 2) If energy is higher only accept with boltzmann function
         *
         * @return true to keep change, false to reject
         */
        private boolean keepRandomChange(CandidateSolution temp) {
                boolean result = false;
                //TODO: Ben, Implement. Using the Boltzmann function

                return result;
        }

        public int getBestSolutionEnergy() {
                //TODO: Ben, Implement
                return 0;
        }

        public int getBestSolutionFitness() {
                //TODO: Ben, Implement
                return 0;
        }

        public int getTotalRunningTime() {
                //TODO: Ben, Implement
                return 0;
        }
}
