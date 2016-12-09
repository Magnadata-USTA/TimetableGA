package TimetableGA;

public class Timeslot {

	private int semesterID;
	private int moduleID;
	private int profesorID;
	private char courseID;
	private int day;
	private int startTime;
	private int endTime;
	
	
public Timeslot(int semesterID, int moduleID, int profesorID, char courseID, int day, int startTime, int endTime) {
	this.setSemesterID(semesterID);
	this.setModuleID(moduleID);
	this.setProfesorID(profesorID);
	this.setcourseID(courseID);
	this.setDay(day);
	this.setStartTime(startTime);
	this.setEndTime(endTime);
}

public int getSemesterID() {
	return semesterID;
}

public void setSemesterID(int semesterID) {
	this.semesterID = semesterID;
}

public int getModuleID() {
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

public char getcourseID() {
	return courseID;
}

public void setcourseID(char courseID) {
	this.courseID = courseID;
}

public int getDay() {
	return day;
}

public void setDay(int day) {
	this.day = day;
}

public int getStartTime() {
	return startTime;
}

public void setStartTime(int startTime) {
	this.startTime = startTime;
}

public int getEndTime() {
	return endTime;
}

public void setEndTime(int endTime) {
	this.endTime = endTime;
}



}
