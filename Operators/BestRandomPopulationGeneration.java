package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;
import Utils.*;

/**
 * Created by Felipe on 27/12/2016.
 */
public class BestRandomPopulationGeneration extends Generation {

    public Population initiatePopulation(int size, Chromosome chromosome, Faculty faculty) {
        int sizeBest = 100;
        Population population = new Population();
        Chromosome newChromosome = new Chromosome();
        RandomPopulationGeneration r = new RandomPopulationGeneration();
        for (int i = 0; i < size; i++) {
            newChromosome = r.initiatePopulation(sizeBest, chromosome, faculty).getBestChromosome();
            population.addChromosome(newChromosome);
            int fitness = newChromosome.getFitness();
            System.out.println("Fitness value new is = "+fitness);
            //SystemGA.printOnScreen(newChromosome);
            //SystemGA.pause();
        }
        return population;
    }
}
