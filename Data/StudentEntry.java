package Data;

import java.util.*;

public class StudentEntry {
	private Vector <String> preferences = new Vector<String>();
	private String name;
	private int initial_num_of_pref = 0;
	private boolean isPreassigned =false;
	private Random rnd = new Random();
	
	StudentEntry(String input_name){
		name = input_name;
	}
	
	public String getStudentName(){
		return name;
	}
	
	public String getRandomPreference() {			//@return random preference cantained in preferences
		Vector<String> pref = getOrderedPreferences();
		int rndIndex = rnd.nextInt(pref.size());
		return pref.get(rndIndex);
	}
	
	public boolean hasPreference(String preference){
		return  getOrderedPreferences().contains(preference);
	}
	
	public Vector<String> getOrderedPreferences(){
		return preferences;
	}
	
	public void preassignProject(String	pname){
		preferences.clear();
		addProject(pname);
		isPreassigned = true;
	}
	
	public boolean hasPreassignedProject(){
		return isPreassigned;
	}	
	
	public void setNumberOfStatedPreferences(){
		initial_num_of_pref = preferences.size();	
	}
	
	public int getNumberOfStatedPreferences(){
		return initial_num_of_pref;
	}
	
	public void addProject(String	pname){
		if (!isPreassigned && !hasPreference(pname)){
			preferences.add(pname.intern());
		}
	}
	
	public int getRanking(String pname){
		return preferences.indexOf(pname); //finds position in preference list
	}
	 
	public String toString(){
		return ("\nStudent name: " + name+ "\nPreferences: " +  preferences + "\n");
	}
}