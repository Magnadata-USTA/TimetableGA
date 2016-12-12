package TimetableGA;

public class Gene {
	
	private int semesterID;
	private int moduleID;
	private int profesorID;
	private char courseID;
	private int day;
	private int startTime;
	private int endTime;
	
	public Gene(){}
	
	public Gene(int semesterID, int moduleID,  int profesorID, char courseID, int day, int startTime) {
		this.semesterID = semesterID;
		this.moduleID = moduleID;
		this.profesorID = profesorID;
		this.courseID = courseID;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	int getSemesterID(){
		return semesterID;
	}
	public void seSemesterID(int semesterID) {
		this.semesterID = semesterID;
	}
	int getDay(){
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	int getStartTime(){
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	int getModuleID(){
		return moduleID;
	}
	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
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
	public int getEndTime() {
		return endTime;
	}


}
