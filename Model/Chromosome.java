package Model;
 
 import Utils.*;

 import java.util.ArrayList;
 import java.util.Random;

public class Chromosome implements Cloneable{
 
 	private ArrayList<Gene> genes;

	public int getFitness() {
		return fitness;
	}

	public void setFitness(Faculty faculty) {
		int deadTimeValue = 0;
		int hourCheckValue = 0;
		int professorsFitness = 0;

		for( int i = 1 ; i <= Constraints.NUMBER_SEMESTERS ; i++ ){
			deadTimeValue = deadTimeValue + deadTime(i);
			int lunchTime = hourCheck(i, Constraints.LUNCH_TIME);
			//System.out.println(i+ " " +lunchTime);
			hourCheckValue = hourCheckValue + lunchTime;
		}
		professorsFitness = professorsPreferences(faculty);
		this.fitness = deadTimeValue + hourCheckValue + professorsFitness;
		//System.out.println(deadTimeValue + " " + hourCheckValue + " " + professorsFitness);
	}

	private int fitness;
 	
 	public Chromosome() {
 		this.genes = new ArrayList<Gene>();
 	}

	public Chromosome clone(){
		Chromosome chromosome = new Chromosome();
		for ( int i = 0 ; i < genes.size() ; i++ ) {
			chromosome.genes.add(genes.get(i));
		}
		return chromosome;
	}

 	public void addGene(Gene g){
 		this.genes.add(g);
 	}
 	
 	public ArrayList<Gene> getGenes(){
 		return this.genes;
 	}

 	public int fitness(Faculty faculty){
 		//This method adds all the points from the others fitness value process
 		int deadTimeValue = 0;
 		int hourCheckValue = 0;
		int professorsFitness = 0;
 		int fitness;

 		for( int i = 1 ; i <= Constraints.NUMBER_SEMESTERS ; i++ ){
 			deadTimeValue = deadTimeValue + deadTime(i);
 			int lunchTime = hourCheck(i, Constraints.LUNCH_TIME);
			//System.out.println(i+ " " +lunchTime);
			hourCheckValue = hourCheckValue + lunchTime;
 		}
		professorsFitness = professorsPreferences(faculty);
 		fitness = deadTimeValue + hourCheckValue + professorsFitness;
		//System.out.println(deadTimeValue + " " + hourCheckValue + " " + professorsFitness);
		return fitness;
 	}

	public int professorsPreferences(Faculty faculty){
		// This method compute the fitness from the professors' preferences
		int preferencesValue = 0;
		for ( int i = 0 ; i < genes.size() ; i++ ) {
			for ( int j = 0 ; j < faculty.getProfessors().size() ; j++ ) {
				if(genes.get(i).getProfesorID() == faculty.getProfessors().get(j).getProfessorID()){
					for ( int k = 0 ; k < faculty.getProfessors().get(j).getPreferences().size() ; k++ ) {
						if (genes.get(i).getDay() == faculty.getProfessors().get(j).getPreferences().get(k).getDay()) {
							if (genes.get(i).getStartTime() == faculty.getProfessors().get(j).getPreferences().get(k).getStartTime()) {
								preferencesValue = preferencesValue + faculty.getProfessors().get(j).getPreferences().get(k).getValue();
							}
						}
					}

				}
			}
		}
		return preferencesValue;
	}
 	
 	int deadTime(int semesterID){
 		//This method analyzes the timetables and says how many free timeslots are between classes
 		int numDays = Constraints.DAY_MAX;
 		int numTimeslots = ((Constraints.HR_MAX-Constraints.EARLIEST_TIME)/2);
 		Gene [][] genesSemester = new Gene[numTimeslots][numDays];
 		int [][] genesModuleID = new int[numTimeslots][numDays];
 		int [][] initialPoint = new int[numTimeslots][numDays];
 		int [][] endingPoint = new int[numTimeslots][numDays];
 		int [] basePoints = new int[numDays];
 		int [] startPoints = new int[numDays];
 		int [] lastPoints = new int[numDays];
 		for (int i = 0; i < genes.size(); i++) {
 			int semester;
 			int day;
 			int startTime;
 			startTime = this.genes.get(i).getStartTime();
 			day = this.genes.get(i).getDay();
 			semester = this.genes.get(i).getSemesterID();
 			if (semesterID == semester){
 				int timeslotsValue;
 				timeslotsValue = (startTime-Constraints.EARLIEST_TIME)/2;
 				genesSemester[timeslotsValue][day-1] = this.genes.get(i);
 				genesModuleID[timeslotsValue][day-1] = this.genes.get(i).getModuleID();
 				}
 		}
 		//The next 'for' gets the initial points, for each day, of the timetable
 		for (int yy=0; yy < genesSemester[yy].length; yy++) {			  
 			int a = 0;
 			for (int x = 0; x < genesSemester.length; x++) {
 				  if (genesSemester[x][yy] != null) {
 			    		initialPoint[x][yy] = 1;
 			    		a = 1;
 			    		break;
 			    	}
 			  }
 			if (a == 1)continue;
 	     }
 		//The next 'for' gets the ending points, for each day, of the timetable
 		for (int yy=0; yy < genesSemester[yy].length; yy++) {			  
 			int a = 0;
 			for (int x = genesSemester.length-1; x >= 0 ; x--) {	
 				  if (genesSemester[x][yy] != null) {
 			    		endingPoint[x][yy] = 1;
 			    		a = 1;
 			    		break;
 			    	}
 			  }
 			if (a == 1)continue;
 	     }
 		//The next 'for' gets the value of the first condition in the 'for' value
 		for (int yy=0; yy < genesSemester[yy].length; yy++) {			  
 			int a = 0;
 			for (int x = 0; x < genesSemester.length; x++) {
 				  if (initialPoint[x][yy] == 1) {
 			    		startPoints[yy] = x;
 			    		a = 1;
 			    		break;
 			    	}
 			  }
 			if (a == 1)continue;
 	     }
 		//The next 'for' gets the value of the second condition in the 'for' value
 		for (int yy=0; yy < genesSemester[yy].length; yy++) {			  
 			int a = 0;
 			for (int x = genesSemester.length-1; x >= 0 ; x--) {	
 				  if (endingPoint[x][yy] == 1) {
 			    		lastPoints[yy] = x;
 			    		a = 1;
 			    		break;
 			    	}
 			  }
 			if (a == 1)continue;
 	     }
 		// This is the 'for' value
 		//This 'for' gets the final value for the first chromosome
 		for (int yy = 0; yy < genesSemester[yy].length; yy++) {		
 			for (int x = startPoints[yy] ; x < lastPoints[yy] ; x++) {
 				if (genesSemester[x][yy] == null) {
 					basePoints[yy] = basePoints[yy]+1;
 				}							     
 		    }
 		}
 		//This 'for' get the final value for the timeTables
 		int timetableValue = 0; 
 		for (int i = 0; i< basePoints.length ; i++) {
 			timetableValue=timetableValue+basePoints[i]; 
 		}
 		return timetableValue;
 	}
 	
 	int hourCheck(int semester , int hour){
 		int hourcheckValue = 0;
 		for (int i = 0 ; i < genes.size() ; i++) {
 			if (semester == this.genes.get(i).getSemesterID() && hour == this.genes.get(i).getStartTime()){
 				hourcheckValue++;
 			}
 		}
 		return hourcheckValue;
 	}
 	
 	int dayCheck(int dayToCheck){
 			
 		int daycheckValue = 0;
 		for (int i = 0 ; i < genes.size() ; i++) {
 			int day;
 			day = this.genes.get(i).getDay();
 			if (dayToCheck == day){
 				daycheckValue = daycheckValue+1;
 			}
 		}
		return daycheckValue;
 	}
 //________________________________________________________________________________________________________________	
 // Check functions
 	
 	//check if a given timeslot is empty for assignment
 	//return true if available or false if it�s not
	public Gene findEmptyTimeslot(Course course){
		boolean availability = false;
		Gene gene = new Gene();
		while(availability == false ){
			NumberGenerator ng = new NumberGenerator();
			int day = ng.randomDay();
			int startTime = ng.randomEvenStartTime();
			gene = new Gene(course.getSemesterID(), course.getModuleID(), course.getProfesorID(), course.getCourseID(), day, startTime);
			availability = this.checkTimeslotAvailability(gene);
			if (availability == true){
				return gene;
			}
		}
		return gene;
	}
 	
	public boolean checkTimeslotAvailability(Gene gene){
 		int semesterID = gene.getSemesterID();
 		int moduleID = gene.getModuleID();
 		char courseID = gene.getCourseID();
 		int day = gene.getDay();
 		int startTime = gene.getStartTime();
 		
 		for ( int i = 0 ; i < this.genes.size() ; i++ ){
 			if ( semesterID == this.genes.get(i).getSemesterID() ){
				if ( day == this.genes.get(i).getDay() ){
					if ( startTime == this.genes.get(i).getStartTime() ){
						//System.out.println("boolean check true");
						return false;
					}
				}
			}
		}
 		//System.out.println("boolean check false");
 		return true; 
 	}

	 public boolean checkTimeslotDoubling(Gene gene){
 		char courseID = gene.getCourseID();
 		int day = gene.getDay();
 		int moduleID = gene.getModuleID();
 		
 		for ( int i = 0 ; i < this.genes.size() ; i++ ){
 			if ( moduleID == this.genes.get(i).getModuleID() ){
	 			if ( day == this.genes.get(i).getDay() ){	
	 				if ( courseID == this.genes.get(i).getCourseID() ){
	 					return true;
					}
				}
	 		}
		}
		return false;
	}

	 public boolean checkProfessorDoubling(Gene gene){
		 int professorID = gene.getProfesorID();
		 int day = gene.getDay();
		 int startTime = gene.getStartTime();

		 for ( int i = 0 ; i < this.genes.size() ; i++ ){
			 if ( professorID == this.genes.get(i).getProfesorID()){
				 if ( day == this.genes.get(i).getDay() ){
					 if ( startTime == this.genes.get(i).getStartTime() ){
						 return true;
					 }
				 }
			 }
		 }
		 return false;
	 }

	 //Check chromosome validity
	 public boolean checkValidity(){
		 //Check if the number of timeslots of all courses is correct
		 return true;
	 }

	 //Delete genes by moduleID
	public boolean deleteGenesByModuleID(){
		return true;
	}
 } 