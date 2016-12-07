package TimetableGA;
import java.util.ArrayList;


public class Profesor {
	
	private int profesorID;
	private ArrayList<Module> modules;
	private ArrayList<Preference> preferences;
	
	public Profesor(int profesorID, ArrayList<Module> modules, ArrayList<Preference> preferences) {
		this.setProfesorID(profesorID);
		this.setModules(modules);
		this.setPreferences(preferences);
	}

	public int getProfesorID() {
		return profesorID;
	}

	public void setProfesorID(int profesorID) {
		this.profesorID = profesorID;
	}

	public ArrayList<Module> getModules() {
		return modules;
	}

	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}

	public ArrayList<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(ArrayList<Preference> preferences) {
		this.preferences = preferences;
	}
}
