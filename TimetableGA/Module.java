package TimetableGA;

public class Module {
	
	private int moduleID; 
	private int semesterID;
	private String name;
	private int duration;
	private int hoursPerWeek;
	private String group;
	
	public Module(int moduleID, int semesterID, String name, int duration, int hoursPerWeek, String group){
		this.setModuleID(moduleID);
		this.setSemesterID(semesterID);
		this.setName(name);
		this.setDuration(duration);
		this.setHoursPerWeek(hoursPerWeek);
		this.setGroup(group);		
	}

	public int getModuleID() {
		return moduleID;
	}

	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
	}

	public int getSemesterID() {
		return semesterID;
	}

	public void setSemesterID(int semesterID) {
		this.semesterID = semesterID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(int hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
