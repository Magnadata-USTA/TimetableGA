package TimetableGA;

import DataLoading.DataReading;
import Model.Chromosome;
import Model.Population;
import Model.Faculty;
import Operators.*;

/**
 * Created by Felipe on 07/01/2017.
 */
public class GA1 extends GeneticAlgorithm {

    @Override
    Chromosome run(DataReading input, int sizePopulation, int numGenerations, double probabilityChromosomeMutation, double  probabilityGeneMutation, double probabilityChromosomesCrossover, double percentageDominantChromosome) {
        Faculty faculty = input.loadData();
        Chromosome baseChromosome = input.loadChromosome();
        baseChromosome.setFitness(faculty);
        Population population;
        //Generate initial population
        //BestRandomPopulationGeneration g = new BestRandomPopulationGeneration();
        RandomPopulationGeneration g = new RandomPopulationGeneration();
        population = g.initiatePopulation(sizePopulation, baseChromosome, faculty);

        //VerticalMutation m = new VerticalMutation();
        HorizontalMutation m = new HorizontalMutation();
        //SoftConstraintsMutation m = new SoftConstraintsMutation();
        CourseCrossover c = new CourseCrossover();
        //RandomSelection s = new RandomSelection();
        //ElitistSelection s = new ElitistSelection();
        //TruncationSelection s = new TruncationSelection();
        TournamentSelection s = new TournamentSelection();
        //SUSSelection s = new SUSSelection();
        //RouletteSelection s = new RouletteSelection();

        //Repeat numGenerations times
        for(int i = 1; i<numGenerations+1; i++){
            //Mutate population
            population = m.mutatePopulation(population.clone(faculty), probabilityChromosomeMutation, probabilityGeneMutation, faculty);
            //Crossover population
            population = c.crossPopulation(population.clone(faculty), probabilityChromosomesCrossover, percentageDominantChromosome, faculty);
            //Select population
            population = s.selectPopulation(sizePopulation, population.clone(faculty), faculty);
            System.out.println(i + " " + population.getChromosomes().size() + " " + population.getBestFitness() + " " + population.getFitnessAverage() + " " + population.getFitnessStandardDeviation());
        }
        //Return best individual
        return population.getBestChromosome();
    }
}
