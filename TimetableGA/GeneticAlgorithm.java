package TimetableGA;

import Model.*;
import Operators.*;

import DataLoading.DataReading;
import Utils.SystemGA;

public abstract class GeneticAlgorithm {

	abstract Chromosome run(DataReading input, int sizePopulation, int numGenerations, double probabilityChromosomeMutation, double  probabilityGeneMutation, double probabilityChromosomesCrossover, double percentageDominantChromosome);

}


