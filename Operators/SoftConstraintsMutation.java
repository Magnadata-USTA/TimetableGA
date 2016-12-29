package Operators;

import Model.*;

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
        Chromosome mutatedChromosome = chromosome.clone();

        System.out.println("New gene");
        for (int j = 0; j < chromosome.getGenes().size() ; j++) {
            //Get genes with faculty soft constraints
            //System.out.println("Faculty soft constraints");
            for (int k = 0; k < courses.size() ; k++) {
                if (chromosome.getGenes().get(j).getModuleID() == courses.get(k).getModuleID()) {
                    if (chromosome.getGenes().get(j).getCourseID() == courses.get(k).getCourseID()) {
                        if (faculty.checkFacultySoftConstraints(chromosome.getGenes().get(j))){
                            genesSoftConstraints.addGene(chromosome.getGenes().get(j).clone());
                            System.out.println(chromosome.getGenes().get(j).getStartTime() + " " + chromosome.getGenes().get(j).getDay() + " " + chromosome.getGenes().get(j).getSemesterID());
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
                                    System.out.println(faculty.getProfessors().get(l).getProfessorID() + " " + chromosome.getGenes().get(j).getStartTime() + " " + chromosome.getGenes().get(j).getDay() + " " + chromosome.getGenes().get(j).getSemesterID());
                                }
                            }
                        }
                    }
                }
            }
            //Get genes with other (lunch) constraints
            if(chromosome.getGenes().get(j).checkSoftConstraintTime(Constraints.LUNCH_TIME)){
                genesSoftConstraints.addGene(chromosome.getGenes().get(j).clone());
                System.out.println(chromosome.getGenes().get(j).getStartTime() + " " + chromosome.getGenes().get(j).getDay() + " " + chromosome.getGenes().get(j).getSemesterID());
            }
        }

        System.out.println("Genes without doublings");
        //Remove doublings in genesSoftConstraints
        Chromosome newGenesSoftConstraints = new Chromosome();
        for (int j = 0; j < genesSoftConstraints.getGenes().size() ; j++) {
            Gene gene = genesSoftConstraints.getGenes().get(j).clone();
            newGenesSoftConstraints.addGene(gene);
            System.out.println(gene.getStartTime() + " " + gene.getDay() + " " + gene.getSemesterID());
            while(genesSoftConstraints.removeGene(genesSoftConstraints.getGenes().get(j))){

            }
        }

        //Get  list of freetimeslots

        //Relocate genes violating soft constraints with a probability of probabilityGM
        return mutatedChromosome;
    }
}
