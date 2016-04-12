
public class Module {
	
	private int moduleID; 
	private int semesterID;
	private String name;
	private int duration;
	private int hoursPerWeek;
	private String group;
	
	public Module(int moduleID, int semesterID, String name, int duration, int hoursPerWeek, String group){
		this.moduleID = moduleID;
		this.semesterID = semesterID;
		this.name = name;
		this.duration = duration;
		this.hoursPerWeek = hoursPerWeek;
		this.group = group;		
	}

}
