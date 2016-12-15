package TimetableGA;
 
 import java.util.ArrayList;

 public class Chromosome implements Cloneable{
 
 	private ArrayList<Gene> genes;
 	//private ArrayList<Course> course;
 	
 	public Chromosome() {
 		this.genes = new ArrayList<Gene>();
 	}

	protected Chromosome clone(){
		Chromosome chromosome = new Chromosome();
		for ( int i = 0 ; i < genes.size() ; i++ ) {
			chromosome.genes.add(genes.get(i));
		}
		return chromosome;
	}

 	public void addGene(Gene g){
 		this.genes.add(g);
 	}
 	
 	ArrayList<Gene> getGenes(){
 		return this.genes;
 	}

 	int fitness(){
 		//This method adds all the points from the others value process
 		int deadTimeValue = 0;
 		int hourCheckValue = 0;
 		int fitness;

 		for( int i = 1 ; i <= Constraints.NUMBER_SEMESTERS ; i++ ){
 			deadTimeValue = deadTimeValue + deadTime(i);
 			int lunchTime = hourCheck(i, Constraints.LUNCH_TIME);
			System.out.println(i+ " " +lunchTime);
			hourCheckValue = hourCheckValue + lunchTime;
 		}
 		fitness = deadTimeValue+hourCheckValue;
		System.out.println(deadTimeValue + " " + hourCheckValue);
		return fitness;
 	}

	int professorsPreferences(Faculty faculty){
		int preferencesValue = 0;
		for ( int i = 0 ; i < genes.size() ; i++ ) {
			for ( int j = 0 ; j < faculty.getProfessors().size() ; j++ ) {
				if(genes.get(i).getProfesorID() == faculty.getProfessors().get(j).getProfessorID()){
					for ( int k = 0 ; k < faculty.getProfessors().get(j).getPreferences().size() ; k++ ) {
						if (genes.get(i).getDay() == faculty.getProfessors().get(j).getPreferences().get(k).getDay()) {
							if (genes.get(i).getStartTime() == faculty.getProfessors().get(j).getPreferences().get(k).getStartTime()) {
								preferencesValue = preferencesValue + faculty.getProfessors().get(j).getPreferences().get(k).getValue();
								return preferencesValue;
							}
						}
					}

				}
			}
		}
		return preferencesValue;
	}
 	
 	int deadTime(int semesterID){
 		//This method analyze the timetables and say how many deadtimes are between classes
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
 	
 	/*
 	 * homework
 	 * */
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
 	
	boolean checkTimeslotAvailability(Gene gene){
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
 	
	boolean checkTimeslotDoubling(Gene gene){
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

	 boolean checkProfessorDoubling(Gene gene){
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
 } 