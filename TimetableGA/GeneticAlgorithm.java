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
		this.chromosomes = new ArrayList<>();
	}
	
	
	public static void main (String args[]){
		GeneticAlgorithm ga = new GeneticAlgorithm(1000,5);
		System.out.println("size elite " + Integer.toString(ga.sizeElite) + " size Gen " + ga.numGenerations);
		Chromosome baseChromosome;
		Faculty teleco;
		DataReading baseTimetable = new DataReading();
		baseChromosome = baseTimetable.loadChromosome();
	    teleco = baseTimetable.loadData();
	    //load initial population
		ArrayList<Chromosome> newPopulation = ga.initiatePopulation(10, baseChromosome, teleco);
	    int fitness = baseChromosome.fitness();	
	    System.out.println("Fitness value initial is = "+fitness);
		//fitness = newPopulation.fitness();
		//System.out.println("Fitness value new is = "+fitness);
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

	ArrayList<Chromosome> initiatePopulation(int size, Chromosome baseChromosome, Faculty faculty){
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		for (int h = 0 ; h < size ;  h++) {
			Chromosome chromosome = baseChromosome.clone();
			ArrayList<Course> courses = faculty.getCourses();
			for (int i = 0; i < courses.size(); i++) {
				int duration = courses.get(i).getDuration();
				int hoursPerWeek = courses.get(i).getHoursPerWeek();
				int numberTimeslots = hoursPerWeek / duration;

				for (int j = 0; j < numberTimeslots; j++) {
					boolean doublingCourse = true;
					boolean professorAvailable;
					boolean doublingProfessor;

					while (doublingCourse == true) {
						Gene available = findEmptyTimeslot(chromosome, courses.get(i));
						for (int k = 0; k < faculty.getProfessors().size(); k++) {
							if (faculty.getProfessors().get(k).getProfessorID() == courses.get(i).getProfesorID()) {
								professorAvailable = faculty.getProfessors().get(k).checkProfessorAvailability(available);
								if (professorAvailable == true) {
									doublingCourse = chromosome.checkTimeslotDoubling(available);
									if (doublingCourse == false) {
										doublingProfessor = chromosome.checkProfessorDoubling(available);
										if (doublingProfessor == false) {
											chromosome.addGene(available);
											break;
										} else {
											doublingCourse = true;
										}
									}
								} else {
									break;
								}
							}
						}
					}
				}
			}
			newPopulation.add(chromosome);
			int fitness = chromosome.fitness();
			System.out.println("Fitness value new is = "+fitness);
			for( int ii = 1 ; ii <= Constraints.NUMBER_SEMESTERS ; ii++ ){
				printOnScreen(chromosome, ii);
				System.out.println();
			}
			pauseProg();
		}
		return newPopulation;
	}

	static void pauseProg(){
		System.out.println("Press enter to continue...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}

	Gene findEmptyTimeslot(Chromosome chromosome, Course course){
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
			//-System.out.println("random day = "+day+"random startime = "+startTime);
			testingGene = new Gene(course.getSemesterID(), course.getModuleID(), course.getProfesorID(), course.getCourseID(), day, startTime);
			availability = chromosome.checkTimeslotAvailability(testingGene);
			if (availability == true){
				//chromosome.addGene(testingGene);
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
		  		printOnScreen(chromosome, ii);
				System.out.println();}
		 */
		
		int numDays = Constraints.DAY_MAX;
 		int numTimeslots = ((Constraints.HR_MAX-Constraints.EARLIEST_TIME)/2);
 		Gene [][] genesSemester = new Gene[numTimeslots][numDays];
 		int [][] variableToPrint = new int[numTimeslots][numDays];
 		String [][] variable2ToPrint = new String[numTimeslots][numDays];
 		for (int i = 0; i < baseChromosome.getGenes().size(); i++) {
 			Gene y = new Gene();
 		    y = baseChromosome.getGenes().get(i);
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
	
}


