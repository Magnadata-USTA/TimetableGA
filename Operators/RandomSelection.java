package Operators;

import Model.Chromosome;
import Model.Population;
import Model.Faculty;

import java.util.ArrayList;

/**
 * Created by Felipe on 03/01/2017.
 */
public class RandomSelection extends Selection {
    @Override
    public Population selectPopulation(int size, Population population, Faculty faculty) {
        Population futurePopulation = new Population();

        for (int i=0; i<size; i++){
            Chromosome chromosome = population.randomChromosome().clone();
            chromosome.setFitness(faculty);
            if(i!=0){
                if(!futurePopulation.isChromosome(chromosome)){
                    futurePopulation.addChromosome(chromosome);
                } else {
                    i--;
                }
            } else{
                futurePopulation.addChromosome(chromosome);
            }
        }
        return futurePopulation;
    }
}
