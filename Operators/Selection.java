package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;

import java.util.ArrayList;

/**
 * Created by Felipe on 26/12/2016.
 */
public abstract class Selection {
    abstract Population selectPopulation(int size, Population population, Faculty faculty);
}


