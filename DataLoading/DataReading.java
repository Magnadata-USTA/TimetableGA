package DataLoading;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import Model.*;

import com.csvreader.CsvReader; 

public class DataReading{
	
	public Chromosome loadChromosome(){
		
		Chromosome c = new Chromosome();
		try {        
	        CsvReader baseTimetable_import = new CsvReader("C:\\Users\\Felipe\\IdeaProjects\\TimetableGA\\Data\\Dataset4\\Base Timetables.csv");
	        baseTimetable_import.readHeaders();	         
	        while (baseTimetable_import.readRecord())
	        {
	        	String sID = baseTimetable_import.get("semesterID");
	        	int semesterID = Integer.parseInt(sID);
				String mID = baseTimetable_import.get("moduleID");
				int moduleID = Integer.parseInt(mID);
				String pID = baseTimetable_import.get("professorID");
				int professorID = Integer.parseInt(pID);
				String cID = baseTimetable_import.get("courseID");
	        	char courseID = cID.charAt(0);
	        	String d = baseTimetable_import.get("day");
	        	int day = Integer.parseInt(d);
	        	String sT = baseTimetable_import.get("startTime");
	        	int startTime = Integer.parseInt(sT);       	         
	            c.addGene(new Gene(semesterID, moduleID, professorID, courseID, day, startTime));
	        }	         
	        baseTimetable_import.close();	         
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }		
		return c;		
	}
	
	public Faculty loadData(){
		
		Faculty c = new Faculty();

		try {
			
			CsvReader modules_import = new CsvReader("C:\\Users\\Felipe\\IdeaProjects\\TimetableGA\\Data\\Dataset4\\Modules.csv");
	        modules_import.readHeaders();
	        
	        CsvReader courses_import = new CsvReader("C:\\Users\\Felipe\\IdeaProjects\\TimetableGA\\Data\\Dataset4\\Courses.csv");
	        courses_import.readHeaders();

			CsvReader preferencesProfessors_import = new CsvReader("C:\\Users\\Felipe\\IdeaProjects\\TimetableGA\\Data\\Dataset4\\PreferencesProfessors.csv");
			preferencesProfessors_import.readHeaders();

			CsvReader preferencesFaculty_import = new CsvReader("C:\\Users\\Felipe\\IdeaProjects\\TimetableGA\\Data\\Dataset4\\PreferencesFaculty.csv");
			preferencesFaculty_import.readHeaders();

			CsvReader professors_import = new CsvReader("C:\\Users\\Felipe\\IdeaProjects\\TimetableGA\\Data\\Dataset4\\Professors.csv");
			professors_import.readHeaders();

			while (professors_import.readRecord())
			{
				String pID = professors_import.get("professorID");
				String name = professors_import.get("name");
				int professorID = Integer.parseInt(pID);
				c.addProfessor(new Professor(professorID, name));
			}

			ArrayList<Professor> professors =  c.getProfessors();

	        while (modules_import.readRecord())
	        {
	        	String mID = modules_import.get("moduleID");
	        	int moduleID = Integer.parseInt(mID);
	        	String sID = modules_import.get("semesterID");
	        	int semesterID = Integer.parseInt(sID);
	        	String name = modules_import.get("name");
	        	String du = modules_import.get("duration");
	        	int duration = Integer.parseInt(du);
	        	String hpw = modules_import.get("hoursPerWeek");
	        	int hoursPerWeek = Integer.parseInt(hpw);
	        	String group = modules_import.get("group");
	        	c.addModule(new Module(moduleID, semesterID, name, duration, hoursPerWeek, group));
	        }

			System.out.println();

			ArrayList<Module> modules = c.getModules();
			while(courses_import.readRecord())
			{
				String mID = courses_import.get("moduleID");
				int moduleID = Integer.parseInt(mID);
				String pID = courses_import.get("professorID");
				int professorID = Integer.parseInt(pID);
				String cID = courses_import.get("courseID");
				char courseID = cID.charAt(0);
				for(int i = 0 ; i < modules.size() ; i++) {
					if(modules.get(i).getModuleID() == moduleID) {
						c.addCourse(new Course(modules.get(i).getModuleID(), modules.get(i).getSemesterID(), modules.get(i).getName(), modules.get(i).getDuration(), modules.get(i).getHoursPerWeek(), modules.get(i).getGroup(), professorID, courseID));
						for(int j = 0 ; j < professors.size() ; j++) {
							if(professors.get(j).getProfessorID() == professorID){
								if(professors.get(j).getModules().size() == 0){
									professors.get(j).addModule(new Module(moduleID, modules.get(i).getSemesterID(), modules.get(i).getName(), modules.get(i).getDuration(), modules.get(i).getHoursPerWeek(), modules.get(i).getGroup()));
									//System.out.println(moduleID + " "+ professorID+" " +courseID);
								}
								for (int k = 0; k < professors.get(j).getModules().size(); k++) {
									if(professors.get(j).getModules().get(k).getModuleID() != moduleID) {
										professors.get(j).addModule(new Module(moduleID, modules.get(i).getSemesterID(), modules.get(i).getName(), modules.get(i).getDuration(), modules.get(i).getHoursPerWeek(), modules.get(i).getGroup()));
										//System.out.println(moduleID + " "+ professorID+" " +courseID);
									}
								}
							}
						}
						break;
					}
				}
			}

			while (preferencesProfessors_import.readRecord())
			{
				String pID = preferencesProfessors_import.get("professorID");
				int professorID = Integer.parseInt(pID);
				String d = preferencesProfessors_import.get("day");
				int day = Integer.parseInt(d);
				String sT = preferencesProfessors_import.get("startTime");
				int startTime = Integer.parseInt(sT);
				String v = preferencesProfessors_import.get("value");
				int value = Integer.parseInt(v);
				for(int i = 0 ; i < professors.size() ; i++){
					Professor professor = professors.get(i);
					if(professor.getProfessorID() == professorID){
						c.getProfessors().get(i).addPreference(new Preference(day, startTime, value));
						//System.out.println(i + " "+ professor.getPreferences().size());
					}
				}
			}

			while (preferencesFaculty_import.readRecord())
			{
				String d = preferencesFaculty_import.get("day");
				int day = Integer.parseInt(d);
				String sT = preferencesFaculty_import.get("startTime");
				int startTime = Integer.parseInt(sT);
				String v = preferencesFaculty_import.get("value");
				int value = Integer.parseInt(v);
				c.addPreference(new Preference(day, startTime, value));
				//System.out.println(c.getPreferences().size());
			}

			modules_import.close();
			courses_import.close();
			preferencesProfessors_import.close();
			preferencesFaculty_import.close();
			professors_import.close();

			} catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return c;
		}
}