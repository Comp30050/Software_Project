package Core;

import Candidate.CandidateSolution;
import Data.PreferenceTable;

import javax.swing.*;

public class Core {
        private static final int MAX_ITERATIONS = 1000;
        private PreferenceTable pt;
        private GeneticSearch gs;
        private StochasticSearch ss;
        private MouseListen ml;
        private GUI gui;

        public Core() {
                gs = new GeneticSearch(pt);
                ss = new StochasticSearch(pt);
                gui = new GUI(this);
                ml = new MouseListen(this);
                pt = new PreferenceTable("Project allocation.txt");     //Get GUI to pass file name later....maybe....#Not arsed atm

                //Will instantiate a GUI class here
                /*
                   while GUI not closed {
                       if GUI app sends a new algorithm request{
                                get the algorithm preference
                                get the input file
                                pt = new PreferenceTable(file specified);
                                cs = new CandidateSolution();

                                if it's a genetic {
                                        geneticsearch =  new GeneticSearch(pt, cs);
                                        while( still haven't generate the best solution ) {
                                                make it better
                                        }
                                } else {
                                        stochasticsearch = new StochasticSearch(pt, cs);
                                        while( still haven't generate the best solution ) {
                                                make it better
                                        }
                                }
                       }
                  }
                 */
        }

        public void rightButtonClicked(Object source) {
        }

        public void leftButtonClicked(Object o) {
                CandidateSolution cs;
                JButton button =null;

                if(o instanceof JButton) {
                        button = (JButton)o;
                        if(button == gui.getExitButton()) {
                                System.exit(0);
                        }
                } else {
                        String algorithm = gui.getAlgorithm();
                        if(algorithm.equalsIgnoreCase("Simulated Annealing")) {
                                cs = ss.generateSolution();
                        } else {
                                cs = gs.generateSolution(MAX_ITERATIONS);
                        }
                }

        }

        public static void main(String[] args) {
                new Core();
        }
}
