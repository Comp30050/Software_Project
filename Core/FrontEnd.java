package Core;

import Candidate.CandidateAssignment;
import Candidate.CandidateSolution;
import Data.PreferenceTable;
import Interfaces.SolutionType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontEnd {
        public PreferenceTable pt;
        public CandidateSolution cs;

        public FrontEnd() {
                new GUI(this);
        }

        public void initializePrefTable(String file) {
                pt = new PreferenceTable(file);
        }

        //Polymorphically call generate solution
        public void initializeSearch(SolutionType ss) {
                this.cs = ss.generateSolution();
        }

        public void saveFileToOutput() {
                FileWriter fw;
                HashMap<Integer, Integer> projectAssignments;

                try {
                        fw = new FileWriter("Generated_student_to_project_mapping.tsv");
                        fw.append("Student Name:\tStudent Assignment:\tStudent Energy: 1[low]-100[high]\n\n");
                        for(int i=0; i < cs.solutionSize(); i++) {
                                CandidateAssignment studAssignment = cs.getAssignmentAtIndex(i);

                                fw.append(studAssignment.getStudent().getStudentName() +"\t" +
                                        studAssignment.getAssignment() + "\t"
                                        + studAssignment.getEnergy() + "\t\n");
                        }
                        fw.append("\t\tTotal Energy:"+cs.getEnergy()+"\n\n");

                        //Statistics based on how many students got their first assignment, second assignment etc...
                        projectAssignments = buildProjectsReceivedMap();
                        fw.append("Prefered project number: 1[most] - 10[least]\tNum students who got this preference:\n");
                        for(Map.Entry e : projectAssignments.entrySet()) {
                                if(e.getValue() != null) {
                                        fw.append(((Integer)e.getKey()+1)+"\t"+e.getValue()+"\n");
                                }
                        }
                        fw.close();
                } catch (IOException e) {
                }
        }

        private HashMap<Integer, Integer> buildProjectsReceivedMap() {
                HashMap<Integer, Integer> projectAssignments = new HashMap<>();
                for(int i=0; i < cs.solutionSize(); i++) {
                        CandidateAssignment candAssign = cs.getAssignmentAtIndex(i);
                        int studentPreferenceIndex = candAssign.getStudent().getRanking(candAssign.getAssignment());

                        if (projectAssignments.get(studentPreferenceIndex) == null) { //If no student got their i'th preference already
                                projectAssignments.put(studentPreferenceIndex, 1);
                        } else { //Otherwise someone already got their i'th preference so increment it by 1
                                projectAssignments.put(studentPreferenceIndex, projectAssignments.get(studentPreferenceIndex) + 1);
                        }
                }
                return projectAssignments;
        }

        public static void main(String[] args) {
                new FrontEnd();
        }
}

