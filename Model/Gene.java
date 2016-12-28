package Model;

public class Gene implements Cloneable{
	
	private int semesterID;
	private int moduleID;
	private int profesorID;
	private char courseID;
	private int day;
	private int startTime;
	private int endTime;
	
	public Gene() {}

	public Gene clone(){
		Gene gene = new Gene();
		gene.setSemesterID(this.semesterID);
		gene.setModuleID(this.moduleID);
		gene.setProfesorID(this.profesorID);
		gene.setCourseID(this.courseID);
		gene.setDay(this.day);
		gene.setStartTime(this.startTime);
		return gene;
	}
	
	public Gene(int semesterID, int moduleID,  int profesorID, char courseID, int day, int startTime) {
		this.semesterID = semesterID;
		this.moduleID = moduleID;
		this.profesorID = profesorID;
		this.courseID = courseID;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getSemesterID(){
		return semesterID;
	}
	public void setSemesterID(int semesterID) {
		this.semesterID = semesterID;
	}
	public int getDay(){
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getStartTime(){
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getModuleID(){
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

	public boolean checkGeneValidity(){
		if(startTime >= Constraints.EARLIEST_TIME && startTime <= Constraints.LATEST_TIME){
			if(day >= Constraints.DAY_MIN && day <= Constraints.DAY_MAX){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
