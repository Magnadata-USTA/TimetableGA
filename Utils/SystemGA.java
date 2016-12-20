package Utils;

import Model.Chromosome;
import Model.Constraints;
import Model.Gene;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Felipe on 19/12/2016.
 */
public class SystemGA {

    //Write a file with generation number, average fitness and best fitness
    public static void printOnFile(){

    }

    //Compute average fitness value
    public void averageFitness(ArrayList<Chromosome> chromosomes){

    }

    //Find the best fitness value
    public void bestFitness(ArrayList<Chromosome> chromosomes){

    }

    public static void pause(){
        java.lang.System.out.println("Press enter to continue...");
        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();
    }

    public static void printOnScreen(Chromosome chromosome) {
        for (int semesterID = 1; semesterID <= Constraints.NUMBER_SEMESTERS; semesterID++) {
            System.out.println("S: " + semesterID + "|\t" + "Monday\t\t" + "Tuesday\t\t" + "Wednesday\t" + "Thursday\t" + "Friday\t\t|");
            int numDays = Constraints.DAY_MAX;
            int numTimeslots = ((Constraints.HR_MAX - Constraints.EARLIEST_TIME) / 2);
            Gene[][] genesSemester = new Gene[numTimeslots][numDays];
            int[][] variableToPrint = new int[numTimeslots][numDays];
            String[][] variable2ToPrint = new String[numTimeslots][numDays];
            for (int i = 0; i < chromosome.getGenes().size(); i++) {
                Gene y = new Gene();
                y = chromosome.getGenes().get(i);
                int semester;
                int day;
                int startTime;
                startTime = y.getStartTime();
                day = y.getDay();
                semester = y.getSemesterID();
                if (semesterID == semester) {
                    int timeslotsValue;
                    timeslotsValue = (startTime - Constraints.EARLIEST_TIME) / 2;
                    genesSemester[timeslotsValue][day - 1] = y;
                    /*
                     * The variable shown on the screen can be change in the next line
                     * It must be type INT
                     */
                    String r = Integer.toString(y.getModuleID());
                    String t = Character.toString(y.getCourseID());
                    variable2ToPrint[timeslotsValue][day - 1] = r + t;
                }
            }
            //The next 'for' shows the timetable on the screen
            int n = 0;
            for (int x = 0; x < genesSemester.length; x++) {
                int s = x+Constraints.EARLIEST_TIME;
                if (s%2 == 0 ) {
                    s = s + n;
                    n = n + 1;
                    System.out.print("|" + s +"\t|\t");
                }
                else {
                    s = s + n;
                    n = n + 1;
                    System.out.print("|" + s +"\t|\t");
                }
                for (int yy = 0; yy < genesSemester[x].length; yy++) {
                    System.out.print(variable2ToPrint[x][yy]);
                    if (yy != genesSemester[x].length) System.out.print("\t\t");
                }
                System.out.println("|");
            }
            System.out.println();
        }
    }
}
