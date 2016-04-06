package Core;

import Candidate.CandidateSolution;
import Data.PreferenceTable;

public class Core {
        private CandidateSolution cs;
        private PreferenceTable pt;

        public Core() {
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
}
