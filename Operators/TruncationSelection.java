package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;

/**
 * Created by Felipe on 06/01/2017.
 */
public class TruncationSelection extends Selection {
    @Override
    public Population selectPopulation(int size, Population population, Faculty faculty) {
        Population futurePopulation = new Population();
        //The fittest 20% of the population are chosen to be replicated
        int subPopulationSize = (int) Math.round(population.getChromosomes().size()*0.2);
        //System.out.println("subPopulationSize " + subPopulationSize);
        ElitistSelection es = new ElitistSelection();
        Population subPopulation = es.selectPopulation(subPopulationSize, population,faculty);
        boolean populationComplete = false;

        while(!populationComplete) {
            for (int i = 0; i < subPopulation.getChromosomes().size(); i++) {
                Chromosome chromosome = subPopulation.getChromosomes().get(i).clone();
                chromosome.setFitness(faculty);
                futurePopulation.addChromosome(chromosome);
                if(futurePopulation.getChromosomes().size()==size){
                    populationComplete = true;
                    break;
                }
            }
        }
        return futurePopulation;
    }
}
