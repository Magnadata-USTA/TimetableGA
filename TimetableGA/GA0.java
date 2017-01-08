package TimetableGA;

import DataLoading.DataReading;
import Model.Chromosome;
import Model.Faculty;
import Model.Population;
import Operators.*;
import Utils.SystemGA;

/**
 * Created by Felipe on 07/01/2017.
 */
public class GA0 extends GeneticAlgorithm{

    public Chromosome run(DataReading input, int sizePopulation, int numGenerations, double probabilityChromosomeMutation, double  probabilityGeneMutation, double probabilityChromosomesCrossover, double percentageDominantChromosome){
        Faculty faculty = input.loadData();
        Chromosome baseChromosome = input.loadChromosome();
        baseChromosome.setFitness(faculty);
        Population population;
        int fitness = baseChromosome.getFitness();
        System.out.println("Fitness value initial is = "+fitness);

        //Population p = new Population();
        //Generation
        RandomPopulationGeneration r = new RandomPopulationGeneration();
        population = r.initiatePopulation(10, baseChromosome, faculty);
        //BestRandomPopulationGeneration rb = new BestRandomPopulationGeneration();
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
        //ElitistSelection es = new ElitistSelection();
        //population = es.selectPopulation(10, population, faculty);
        //RouletteSelection ros = new RouletteSelection();
        //population = ros.selectPopulation(10, population, faculty);
        //SUSSelection sus = new SUSSelection();
        //population = sus.selectPopulation(10, population, faculty);
        //TournamentSelection ts = new TournamentSelection();
        //population = ts.selectPopulation(10, population, faculty);
        TruncationSelection truns = new TruncationSelection();
        population = truns.selectPopulation(10, population, faculty);
        System.out.println(population.getChromosomes().size());
        System.out.println(population.getFitnessAverage());
        System.out.println(population.getFitnessStandardDeviation());
        System.out.println(population.getBestFitness());
        System.out.println(population.getWorseFitness());
        SystemGA.printFitness(population);

        return baseChromosome;
    }
}
