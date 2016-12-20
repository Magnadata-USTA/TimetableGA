package TimetableGA;

import java.util.ArrayList;

import Model.*;
import Operators.*;

import DataLoading.DataReading;

public class GeneticAlgorithm {
	private DataReading input;
	private ArrayList<Chromosome> chromosomes;
	private int numGenerations;
	private int sizeElite;

	public ArrayList<Chromosome> getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(ArrayList<Chromosome> chromosomes) {
		this.chromosomes = chromosomes;
	}

	public GeneticAlgorithm(DataReading input, int sizeElite, int numGenerations){
		this.input = input;
		this.chromosomes = new ArrayList<>();
		this.sizeElite = sizeElite;
		this.numGenerations = numGenerations;
	}

	public Chromosome run (){
		Faculty faculty;
		Chromosome baseChromosome;
		baseChromosome = input.loadChromosome();
		faculty = input.loadData();
		baseChromosome.setFitness(faculty);
		int fitness = baseChromosome.getFitness();
		System.out.println("Fitness value initial is = "+fitness);

		//Reproduction
		RandomPopulationReproduction r = new RandomPopulationReproduction();
		this.setChromosomes(r.initiatePopulation(10, baseChromosome, faculty));

		//Mutation and Crossover

		return baseChromosome;
	}
}


