package Data;

import java.io.*;
import java.util.*;

public class PreferenceTable {
	private Vector<Vector<String>> table = null;
	private	HashMap<String, StudentEntry> studentLookup =new HashMap<>();
	private Random rnd = new Random();
	
	public PreferenceTable(String filename){
		 table = loadContentFromFile(filename);	
	}
	 
	public PreferenceTable() {}
	
	/*Fills each student in the preference tables preferences with a random pref until they have maxPrefs
	 *@param maxprefs: number of preferences each student should have after method runs */
	public void fillPreferencesOfAll(int maxPrefs){
		for(StudentEntry student: getAllStudentEntries()){
			if (!student.hasPreassignedProject()){
				while(student.getOrderedPreferences().size() < maxPrefs){
					student.addProject(getRandomPreference());
				}
			}
		}
	}
	
	//returns a List of all student entries
	public Vector <StudentEntry> getAllStudentEntries(){
		return  new  Vector<StudentEntry>(studentLookup.values());
	}
	
	public StudentEntry getEntryFor(String sname){
		if (studentLookup.containsKey(sname)){
			return  studentLookup .get(sname);
		}
		
		return null;
	}
	
	//@return: a random student
	public StudentEntry getRandomStudent() {
		Vector<StudentEntry> allStudents = getAllStudentEntries();
		int randomStudentIndex = rnd.nextInt(allStudents.size() - 1);
		return allStudents.get(randomStudentIndex); 
	}
	
	/*chooses a random student, then one of those students preferences at random
	 * @return: a random preference
	 */
	public String getRandomPreference() {
		StudentEntry rndStudent;
		//makes sure preference isnt a preassigned one
		do{
			rndStudent = getRandomStudent();
		}while( rndStudent.hasPreassignedProject());
			
		return rndStudent.getRandomPreference(); 
	}
	
	private StudentEntry create_student_entry(Vector<String> student_data){
		Iterator<String> data_itr = student_data.iterator();
		
		StudentEntry student = new StudentEntry(data_itr.next());
		if (data_itr.next().equals("Yes")){
			student.preassignProject(data_itr.next());
		}
		
		else {
			data_itr.next();
			while(data_itr.hasNext()){
				student.addProject( data_itr.next());
			}
		}
		student.setNumberOfStatedPreferences();
		return student;
	}
	
	/*Adds a series of string tokens to a vector, which then in turn is added as an element of the targetTable
	 *@param tokens: series of string tokens to be added
	 *@param targetTable: a vector of string vectors which tokens are too be added to
	 */
	private void addTokensToTable(StringTokenizer tokens, Vector<Vector<String>> targetTable){
		Vector<String> tableElement = new Vector<String>();
		
		while (tokens.hasMoreTokens()){	
			tableElement.add(tokens.nextToken());	
		}
		targetTable.add(tableElement);
	}
	
	/*Reads a file  and converts its text to a preference table.  
	 * Each line is converted to a vector of strings and then stored in a 2d vector to form the preference table
	 * Each line vector is also made a StudentEntry and added to the studentLookup
	 * @param filename: target file to be converted (must be tabs deliminated)
	 * @return: 2d vector of string vectors forming preference table  */
	private Vector<Vector<String>> loadContentFromFile(String filename){
		Vector<Vector<String>> fileTable = new Vector<Vector<String>>();
		BufferedReader bufferedFile = FileIO.bufferFile(filename);
		String currentLine = FileIO.nextLine(bufferedFile);
		currentLine =  FileIO.nextLine(bufferedFile);
		
		while (currentLine != null) {
			StringTokenizer tokens = new StringTokenizer(currentLine,	"\t");
			addTokensToTable(tokens, fileTable);
			StudentEntry new_student = create_student_entry(fileTable.lastElement());
			studentLookup .put(new_student.getStudentName(),	new_student);
			currentLine =  FileIO.nextLine(bufferedFile);
		}
		return fileTable;
	}
	
	//prints table for testing purposes 
	public void displayTable(){
		for (Vector<String> line: table){
			System.out.println(line);
		}		
	}
}