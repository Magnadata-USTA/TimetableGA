package Model;
import java.util.ArrayList;


public class Faculty {
	private String name;
	private ArrayList<Module> modules;
	private ArrayList<Course> courses;
	private ArrayList<Professor> professors;
	
	public Faculty(String name) {
		this.name = name;
	}
	
	public Faculty() {
		modules = new ArrayList<Module>();
		courses = new ArrayList<Course>();
		professors = new ArrayList<Professor>();
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

	public ArrayList<Module> getModules(){
		return this.modules;
	}

	public ArrayList<Course> getCourses() {
		return this.courses;
	}

	public ArrayList<Professor> getProfessors(){
		return this.professors;
	}

}
