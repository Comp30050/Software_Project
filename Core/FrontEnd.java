package Core;

import Candidate.CandidateAssignment;
import Candidate.CandidateSolution;
import Data.PreferenceTable;

import java.io.FileWriter;
import java.io.IOException;

public class FrontEnd {
        public PreferenceTable pt;
        public CandidateSolution cs;

        public FrontEnd() {
                new GUI(this);
        }

        public void initializePrefTable(String file) {
                pt = new PreferenceTable(file);
        }


        public void initializeSearch(StochasticSearch ss) {
                this.cs = ss.generateSolution();
                System.out.println("Energy :D ="+ss.getBestSolutionEnergy());
        }

        public void initializeSearch(GeneticSearch gs) {
                this.cs = gs.generateSolution(10000);
                System.out.println("Energy :D ="+gs.getBestSolutionEnergy());
        }

        public void saveFileToOutput() {
                FileWriter fw;
                try {
                        fw = new FileWriter("Generated_student_to_project_mapping.tsv");
                        fw.append("Student Name:\tStudent Assignment:\tStudent Energy:(1-100)\n\n");
                        for(int i=0; i < cs.solutionSize(); i++) {
                                CandidateAssignment studAssignment = cs.getAssignmentAtIndex(i);

                                fw.append(studAssignment.getStudent().getStudentName() +"\t" +
                                        studAssignment.getAssignment() + "\t"
                                        + studAssignment.getEnergy() + "\t\n");
                        }
                        fw.append("\t\tTotal Energy:"+cs.getEnergy()+"\n");
                        fw.close();
                } catch (IOException e) {
                }
        }


        public static void main(String[] args) {
                new FrontEnd();
        }
}

