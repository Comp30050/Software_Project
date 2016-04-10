package Interfaces;
import java.util.Comparator;

import Candidate.CandidateSolution;

public class SolutionComparator implements Comparator<CandidateSolution>{

	public int compare(CandidateSolution sol1, CandidateSolution sol2) {
	    return  sol2.getFitness() - sol1.getFitness();
	}

}
