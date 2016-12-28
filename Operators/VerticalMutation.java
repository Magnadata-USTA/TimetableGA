package Operators;

import Model.*;
import Utils.SystemGA;

import java.util.ArrayList;

/**
 * Created by Felipe on 27/12/2016.
 */
public class VerticalMutation extends Mutation {

    public Population mutatePopulation(Population population, double probabilityCM, double probabilityGM, Faculty faculty){
        Population futurePopulation = new Population();
        //Population mutatedPopulation = population.clone();
        ArrayList<Course> courses = faculty.getCourses();
        for (int i = 0; i < population.getChromosomes().size() ; i++) {
            if(Math.random() < probabilityCM){
                Chromosome chromosome = population.getChromosomes().get(i).clone();
                Chromosome mutatedChromosome = chromosome.clone();
                Chromosome oldChromosome = population.getChromosomes().get(i).clone();
                //Delete chromosome from futurePopulation
                //mutatedPopulation.getChromosomes().remove(i);
                for (int j = 0; j < chromosome.getGenes().size() ; j++) {
                    for (int k = 0; k < courses.size() ; k++) {
                        if (chromosome.getGenes().get(j).getModuleID() == courses.get(k).getModuleID()) {
                            if (chromosome.getGenes().get(j).getCourseID() == courses.get(k).getCourseID()) {
                                if (Math.random() < probabilityGM) {
                                    Gene gene = chromosome.getGenes().get(j).clone();
                                    //Delete gene to avoid doublingCourse hardConstraintsViolation
                                    if(mutatedChromosome.removeGene(gene) == true) {
                                        int startTime = gene.getStartTime();
                                        int newStartTime = startTime;
                                        if (Math.random() < 0.5) {
                                            //Move vertically down
                                            newStartTime += Constraints.EVENT_DURATION;
                                        } else {
                                            //Move vertically up
                                            newStartTime -= Constraints.EVENT_DURATION;
                                        }
                                        gene.setStartTime(newStartTime);
                                        if (mutatedChromosome.checkTimeslotAvailability(gene) == true) {
                                            if (mutatedChromosome.hardConstraintsViolation(courses.get(k), faculty, gene) == false) {
                                                chromosome.getGenes().get(j).setStartTime(newStartTime);
                                                System.out.println(gene.getModuleID() + " " + gene.getCourseID() + " " + gene.getSemesterID() + " " + gene.getDay() + " " + gene.getStartTime());
                                                //mutatedChromosome = chromosome.clone();
                                                //SystemGA.pause();
                                            }
                                        }
                                    } else {
                                        System.out.println("Gene does not found");
                                    }
                                    mutatedChromosome = chromosome.clone();
                                }
                            }
                        }
                    }
                }
                mutatedChromosome.setFitness(faculty);
                oldChromosome.setFitness(faculty);
                if (futurePopulation.equals(oldChromosome, mutatedChromosome) == true){
                    futurePopulation.addChromosome(mutatedChromosome);
                    System.out.println("Old chromosome");
                    System.out.println(oldChromosome.getFitness());
                    System.out.println(mutatedChromosome.getFitness());
                    //SystemGA.printOnScreen(oldChromosome);
                    //SystemGA.printOnScreen(mutatedChromosome);
                    SystemGA.pause();
                } else {
                    System.out.println("New chromosome");
                    futurePopulation.addChromosome(oldChromosome);
                    futurePopulation.addChromosome(chromosome);
                }
            }
        }
        return futurePopulation;
    }
}
