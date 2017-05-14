package Utils;

import Model.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


/**
 * Created by Felipe on 19/12/2016.
 */
public class SystemGA {

    public static void printFitness(Population population){
        for (int i = 0; i < population.getChromosomes().size(); i++){
            System.out.println("Fitness chromosome" + i + " : " + population.getChromosomes().get(i).getFitness());
        }
    }

    public static void pause(){
        System.out.println("Press enter to continue...");
        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();
    }

    public static void printInHtml(Chromosome chromosome, Faculty faculty, int sizePopulation, int numGenerations, double probabilityChromosomeMutation,
                                   double probabilityGeneMutation, double probabilityChromosomesCrossover, double percentageDominantChromosome){
        try{
            chromosome.setFitness(faculty);
            File file = new File("C:\\Users\\Felipe\\IdeaProjects\\TimetableGA\\outputHTML\\Timetable-" +
                    String.format("%.2f", probabilityChromosomeMutation) + "-" +
                    String.format("%.2f", probabilityGeneMutation) + "-" +
                    String.format("%.2f", probabilityChromosomesCrossover) + "-" +
                    percentageDominantChromosome + ".html");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("<!DOCTYPE html>" + "\n");
            bw.write("<html>" + "\n");
            bw.write("<head>" + "\n");
            bw.write("<style>" + "\n");
            bw.write("table, th, td {" + "\n");
            bw.write("  border: 1px solid black;" + "\n");
            bw.write("  border-collapse: collapse;" + "\n");
            bw.write("}" + "\n");
            bw.write("th, td {" + "\n");
            bw.write("  padding: 5px;" + "\n");
            bw.write("  text-align: center;" + "\n");
            bw.write("}" + "\n");
            bw.write("</style>" + "\n");
            bw.write("</head>" + "\n");
            bw.write("<body>" + "\n");
            bw.write("<h3>Parameters</h3>\n");

            bw.write("<table style=\"width:70%\">\n");
            bw.write("  <tr>\n");

            bw.write("      <td>Fitness: " + chromosome.getFitness() + "</td>\n");
            bw.write("      <td>Population Size: " + sizePopulation + "</td>\n");
            bw.write("      <td>Number of Generations: " + numGenerations + "</td>\n");
            bw.write("      <td>Probability Chromosome Mutation: " + probabilityChromosomeMutation + "</td>\n");
            bw.write("      <td>Probability Gene Mutation: " + probabilityGeneMutation + "</td>\n");
            bw.write("      <td>Probability Chromosomes Crossover: " + probabilityChromosomesCrossover + "</td>\n");
            bw.write("      <td>Percentage Dominant Chromosome: " + percentageDominantChromosome + "</td>\n");
            bw.write("  </tr>\n");
            bw.write("  </table>\n");
            bw.write("<h1>Horarios 2017-2</h1>" + "\n\n");

            for (int semesterID = 1; semesterID <= Constraints.NUMBER_SEMESTERS; semesterID++) {
                ArrayList<Gene> coursesSemester = new ArrayList<Gene>();
                for (int i = 0; i < chromosome.getGenes().size(); i++) {
                    if (semesterID == chromosome.getGenes().get(i).getSemesterID()) {
                        if(coursesSemester.size() != 0){
                            //Check if course is present
                            if(chromosome.getGenes().get(i).getModuleID() == coursesSemester.get(coursesSemester.size()-1).getModuleID()){
                                if(chromosome.getGenes().get(i).getCourseID() == coursesSemester.get(coursesSemester.size()-1).getCourseID()){
                                    coursesSemester.add(chromosome.getGenes().get(i));
                                }
                            }else{
                                coursesSemester.add(chromosome.getGenes().get(i));
                            }
                        } else {
                            coursesSemester.add(chromosome.getGenes().get(i));
                        }
                    }
                }
                bw.write("<h3>Semestre: " + semesterID + "</h3>\n");
                bw.write("<table style=\"width:70%\">\n");
                bw.write("  <tr>\n");
                bw.write("      <th></th>\n");
                bw.write("      <th>Lunes</th>\n");
                bw.write("      <th>Martes</th>\n");
                bw.write("      <th>Miercoles</th>\n");
                bw.write("      <th>Jueves</th>\n");
                bw.write("      <th>Viernes</th>\n");
                bw.write("      <th>Sabado</th>\n");
                bw.write("  </tr>\n");

                for (int hour = Constraints.EARLIEST_TIME; hour < Constraints.LATEST_TIME; hour+=2) {
                    bw.write("  <tr>\n");
                    bw.write("      <th>" + hour + "</th>\n");
                    for (int dayIt = Constraints.DAY_MIN; dayIt < Constraints.DAY_MAX+1; dayIt++) {
                        boolean emptyTimeSlot = true;
                        for (int i = 0; i < coursesSemester.size(); i++) {
                            if (hour == coursesSemester.get(i).getStartTime()){
                                if(dayIt ==  coursesSemester.get(i).getDay()){
                                    String professorName = "Profesor Ciencias Basicas o Humanidades";
                                    for (int j = 0; j < faculty.getProfessors().size(); j++) {
                                        if (coursesSemester.get(i).getProfessorID() == faculty.getProfessors().get(j).getProfessorID()) {
                                            professorName = faculty.getProfessors().get(j).getName();
                                            break;
                                        }
                                    }
                                    String moduleName = "NA";
                                    for (int j = 0; j < faculty.getModules().size(); j++) {
                                        if (coursesSemester.get(i).getModuleID() == faculty.getModules().get(j).getModuleID()) {
                                            moduleName = faculty.getModules().get(j).getName();
                                            break;
                                        }
                                    }
                                    bw.write("      <td>" + coursesSemester.get(i).getModuleID() + "-" + coursesSemester.get(i).getCourseID() +
                                                "<br>" + moduleName + "<br>" +professorName + "</td>\n");
                                    emptyTimeSlot = false;
                                    break;
                                }
                            }
                        }
                        if(emptyTimeSlot == true){
                            bw.write("      <td></td>\n");
                        }
                    }
                    bw.write("  </tr>\n");
                }
                bw.write("</table>\n");
            }
            bw.write("</body>\n");
            bw.write("</html>\n");
            bw.flush();
            bw.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static  void printStatsInFile(Population population, int generation, double probabilityChromosomeMutation,
                                         double probabilityGeneMutation, double probabilityChromosomesCrossover, double percentageDominantChromosome){
        String path = "C:\\Users\\Felipe\\IdeaProjects\\TimetableGA\\output\\Timetable-" +
                String.format("%.2f", probabilityChromosomeMutation) + "-" +
                String.format("%.2f", probabilityGeneMutation) + "-" +
                String.format("%.2f", probabilityChromosomesCrossover) + "-" +
                percentageDominantChromosome + "-STATS.txt";
        try {
            File file = new File(path);
            file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(fw);
            printer.append(generation + " " + population.getChromosomes().size() + " " + population.getBestFitness() + " " +
                    population.getFitnessAverage() + " " + population.getFitnessStandardDeviation() + "\n");
            printer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static  void printInFile(Chromosome chromosome, Faculty faculty, double probabilityChromosomeMutation,
                                    double probabilityGeneMutation, double probabilityChromosomesCrossover, double percentageDominantChromosome){
        try{
            chromosome.setFitness(faculty);
            File file = new File("C:\\Users\\Felipe\\IdeaProjects\\TimetableGA\\output\\Timetable-" +
                    String.format("%.2f", probabilityChromosomeMutation) + "-" +
                    String.format("%.2f", probabilityGeneMutation) + "-" +
                    String.format("%.2f", probabilityChromosomesCrossover) + "-" +
                    percentageDominantChromosome + ".txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Fitness: " + chromosome.getFitness());

            for (int semesterID = 1; semesterID <= Constraints.NUMBER_SEMESTERS; semesterID++) {
                ArrayList<Gene> coursesSemester = new ArrayList<Gene>();
                for (int i = 0; i < chromosome.getGenes().size(); i++) {
                    if (semesterID == chromosome.getGenes().get(i).getSemesterID()) {
                        if(coursesSemester.size() != 0){
                            //Check if course is present
                            if(chromosome.getGenes().get(i).getModuleID() == coursesSemester.get(coursesSemester.size()-1).getModuleID()){
                                if(chromosome.getGenes().get(i).getCourseID() == coursesSemester.get(coursesSemester.size()-1).getCourseID()){
                                    coursesSemester.add(chromosome.getGenes().get(i));
                                }
                            }else{
                                coursesSemester.add(chromosome.getGenes().get(i));
                            }
                        } else {
                            coursesSemester.add(chromosome.getGenes().get(i));
                        }
                    }
                }
                bw.write("\n\nSemestre: " + semesterID + "\n");
                ArrayList<String> Days = new ArrayList<String>();
                Days.add("Lunes");
                Days.add("Martes");
                Days.add("Miercoles");
                Days.add("Jueves");
                Days.add("Viernes");
                Days.add("Sábado");
                for (int i = 0; i < coursesSemester.size(); i++) {
                    int startTime = coursesSemester.get(i).getStartTime();
                    int endTime = coursesSemester.get(i).getStartTime() + 2;
                    int day = coursesSemester.get(i).getDay()-1;

                    String professorName = "Profesor Ciencias Básicas o Humanidades";
                    for (int j = 0; j < faculty.getProfessors().size(); j++) {
                        if(coursesSemester.get(i).getProfessorID() == faculty.getProfessors().get(j).getProfessorID()){
                            professorName = faculty.getProfessors().get(j).getName();
                            break;
                        }
                    }
                    String moduleName = "NA";
                    for (int j = 0; j < faculty.getModules().size(); j++) {
                        if(coursesSemester.get(i).getModuleID() == faculty.getModules().get(j).getModuleID()){
                            moduleName = faculty.getModules().get(j).getName();
                            break;
                        }
                    }

                    if(i == 0){
                        bw.write(coursesSemester.get(i).getModuleID() + "-" + coursesSemester.get(i).getCourseID() + " " + moduleName + "\n");
                        bw.write(coursesSemester.get(i).getProfessorID() + " " + professorName + "\n");
                        bw.write(Days.get(day) + " " + startTime+ " " + endTime + " | ");
                    } else{
                        if(coursesSemester.get(i).getModuleID() == coursesSemester.get(i-1).getModuleID()){
                            bw.write(Days.get(day) + " " + startTime+ " " + endTime + " | ");
                        } else {
                            bw.write("\n\n");
                            bw.write(coursesSemester.get(i).getModuleID() + "-" + coursesSemester.get(i).getCourseID() + " " + moduleName + "\n");
                            bw.write(coursesSemester.get(i).getProfessorID() + " " + professorName + "\n");
                            bw.write(Days.get(day) + " " + startTime+ " " + endTime + " | ");
                        }
                    }
                }
            }
            bw.flush();
            bw.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static  void printBySemester(Chromosome chromosome, Faculty faculty){
        for (int semesterID = 1; semesterID <= Constraints.NUMBER_SEMESTERS; semesterID++) {
            ArrayList<Gene> coursesSemester = new ArrayList<Gene>();
            for (int i = 0; i < chromosome.getGenes().size(); i++) {
                if (semesterID == chromosome.getGenes().get(i).getSemesterID()) {
                    if(coursesSemester.size() != 0){
                        //Check if course is present
                        //for(int j = 0; j < coursesSemester.size(); j++) {
                        //System.out.println(coursesSemester.size());
                            if(chromosome.getGenes().get(i).getModuleID() == coursesSemester.get(coursesSemester.size()-1).getModuleID()){
                                if(chromosome.getGenes().get(i).getCourseID() == coursesSemester.get(coursesSemester.size()-1).getCourseID()){
                                    coursesSemester.add(chromosome.getGenes().get(i));
                                    //System.out.println(chromosome.getGenes().get(i).getModuleID() + " " + chromosome.getGenes().get(i).getCourseID());
                                    //SystemGA.pause();
                                    //j = coursesSemester.size();
                                }
                            }else{
                                coursesSemester.add(chromosome.getGenes().get(i));
                                //System.out.println(chromosome.getGenes().get(i).getModuleID() + " " + chromosome.getGenes().get(i).getCourseID());
                            }
                        //}
                    } else {
                        coursesSemester.add(chromosome.getGenes().get(i));
                        //System.out.println(chromosome.getGenes().get(i).getModuleID() + "-" + chromosome.getGenes().get(i).getCourseID());
                        //SystemGA.pause();
                    }
                }
            }
            System.out.println("\n");
            System.out.println("Semestre: " + semesterID);
            ArrayList<String> Days = new ArrayList<String>();
            Days.add("Lunes");
            Days.add("Martes");
            Days.add("Miercoles");
            Days.add("Jueves");
            Days.add("Viernes");
            Days.add("Sábado");
           for (int i = 0; i < coursesSemester.size(); i++) {
               int startTime = coursesSemester.get(i).getStartTime();
               int endTime = coursesSemester.get(i).getStartTime() + 2;
               int day = coursesSemester.get(i).getDay()-1;

               String professorName = "Profesor Ciencias Básicas o Humanidades";
               for (int j = 0; j < faculty.getProfessors().size(); j++) {
                   if(coursesSemester.get(i).getProfessorID() == faculty.getProfessors().get(j).getProfessorID()){
                       professorName = faculty.getProfessors().get(j).getName();
                       break;
                   }
               }
               String moduleName = "NA";
               for (int j = 0; j < faculty.getModules().size(); j++) {
                   if(coursesSemester.get(i).getModuleID() == faculty.getModules().get(j).getModuleID()){
                            moduleName = faculty.getModules().get(j).getName();
                            break;
                   }
               }

                if(i == 0){
                    System.out.println(coursesSemester.get(i).getModuleID() + "-" + coursesSemester.get(i).getCourseID() + " " + moduleName);
                    System.out.println(coursesSemester.get(i).getProfessorID() + " " + professorName);
                    System.out.print(Days.get(day) + " " + startTime+ " " + endTime + " | ");
                } else{
                    if(coursesSemester.get(i).getModuleID() == coursesSemester.get(i-1).getModuleID()){
                        System.out.print(Days.get(day) + " " + startTime+ " " + endTime + " | ");
                    } else {
                        System.out.println("\n");
                        System.out.println(coursesSemester.get(i).getModuleID() + "-" + coursesSemester.get(i).getCourseID() + " " + moduleName);
                        System.out.println(coursesSemester.get(i).getProfessorID() + " " + professorName);
                        System.out.print(Days.get(day) + " " + startTime+ " " + endTime + " | ");
                    }
                }
            }
        }
    }

    public static void printOnScreen(Chromosome chromosome) {
        for (int semesterID = 1; semesterID <= Constraints.NUMBER_SEMESTERS; semesterID++) {
            System.out.println("S: " + semesterID + "|\t" + "Monday\t\t" + "Tuesday\t\t" + "Wednesday\t" + "Thursday\t\t" + "Friday\t\t|");
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
                    variable2ToPrint[timeslotsValue][day - 1] = r + t + "-" + y.getProfessorID();
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
                    if (yy != genesSemester[x].length) System.out.print("\t");
                }
                System.out.println("|");
            }
            System.out.println();
        }
    }
}
