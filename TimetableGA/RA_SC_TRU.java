package TimetableGA;

import DataLoading.DataReading;
import Model.Chromosome;
import Model.Faculty;
import Model.Population;
import Operators.*;
import Utils.SystemGA;

import java.util.Date;

/**
 * Created by Felipe on 07/01/2017.
 */
public class RA_SC_TRU extends GeneticAlgorithm {

    @Override
    Chromosome run(DataReading input, int sizePopulation, int numGenerations, double probabilityChromosomeMutation, double  probabilityGeneMutation, double probabilityChromosomesCrossover, double percentageDominantChromosome) {
        String filename = "RA-SC-TRU-";
        Faculty faculty = input.loadData();
        Chromosome baseChromosome = input.loadChromosome();
        baseChromosome.setFitness(faculty);
        System.out.println(baseChromosome.getFitness());
        Population population;

        Date date = new Date();
        System.out.println("Data load at: " + date.toString());

        RandomPopulationGeneration g = new RandomPopulationGeneration();
        population = g.initiatePopulation(sizePopulation, baseChromosome, faculty);

        date = new Date();
        System.out.println("Initial population at:" + date.toString());
        long unixTime = System.currentTimeMillis() / 1000L;
        System.out.println("UnixTime:" + unixTime);

        SoftConstraintsMutation m = new SoftConstraintsMutation();
        CourseCrossover c = new CourseCrossover();
        TruncationSelection s = new TruncationSelection();

        Chromosome theBestChromosome = new Chromosome();
        theBestChromosome = population.getBestChromosome();

        //Repeat numGenerations times
        for(int i = 1; i<numGenerations+1; i++){
            //Mutate population
            population = m.mutatePopulation(population.clone(faculty), probabilityChromosomeMutation, probabilityGeneMutation, faculty);
            //Crossover population
            population = c.crossPopulation(population.clone(faculty), probabilityChromosomesCrossover, percentageDominantChromosome, faculty);
            //Select population
            population = s.selectPopulation(sizePopulation, population.clone(faculty), faculty);

            date = new Date();
            //Write in file
            System.out.println(i + " " + population.getChromosomes().size() + " " + population.getBestFitness() + " " + population.getFitnessAverage() + " " + population.getFitnessStandardDeviation() + " " + date.toString());
            SystemGA.printStatsInFile(population, i, probabilityChromosomeMutation, probabilityGeneMutation, probabilityChromosomesCrossover, percentageDominantChromosome, filename);
            theBestChromosome.setFitness(faculty);
            population.getBestChromosome().setFitness(faculty);
            if(theBestChromosome.getFitness() >= population.getBestChromosome().getFitness()){
                theBestChromosome = population.getBestChromosome();
            }
        }
        //Return best individual
        population.addChromosome(theBestChromosome);
        long unixTime2 = System.currentTimeMillis() / 1000L;
        date = new Date();
        System.out.println("Initial population at:" + date.toString());
        System.out.println("UnixTime2:" + unixTime2);
        System.out.println("Diference:" + (unixTime2-unixTime));
        return population.getBestChromosome();
    }
}
