import Candidate.CandidateSolution;
import Core.GeneticSearch;
import Data.PreferenceTable;

import java.io.*;

public class Main {

	//loads test file to a table and prints for testing purposes 
	public static void main(String[] args) {
		//String filename = "Data"+File.separator+"Project allocation data.tsv";
		PreferenceTable test = new PreferenceTable("Project allocation data.tsv");
		test.fillPreferencesOfAll(10);
		CandidateSolution testSolution = new CandidateSolution(test);
		System.out.println("Solution energy: "+ testSolution.getEnergy());
		System.out.println("Solution fitness: " + testSolution.getFitness());
		
		GeneticSearch test1 = new GeneticSearch(test);
		CandidateSolution best = test1.generateSolution();
		System.out.print(best.getFitness());
	}
}
