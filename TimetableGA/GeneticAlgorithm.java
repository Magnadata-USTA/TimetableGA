package TimetableGA;

import java.util.ArrayList;

import Model.*;
import Operators.*;

import DataLoading.DataReading;

public class GeneticAlgorithm {
	private int numGenerations;
	private int sizeElite;

	public ArrayList<Chromosome> getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(ArrayList<Chromosome> chromosomes) {
		this.chromosomes = chromosomes;
	}

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
		int fitness = baseChromosome.fitness(teleco);
		RandomPopulationReproduction population = new RandomPopulationReproduction();
		ga.setChromosomes(population.initiatePopulation(10, baseChromosome, teleco));
	    System.out.println("Fitness value initial is = "+fitness);
	}
}


