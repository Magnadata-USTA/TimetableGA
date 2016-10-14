package TimetableGA;

public class Course extends Module {


	private int moduleID2;
	private int profesorID;
	private String courseID;
	
	public Course(int moduleID, int semesterID, String name, int duration, int hoursPerWeek, String group, int moduleID2, int profesorID, String courseID) {
		super(moduleID, semesterID, name, duration, hoursPerWeek, group);
		this.setModuleID2(moduleID2);
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
	
	public int getModuleID2() {
		return moduleID2;
	}

	public void setModuleID2(int moduleID2) {
		this.moduleID2 = moduleID2;
	}

	
}
