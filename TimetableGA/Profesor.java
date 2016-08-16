package TimetableGA;
import java.util.ArrayList;


public class Profesor {
	
	private int profesorID;
	private ArrayList<Module> modules;
	private ArrayList<Preference> preferences;
	
	public Profesor(int profesorID, ArrayList<Module> modules, ArrayList<Preference> preferences) {
		this.profesorID = profesorID;
		this.modules = modules;
		this.preferences = preferences;
	}
}
