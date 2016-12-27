package Operators;

import Model.Chromosome;

import java.util.ArrayList;

/**
 * Created by Felipe on 26/12/2016.
 */
public abstract class Selection {
    abstract ArrayList<Chromosome> selectPopulation(Chromosome chromosome);
}


