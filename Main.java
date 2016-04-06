import Candidate.CandidateSolution;
import Data.PreferenceTable;

import java.io.*;

public class Main {

	//loads test file to a table and prints for testing purposes 
	public static void main(String[] args) {
		String filename = "Data"+File.separator+"Project_allocation_data.tsv";
		PreferenceTable test = new PreferenceTable(filename);
		test.fillPreferencesOfAll(10);
		CandidateSolution testSolution = new CandidateSolution(test);
		System.out.println("Solution energy: "+ testSolution.getEnergy());
		System.out.println("Solution fitness: " + testSolution.getFitness());
		
	}
}
