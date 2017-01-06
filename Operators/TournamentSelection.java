package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;

/**
 * Created by Felipe on 05/01/2017.
 * https://en.wikipedia.org/wiki/Tournament_selection
 */
public class TournamentSelection extends Selection {

    @Override
    public Population selectPopulation(int size, Population population, Faculty faculty) {
        Population futurePopulation = new Population();
        //20% of individuals are chosen randomly for tournament
        int subPopulationSize = (int) Math.round(population.getChromosomes().size()*0.2);
        System.out.println("subPopulationSize " + subPopulationSize);

        for (int i = 0; i < size; i++) {
            Population subPopulation = population.generateSubPopulation(subPopulationSize);
            Chromosome chromosome = subPopulation.getBestChromosome().clone();
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
