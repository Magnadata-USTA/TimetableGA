package TimetableGA;

import DataLoading.DataReading;

/**
 * Created by Felipe on 19/12/2016.
 */
public class main {

    public static void main (String args[]){
        DataReading input = new DataReading();
        GeneticAlgorithm ga = new GeneticAlgorithm(input, 1000, 5);
        ga.run();
    }
}
