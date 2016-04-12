import java.util.ArrayList;
import java.util.Random;


/*
 * Chromosome: valid solution. 
 * */
public class Chromosome {

	private ArrayList<Gene> genes;
	//private moduleConstraint;
	
	public Chromosome() {
		Random rand = new Random(); 
		int day = 0;
		
		//rand.nextInt(20)+5;
	}
	
	void addGene(Gene g){
		genes.add(g);
		//Gene g1 = new Gene(1,2,3);
	}
	
	void getValue(){
		
	}

	void isValid(){
		
	}
}
