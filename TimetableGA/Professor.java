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
}
