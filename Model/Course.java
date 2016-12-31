package Model;

public class Course extends Module {

	private int profesorID;
	private char courseID;
	
	
	public Course(int moduleID, int semesterID, String name, int duration, int hoursPerWeek, String group, int profesorID, char courseID) {
		super(moduleID, semesterID, name, duration, hoursPerWeek, group);
		this.setCourseID(courseID);
		this.setProfesorID(profesorID);		
	}

	public int getProfesorID() {
		return profesorID;
	}

	public void setProfesorID(int profesorID) {
		this.profesorID = profesorID;
	}

	public char getCourseID() {
		return courseID;
	}

	public void setCourseID(char courseID) {
		this.courseID = courseID;
	}

		
}
