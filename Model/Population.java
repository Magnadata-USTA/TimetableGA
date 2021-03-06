package Model;

import java.util.ArrayList;

/**
 * Created by Felipe on 26/12/2016.
 */
public class Population implements Cloneable{

    private ArrayList<Chromosome> chromosomes;

    public Population(ArrayList<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }
    public Population() {
        this.chromosomes = new ArrayList<Chromosome>();
    }

    public Population clone (Faculty faculty){
        Population population = new Population();
        Chromosome chromosome;
        for(int i=0; i<chromosomes.size(); i++){
            chromosome = chromosomes.get(i).clone();
            chromosome.setFitness(faculty);
            population.addChromosome(chromosome);
        }
        return population;
    }

    public void setChromosomes(ArrayList<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public ArrayList<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public void addChromosome(Chromosome chromosome){
        this.chromosomes.add(chromosome);
    }

    public int getTotalFitness(){
        int totalFitness = 0;
        for (int i = 0; i < chromosomes.size(); i++){
            totalFitness += chromosomes.get(i).getFitness();
        }
        return totalFitness;
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

    public boolean equals(Chromosome chromosome1, Chromosome chromosome2){
        int numberEvents = 0;
        if(chromosome1.getGenes().size() == chromosome2.getGenes().size()) {
            for (int i = 0; i < chromosome1.getGenes().size(); i++) {
                for (int j = 0; j < chromosome2.getGenes().size(); j++) {
                    if (chromosome1.getGenes().get(i).getModuleID() == chromosome2.getGenes().get(j).getModuleID()){
                        if (chromosome1.getGenes().get(i).getCourseID() == chromosome2.getGenes().get(j).getCourseID()) {
                            if (chromosome1.getGenes().get(i).getSemesterID() == chromosome2.getGenes().get(j).getSemesterID()) {
                                if (chromosome1.getGenes().get(i).getDay() == chromosome2.getGenes().get(j).getDay()) {
                                    if (chromosome1.getGenes().get(i).getStartTime() == chromosome2.getGenes().get(j).getStartTime()) {
                                        numberEvents += 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Bad chromosome. Number of timeslots is different.");
        }
        if(numberEvents == chromosome1.getGenes().size()){
            //System.out.println(numberEvents + " " + chromosome1.getGenes().size());
            return true;
        } else {
            return false;
        }
    }

    public Chromosome randomChromosome(){
        int sizePopulation = this.getChromosomes().size();
        int position = (int)(sizePopulation*Math.random());
        //System.out.println("Crossover " + position);
        return this.getChromosomes().get(position).clone();
    }

    public boolean isChromosome(Chromosome chromosome){
        for (int i=0; i<this.getChromosomes().size(); i++){
            if(this.equals(this.getChromosomes().get(i), chromosome)){
                return true;
            }
        }
        return false;
    }

    public Population generateSubPopulation(int size){
        Population subPopulation = new Population();
        for (int i=0; i<size; i++){
            Chromosome chromosome = this.randomChromosome();
            if(i!=0){
                if(!subPopulation.isChromosome(chromosome)){
                    subPopulation.addChromosome(chromosome);
                } else {
                    i--;
                }
            } else{
                subPopulation.addChromosome(chromosome);
            }
        }
        return subPopulation;
    }

}
