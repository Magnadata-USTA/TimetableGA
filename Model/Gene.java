package Model;

public class Gene implements Cloneable{
	
	private int semesterID;
	private int moduleID;
	private int professorID;
	private char courseID;
	private int day;
	private int startTime;

	public Gene() {}

	public Gene clone(){
		Gene gene = new Gene();
		gene.setSemesterID(this.semesterID);
		gene.setModuleID(this.moduleID);
		gene.setProfessorID(this.professorID);
		gene.setCourseID(this.courseID);
		gene.setDay(this.day);
		gene.setStartTime(this.startTime);
		return gene;
	}
	
	public Gene(int semesterID, int moduleID,  int professorID, char courseID, int day, int startTime) {
		this.semesterID = semesterID;
		this.moduleID = moduleID;
		this.professorID = professorID;
		this.courseID = courseID;
		this.day = day;
		this.startTime = startTime;
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
	public int getProfessorID() {
		return professorID;
	}
	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}
	public char getCourseID() {
		return courseID;
	}
	public void setCourseID(char courseID) {
		this.courseID = courseID;
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
