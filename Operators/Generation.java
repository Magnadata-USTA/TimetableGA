package Operators;

import Model.Chromosome;
import Model.Faculty;
import Model.Population;

import java.util.ArrayList;

/**
 * Created by Felipe on 17/12/2016.
 */
public abstract class Generation {
    abstract Population initiatePopulation(int size, Chromosome chromosome, Faculty faculty);
}
