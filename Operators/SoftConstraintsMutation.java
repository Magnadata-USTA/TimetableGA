package Operators;

import Model.*;
import Utils.NumberGenerator;
import Utils.SystemGA;

import java.util.ArrayList;

/**
 * Created by Felipe on 28/12/2016.
 */
public class SoftConstraintsMutation extends Mutation {

    @Override
    public Population mutatePopulation(Population population, double probabilityCM, double probabilityGM, Faculty faculty) {
        Population futurePopulation = new Population();
        for (int i = 0; i < population.getChromosomes().size() ; i++) {
            if(Math.random() < probabilityCM){
                Chromosome oldChromosome = population.getChromosomes().get(i).clone();
                Chromosome mutatedChromosome = mutateChromosome(oldChromosome, faculty, probabilityGM);
                //oldChromosome.setFitness(faculty);
                if (futurePopulation.equals(population.getChromosomes().get(i), mutatedChromosome) == true){
                    futurePopulation.addChromosome(mutatedChromosome);
                    //System.out.println("Old chromosome");
                } else {
                    //System.out.println("New chromosome");
                    futurePopulation.addChromosome(population.getChromosomes().get(i));
                    //SystemGA.printOnScreen(population.getChromosomes().get(i));
                    futurePopulation.addChromosome(mutatedChromosome);
                    //SystemGA.printOnScreen(mutatedChromosome);
                    //SystemGA.pause();
                }
            }
        }
        return futurePopulation;
    }

    @Override
    Chromosome mutateChromosome(Chromosome chromosome, Faculty faculty, double probabilityGM) {
        //List genes located violating soft constraints
        ArrayList<Course> courses = faculty.getCourses();
        Chromosome genesSoftConstraints = new Chromosome();

        //System.out.println("New gene");
        for (int j = 0; j < chromosome.getGenes().size() ; j++) {
            //Get genes with faculty soft constraints
            //System.out.println("Faculty soft constraints");
            for (int k = 0; k < courses.size() ; k++) {
                if (chromosome.getGenes().get(j).getModuleID() == courses.get(k).getModuleID()) {
                    if (chromosome.getGenes().get(j).getCourseID() == courses.get(k).getCourseID()) {
                        if (faculty.checkFacultySoftConstraints(chromosome.getGenes().get(j))){
                            genesSoftConstraints.addGene(chromosome.getGenes().get(j).clone());
                            //System.out.println(chromosome.getGenes().get(j).getProfessorID() + " " +chromosome.getGenes().get(j).getStartTime() + " " + chromosome.getGenes().get(j).getDay() + " " + chromosome.getGenes().get(j).getSemesterID());
                        }
                    }
                }
            }
            //Get genes with professors soft constraints
            //System.out.println("Professor soft constraints");
            for (int k = 0; k < courses.size() ; k++) {
                if (chromosome.getGenes().get(j).getModuleID() == courses.get(k).getModuleID()) {
                    if (chromosome.getGenes().get(j).getCourseID() == courses.get(k).getCourseID()) {
                        for ( int l = 0 ; l < faculty.getProfessors().size() ; l++ ) {
                            if ( chromosome.getGenes().get(j).getProfessorID() == faculty.getProfessors().get(l).getProfessorID()) {
                                if(faculty.getProfessors().get(l).checkProfessorSoftConstraints(chromosome.getGenes().get(j))){
                                    genesSoftConstraints.addGene(chromosome.getGenes().get(j).clone());
                                    //System.out.println(faculty.getProfessors().get(l).getProfessorID() + " " + chromosome.getGenes().get(j).getStartTime() + " " + chromosome.getGenes().get(j).getDay() + " " + chromosome.getGenes().get(j).getSemesterID());
                                }
                            }
                        }
                    }
                }
            }
            //Get genes with other (lunch) constraints
            if(chromosome.getGenes().get(j).checkSoftConstraintTime(Constraints.LUNCH_TIME)){
                for (int k = 0; k < courses.size() ; k++) {
                    if (chromosome.getGenes().get(j).getModuleID() == courses.get(k).getModuleID()) {
                        if (chromosome.getGenes().get(j).getCourseID() == courses.get(k).getCourseID()) {
                            genesSoftConstraints.addGene(chromosome.getGenes().get(j).clone());
                            //System.out.println(chromosome.getGenes().get(j).getProfessorID() + " " + chromosome.getGenes().get(j).getStartTime() + " " + chromosome.getGenes().get(j).getDay() + " " + chromosome.getGenes().get(j).getSemesterID());
                        }
                    }
                }
            }
        }

        //System.out.println("Genes without doublings");
        //Remove doublings in genesSoftConstraints
        Chromosome newGenesSoftConstraints = new Chromosome();
        for (int j = 0; j < genesSoftConstraints.getGenes().size() ; j++) {
            if(j==0) {
                newGenesSoftConstraints.addGene(genesSoftConstraints.getGenes().get(j).clone());
                //System.out.println("Genes without doublings. j=0");
            }

            newGenesSoftConstraints.getGenes().size();
            if(!newGenesSoftConstraints.isGene(genesSoftConstraints.getGenes().get(j))){
                newGenesSoftConstraints.addGene(genesSoftConstraints.getGenes().get(j).clone());
                //System.out.println("Genes without doublings");
            }
        }

        for (int j = 0; j < newGenesSoftConstraints.getGenes().size() ; j++) {
            // With a probability of probabilityGM
            if (Math.random() < probabilityGM) {
                Gene gene = newGenesSoftConstraints.getGenes().get(j).clone();
                boolean mutatedGene = false;
                //Remove genes of newGenesSoftConstraints in chromosome
                chromosome.removeGene(gene);
                //Try a max. of maxIterations times to relocate randomly the gene in a timeslot with no softconstraint violation
                int maxIterations = 100;
                for (int k = 0; k < maxIterations ; k++) {
                    //Relocate gene violating soft constraints
                    NumberGenerator ng = new NumberGenerator();
                    gene.setDay(ng.randomDay());
                    gene.setStartTime(ng.randomEvenStartTime());

                    //The following code can be optimized
                    if (chromosome.checkTimeslotAvailability(gene)) {
                        for (int m = 0; m < chromosome.getGenes().size() ; m++) {
                            for (int n = 0; n < courses.size(); n++) {
                                if (chromosome.getGenes().get(m).getModuleID() == courses.get(n).getModuleID()) {
                                    if (chromosome.getGenes().get(m).getCourseID() == courses.get(n).getCourseID()) {
                                        if (!chromosome.hardConstraintsViolation(courses.get(n), faculty, gene)) {
                                            for (int l = 0; l < faculty.getProfessors().size(); l++) {
                                                if (faculty.getProfessors().get(l).getProfessorID() == gene.getProfessorID()) {
                                                    //If professor preference value is not 1. Value of 2 is verified on checkProfessorSoftConstraints function
                                                    if(faculty.getProfessors().get(l).checkProfessorDesirableAvailability(gene)){
                                                        //chromosome.addGene(gene);
                                                        //System.out.println(k +" "+gene.getModuleID() + " " + gene.getCourseID() + " " + gene.getSemesterID() + " " + gene.getDay() + " " + gene.getStartTime());
                                                        //SystemGA.pause();
                                                        m = chromosome.getGenes().size();
                                                        n = courses.size();
                                                        l = faculty.getProfessors().size();
                                                        k = maxIterations;
                                                        mutatedGene = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }

                }
                if(!mutatedGene){
                    chromosome.addGene(newGenesSoftConstraints.getGenes().get(j));
                } else {
                    chromosome.addGene(gene);
                }
                //System.out.println(gene.getProfessorID() + " " +gene.getStartTime() + " " + gene.getDay() + " " + gene.getSemesterID());
                //System.out.println(newGenesSoftConstraints.getGenes().get(j).getProfessorID() + " " + newGenesSoftConstraints.getGenes().get(j).getStartTime() + " " + newGenesSoftConstraints.getGenes().get(j).getDay() + " " + newGenesSoftConstraints.getGenes().get(j).getSemesterID());
                //SystemGA.printOnScreen(chromosome);
                //SystemGA.pause();
            }
        }
        /*
        for (int j = 0; j < newGenesSoftConstraints.getGenes().size() ; j++) {
            System.out.println(newGenesSoftConstraints.getGenes().get(j).getProfessorID() + " " + newGenesSoftConstraints.getGenes().get(j).getStartTime() + " " + newGenesSoftConstraints.getGenes().get(j).getDay() + " " + newGenesSoftConstraints.getGenes().get(j).getSemesterID());
        }*/
        chromosome.setFitness(faculty);
        return chromosome;
    }
}
