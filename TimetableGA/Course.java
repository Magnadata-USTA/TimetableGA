package TimetableGA;

public class Course extends Module {

	private char courseID;
	private int profesorID;
	
	public Course(int moduleID, int semesterID, String name, int duration, int hoursPerWeek, String group, int profesorID, char courseID) {
		super(moduleID, semesterID, name, duration, hoursPerWeek, group);
		this.courseID = courseID;
		this.profesorID = profesorID;		
	}

}
