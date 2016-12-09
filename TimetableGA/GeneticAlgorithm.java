package TimetableGA;


import java.awt.image.CropImageFilter;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
	    
	    //load initial population
	    ga.initiatePopulation(10, baseChromosome, teleco);
	    
	    
	    int fitness = baseChromosome.fitness();	
	    System.out.println("Fitness value is = "+fitness);
		ga.algorithm();
		//ga.initiatePopulation(1000);
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
	
	void initiatePopulation(int size, Chromosome baseChromosome, Faculty faculty){ 			
		
		Chromosome newPopulation = new Chromosome();
		newPopulation = baseChromosome; 		
		ArrayList<Course> courses = faculty.getCourses();		
		for ( int i = 0 ; i < courses.size() ;  i++){
			System.out.println("VUELTA NUMERO "+i);
			int duration = courses.get(i).getDuration();
			int hoursPerWeek = courses.get(i).getHoursPerWeek();
			int numberTimeslots = hoursPerWeek/duration;
			System.out.println("duration="+duration+" hoursperweek="+hoursPerWeek+" numbertimeslots="+numberTimeslots);
			
			for ( int j = 0 ; j < numberTimeslots ; j++ ){
				System.out.println("VUELTA NUMERO "+j);
				boolean doubling = true;				
				while(doubling == true){
					Gene available = findEmptyTimeslot(newPopulation, courses.get(i));	
					doubling = newPopulation.checkTimeslotDoubling(available);
					System.out.println("VUELTA NUMERO " +" "+j+" "+i);
					if(doubling == false)
						{
							newPopulation.addGene(available);
							System.out.println(" ADDGENE ");
							break;
						}
				}
				
				//break;
				//System.out.println("break"+i+" "+j);
			}
			for( int ii = 1 ; ii <= Constraints.NUMBER_SEMESTERS ; ii++ ){
		  		printOnScreen(newPopulation, ii);
				System.out.println();}
		}	
		
	}
	
	public static void pauseProg(){
		System.out.println("Press enter to continue...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
		}
	
		Gene findEmptyTimeslot(Chromosome newPopulation, Course course){
		boolean availability = false;
		Gene testingGene = new Gene();
		while(availability == false ){						
				//System.out.println(courses.get(i).getModuleID()+" "+courses.get(i).getCourseID()+" = "+numberTimeslots);
				Random randomNumber = new Random();
				int day = (randomNumber.nextInt(Constraints.DAY_MAX-Constraints.DAY_MIN) + Constraints.DAY_MIN); 
				randomNumber = new Random();
				int startTime = (randomNumber.nextInt(Constraints.LATEST_TIME-Constraints.EARLIEST_TIME) + Constraints.EARLIEST_TIME);
				if ( startTime%2 != 0 ){
					startTime = startTime+1;
				}
				System.out.println("random day = "+day+"random startime = "+startTime);
				testingGene = new Gene(course.getSemesterID(), course.getCourseID(), day, startTime, course.getModuleID());
				availability = newPopulation.checkTimeslotAvailability(testingGene);
				if (availability == true){						
					//newPopulation.addGene(testingGene);							
					//System.out.println(day +"  "+ startTime + "  "+availability);
					//pauseProg();
					return testingGene;
				}
				//break;
		}
		return testingGene;
	}
	
	void printOnScreen(Chromosome baseChromosome, int semesterID){
		
		/*
		 * This is the way to print all semesters
		 * 
		 * for( int ii = 1 ; ii <= Constraints.NUMBER_SEMESTERS ; ii++ ){
		  		printOnScreen(newPopulation, ii);
				System.out.println();}
		 */
		
		int numDays = Constraints.DAY_MAX;
 		int numTimeslots = ((Constraints.HR_MAX-Constraints.EARLIEST_TIME)/2);
 		Gene [][] genesSemester = new Gene[numTimeslots][numDays];
 		int [][] variableToPrint = new int[numTimeslots][numDays];
 		String [][] variable2ToPrint = new String[numTimeslots][numDays];
 		for (int i = 0; i < baseChromosome.getGene().size(); i++) {			
 			Gene y = new Gene();
 		    y = baseChromosome.getGene().get(i);
 			int semester;
 			int day;
 			int startTime;
 			startTime = y.getStartTime();
 			day = y.getDay();
 			semester = y.getSemesterID();
 			if (semesterID == semester){
 				int timeslotsValue;
 				timeslotsValue = (startTime-Constraints.EARLIEST_TIME)/2;
 				genesSemester[timeslotsValue][day-1] = y;
 				/*
 				 * The variable shown on the screen can be change in the next line
 				 * It must be type INT
 				 */
 				String r = Integer.toString(y.getModuleID());
 				String  t = Character.toString(y.getCourseID());
 				variable2ToPrint[timeslotsValue][day-1] = r+t;
 				}
 		}		
		//The next 'for' shows the timetable on the screen
 		for (int x=0; x < genesSemester.length; x++) {
 			  System.out.print("|");
 			  for (int yy=0; yy < genesSemester[x].length; yy++) {
 			    System.out.print (variable2ToPrint[x][yy]);
 			    if (yy!=genesSemester[x].length) System.out.print("\t\t");
 			  }
 			  System.out.println("|");
 		}	
		
		
		
	}
	
	void orderByValue(){
		
	}
	
/*
	
	ArrayList<Professor> dataLoaderProfesors(){
		//Load data
		ArrayList<Professor> profesors = new ArrayList<Professor>();
		ArrayList<Module> modules = new ArrayList<Module>();
		modules = dataLoaderModules();
		
		//Professor 1 data
		ArrayList<Module> modulesP1 = new ArrayList<Module>();
		modulesP1.add( modules.get(0));
		modulesP1.add( modules.get(5));
		modulesP1.add( modules.get(11));
		ArrayList<Preference> preferencesP1 = new ArrayList<Preference>();
		preferencesP1.add(new Preference(1,6,0));
		Professor p1 = new Professor(51350111, modulesP1, preferencesP1);
		profesors.add(p1);

		//Professor 2 data
		ArrayList<Module> modulesP2 = new ArrayList<Module>();
		modulesP2.add( modules.get(1));
		modulesP2.add( modules.get(6));
		modulesP2.add( modules.get(13));
		ArrayList<Preference> preferencesP2 = new ArrayList<Preference>();
		preferencesP2.add(new Preference(1,6,0));
		Professor p2 = new Professor(51350222, modulesP2, preferencesP2);
		profesors.add(p2);
		
		//Professor 3 data
		ArrayList<Module> modulesP3 = new ArrayList<Module>();
		modulesP3.add( modules.get(2));
		modulesP3.add( modules.get(7));
		ArrayList<Preference> preferencesP3 = new ArrayList<Preference>();
		preferencesP3.add(new Preference(1,6,0));
		Professor p3 = new Professor(80876111, modulesP3, preferencesP3);
		profesors.add(p3);		
		
		//Professor 4 data
		ArrayList<Module> modulesP4 = new ArrayList<Module>();
		modulesP4.add( modules.get(3));
		ArrayList<Preference> preferencesP4 = new ArrayList<Preference>();
		preferencesP3.add(new Preference(1,6,0));
		Professor p4 = new Professor(6542222, modulesP4, preferencesP4);
		profesors.add(p4);		
		
		//Professor 5 data
		ArrayList<Module> modulesP5 = new ArrayList<Module>();
		modulesP5.add( modules.get(4));
		modulesP5.add( modules.get(10));
		ArrayList<Preference> preferencesP5 = new ArrayList<Preference>();
		preferencesP5.add(new Preference(1,6,0));
		Professor p5 = new Professor(20305111, modulesP5, preferencesP5);
		profesors.add(p5);		
		
		//Professor 6 data
		ArrayList<Module> modulesP6 = new ArrayList<Module>();
		modulesP6.add( modules.get(8));
		ArrayList<Preference> preferencesP6 = new ArrayList<Preference>();
		preferencesP6.add(new Preference(1,6,0));
		Professor p6 = new Professor(20305222, modulesP6, preferencesP6);
		profesors.add(p6);	

		//Professor 7 data
		ArrayList<Module> modulesP7 = new ArrayList<Module>();
		modulesP7.add( modules.get(9));
		modulesP7.add( modules.get(16));
		ArrayList<Preference> preferencesP7 = new ArrayList<Preference>();
		preferencesP7.add(new Preference(1,6,0));
		Professor p7 = new Professor(6542111, modulesP7, preferencesP7);
		profesors.add(p7);	
		
		//Professor 8 data
		ArrayList<Module> modulesP8 = new ArrayList<Module>();
		modulesP8.add( modules.get(15));
		ArrayList<Preference> preferencesP8 = new ArrayList<Preference>();
		preferencesP8.add(new Preference(1,6,0));
		Professor p8 = new Professor(80876222, modulesP8, preferencesP8);
		profesors.add(p8);			
		
		//Professor 9 data
		ArrayList<Module> modulesP9 = new ArrayList<Module>();
		modulesP9.add( modules.get(14));
		ArrayList<Preference> preferencesP9 = new ArrayList<Preference>();
		preferencesP9.add(new Preference(1,6,0));
		Professor p9 = new Professor(80876333, modulesP9, preferencesP9);
		profesors.add(p9);		
		
		//Professor 10 data
		ArrayList<Module> modulesP10 = new ArrayList<Module>();
		modulesP10.add( modules.get(11));
		modulesP10.add( modules.get(12));
		ArrayList<Preference> preferencesP10 = new ArrayList<Preference>();
		preferencesP10.add(new Preference(1,6,0));
		Professor p10 = new Professor(80876333, modulesP10, preferencesP10);
		profesors.add(p10);	

		return profesors;		
	}
	
	
*/
}


