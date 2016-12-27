package Operators;

import Model.Population;

import java.util.ArrayList;

/**
 * Created by Felipe on 19/12/2016.
 */
public abstract class Mutation {

    abstract Population mutatePopulation(Population population, double probabilityOfMutation, double  percentageOfGenes);
}
