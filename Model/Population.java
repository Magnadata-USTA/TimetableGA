package Model;

import java.util.ArrayList;

/**
 * Created by Felipe on 26/12/2016.
 */
public class Population {

    private ArrayList<Chromosome> chromosomes;

    public Population(ArrayList<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }
    public Population() {
        this.chromosomes = new ArrayList<Chromosome>();
    }

    public ArrayList<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public void addChromosome(Chromosome chromosome){
        this.chromosomes.add(chromosome);
    }

    public Chromosome getBestChromosome(){
        int bestPosition = 0;
        double fitness = Double.POSITIVE_INFINITY;
        for (int i = 0; i < chromosomes.size(); i++){
            if(chromosomes.get(i).getFitness() < fitness) {
                fitness = chromosomes.get(i).getFitness();
                bestPosition = i;
            }
        }
        return chromosomes.get(bestPosition);
    }

    public double getBestFitness(){
        double fitness = Double.POSITIVE_INFINITY;
        for (int i = 0; i < chromosomes.size(); i++){
            if(chromosomes.get(i).getFitness() < fitness)
                fitness = chromosomes.get(i).getFitness();
        }
        return fitness;
    }

    public double getWorseFitness(){
        double fitness = 0;
        for (int i = 0; i < chromosomes.size(); i++){
            if(chromosomes.get(i).getFitness() > fitness)
                fitness = chromosomes.get(i).getFitness();
        }
        return fitness;
    }

    public double getFitnessAverage(){
        double totalFitness = 0;
        for (int i = 0; i < chromosomes.size(); i++){
            totalFitness+=chromosomes.get(i).getFitness();
        }
        return totalFitness/chromosomes.size();
    }

    public double getFitnessVariance(){
        float variance = 0;
        double mean = getFitnessAverage();
        double temp = 0;
        for(int i = 0; i < chromosomes.size(); i++)
            temp += (chromosomes.get(i).getFitness()-mean)*(chromosomes.get(i).getFitness()-mean);
        return temp/chromosomes.size();
    }

    public double getFitnessStandardDeviation(){
        return Math.sqrt(getFitnessVariance());
    }

}
