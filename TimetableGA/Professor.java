package TimetableGA;
import java.util.ArrayList;


public class Professor {
	
	private int professorID;
	private ArrayList<Module> modules;
	private ArrayList<Preference> preferences;

	public Professor(int professorID) {
		this.professorID = professorID;
		this.modules = new ArrayList<Module>();
		this.preferences = new ArrayList<Preference>();
	}

	public int getProfessorID() {
		return professorID;
	}

	public void addModule(Module module){
		this.modules.add(module);
	}

	public ArrayList<Module> getModules() {
		return modules;
	}

	public void addPreference(Preference preference){
		this.preferences.add(preference);
	}

	public ArrayList<Preference> getPreferences() {
		return preferences;
	}

	public boolean checkProfessorAvailability(Gene gene){
		int day = gene.getDay();
		int starTime = gene.getStartTime();
		int value = 2;

		for ( int i = 0 ; i < this.preferences.size() ; i++ ){
			if (day == this.preferences.get(i).getDay()){
				if(starTime == this.preferences.get(i).getStartTime()){
					if (value == this.preferences.get(i).getValue()){
						return false;
					}
				}
			}
		}
		return true;
	}
}
