package TimetableGA;

import DataLoading.DataReading;
import Model.Chromosome;
import Utils.SystemGA;

/**
 * Created by Felipe on 19/12/2016.
 */
public class main {

    public static void main (String args[]){
        DataReading input = new DataReading();
        int sizePopulation = 5;
        int numGenerations = 1;
        double probabilityChromosomeMutation = 0.8;
        double probabilityGeneMutation = 0.3;
        double probabilityChromosomesCrossover = 0.3 ;
        double percentageDominantChromosome = 0.5;

        GA1 ga1 = new GA1();
        Chromosome c = ga1.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, probabilityChromosomesCrossover, percentageDominantChromosome);
        SystemGA.printBySemester(c);
        //SystemGA.printOnScreen(c);
    }
}
