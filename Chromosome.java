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
	
	void getValue(int semesterID){
		int numDays = Constraints.DAY_MAX-1;
		int numTimeslots = ((Constraints.HR_MAX-Constraints.HR_MIN)/2)-1;
		System.out.println(numTimeslots);
		Gene [][] genesSemester = new Gene[numDays][numTimeslots];
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
				genesSemester[day-1][timeslotsValue] = y;			
				}
			//System.out.println(genes.get(i));
		}
		for (int x=0; x < genesSemester.length; x++) {
			  System.out.print("|");
			  for (int yy=0; yy < genesSemester[x].length; yy++) {
			    System.out.print (genesSemester[x][yy]);
			    if (yy!=genesSemester[x].length-1) System.out.print("\t");
			  }
			  System.out.println("|");
			}
	}

	void isValid(){
		
	}
	
	int fitness(){
		for (int i = 0; i < genes.size(); i++) {
			System.out.println(genes.get(i));
		}
		return 0;
	}
}
