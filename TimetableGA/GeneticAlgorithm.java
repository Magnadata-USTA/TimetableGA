package TimetableGA;


import java.awt.image.CropImageFilter;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import DataLoading.DataReading;

public class GeneticAlgorithm {
	//private Arrays population;
	private int numGenerations;
	private int sizeElite;
	private ArrayList<Chromosome> chromosomes;

	
	public GeneticAlgorithm(int sizeElite, int numGenerations){
		this.sizeElite = sizeElite;
		this.numGenerations = numGenerations;	
	}
	
	
	public static void main (String args[]){
		GeneticAlgorithm ga = new GeneticAlgorithm(1000,5);
		System.out.println("size elite " + Integer.toString(ga.sizeElite) + " size Gen " + ga.numGenerations);
		Chromosome baseChromosome = new Chromosome();
		Faculty teleco = new Faculty();
		DataReading baseTimetable = new DataReading();
		baseChromosome = baseTimetable.loadChromosome();
	    teleco = baseTimetable.loadModules();
	    
	    
	    int fitness = baseChromosome.fitness();	
	    System.out.println("Fitness value is = "+fitness);
		ga.algorithm();
		ga.initiatePopulation(1000);
	}

	
	public void algorithm(){
		ArrayList<Course> Courses = new ArrayList<Course>();
		//Courses = dataLoaderCourses();
		//add timetables BaseChromosome; Base solution given by others departments
		//Create a new faculty
	    Faculty teleco = new Faculty("IngenierÃ­a de Telecomunicaciones");				
		//Load faculty data						
		//Timeslot(int moduleID, int profesorID, char groupID, int day, int startTime, int endTime)	
	    //Chromosome aye = new Chromosome();
	    //aye.fitness();
	    
	}
	
	void initiatePopulation(int size/*, Chromosome baseChromosome*/){ 
		
		ArrayList<Gene> ipx = new ArrayList<Gene>();		
		Chromosome c = new Chromosome();
		
		/*System.out.println("gene"+c.getGene().get(1).getDay());
		System.arraycopy(c.getGene(), 0, ipx, 0, c.getGene().size());
		
		System.out.println("ipx"+ipx.size());
		int numDays = Constraints.DAY_MAX;
 		int numTimeslots = ((Constraints.HR_MAX-Constraints.HR_MIN)/2);
 		Gene [][] genesSemester = new Gene[numTimeslots][numDays];
 		int [][] genesModuleID = new int[numTimeslots][numDays];
 		for (int i = 0; i < ipx.size(); i++) {			
 			Gene y = new Gene();
 		    y = ipx.get(i);
 			int semester;
 			int day;
 			int startTime;
 			startTime = y.getStartTime();
 			System.out.println("stratime"+startTime);
 			day = y.getDay();
 			semester = y.getSemesterID();
 			if (1 == semester){
 				int timeslotsValue;
 				timeslotsValue = (startTime-Constraints.HR_MIN)/2;
 				genesSemester[timeslotsValue][day-1] = y;
 				genesModuleID[timeslotsValue][day-1] = y.getModuleID();
 				}
 		}
 		System.out.println("empieza prueba");
 		//The next 'for' shows the timetable on the screen
 		for (int x=0; x < genesSemester.length; x++) {
 			  System.out.print("|");
 			  for (int yy=0; yy < genesSemester[x].length; yy++) {
 			    System.out.print (genesModuleID[x][yy]);
 			    if (yy!=genesSemester[x].length) System.out.print("\t\t");
 			  }
 			  System.out.println("|");
 		}
 		System.out.print("termina prueba");*/
 		
 		Course q = new Course();
 		
 		int timeslot = 0;
 		//int hoursPerWeek = 0;
 		//int duration = 0;
 		//duration = q.getModules().get(1).getDuration();
		//hoursPerWeek = q.getModules().get(1).getHoursPerWeek();
 		
 		timeslot = q.getHoursPerWeek();
 		System.out.println(timeslot+"talegurropiteresdemujer");
 		
 		
 		/*for( int i = 0 ; i < q.length ; i++){
 			
 			timeslot = q.getHoursPerWeek();
 			
 	 		for( int j = 0 ; j < timeslot ; j++){
 	 			
 	 			
 	 		}
 			
 		}*/
 		
 		
		
		
		
	}
	
	void orderByValue(){
		
	}
	
/*
	
	ArrayList<Profesor> dataLoaderProfesors(){
		//Load data
		ArrayList<Profesor> profesors = new ArrayList<Profesor>();
		ArrayList<Module> modules = new ArrayList<Module>();
		modules = dataLoaderModules();
		
		//Profesor 1 data
		ArrayList<Module> modulesP1 = new ArrayList<Module>();
		modulesP1.add( modules.get(0));
		modulesP1.add( modules.get(5));
		modulesP1.add( modules.get(11));
		ArrayList<Preference> preferencesP1 = new ArrayList<Preference>();
		preferencesP1.add(new Preference(1,6,0));
		Profesor p1 = new Profesor(51350111, modulesP1, preferencesP1);
		profesors.add(p1);

		//Profesor 2 data
		ArrayList<Module> modulesP2 = new ArrayList<Module>();
		modulesP2.add( modules.get(1));
		modulesP2.add( modules.get(6));
		modulesP2.add( modules.get(13));
		ArrayList<Preference> preferencesP2 = new ArrayList<Preference>();
		preferencesP2.add(new Preference(1,6,0));
		Profesor p2 = new Profesor(51350222, modulesP2, preferencesP2);
		profesors.add(p2);
		
		//Profesor 3 data
		ArrayList<Module> modulesP3 = new ArrayList<Module>();
		modulesP3.add( modules.get(2));
		modulesP3.add( modules.get(7));
		ArrayList<Preference> preferencesP3 = new ArrayList<Preference>();
		preferencesP3.add(new Preference(1,6,0));
		Profesor p3 = new Profesor(80876111, modulesP3, preferencesP3);
		profesors.add(p3);		
		
		//Profesor 4 data
		ArrayList<Module> modulesP4 = new ArrayList<Module>();
		modulesP4.add( modules.get(3));
		ArrayList<Preference> preferencesP4 = new ArrayList<Preference>();
		preferencesP3.add(new Preference(1,6,0));
		Profesor p4 = new Profesor(6542222, modulesP4, preferencesP4);
		profesors.add(p4);		
		
		//Profesor 5 data
		ArrayList<Module> modulesP5 = new ArrayList<Module>();
		modulesP5.add( modules.get(4));
		modulesP5.add( modules.get(10));
		ArrayList<Preference> preferencesP5 = new ArrayList<Preference>();
		preferencesP5.add(new Preference(1,6,0));
		Profesor p5 = new Profesor(20305111, modulesP5, preferencesP5);
		profesors.add(p5);		
		
		//Profesor 6 data
		ArrayList<Module> modulesP6 = new ArrayList<Module>();
		modulesP6.add( modules.get(8));
		ArrayList<Preference> preferencesP6 = new ArrayList<Preference>();
		preferencesP6.add(new Preference(1,6,0));
		Profesor p6 = new Profesor(20305222, modulesP6, preferencesP6);
		profesors.add(p6);	

		//Profesor 7 data
		ArrayList<Module> modulesP7 = new ArrayList<Module>();
		modulesP7.add( modules.get(9));
		modulesP7.add( modules.get(16));
		ArrayList<Preference> preferencesP7 = new ArrayList<Preference>();
		preferencesP7.add(new Preference(1,6,0));
		Profesor p7 = new Profesor(6542111, modulesP7, preferencesP7);
		profesors.add(p7);	
		
		//Profesor 8 data
		ArrayList<Module> modulesP8 = new ArrayList<Module>();
		modulesP8.add( modules.get(15));
		ArrayList<Preference> preferencesP8 = new ArrayList<Preference>();
		preferencesP8.add(new Preference(1,6,0));
		Profesor p8 = new Profesor(80876222, modulesP8, preferencesP8);
		profesors.add(p8);			
		
		//Profesor 9 data
		ArrayList<Module> modulesP9 = new ArrayList<Module>();
		modulesP9.add( modules.get(14));
		ArrayList<Preference> preferencesP9 = new ArrayList<Preference>();
		preferencesP9.add(new Preference(1,6,0));
		Profesor p9 = new Profesor(80876333, modulesP9, preferencesP9);
		profesors.add(p9);		
		
		//Profesor 10 data
		ArrayList<Module> modulesP10 = new ArrayList<Module>();
		modulesP10.add( modules.get(11));
		modulesP10.add( modules.get(12));
		ArrayList<Preference> preferencesP10 = new ArrayList<Preference>();
		preferencesP10.add(new Preference(1,6,0));
		Profesor p10 = new Profesor(80876333, modulesP10, preferencesP10);
		profesors.add(p10);	

		return profesors;		
	}
	
	
*/	

}
