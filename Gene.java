
public class Gene {
	
	private int day;
	private int startTime;
	private int moduleID;
	private int semesterID;
	
	public Gene(int semesterID, int day, int startTime, int moduleID) {
		this.semesterID = semesterID;
		this.day = day;
		this.startTime = startTime;
		this.moduleID = moduleID;
	}
	
	public Gene(){
		
	}
	
	int getSemesterID(){
		return semesterID;
	}
	
	int getDay(){
		return day;
	}
	int getStartTime(){
		return startTime;
	}
	int getModuleID(){
		return moduleID;
	}
}
