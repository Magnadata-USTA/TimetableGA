package Operators;

import Model.*;
import Utils.*;

import java.util.ArrayList;

/**
 * Created by Felipe on 17/12/2016.
 */
public class RandomPopulationReproduction extends Reproduction {

    public ArrayList<Chromosome> initiatePopulation(int size, Chromosome chromosome, Faculty faculty){
        ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
        for (int h = 0 ; h < size ;  h++) {
            Chromosome newChromosome = chromosome.clone();
            ArrayList<Course> courses = faculty.getCourses();
            for (int i = 0; i < courses.size(); i++) {
                int duration = courses.get(i).getDuration();
                int hoursPerWeek = courses.get(i).getHoursPerWeek();
                int numberTimeslots = hoursPerWeek / duration;

                for (int j = 0; j < numberTimeslots; j++) {
                    boolean doublingCourse = true;
                    boolean professorAvailable;
                    boolean doublingProfessor;

                    while (doublingCourse == true) {
                        Gene available = newChromosome.findEmptyTimeslot(courses.get(i));
                        for (int k = 0; k < faculty.getProfessors().size(); k++) {
                            if (faculty.getProfessors().get(k).getProfessorID() == courses.get(i).getProfesorID()) {
                                professorAvailable = faculty.getProfessors().get(k).checkProfessorAvailability(available);
                                if (professorAvailable == true) {
                                    doublingCourse = newChromosome.checkTimeslotDoubling(available);
                                    if (doublingCourse == false) {
                                        doublingProfessor = newChromosome.checkProfessorDoubling(available);
                                        if (doublingProfessor == false) {
                                            newChromosome.addGene(available);
                                            break;
                                        } else {
                                            doublingCourse = true;
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            newPopulation.add(newChromosome);
            newChromosome.setFitness(faculty);
            int fitness = newChromosome.getFitness();
            System.out.println("Fitness value new is = "+fitness);
			//SystemGA.printOnScreen(newChromosome);
			//SystemGA.pause();
        }
        return newPopulation;
    }
}
