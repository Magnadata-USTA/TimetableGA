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
        //Impact of selection policies
        int sizePopulation = 50;
        int numGenerations = 1000;
        double probabilityChromosomesCrossover = 0.03;
        double probabilityChromosomeMutation = 0.05;
        double probabilityGeneMutation = 0.04;
        double percentageDominantChromosome = 0.90;

        Faculty faculty = input.loadData();

        RA_HOVE_ELI RA_HOVE_ELI = new RA_HOVE_ELI();
        Chromosome colcaci1 = RA_HOVE_ELI.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_HOVE_RA RA_HOVE_RA = new RA_HOVE_RA();
        Chromosome colcaci2 = RA_HOVE_RA.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_HOVE_RWH RA_HOVE_RWH = new RA_HOVE_RWH();
        Chromosome colcaci3 = RA_HOVE_RWH.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_HOVE_SUS RA_HOVE_SUS = new RA_HOVE_SUS();
        Chromosome colcaci4 = RA_HOVE_SUS.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_HOVE_TRU RA_HOVE_TRU = new RA_HOVE_TRU();
        Chromosome colcaci5 = RA_HOVE_TRU.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_HOVE_TSE RA_HOVE_TSE = new RA_HOVE_TSE();
        Chromosome colcaci6 = RA_HOVE_TSE.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);

/*
        RA_HO_ELI RA_HO_ELI = new RA_HO_ELI();
        Chromosome c31 = RA_HO_ELI.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_VE_ELI RA_VE_ELI = new RA_VE_ELI();
        Chromosome c32 = RA_VE_ELI.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_SC_ELI RA_SC_ELI = new RA_SC_ELI();
        Chromosome c33 = RA_SC_ELI.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);

        RX_HO_ELI RX_HO_ELI = new RX_HO_ELI();
        Chromosome c34 = RX_HO_ELI.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_VE_ELI RX_VE_ELI = new RX_VE_ELI();
        Chromosome c35 = RX_VE_ELI.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_SC_ELI RX_SC_ELI = new RX_SC_ELI();
        Chromosome c36 = RX_SC_ELI.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
*/
/*
        //Generation: Random. Mutation: Horizontal.
        RA_HO_RA RA_HO_RA = new RA_HO_RA();
        Chromosome c1 = RA_HO_RA.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_HO_RWH RA_HO_RWH = new RA_HO_RWH();
        Chromosome c2 = RA_HO_RWH.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_HO_SUS RA_HO_SUS = new RA_HO_SUS();
        Chromosome c3 = RA_HO_SUS.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_HO_TRU RA_HO_TRU = new RA_HO_TRU();
        Chromosome c4 = RA_HO_TRU.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_HO_TSE RA_HO_TSE = new RA_HO_TSE();
        Chromosome c5 = RA_HO_TSE.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);



        //Generation: Random. Mutation: Vertical.
        RA_VE_RA RA_VE_RA = new RA_VE_RA();
        Chromosome c6 = RA_VE_RA.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_VE_RWH RA_VE_RWH = new RA_VE_RWH();
        Chromosome c7 = RA_VE_RWH.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_VE_SUS RA_VE_SUS = new RA_VE_SUS();
        Chromosome c8 = RA_VE_SUS.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_VE_TRU RA_VE_TRU = new RA_VE_TRU();
        Chromosome c9 = RA_VE_TRU.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_VE_TSE RA_VE_TSE = new RA_VE_TSE();
        Chromosome c10 = RA_VE_TSE.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);


        //Generation: BestRandom. Mutation: Horizontal.
        RX_HO_RA RX_HO_RA = new RX_HO_RA();
        Chromosome c11 = RX_HO_RA.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_HO_RWH RX_HO_RWH = new RX_HO_RWH();
        Chromosome c12 = RX_HO_RWH.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_HO_SUS RX_HO_SUS = new RX_HO_SUS();
        Chromosome c13 = RX_HO_SUS.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_HO_TRU RX_HO_TRU = new RX_HO_TRU();
        Chromosome c14 = RX_HO_TRU.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_HO_TSE RX_HO_TSE = new RX_HO_TSE();
        Chromosome c15 = RX_HO_TSE.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);

        //Generation: BestRandom. Mutation: Vertical.
        RX_VE_RA RX_VE_RA = new RX_VE_RA();
        Chromosome c16 = RX_VE_RA.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_VE_RWH RX_VE_RWH = new RX_VE_RWH();
        Chromosome c17 = RX_VE_RWH.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_VE_SUS RX_VE_SUS = new RX_VE_SUS();
        Chromosome c18 = RX_VE_SUS.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_VE_TRU RX_VE_TRU = new RX_VE_TRU();
        Chromosome c19 = RX_VE_TRU.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_VE_TSE RX_VE_TSE = new RX_VE_TSE();
        Chromosome c20 = RX_VE_TSE.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);

        //Generation: Random. Mutation: SoftConstraint.
        RA_SC_RA RA_SC_RA = new RA_SC_RA();
        Chromosome c21 = RA_SC_RA.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_SC_RWH RA_SC_RWH = new RA_SC_RWH();
        Chromosome c22 = RA_SC_RWH.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_SC_SUS RA_SC_SUS = new RA_SC_SUS();
        Chromosome c23 = RA_SC_SUS.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_SC_TRU RA_SC_TRU = new RA_SC_TRU();
        Chromosome c24 = RA_SC_TRU.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RA_SC_TSE RA_SC_TSE = new RA_SC_TSE();
        Chromosome c25 = RA_SC_TSE.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);

        //Generation: BestRandom. Mutation: SoftConstraint.
        RX_SC_RA RX_SC_RA = new RX_SC_RA();
        Chromosome c26 = RX_SC_RA.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_SC_RWH RX_SC_RWH = new RX_SC_RWH();
        Chromosome c27 = RX_SC_RWH.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_SC_SUS RX_SC_SUS = new RX_SC_SUS();
        Chromosome c28 = RX_SC_SUS.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_SC_TRU RX_SC_TRU = new RX_SC_TRU();
        Chromosome c29 = RX_SC_TRU.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
        RX_SC_TSE RX_SC_TSE = new RX_SC_TSE();
        Chromosome c30 = RX_SC_TSE.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                probabilityChromosomesCrossover, percentageDominantChromosome);
*/
        /*
        double stepProbabilityChromosomeMutation = 0.03;
        double stepProbabilityGeneMutation = 0.03;
        double stepProbabilityChromosomesCrossover = 0.02;

        while (probabilityChromosomesCrossover <= 1) {
            while (probabilityChromosomeMutation <= 1) {
                while (probabilityGeneMutation <= 1) {
                    Chromosome c = ga1.run(input, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, +
                            probabilityChromosomesCrossover, percentageDominantChromosome);
                    SystemGA.printInHtml(c, faculty, sizePopulation, numGenerations, probabilityChromosomeMutation, probabilityGeneMutation, probabilityChromosomesCrossover, +
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
        */
    }
}
