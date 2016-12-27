package TimetableGA;

import Model.*;
import Operators.*;

import DataLoading.DataReading;

public class GeneticAlgorithm {
	private DataReading input;
	private Population population;
	private int numGenerations;
	private int sizeElite;

	public GeneticAlgorithm(DataReading input, int sizeElite, int numGenerations){
		this.input = input;
		this.population = new Population();
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

		//Population p = new Population();
		//Generation
		RandomPopulationGeneration r = new RandomPopulationGeneration();
		BestRandomPopulationGeneration rb = new BestRandomPopulationGeneration();
		population = r.initiatePopulation(100, baseChromosome, faculty);
		//population = rb.initiatePopulation(5, baseChromosome, faculty);
		System.out.println(population.getFitnessAverage());
		System.out.println(population.getFitnessStandardDeviation());
		System.out.println(population.getBestFitness());
		System.out.println(population.getWorseFitness());

		//Mutation and Crossover
		SwapGenesVerticallyMutation m = new SwapGenesVerticallyMutation();
		population = m.mutatePopulation(population, 0.03, 0.8);
		System.out.println(population.getChromosomes().size());

		return baseChromosome;
	}
}


