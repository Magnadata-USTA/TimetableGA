package Operators;

import Model.Population;

/**
 * Created by Felipe on 27/12/2016.
 */
public class SwapGenesVerticallyMutation extends Mutation {

    public Population mutatePopulation(Population population, double probability, double percentage){
        for (int i = 0; i < population.getChromosomes().size() ; i++) {
            if(Math.random() < probability){
                population.addChromosome(population.getChromosomes().get(i));
            }
        }
        return population;
    }
}
