package TimetableGA;
import java.util.ArrayList;


public class Timetable {

	private int semesterID;
	private ArrayList<Timeslot> timeslots; 
	
	public Timetable(int semesterID, ArrayList<Timeslot> timeslots) {
		this.semesterID = semesterID;
		this.timeslots = timeslots;
	}
	
	public Timetable(){
		
	}
	
	public int getSemesterID(){
		return this.semesterID;
	}

}
