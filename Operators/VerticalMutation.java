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
        for (int i = 0; i < population.getChromosomes().size() ; i++) {
            Chromosome oldChromosome = population.getChromosomes().get(i).clone();
            oldChromosome.setFitness(faculty);
            if(Math.random() < probabilityCM){
                Chromosome mutatedChromosome = mutateChromosome(oldChromosome, faculty, probabilityGM);
                //oldChromosome.setFitness(faculty);
                if (futurePopulation.equals(population.getChromosomes().get(i), mutatedChromosome)){
                    futurePopulation.addChromosome(mutatedChromosome);
                    //System.out.println("Old chromosome");
                } else {
                    //System.out.println("New chromosome");
                    futurePopulation.addChromosome(population.getChromosomes().get(i));
                    futurePopulation.addChromosome(mutatedChromosome);
                }
            } else {
                futurePopulation.addChromosome(oldChromosome);
            }
        }
        return futurePopulation;
    }

    public Chromosome mutateChromosome(Chromosome chromosome, Faculty faculty, double probabilityGM){
        ArrayList<Course> courses = faculty.getCourses();
        Chromosome mutatedChromosome = chromosome.clone();
        for (int j = 0; j < chromosome.getGenes().size() ; j++) {
            for (int k = 0; k < courses.size() ; k++) {
                if (chromosome.getGenes().get(j).getModuleID() == courses.get(k).getModuleID()) {
                    if (chromosome.getGenes().get(j).getCourseID() == courses.get(k).getCourseID()) {
                        if (Math.random() < probabilityGM) {
                            Gene gene = chromosome.getGenes().get(j).clone();
                            //Delete gene to avoid doublingCourse hardConstraintsViolation
                            if(mutatedChromosome.removeGene(gene)) {
                                int newStartTime = gene.getStartTime();
                                if (Math.random() < 0.5) {
                                    //Move vertically down
                                    newStartTime += Constraints.EVENT_DURATION;
                                } else {
                                    //Move vertically up
                                    newStartTime -= Constraints.EVENT_DURATION;
                                }
                                gene.setStartTime(newStartTime);
                                //System.out.println(gene.getModuleID() + " " + gene.getCourseID() + " " + gene.getSemesterID() + " " + gene.getDay() + " " + gene.getStartTime());
                                //SystemGA.printOnScreen(mutatedChromosome);
                                //SystemGA.pause();
                                if (mutatedChromosome.checkTimeslotAvailability(gene)) {
                                    if (!mutatedChromosome.hardConstraintsViolation(courses.get(k), faculty, gene)) {
                                        chromosome.getGenes().get(j).setStartTime(newStartTime);
                                        //System.out.println(gene.getModuleID() + " " + gene.getCourseID() + " " + gene.getSemesterID() + " " + gene.getDay() + " " + gene.getStartTime());
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
        return mutatedChromosome;
    }
}
