import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GeneticAlgorithm {
	//private Arrays population;
	private int numGenerations;
	private int sizeElite;
	private ArrayList<Chromosome> chromosomes;

	
	public GeneticAlgorithm(int sizeElite, int numGenerations){
		this.sizeElite = sizeElite;
		this.numGenerations = numGenerations;
		//population = this.population;
	}
	
	public static void main (String args[]){
		GeneticAlgorithm ga = new GeneticAlgorithm(1000,5);
		System.out.println("size eluite " + Integer.toString(ga.sizeElite) + " size Gen " + ga.numGenerations);
		
		Random rand = new Random(); 
		for (int j = 0; j<10; j++)
	     {
			System.out.println(rand.nextInt(20)+5);
	     }
	}
	
	
	void algorithm(){
		
	}
	
	void initiatePopulation(int size){ 
	
	}
	
	void orderByValue(){
		
	}
	
	
}
