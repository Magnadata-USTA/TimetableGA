package Operators;

import Model.Chromosome;
import Model.Course;
import Model.Faculty;
import Model.Population;
import Model.Gene;
import Utils.SystemGA;

import java.util.ArrayList;

/**
 * Created by Felipe on 30/12/2016.
 */
public class CourseCrossover extends Crossover {
    @Override
    public Population crossPopulation(Population population, double probabilityChC, double percentageDC, Faculty faculty) {
        int numberOfCrossovers = population.getChromosomes().size();
        for (int i = 0; i <  numberOfCrossovers; i++) {
            //With probabilityChC select two random chromosomes from population for crossover
            if (Math.random() < probabilityChC) {
                Chromosome chromosome1 = population.randomChromosome();
                Chromosome chromosome2 = population.randomChromosome();
                while (population.equals(chromosome1, chromosome2)) {
                    chromosome2 = population.randomChromosome();
                }
                Chromosome childChromosome1 = crossChromosomes(chromosome1, chromosome2, percentageDC, faculty);
                if (!population.equals(chromosome1, childChromosome1)) {
                    if (!population.equals(chromosome2, childChromosome1)) {
                        population.addChromosome(childChromosome1);
                    }
                }
                Chromosome childChromosome2 = crossChromosomes(chromosome2, chromosome1, percentageDC, faculty);
                if (!population.equals(chromosome1, childChromosome2)) {
                    if (!population.equals(chromosome2, childChromosome2)) {
                        population.addChromosome(childChromosome2);
                    }
                }
            }
        }
        return population;
    }

    public Chromosome crossChromosomes(Chromosome chromosome1, Chromosome chromosome2, double percentageDC, Faculty faculty) {
        ArrayList<Course> courses = faculty.getCourses();
        //Generate baseChromosome
        Chromosome baseChromosome = new Chromosome();
        for (int j = 0; j < chromosome1.getGenes().size(); j++) {
            if (!faculty.checkCourse(chromosome1.getGenes().get(j))) {
                baseChromosome.addGene(chromosome1.getGenes().get(j).clone());
                //System.out.println("C1 " + chromosome1.getGenes().get(j).getModuleID() + " " + chromosome1.getGenes().get(j).getCourseID() + " " + chromosome1.getGenes().get(j).getProfessorID() + " " + chromosome1.getGenes().get(j).getStartTime() + " " + chromosome1.getGenes().get(j).getDay() + " " + chromosome1.getGenes().get(j).getSemesterID());
            }
        }

        //For each course with probabilityCoC
        // (if !hardConstraintsViolation) crossover course of chromosome1 into chromosome2 and viceversa (generation of one or two children).
        // (else) (no children generated)
        Chromosome childChromosome = baseChromosome.clone();
        for (int k = 0; k < courses.size(); k++) {
            boolean geneCrossover = false;
            boolean facultyCourse = false;
            if (Math.random() < percentageDC) {
                for (int j = 0; j < chromosome1.getGenes().size(); j++) {
                    geneCrossover = false;
                    facultyCourse = false;
                    Gene gene = chromosome1.getGenes().get(j);
                    if (gene.getModuleID() == courses.get(k).getModuleID()) {
                        if (gene.getCourseID() == courses.get(k).getCourseID()) {
                            facultyCourse = true;
                            if (childChromosome.checkTimeslotAvailability(gene)) {
                                if (!childChromosome.hardConstraintsViolation(courses.get(k), faculty, gene)) {
                                    childChromosome.addGene(chromosome1.getGenes().get(j).clone());
                                    //System.out.println("C1 " + chromosome1.getGenes().get(j).getModuleID() + " " + chromosome1.getGenes().get(j).getCourseID() + " " + chromosome1.getGenes().get(j).getProfessorID() + " " + chromosome1.getGenes().get(j).getStartTime() + " " + chromosome1.getGenes().get(j).getDay() + " " + chromosome1.getGenes().get(j).getSemesterID());
                                    geneCrossover = true;
                                }
                            }
                        }
                    }
                    if (!geneCrossover) {
                        if (facultyCourse) {
                            childChromosome.addRandomGene(courses.get(k), faculty);
                        }
                    }
                }
            } else {
                for (int l = 0; l < chromosome2.getGenes().size(); l++) {
                    geneCrossover = false;
                    facultyCourse = false;
                    Gene gene = chromosome2.getGenes().get(l);
                    if (gene.getModuleID() == courses.get(k).getModuleID()) {
                        if (gene.getCourseID() == courses.get(k).getCourseID()) {
                            facultyCourse = true;
                            if (childChromosome.checkTimeslotAvailability(gene)) {
                                if (!childChromosome.hardConstraintsViolation(courses.get(k), faculty, gene)) {
                                    childChromosome.addGene(chromosome2.getGenes().get(l).clone());
                                    //System.out.println("C2 " + chromosome2.getGenes().get(l).getModuleID() + " " + chromosome2.getGenes().get(l).getCourseID() + " " + chromosome2.getGenes().get(l).getProfessorID() + " " + chromosome2.getGenes().get(l).getStartTime() + " " + chromosome2.getGenes().get(l).getDay() + " " + chromosome2.getGenes().get(l).getSemesterID());
                                    geneCrossover = true;

                                }
                            }
                        }
                    }
                    if (!geneCrossover) {
                        if (facultyCourse) {
                            childChromosome.addRandomGene(courses.get(k), faculty);
                        }
                    }
                }
            }
        }
        childChromosome.setFitness(faculty);
        //SystemGA.printOnScreen(childChromosome);
        return childChromosome;
    }
}
