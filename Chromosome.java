import java.util.ArrayList;

/*
 * Chromosome: valid solution. 
 * */
public class Chromosome {

	private ArrayList<Gene> genes;
	//private moduleConstraint;
	
	public Chromosome() {
		//Random rand = new Random(); 
		//int day = 0;
		//rand.nextInt(20)+5;
		genes = new ArrayList<Gene>();
	}
	
	void addGene(Gene g){		
		genes.add(g);
		//System.out.println(g);
		//Gene g1 = new Gene(1,2,3);
	}
	
	void getValue(){
				
	}

	void isValid(){
		
	}
	
	int fitness(int semesterID){
		int numDays = Constraints.DAY_MAX;
		int numTimeslots = ((Constraints.HR_MAX-Constraints.HR_MIN)/2);
		//System.out.println(numTimeslots);
		Gene [][] genesSemester = new Gene[numTimeslots][numDays];
		int [][] genesModuleID = new int[numTimeslots][numDays];
		int [] basePoints = new int[numDays];
		int [][] initialPoint = new int[numTimeslots][numDays];
		int [][] endingPoint = new int[numTimeslots][numDays];
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
				timeslotsValue = (startTime-Constraints.HR_MIN)/2;
				genesSemester[timeslotsValue][day-1] = y;
				genesModuleID[timeslotsValue][day-1] = y.getModuleID();
				}
		}
		for (int x=0; x < genesSemester.length; x++) {
			  System.out.print("|");
			  for (int yy=0; yy < genesSemester[x].length; yy++) {
			    System.out.print (genesModuleID[x][yy]);
			    if (yy!=genesSemester[x].length) System.out.print("\t\t");
			  }
			  System.out.println("|");
		}
/**		for (int yy=0; yy < genesSemester[yy].length; yy++) {			  
			for (int x=0; x < genesSemester.length; x++) {			    
				  //System.out.println(genesSemester[x][yy]);
				  if (genesSemester[x][yy] == null) {
			    		//System.out.println(".|.");
			    		basePoints[yy]=basePoints[yy]+1;
			    	}			    
			  }
			  //System.out.println(basePoints[yy]);
	     }
**/
		for (int yy=0; yy < genesSemester[yy].length; yy++) {			  
			int a = 0;
			for (int x = 0; x < genesSemester.length; x++) {
				  if (genesSemester[x][yy] != null) {
			    		initialPoint[x][yy] = 1;
			    		a = 1;
			    		break;
			    	}
			  }
			if (a == 1)break;
	     }
		//System.out.println(initialPoint[0][0]);	
		for (int yy=0; yy < genesSemester[yy].length; yy++) {			  
			int a = 0;
			int x = 0;
			for (x = genesSemester.length-1; x > 0 ; x--) {	
				  if (genesSemester[x][yy] != null) {
			    		endingPoint[yy][x] = 1;
			    		a = 1;
			    		break;
			    	}
			  }
			if (a == 1)break;
	     }
		//System.out.println(endingPoint[0][4]);
		
		
		
		for (int yy=0; yy < genesSemester[yy].length; yy++) {			  
			int b = 0;
			for (int x = 0; x < genesSemester.length; x++) {			    
				 //System.out.println(genesSemester[x][yy]);
				 //System.out.println(initialPoint[x][yy]);
				 //System.out.println(endingPoint[x][yy]);
			     if (initialPoint[x][yy] == 1) b = 1;
			     if (b == 1) {
				    if(genesSemester[x][yy] != null){
					   basePoints[yy]=basePoints[yy]+1;
					  if(endingPoint[x][yy] == 1){
						  continue;							  
					  }
				  }			  
		       }			    
			  }
			  System.out.println(basePoints[yy]);
	     }
		
		return 0;
	}
}
