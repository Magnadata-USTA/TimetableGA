package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;
import Utils.SystemGA;

/**
 * Created by Felipe on 03/01/2017.
 */
public class ElitistSelection extends Selection {

    @Override
    public Population selectPopulation(int size, Population population, Faculty faculty) {
        Population futurePopulation = new Population();

        for (int i=0; i<size; i++){
            Chromosome chromosome = population.getBestChromosome().clone();
            chromosome.setFitness(faculty);
            futurePopulation.addChromosome(chromosome);

            //Overwrite fitness BestChromosome
            population.getBestChromosome().setFitness(Integer.MAX_VALUE);
        }
        return futurePopulation;
    }
}
