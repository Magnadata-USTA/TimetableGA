package DataLoading;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import TimetableGA.*;

import com.csvreader.CsvReader; 

public class RTimetable{
	
	public Chromosome loadChromosome(){
		
		Chromosome c2 = new Chromosome();
		try {
	        
	        List<Timeslot> usuarios = new ArrayList<Timeslot>();
	        //ArrayList<Gene> genes = new ArrayList<Gene>();
	        //Chromosome c2 = new Chromosome(); 
	        CsvReader usuarios_import = new CsvReader("D:\\Users\\lenovo\\Desktop\\firstsemester.csv");
	        usuarios_import.readHeaders();
	         
	        while (usuarios_import.readRecord())
	        {
	        	String sID = usuarios_import.get("semesterID");
	        	int semesterID = Integer.parseInt(sID);
	        	String mID = usuarios_import.get("moduleID");
	        	int moduleID = Integer.parseInt(mID);
	        	String pID = usuarios_import.get("profesorID");
	        	int profesorID = Integer.parseInt(pID);
	        	String gID = usuarios_import.get("groupID");
	        	char groupID = gID.charAt(0);
	        	String d = usuarios_import.get("day");
	        	int day = Integer.parseInt(d);
	        	String sT = usuarios_import.get("startTime");
	        	int startTime = Integer.parseInt(sT);
	        	String eT = usuarios_import.get("endTime");
	        	int endTime = Integer.parseInt(eT);        	             
	            usuarios.add(new Timeslot(semesterID, moduleID, profesorID, groupID, day, startTime, endTime)); 
	            c2.addGene(new Gene(semesterID, day, startTime, moduleID)); 	            	        
	        }
	         
	        usuarios_import.close();
	         
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		return c2;
		
	}
}
