import Candidate.CandidateSolution;
import Core.GeneticSearch;
import Data.PreferenceTable;

import java.io.*;

public class Main {

	//loads test file to a table and prints for testing purposes 
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String filename = "Data"+File.separator+"Project_allocation_data.tsv";
		PreferenceTable test = new PreferenceTable(filename);
		test.fillPreferencesOfAll(10);
		CandidateSolution testSolution = new CandidateSolution(test);
		System.out.println("Solution energy: "+ testSolution.getEnergy());
		System.out.println("Solution fitness: " + testSolution.getFitness());
		
		GeneticSearch test1 = new GeneticSearch(test);
		CandidateSolution best = test1.generateSolution();
		System.out.println(best.getFitness());
		
		
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime/1000);
	}
}
