package TimetableGA;

public class Course extends Module {

	private int profesorID;
	private String courseID;
	
	
	public Course(int moduleID, int semesterID, String name, int duration, int hoursPerWeek, String group, int profesorID, String courseID) {
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

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
		
}
