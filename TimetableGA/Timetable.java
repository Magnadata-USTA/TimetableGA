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

	public boolean checkTimeslotAvailability(Timeslot timeslot){
 		int semesterID = timeslot.getSemesterID();
 		int moduleID = timeslot.getModuleID();
 		int groupID = timeslot.getGroupID();
 		int day = timeslot.getDay();
 		int startTime = timeslot.getStartTime();
 		
 		for ( int i = 0 ; i < timeslots.size() ; i++ ){
 			if ( semesterID ==  ){}
 		}
 		
 		return true; 		
 	}
}
