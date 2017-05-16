package Operators;

import Model.*;
import Utils.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Felipe on 17/12/2016.
 */
public class RandomPopulationGeneration extends Generation {

    public Population initiatePopulation(int size, Chromosome chromosome, Faculty faculty){
        Population population = new Population();
        for (int h = 0 ; h < size ;  h++) {
            System.out.println("Size: " + h);
            Chromosome newChromosome = chromosome.clone();
            ArrayList<Course> courses = faculty.getCourses();
            for (int i = 0; i < courses.size(); i++) {
                int duration = courses.get(i).getDuration();
                int hoursPerWeek = courses.get(i).getHoursPerWeek();
                int numberTimeslots = hoursPerWeek/duration;

                for (int j = 0; j < numberTimeslots; j++) {
                    //Avoid program blocks by restarting chromosome creation.
                    newChromosome.addRandomGene(courses.get(i), faculty);
                    /*if(!newChromosome.addRandomGene(courses.get(i), faculty)){
                        i = 0;
                        j = numberTimeslots;
                        newChromosome = chromosome.clone();
                        System.out.println("here again " + population.getChromosomes().size());
                        SystemGA.pause();
                        //break;
                    }*/
                    /*
                    boolean hardConstraintsViolation = true;
                    while (hardConstraintsViolation) {
                        Gene availableGene = newChromosome.findEmptyTimeslot(courses.get(i));
                        hardConstraintsViolation = newChromosome.hardConstraintsViolation(courses.get(i), faculty, availableGene);
                        if(!hardConstraintsViolation){
                            newChromosome.addGene(availableGene);
                        }
                    }*/
                }
            }
            population.addChromosome(newChromosome);
            newChromosome.setFitness(faculty);
            //Date date = new Date();
            //int fitness = newChromosome.getFitness();
            //System.out.println("Fitness value new is = "+fitness + " " + date.toString());
			//SystemGA.printOnScreen(newChromosome);
			//SystemGA.pause();
        }
        return population;
    }
}
