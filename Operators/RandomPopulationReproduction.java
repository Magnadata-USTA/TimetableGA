package Operators;

import Model.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
            int fitness = newChromosome.fitness(faculty);
            System.out.println("Fitness value new is = "+fitness);
            /*
			for( int ii = 1 ; ii <= Constraints.NUMBER_SEMESTERS ; ii++ ){
				printOnScreen(newChromosome, ii);
				System.out.println();
			}
			pauseProg();
            */
        }
        return newPopulation;
    }

    public static void pauseProg(){
        System.out.println("Press enter to continue...");
        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();
    }

    void printOnScreen(Chromosome chromosome, int semesterID){
		/*
		 * This is the way to print all semesters
		 *
		 * for( int ii = 1 ; ii <= Constraints.NUMBER_SEMESTERS ; ii++ ){
		  		printOnScreen(chromosome, ii);
				System.out.println();}
		 */
        int numDays = Constraints.DAY_MAX;
        int numTimeslots = ((Constraints.HR_MAX-Constraints.EARLIEST_TIME)/2);
        Gene [][] genesSemester = new Gene[numTimeslots][numDays];
        int [][] variableToPrint = new int[numTimeslots][numDays];
        String [][] variable2ToPrint = new String[numTimeslots][numDays];
        for (int i = 0; i < chromosome.getGenes().size(); i++) {
            Gene y = new Gene();
            y = chromosome.getGenes().get(i);
            int semester;
            int day;
            int startTime;
            startTime = y.getStartTime();
            day = y.getDay();
            semester = y.getSemesterID();
            if (semesterID == semester){
                int timeslotsValue;
                timeslotsValue = (startTime-Constraints.EARLIEST_TIME)/2;
                genesSemester[timeslotsValue][day-1] = y;
 				/*
 				 * The variable shown on the screen can be change in the next line
 				 * It must be type INT
 				 */
                String r = Integer.toString(y.getModuleID());
                String  t = Character.toString(y.getCourseID());
                variable2ToPrint[timeslotsValue][day-1] = r+t;
            }
        }
        //The next 'for' shows the timetable on the screen
        for (int x=0; x < genesSemester.length; x++) {
            System.out.print("|");
            for (int yy=0; yy < genesSemester[x].length; yy++) {
                System.out.print (variable2ToPrint[x][yy]);
                if (yy!=genesSemester[x].length) System.out.print("\t\t");
            }
            System.out.println("|");
        }
    }
}
