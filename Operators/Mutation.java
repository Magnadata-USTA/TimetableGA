package Operators;

import Model.Chromosome;

import java.util.ArrayList;

/**
 * Created by Felipe on 19/12/2016.
 */
public abstract class Mutation {

    abstract ArrayList<Chromosome> mutatePopulation(Chromosome cromosome);
}