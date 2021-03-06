package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;
import Utils.SystemGA;

import java.util.ArrayList;

/**
 * Created by Felipe on 05/01/2017.
 */
public class RouletteSelection extends Selection {
    @Override
    public Population selectPopulation(int size, Population population, Faculty faculty) {
        Population futurePopulation = new Population();
        int totalFitness = population.getTotalFitness();

        ArrayList<Double> fitnessComplement = new ArrayList<Double>();
        double complement = 0;
        for (int i=0; i<size; i++){
            complement = (1-(population.getChromosomes().get(i).getFitness()/(double)totalFitness))*100;
            fitnessComplement.add(complement);
        }

        float totalFitnessComplement = 0;
        for (int i=0; i<size; i++){
            totalFitnessComplement += fitnessComplement.get(i);
        }

        for (int i=0; i<size; i++){
            float value = (float) Math.random()*totalFitnessComplement;
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
            /*
            if(i!=0){
                if(!futurePopulation.isChromosome(chromosome)){
                    futurePopulation.addChromosome(chromosome);
                } else {
                    i--;
                }
            } else{
                futurePopulation.addChromosome(chromosome);
            }*/
        }
        return futurePopulation;
    }
}
