package TimetableGA;

import Model.*;
import Operators.*;

import DataLoading.DataReading;
import Utils.SystemGA;

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
		population = r.initiatePopulation(10, baseChromosome, faculty);
		//population = rb.initiatePopulation(5, baseChromosome, faculty);
		System.out.println(population.getChromosomes().size());
		System.out.println(population.getFitnessAverage());
		System.out.println(population.getFitnessStandardDeviation());
		System.out.println(population.getBestFitness());
		System.out.println(population.getWorseFitness());
		SystemGA.printFitness(population);

		//Mutation and Crossover
		VerticalMutation v = new VerticalMutation();
		HorizontalMutation h = new HorizontalMutation();
		SoftConstraintsMutation s = new SoftConstraintsMutation();
		CourseCrossover c = new CourseCrossover();
		//population = v.mutatePopulation(population, 1, 1, faculty);
		//population = h.mutatePopulation(population, 1, 1, faculty);
		//population = s.mutatePopulation(population, 1, 1, faculty);
		population = c.crossPopulation(population, 1, 0.5, faculty);
		//SystemGA.printOnScreen(population.getChromosomes().get(0));
		//SystemGA.pause();
		System.out.println(population.getChromosomes().size());
		System.out.println(population.getFitnessAverage());
		System.out.println(population.getFitnessStandardDeviation());
		System.out.println(population.getBestFitness());
		System.out.println(population.getWorseFitness());
		SystemGA.printFitness(population);

		//RandomSelection rs = new RandomSelection();
		//population = rs.selectPopulation(10, population, faculty);
		ElitistSelection es = new ElitistSelection();
		population = es.selectPopulation(10, population, faculty);
		System.out.println(population.getChromosomes().size());
		System.out.println(population.getFitnessAverage());
		System.out.println(population.getFitnessStandardDeviation());
		System.out.println(population.getBestFitness());
		System.out.println(population.getWorseFitness());
		SystemGA.printFitness(population);

		return baseChromosome;
	}
}


