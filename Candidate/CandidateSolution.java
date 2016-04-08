package Candidate;

import Data.PreferenceTable;
import Data.StudentEntry;

import java.util.*;

public class CandidateSolution {
	private Random rnd = new Random();
	private HashMap<String, CandidateAssignment> solutionMap = new HashMap<String, CandidateAssignment>();
	private Vector<CandidateAssignment> solutionList = new Vector<CandidateAssignment>();
	private static final int PENELTY = 1000;
	
	/*Creates a list and hashmap of assignments for every student in preference table 
	 * (list for returning rand student) (hashmap for returning a spcific student )
	 *@param preferences: a preference table which the candidate solution will be based on  */
	public CandidateSolution(PreferenceTable preferences){
		for (StudentEntry student : preferences.getAllStudentEntries()){
			solutionList.add ( new CandidateAssignment(student));
			solutionMap.put (student.getStudentName() , solutionList.lastElement());
		}
	}
	
	public CandidateAssignment getAssignmentFor(String studentName){
		return solutionMap.get(studentName);
	}
	
	public CandidateAssignment replaceAssignmentAt(int i,CandidateAssignment replace){
		solutionList.set(i, replace);
		return solutionList.get(i);
	}
	
	public CandidateAssignment getAssignmentAtIndex(int i){
		return solutionList.get(i);
	}
	
	public int solutionSize(){
		return solutionList.size();
	}
	
	public CandidateAssignment getRandomAssignment(){
		return solutionList.get(rnd.nextInt(solutionList.size()));	
	}	
	
	public int getEnergy(){
		int energy = 0;
		HashMap<String, Integer> peneltyCheck = new HashMap<>();
		
		for(CandidateAssignment assignment : solutionList){
			if (peneltyCheck.containsKey(assignment.getAssignment()))
				energy += PENELTY;			
			
			energy += assignment.getEnergy();
			peneltyCheck.put(assignment.getAssignment(), 1);
		}
		return energy;
	}
	
	public int getFitness(){
		return -getEnergy();// as energy gets bigger fitness gets smaller
	}
	
}