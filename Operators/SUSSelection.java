package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;

import java.util.ArrayList;

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

        ArrayList<Double> fitnessComplement = new ArrayList<Double>();
        double complement = 0;
        for (int i=0; i<size; i++){
            complement = (totalFitness-population.getChromosomes().get(i).getFitness())/(double)totalFitness;
            fitnessComplement.add(complement);
            //System.out.println(population.getChromosomes().get(i).getFitness()+ " " + complement);
        }

        float totalFitnessComplement = 0;
        for (int i=0; i<size; i++){
            totalFitnessComplement += fitnessComplement.get(i);
        }


        double distance = (double) totalFitnessComplement/size;
        //System.out.println("Distance " + distance + " " + totalFitness);

        double start = Math.random()*distance;
        //System.out.println("Start " + start);

        for (int i=0; i<size; i++){
            double value = start + i*distance;
            int chromosomePosition = 0;
            // locate the random value based on the weights
            for(int j=0; j<fitnessComplement.size(); j++) {
                value -= fitnessComplement.get(j);
                if(value <= 0){
                    chromosomePosition = j;
                    //System.out.println("chromosomePosition " + j + " value " + totalFitnessComplement);
                    break;
                }
            }

            Chromosome chromosome = population.getChromosomes().get(chromosomePosition).clone();
            chromosome.setFitness(faculty);

            futurePopulation.addChromosome(chromosome);
        }
        return futurePopulation;
    }
}
