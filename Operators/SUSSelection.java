package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;

/**
 * Created by Felipe on 05/01/2017.
 * Implements Stochastic universal sampling (SUS)
 * https://en.wikipedia.org/wiki/Stochastic_universal_sampling
 */
public class SUSSelection extends Selection {

    @Override
    public Population selectPopulation(int size, Population population, Faculty faculty) {
        Population futurePopulation = new Population();
        int totalFitness = population.getTotalFitness();
        double distance = (double) totalFitness/size;
        //System.out.println("Distance " + distance + " " + totalFitness);

        double start = Math.random()*distance;
        //System.out.println("Start " + start);

        for (int i=0; i<size; i++){
            double value = start + i*distance;
            int chromosomePosition = 0;
            // locate the random value based on the weights
            for(int j=0; j<population.getChromosomes().size(); j++) {
                value -= population.getChromosomes().get(j).getFitness();
                if(value <= 0){
                    chromosomePosition = j;
                    //System.out.println("chromosomePosition " + j);
                    break;
                }
            }

            Chromosome chromosome = population.getChromosomes().get(chromosomePosition).clone();
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
