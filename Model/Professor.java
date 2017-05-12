package Model;
import java.util.ArrayList;


public class Professor {
	
	private int professorID;
	private String name;
	private ArrayList<Module> modules;
	private ArrayList<Preference> preferences;

	public Professor(int professorID, String name) {
		this.professorID = professorID;
		this.name = name;
		this.modules = new ArrayList<Module>();
		this.preferences = new ArrayList<Preference>();
	}

	public int getProfessorID() {
		return professorID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean checkProfessorSoftConstraints(Gene gene){
		int day = gene.getDay();
		int starTime = gene.getStartTime();
		int value = 1;

		for ( int i = 0 ; i < this.preferences.size() ; i++ ){
			if (day == this.preferences.get(i).getDay()){
				if(starTime == this.preferences.get(i).getStartTime()){
					if (value == this.preferences.get(i).getValue()){
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkProfessorDesirableAvailability(Gene gene){
		int day = gene.getDay();
		int starTime = gene.getStartTime();
		int value = 0;

		for ( int i = 0 ; i < this.preferences.size() ; i++ ){
			if (day == this.preferences.get(i).getDay()){
				if(starTime == this.preferences.get(i).getStartTime()){
					if (value == this.preferences.get(i).getValue()){
						return true;
					}
				}
			}
		}
		return false;
	}
}
