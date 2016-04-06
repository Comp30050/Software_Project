package Candidate;

import Data.StudentEntry;

public class CandidateAssignment {
	private StudentEntry candidate = null;
	private String currentAssignment, previousAssignment;
	
	public CandidateAssignment(StudentEntry student){
		candidate = student;
		randomizeAssignment();	
	}
	
	public void randomizeAssignment(){	//add most recent assignment to top of stack
		previousAssignment = currentAssignment;
		currentAssignment = candidate.getRandomPreference();		
	}
	
	public void undoChange(){
		if (previousAssignment != null){
			currentAssignment = previousAssignment;
			previousAssignment = null;
		}
	}
	
	public String getAssignment(){
		return currentAssignment;
	}
	
	public StudentEntry getStudent(){
		return candidate;
	}
	
	public int getEnergy(){
		int rank = candidate.getRanking(currentAssignment); 
		if (rank == -1) // if not on preference list give highest rank
			rank = 9;
		
		return  (rank + 1) * (rank + 1) ;
	}
	
	public String toString(){	// string repersenting student and his currently assignment
		return candidate.getStudentName() +" was assigned: < "+ getAssignment() + " >";
	}
}