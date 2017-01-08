package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;

import java.util.ArrayList;

/**
 * Created by Felipe on 19/12/2016.
 */
public abstract class Crossover {

    abstract Population crossPopulation(Population population, double probabilityChromosomesCrossover, double percentageDominantChromosome, Faculty faculty);
    abstract Chromosome crossChromosomes(Chromosome chromosomeDominant, Chromosome chromosome2, double percentageDominantChromosome, Faculty faculty);
}
