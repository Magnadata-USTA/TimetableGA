package Model;
import java.util.ArrayList;


public class Faculty {
	private String name;
	private ArrayList<Module> modules;
	private ArrayList<Course> courses;
	private ArrayList<Professor> professors;
	private ArrayList<Preference> preferences;
	
	public Faculty(String name) {
		this.name = name;
	}
	
	public Faculty() {
		modules = new ArrayList<Module>();
		courses = new ArrayList<Course>();
		professors = new ArrayList<Professor>();
		preferences = new ArrayList<Preference>();
	}
	public void addModule(Module module){
		modules.add(module);
	}
	
	public void addCourse(Course course){
		courses.add(course);
	}

	public void addProfessor(Professor professor){
		professors.add(professor);
	}

	public void addPreference(Preference preference){
		preferences.add(preference);
	}

	public ArrayList<Module> getModules(){
		return this.modules;
	}

	public ArrayList<Course> getCourses() {
		return this.courses;
	}

	public ArrayList<Professor> getProfessors(){
		return this.professors;
	}

	public ArrayList<Preference> getPreferences() {
		return preferences;
	}

	public boolean checkFacultyAvailability(Gene gene){
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

	public boolean checkFacultySoftConstraints(Gene gene){
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


}
