package TimetableGA;

import DataLoading.DataReading;
import Model.Chromosome;
import Model.Faculty;
import Utils.SystemGA;

/**
 * Created by Felipe on 19/12/2016.
 */
public class main {

    public static void main (String args[]){
        DataReading input = new DataReading();
        int sizePopulation = 30;
        int numGenerations = 500;
        double probabilityChromosomeMutation = 0.7;
        double probabilityGeneMutation = 0.5;
        double probabilityChromosomesCrossover = 0.3;
        double percentageDominantChromosome = 0.5;

        double stepProbabilityChromosomeMutation = 0.03;
        double stepProbabilityGeneMutation = 0.03;
        double stepProbabilityChromosomesCrossover = 0.03;

        Faculty faculty = input.loadData();
        GA1 ga1 = new GA1();

        while (probabilityChromosomeMutation <= 1) {
            while (probabilityGeneMutation <= 1) {
                while (probabilityChromosomesCrossover <= 1) {
                    Chromosome c = ga1.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                            probabilityChromosomesCrossover, percentageDominantChromosome);
                    SystemGA.printInFile(c, faculty, probabilityChromosomeMutation, probabilityGeneMutation, probabilityChromosomesCrossover, +
                            percentageDominantChromosome);
                    probabilityChromosomesCrossover += stepProbabilityChromosomesCrossover;
                    //SystemGA.pause();
                }
                probabilityGeneMutation +=  stepProbabilityGeneMutation;
                probabilityChromosomesCrossover = 0;
            }
            probabilityChromosomeMutation += stepProbabilityChromosomeMutation;
            probabilityGeneMutation = 0;
        }
    }
}
