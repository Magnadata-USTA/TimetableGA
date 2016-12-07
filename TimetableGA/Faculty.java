package TimetableGA;
import java.util.ArrayList;


public class Faculty {
	
	private ArrayList<Module> modules;
	private ArrayList<Course> courses;
	private String name;
	private ArrayList<Timetable> timetables;
	
	public Faculty(String name) {
		this.name = name;
	}
	
	public Faculty() {
		modules = new ArrayList<Module>();
		courses = new ArrayList<Course>();
	
	}
	public void addModule(Module g){
		
		modules.add(g);
	}
	
	public void addCourse(Course g){
		
		courses.add(g);
	}
	
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
	
	public ArrayList<Module> getModules(){
		return this.modules;
	}
/*
	void addTimetable(int semesterID, ArrayList<Timeslot> timeslots){ 
		Timetable t = new Timetable(semesterID, timeslots);				
		ArrayList<Timetable> timetables = new ArrayList<Timetable>();
		timetables.add(t);
		this.timetables = timetables;
	}
	
	public Timetable getTimetable(int semesterID){
		Timetable ttables = new Timetable();
		int index;
		
		for (int i = 0; i < timetables.size(); i++) {
			System.out.println(timetables.get(i));
			//Timetable ttables = new Timetable();
			ttables = timetables.get(i);
			if (semesterID == ttables.getSemesterID()){
				System.out.println(semesterID + " hurra");
			}
		}
		
		index = timetables.indexOf(semesterID);
		System.out.println(index);
		//ttables = timetables.get(index);
		//System.out.println(ttables);
		return ttables;
	}
*/
	
}
