import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GeneticAlgorithm {
	//private Arrays population;
	private int numGenerations;
	private int sizeElite;
	private ArrayList<Chromosome> chromosomes;

	
	public GeneticAlgorithm(int sizeElite, int numGenerations){
		this.sizeElite = sizeElite;
		this.numGenerations = numGenerations;
		//population = this.population;
	}
	
	public static void main (String args[]){
		GeneticAlgorithm ga = new GeneticAlgorithm(1000,5);
		System.out.println("size elite " + Integer.toString(ga.sizeElite) + " size Gen " + ga.numGenerations);
		
		Random rand = new Random(); 
		for (int j = 0; j<10; j++)
	     {
			System.out.println(rand.nextInt(20)+5);
			System.out.println(rand.nextInt(20)+5);
			System.out.println(rand.nextInt(20)+5);
	     }
		
		ga.algorithm();
		
		//This is a comment!
	}
	
	
	public void algorithm(){
		
		//Create a new faculty
		Faculty teleco = new Faculty("IngenierÃ­a de Telecomunicaciones");
		
		//Load faculty data
		//Timeslot(int moduleID, int profesorID, char groupID, int day, int startTime, int endTime)
		ArrayList<Timeslot> timeslots1stSemester = new ArrayList<Timeslot>();
		Timeslot T11 = new Timeslot(96110,51350111, 'A', 1, 6, 8);
		timeslots1stSemester.add(T11);
		Timeslot T12 = new Timeslot(96110,51350111, 'A', 3, 8, 10);
		timeslots1stSemester.add(T12);
		Timeslot T13 = new Timeslot(96110,51350111, 'A', 4, 10, 12);		
		timeslots1stSemester.add(T13);
		Timeslot T14 = new Timeslot(96111,51350222, 'A', 2, 10, 12);		
		timeslots1stSemester.add(T14);
		Timeslot T15 = new Timeslot(96111,51350222, 'A', 3, 10, 12);		
		timeslots1stSemester.add(T15);
		Timeslot T16 = new Timeslot(96111,51350222, 'A', 5, 14, 16);		
		timeslots1stSemester.add(T16);
		Timeslot T17 = new Timeslot(95139,6542222, 'A', 1, 14, 16);		
		timeslots1stSemester.add(T17);
		Timeslot T18 = new Timeslot(95139,6542222, 'A', 4, 14, 16);		
		timeslots1stSemester.add(T18);
		Timeslot T19 = new Timeslot(95108,20305111, 'A', 2, 14, 16);		
		timeslots1stSemester.add(T19);
		Timeslot T110 = new Timeslot(95108,20305111, 'A', 5, 6, 8);		
		timeslots1stSemester.add(T110);		
		teleco.addTimetable(1,timeslots1stSemester);
		
		ArrayList<Timeslot> timeslots2ndSemester = new ArrayList<Timeslot>();
		Timeslot T21 = new Timeslot(96113,51350111, 'A', 1, 8, 10);
		timeslots2ndSemester.add(T21);
		Timeslot T22 = new Timeslot(96113,51350111, 'A', 3, 10, 12);
		timeslots2ndSemester.add(T22);
		Timeslot T23 = new Timeslot(96113,51350111, 'A', 4, 8, 10);
		timeslots2ndSemester.add(T23);
		Timeslot T24 = new Timeslot(96303,51350222, 'A', 1, 10, 12);
		timeslots2ndSemester.add(T24);
		Timeslot T25 = new Timeslot(96303,51350222, 'A', 3, 6, 8);
		timeslots2ndSemester.add(T25);
		Timeslot T26 = new Timeslot(96303,51350222, 'A', 5, 8, 10);
		timeslots2ndSemester.add(T26);
		Timeslot T27 = new Timeslot(96109,20305111, 'A', 1, 14, 16);
		timeslots2ndSemester.add(T27);
		Timeslot T28 = new Timeslot(96109,20305111, 'A', 4, 12, 14);
		timeslots2ndSemester.add(T28);
		Timeslot T29 = new Timeslot(95284,20305222, 'A', 2, 8, 10);
		timeslots2ndSemester.add(T29);
		Timeslot T210 = new Timeslot(95284,20305222, 'A', 4, 10, 12);
		timeslots2ndSemester.add(T210);
		Timeslot T211 = new Timeslot(95140,6542111, 'A', 2, 12, 14);
		timeslots2ndSemester.add(T211);
		Timeslot T212 = new Timeslot(95140,6542111, 'A', 5, 14, 16);
		timeslots2ndSemester.add(T212);		
		teleco.addTimetable(2,timeslots2ndSemester);
		
		ArrayList<Timeslot> timeslots3rdSemester = new ArrayList<Timeslot>();
		Timeslot T31 = new Timeslot(96117,51350222, 'A', 1, 6, 8);
		timeslots3rdSemester.add(T31);
		Timeslot T32 = new Timeslot(96117,51350222, 'A', 2, 16, 18);
		timeslots3rdSemester.add(T32);
		Timeslot T33 = new Timeslot(96117,51350222, 'A', 4, 18, 20);
		timeslots3rdSemester.add(T33);
		Timeslot T34 = new Timeslot(96115,51350333, 'B', 1, 10, 12);
		timeslots3rdSemester.add(T34);
		Timeslot T35 = new Timeslot(96115,51350333, 'B', 3, 10, 12);
		timeslots3rdSemester.add(T35);
		Timeslot T36 = new Timeslot(96115,51350333, 'B', 5, 8, 10);
		timeslots3rdSemester.add(T36);
		Timeslot T37 = new Timeslot(96115,51350111, 'A', 1, 14, 16);
		timeslots3rdSemester.add(T37);
		Timeslot T38 = new Timeslot(96115,51350111, 'A', 2, 8, 10);
		timeslots3rdSemester.add(T38);
		Timeslot T39 = new Timeslot(96115,51350111, 'A', 4, 8, 10);
		timeslots3rdSemester.add(T39);
		Timeslot T310 = new Timeslot(95141,6542111, 'A', 2, 10, 12);
		timeslots3rdSemester.add(T310);
		Timeslot T311 = new Timeslot(95141,6542111, 'A', 3, 8, 10);
		timeslots3rdSemester.add(T311);
		Timeslot T312 = new Timeslot(96112,51350333, 'A', 2, 12, 14);
		timeslots3rdSemester.add(T312);
		Timeslot T313 = new Timeslot(96112,51350333, 'A', 4, 10, 12);
		timeslots3rdSemester.add(T313);
		Timeslot T314 = new Timeslot(96112,51350333, 'A', 5, 6, 8);
		timeslots3rdSemester.add(T314);
		teleco.addTimetable(3,timeslots3rdSemester);
		
		ArrayList<Course> Courses = new ArrayList<Course>();
		Courses = dataLoaderCourses();
		for (Course number : Courses) {
			   System.out.println("Number = " + number);
		}  
	}
	
	void initiatePopulation(int size){ 
	
	}
	
	void orderByValue(){
		
	}
	
	ArrayList<Module> dataLoaderModules(){
		ArrayList<Module> modules = new ArrayList<Module>();
		//Module(int moduleID, int semesterID, String name, int duration, int hoursPerWeek, String group)
		// 1st semester Modules
		Module m10 = new Module (96110, 1, "CÃ�LCULO DIFERENCIAL", 2, 6, "Ciencias BÃ¡sicas");
		modules.add(m10);
		Module m11 = new Module(96111, 1, "Ã�LGEBRA LINEAL", 2, 6, "Ciencias BÃ¡sicas");
		modules.add(m11);
		Module m12 = new Module(40020, 1, "INTRODUCCIÃ“N A LA INGENIERÃ�A", 2, 6, "Circuitos y electrÃ³nica");
		modules.add(m12);
		Module m13 = new Module(95139, 1, "SEGUNDO IDIOMA I", 2, 4, "Idiomas");
		modules.add(m13);
		Module m14 = new Module(95108, 1, "FILOSOFÃ�A INSTITUCIONAL", 2, 4, "Humanidades");
		modules.add(m14);
		
		// 2nd semester Modules
		Module m20 = new Module(96113, 2, "CÃ�LCULO INTEGRAL", 2, 6, "Ciencias BÃ¡sicas");
		modules.add(m20);
		Module m21 = new Module(96303, 2, "LÃ“GICA DE PROGRAMACIÃ“N", 2, 6, "Sistemas digitales y programaciÃ³n");
		modules.add(m21);
		Module m22 = new Module(14201, 2, "CIRCUITOS Y ELECTRÃ“NICA I", 2, 6, "Circuitos y electrÃ³nica");
		modules.add(m22);
		Module m23 = new Module(95284, 2, "TALLER LECTO-ESCRITURA", 2, 4, "Humanidades");
		modules.add(m23);
		Module m24 = new Module(95140, 2, "SEGUNDO IDIOMA II", 2, 4, "Idiomas");
		modules.add(m24);
		Module m25 = new Module(95109, 2, "ANTROPOLOGÃ�A", 2, 4, "Humanidades");
		modules.add(m25);
	
		// 3rd semester Modules
		Module m30 = new Module(96115, 3, "CÃ�LCULO VECTORIAL", 2, 6, "Ciencias BÃ¡sicas");
		modules.add(m30);
		Module m31 = new Module(96112, 3, "FÃ�SICA MECÃ�NICA", 2, 6, "Ciencias BÃ¡sicas");
		modules.add(m31);
		Module m32 = new Module(96117, 3, "ECUACIONES DIFERENCIALES", 2, 5, "Ciencias BÃ¡sicas");
		modules.add(m32);
		Module m33 = new Module(96304, 3, "PROGRAMACIÃ“N ORIENTADA A OBJETO", 2, 6, "Sistemas digitales y programaciÃ³n");
		modules.add(m33);
		Module m34 = new Module(14303, 3, "CIRCUITOS Y ELECTRÃ“NICA II", 2, 6, "Circuitos y electrÃ³nica");
		modules.add(m34);
		Module m35 = new Module(95141, 3, "SEGUNDO IDIOMA III", 2, 4, "Idiomas");
		modules.add(m35);
		return modules;
	}
	
	ArrayList<Profesor> dataLoaderProfesors(){
		//Load data
		ArrayList<Profesor> profesors = new ArrayList<Profesor>();
		ArrayList<Module> modules = new ArrayList<Module>();
		modules = dataLoaderModules();
		
		//Profesor 1 data
		ArrayList<Module> modulesP1 = new ArrayList<Module>();
		modulesP1.add( modules.get(0));
		modulesP1.add( modules.get(5));
		modulesP1.add( modules.get(11));
		ArrayList<Preference> preferencesP1 = new ArrayList<Preference>();
		preferencesP1.add(new Preference(1,6,0));
		Profesor p1 = new Profesor(51350111, modulesP1, preferencesP1);
		profesors.add(p1);

		//Profesor 2 data
		ArrayList<Module> modulesP2 = new ArrayList<Module>();
		modulesP2.add( modules.get(1));
		modulesP2.add( modules.get(6));
		modulesP2.add( modules.get(13));
		ArrayList<Preference> preferencesP2 = new ArrayList<Preference>();
		preferencesP2.add(new Preference(1,6,0));
		Profesor p2 = new Profesor(51350222, modulesP2, preferencesP2);
		profesors.add(p2);
		
		//Profesor 3 data
		ArrayList<Module> modulesP3 = new ArrayList<Module>();
		modulesP3.add( modules.get(2));
		modulesP3.add( modules.get(7));
		ArrayList<Preference> preferencesP3 = new ArrayList<Preference>();
		preferencesP3.add(new Preference(1,6,0));
		Profesor p3 = new Profesor(80876111, modulesP3, preferencesP3);
		profesors.add(p3);		
		
		//Profesor 4 data
		ArrayList<Module> modulesP4 = new ArrayList<Module>();
		modulesP4.add( modules.get(3));
		ArrayList<Preference> preferencesP4 = new ArrayList<Preference>();
		preferencesP3.add(new Preference(1,6,0));
		Profesor p4 = new Profesor(6542222, modulesP4, preferencesP4);
		profesors.add(p4);		
		
		//Profesor 5 data
		ArrayList<Module> modulesP5 = new ArrayList<Module>();
		modulesP5.add( modules.get(4));
		modulesP5.add( modules.get(10));
		ArrayList<Preference> preferencesP5 = new ArrayList<Preference>();
		preferencesP5.add(new Preference(1,6,0));
		Profesor p5 = new Profesor(20305111, modulesP5, preferencesP5);
		profesors.add(p5);		
		
		//Profesor 6 data
		ArrayList<Module> modulesP6 = new ArrayList<Module>();
		modulesP6.add( modules.get(8));
		ArrayList<Preference> preferencesP6 = new ArrayList<Preference>();
		preferencesP6.add(new Preference(1,6,0));
		Profesor p6 = new Profesor(20305222, modulesP6, preferencesP6);
		profesors.add(p6);	

		//Profesor 7 data
		ArrayList<Module> modulesP7 = new ArrayList<Module>();
		modulesP7.add( modules.get(9));
		modulesP7.add( modules.get(16));
		ArrayList<Preference> preferencesP7 = new ArrayList<Preference>();
		preferencesP7.add(new Preference(1,6,0));
		Profesor p7 = new Profesor(6542111, modulesP7, preferencesP7);
		profesors.add(p7);	
		
		//Profesor 8 data
		ArrayList<Module> modulesP8 = new ArrayList<Module>();
		modulesP8.add( modules.get(15));
		ArrayList<Preference> preferencesP8 = new ArrayList<Preference>();
		preferencesP8.add(new Preference(1,6,0));
		Profesor p8 = new Profesor(80876222, modulesP8, preferencesP8);
		profesors.add(p8);			
		
		//Profesor 9 data
		ArrayList<Module> modulesP9 = new ArrayList<Module>();
		modulesP9.add( modules.get(14));
		ArrayList<Preference> preferencesP9 = new ArrayList<Preference>();
		preferencesP9.add(new Preference(1,6,0));
		Profesor p9 = new Profesor(80876333, modulesP9, preferencesP9);
		profesors.add(p9);		
		
		//Profesor 10 data
		ArrayList<Module> modulesP10 = new ArrayList<Module>();
		modulesP10.add( modules.get(11));
		modulesP10.add( modules.get(12));
		ArrayList<Preference> preferencesP10 = new ArrayList<Preference>();
		preferencesP10.add(new Preference(1,6,0));
		Profesor p10 = new Profesor(80876333, modulesP10, preferencesP10);
		profesors.add(p10);	

		return profesors;
		
	}
	
	ArrayList<Course> dataLoaderCourses(){
		//Load data
				ArrayList<Course> courses = new ArrayList<Course>();
				// Course(int moduleID, int semesterID, String name, int duration, int hoursPerWeek, String group, int profesorID, char courseID)
				// 1st semester Modules
				Course m10 = new Course(96110, 1, "CÃ�LCULO DIFERENCIAL", 2, 6, "Ciencias BÃ¡sicas",51350111, 'A');
				courses.add(m10);
				Course m11 = new Course(96111, 1, "Ã�LGEBRA LINEAL", 2, 6, "Ciencias BÃ¡sicas",51350222, 'A');
				courses.add(m11);
				Course m12 = new Course(40020, 1, "INTRODUCCIÃ“N A LA INGENIERÃ�A", 2, 6, "Circuitos y electrÃ³nica",80876111, 'A');
				courses.add(m12);
				Course m12B = new Course(40020, 1, "INTRODUCCIÃ“N A LA INGENIERÃ�A", 2, 6, "Circuitos y electrÃ³nica",80876111, 'B');
				courses.add(m12B);
				Course m13 = new Course(95139, 1, "SEGUNDO IDIOMA I", 2, 4, "Idiomas",6542222, 'A');
				courses.add(m13);
				Course m14 = new Course(95108, 1, "FILOSOFÃ�A INSTITUCIONAL", 2, 4, "Humanidades",20305111, 'A');
				courses.add(m14);
				
				// 2nd semester Modules
				Course m20 = new Course(96113, 2, "CÃ�LCULO INTEGRAL", 2, 6, "Ciencias BÃ¡sicas", 51350111, 'A');
				courses.add(m20);
				Course m21 = new Course(96303, 2, "LÃ“GICA DE PROGRAMACIÃ“N", 2, 6, "Sistemas digitales y programaciÃ³n",51350222, 'A');
				courses.add(m21);
				Course m22 = new Course(14201, 2, "CIRCUITOS Y ELECTRÃ“NICA I", 2, 6, "Circuitos y electrÃ³nica",80876111, 'A');
				courses.add(m22);
				Course m23 = new Course(95284, 2, "TALLER LECTO-ESCRITURA", 2, 4, "Humanidades",20305222, 'A');
				courses.add(m23);
				Course m24 = new Course(95140, 2, "SEGUNDO IDIOMA II", 2, 4, "Idiomas",6542111, 'A');
				courses.add(m24);
				Course m25 = new Course(95109, 2, "ANTROPOLOGÃ�A", 2, 4, "Humanidades",20305111, 'A');
				courses.add(m25);
			
				// 3rd semester Modules
				Course m30 = new Course(96115, 3, "CÃ�LCULO VECTORIAL", 2, 6, "Ciencias BÃ¡sicas", 51350111, 'A');
				courses.add(m30);
				Course m30B = new Course(96115, 3, "CÃ�LCULO VECTORIAL", 2, 6, "Ciencias BÃ¡sicas", 51350333, 'B');
				courses.add(m30B);
				Course m31 = new Course(96112, 3, "FÃ�SICA MECÃ�NICA", 2, 6, "Ciencias BÃ¡sicas",51350333, 'A');
				courses.add(m31);
				Course m32 = new Course(96117, 3, "ECUACIONES DIFERENCIALES", 2, 5, "Ciencias BÃ¡sicas",51350222, 'A');
				courses.add(m32);
				Course m33 = new Course(96304, 3, "PROGRAMACIÃ“N ORIENTADA A OBJETO", 2, 6, "Sistemas digitales y programaciÃ³n",80876333, 'A');
				courses.add(m33);
				Course m34 = new Course(14303, 3, "CIRCUITOS Y ELECTRÃ“NICA II", 2, 6, "Circuitos y electrÃ³nica",80876222, 'A');
				courses.add(m34);
				Course m35 = new Course(95141, 3, "SEGUNDO IDIOMA III", 2, 4, "Idiomas",6542111, 'A');
				courses.add(m35);
				return courses;
	}

}
