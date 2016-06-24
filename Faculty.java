import java.util.ArrayList;


public class Faculty {
	
	private String name;
	private ArrayList<Timetable> timetables;
	
	public Faculty(String name) {
		this.name = name;
	}
	
	void addTimetable(int semesterID, ArrayList<Timeslot> timeslots){ 
		Timetable t = new Timetable(semesterID, timeslots);				
		ArrayList<Timetable> timetables = new ArrayList<Timetable>();
		timetables.add(t);
		this.timetables = timetables;
	}

}
