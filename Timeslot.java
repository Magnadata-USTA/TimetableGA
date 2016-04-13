
public class Timeslot {

		private int moduleID;
		private int profesorID;
		private char groupID;
		private int day;
		private int startTime;
		private int endTime;		
		
	public Timeslot(int moduleID, int profesorID, char groupID, int day, int startTime, int endTime) {
		this.moduleID = moduleID;
		this.profesorID = profesorID;
		this.groupID = groupID;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

}
