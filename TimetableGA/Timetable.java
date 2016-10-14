package TimetableGA;
import java.util.ArrayList;


public class Timetable {

	private int semesterID;
	private ArrayList<Timeslot> timeslots; 
	
	public Timetable(int semesterID, ArrayList<Timeslot> timeslots) {
		this.semesterID = semesterID;
		this.setTimeslots(timeslots);
	}
	
	public Timetable(){
		
	}
	
	public int getSemesterID(){
		return this.semesterID;
	}

	public ArrayList<Timeslot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(ArrayList<Timeslot> timeslots) {
		this.timeslots = timeslots;
	}

}
