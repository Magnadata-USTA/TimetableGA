package Operators;

import Model.Chromosome;
import Model.Faculty;

import java.util.ArrayList;

/**
 * Created by Felipe on 17/12/2016.
 */
public abstract class Reproduction {

    abstract ArrayList<Chromosome> initiatePopulation(int size, Chromosome cromosome, Faculty faculty);
}
