package DataLoading;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import TimetableGA.*;

import com.csvreader.CsvReader; 

public class DataReading{
	
	public Chromosome loadChromosome(){
		
		Chromosome c = new Chromosome();
		try {        
	        CsvReader baseTimetable_import = new CsvReader("D:\\Users\\lenovo\\Desktop\\Base Timetables.csv");
	        baseTimetable_import.readHeaders();	         
	        while (baseTimetable_import.readRecord())
	        {
	        	String sID = baseTimetable_import.get("semesterID");
	        	int semesterID = Integer.parseInt(sID);
	        	String cID = baseTimetable_import.get("courseID");
	        	char courseID = cID.charAt(0);
	        	/*_________________________________________________________________*/System.out.println(courseID);
	        	String mID = baseTimetable_import.get("moduleID");
	        	int moduleID = Integer.parseInt(mID);
	        	String d = baseTimetable_import.get("day");
	        	int day = Integer.parseInt(d);
	        	String sT = baseTimetable_import.get("startTime");
	        	int startTime = Integer.parseInt(sT);       	         
	            c.addGene(new Gene(semesterID, courseID, day, startTime, moduleID)); 	            	        
	        }	         
	        baseTimetable_import.close();	         
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }		
		return c;		
	}
	
	public Faculty loadModules(){
		
		Faculty c = new Faculty();
		try {	        
	        CsvReader module_import = new CsvReader("D:\\Users\\lenovo\\Desktop\\Modules.csv");	        
	        module_import.readHeaders();
	        
	        CsvReader course_import = new CsvReader("D:\\Users\\lenovo\\Desktop\\Courses.csv");
	        course_import.readHeaders();

	        while (module_import.readRecord())
	        {
	        	String mID = module_import.get("moduleID");
	        	int moduleID = Integer.parseInt(mID);
	        	String sID = module_import.get("semesterID");
	        	int semesterID = Integer.parseInt(sID);
	        	String name = module_import.get("name");
	        	String du = module_import.get("duration");
	        	int duration = Integer.parseInt(du);
	        	String hpw = module_import.get("hoursPerWeek");
	        	int hoursPerWeek = Integer.parseInt(hpw);
	        	String group = module_import.get("group"); 
	        	System.out.println("VERIFICACION DELL GRUPO"+group);
	        	c.addModule(new Module(moduleID, semesterID, name, duration, hoursPerWeek, group));
	        	
	        }
	        
	        		           
	        	ArrayList<Module> modules = c.getModules();
	        	int [] modulesID = new int[modules.size()];
	        	for(int i = 0 ; i < modules.size() ; i++){
	        		modulesID[i] = modules.get(i).getModuleID();
	        	}
	        	System.out.println("prueba");
	        	
	        	while(course_import.readRecord())
	        	{
		        	String mID2 = course_import.get("moduleID2");
		        	int moduleID2 = Integer.parseInt(mID2);
		        	//System.out.println(moduleID2);
		        	String pr = course_import.get("profesorID");
		        	int profesorID = Integer.parseInt(pr);
		        	String cID = course_import.get("courseID");
		        	/*_________________________________________________________________*/System.out.println(" 1 "+cID);
		        	char courseID = cID.charAt(0);
		        	/*_________________________________________________________________*/System.out.println(" 2 "+courseID);
	            	for( int i = 0 ; i < modulesID.length ; i++){
			        	if (modulesID[i] == moduleID2){
		            		c.addCourse(new Course(modules.get(i).getModuleID(), modules.get(i).getSemesterID(), modules.get(i).getName(), modules.get(i).getDuration(), modules.get(i).getHoursPerWeek(), modules.get(i).getGroup(), profesorID, courseID));
		            		//System.out.println(moduleID2 + " "+ courseID);	
		            		break;
		            	}	
			        }		        
	        	}
	        	        
	        System.out.println();
	        module_import.close();	        
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return c;
		}}