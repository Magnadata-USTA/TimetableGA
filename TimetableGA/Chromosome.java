package TimetableGA;
 
 import java.util.ArrayList;

import DataLoading.*;

 public class Chromosome {
 
 	private ArrayList<Gene> genes;
 	private ArrayList<Course> course;
 	
 	public Chromosome() {
 		
 		genes = new ArrayList<Gene>();
 		course = new ArrayList<Course>();
 	}
 	
 	public void addGene(Gene g){
 		
 		genes.add(g);
 	}
 	
 	void getValue(){
 			
 	}
 	
 	ArrayList<Gene> getGene(){
 		
 		return genes;		
 	}
 
 	void isValid(){
 		
 	}
 	
 	public int fitness(){ 		
 		//This method adds all the points from the others value process
 		int deadTimeValue = 0;
 		int hourcheckValue = 0; 
 		int fitness;
 		
 		
 		for( int i = 1 ; i <= Constraints.NUMBER_SEMESTERS ; i++ ){
 			deadTimeValue = deadTimeValue + deadTime(i);
 			hourcheckValue = hourcheckValue + hourCheck(i, Constraints.LUNCH_TIME) + hourCheck(i, Constraints.END_TIME);
 			System.out.println();
 		}
 		
 		fitness = deadTimeValue+hourcheckValue;
 		return fitness; 
 		//System.out.println(hourcheckValue);
 		//System.out.println("La cantidad de bloques libres de este horario es de : "+totalValue);
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
 			Gene y = new Gene();
 		    y = genes.get(i);
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
 				genesModuleID[timeslotsValue][day-1] = y.getModuleID();
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
 	
 	int hourCheck(int semesterID , int hour){
 		
 		int hourcheckValue = 0;
 		for (int i = 0 ; i < genes.size() ; i++) {			
 			Gene x = new Gene();
 		    x = genes.get(i);
 			int semester;
 			int startTime;
 			startTime = x.getStartTime();
 			semester = x.getSemesterID();
 			if (semesterID == semester && startTime == hour){
 				hourcheckValue = hourcheckValue+1;
 			}
 		}
 		//System.out.print (hourcheckValue);
 		return hourcheckValue;		
 	}
 	
 	/*
 	 * homework
 	 * */
 	int dayCheck(int dayToCheck){
 			
 		int daycheckValue = 0;
 		for (int i = 0 ; i < genes.size() ; i++) {			
 			Gene x = new Gene();
 		    x = genes.get(i);
 			int day;
 			day = x.getDay();
 			if (dayToCheck == day){
 				daycheckValue = daycheckValue+1;
 			}
 		}
 		//System.out.print (hourcheckValue);
 		return daycheckValue;		
 	}
 //________________________________________________________________________________________________________________	
 // Check functions
 	
 	//check if a given timeslot is empty for assignment
 	//return true if available or false if it´s not
 	
	public boolean checkTimeslotAvailability(Gene gene){
 		int semesterID = gene.getSemesterID();
 		int moduleID = gene.getModuleID();
 		char courseID = gene.getCourseID();
 		int day = gene.getDay();
 		int startTime = gene.getStartTime();
 		
 		for ( int i = 0 ; i < this.genes.size() ; i++ ){
 			if ( semesterID == this.genes.get(i).getSemesterID() ){
 				if ( moduleID == this.genes.get(i).getModuleID() ){
 					if ( courseID == this.genes.get(i).getCourseID() ){
 						if ( day == this.genes.get(i).getDay() ){
 							if ( startTime == this.genes.get(i).getStartTime() ){
 								//System.out.println("boolean check true");
 								return false;
 							}
 						}
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
	 					//System.out.println("DOUBLING TRUE "+day+" "+this.genes.get(i).getDay()+" "+courseID+" "+this.genes.get(i).getCourseID());
	 					return true;
						}
					}	
	 			}
 			}
		return false;
	}
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 } 