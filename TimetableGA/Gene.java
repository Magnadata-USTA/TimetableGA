package TimetableGA;

public class Gene {
	
	private int semesterID;
	private int moduleID;
	private int profesorID;
	private char groupID;
	private int day;
	private int startTime;
	private int endTime;
	
	public Gene(int semesterID, /*int moduleID, int profesorID, char groupID,*/ int day, int startTime, /*int endTime,*/int moduleID) {
		//semesterID, day, startTime, moduleID)
		this.semesterID = semesterID;
		this.moduleID = moduleID;
		this.profesorID = profesorID;
		this.groupID = groupID;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Gene(){
		
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

	public char getGroupID() {
		return groupID;
	}
	public void setGroupID(char groupID) {
		this.groupID = groupID;
	}
	public int getEndTime() {
		return endTime;
	}

}
