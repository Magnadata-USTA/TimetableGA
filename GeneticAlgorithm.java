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
	     }
		
		ga.algorithm();
	}
	
	
	public void algorithm(){
		
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
		Module m10 = new Module (96110, 1, "CÁLCULO DIFERENCIAL", 2, 6, "Ciencias Básicas");
		modules.add(m10);
		Module m11 = new Module(96111, 1, "ÁLGEBRA LINEAL", 2, 6, "Ciencias Básicas");
		modules.add(m11);
		Module m12 = new Module(40020, 1, "INTRODUCCIÓN A LA INGENIERÍA", 2, 6, "Circuitos y electrónica");
		modules.add(m12);
		Module m12B = new Module(40020, 1, "INTRODUCCIÓN A LA INGENIERÍA", 2, 6, "Circuitos y electrónica");
		modules.add(m12B);
		Module m13 = new Module(95139, 1, "SEGUNDO IDIOMA I", 2, 4, "Idiomas");
		modules.add(m13);
		Module m14 = new Module(95108, 1, "FILOSOFÍA INSTITUCIONAL", 2, 4, "Humanidades");
		modules.add(m14);
		
		// 2nd semester Modules
		Module m20 = new Module(96113, 2, "CÁLCULO INTEGRAL", 2, 6, "Ciencias Básicas");
		modules.add(m20);
		Module m21 = new Module(96303, 2, "LÓGICA DE PROGRAMACIÓN", 2, 6, "Sistemas digitales y programación");
		modules.add(m21);
		Module m22 = new Module(14201, 2, "CIRCUITOS Y ELECTRÓNICA I", 2, 6, "Circuitos y electrónica");
		modules.add(m22);
		Module m23 = new Module(95284, 2, "TALLER LECTO-ESCRITURA", 2, 4, "Humanidades");
		modules.add(m23);
		Module m24 = new Module(95140, 2, "SEGUNDO IDIOMA II", 2, 4, "Idiomas");
		modules.add(m24);
		Module m25 = new Module(95109, 2, "ANTROPOLOGÍA", 2, 4, "Humanidades");
		modules.add(m25);
	
		// 3rd semester Modules
		Module m30 = new Module(96115, 3, "CÁLCULO VECTORIAL", 2, 6, "Ciencias Básicas");
		modules.add(m30);
		Module m30B = new Module(96115, 3, "CÁLCULO VECTORIAL", 2, 6, "Ciencias Básicas");
		modules.add(m30B);
		Module m31 = new Module(96112, 3, "FÍSICA MECÁNICA", 2, 6, "Ciencias Básicas");
		modules.add(m31);
		Module m32 = new Module(96117, 3, "ECUACIONES DIFERENCIALES", 2, 5, "Ciencias Básicas");
		modules.add(m32);
		Module m33 = new Module(96304, 3, "PROGRAMACIÓN ORIENTADA A OBJETO", 2, 6, "Sistemas digitales y programación");
		modules.add(m33);
		Module m34 = new Module(14303, 3, "CIRCUITOS Y ELECTRÓNICA II", 2, 6, "Circuitos y electrónica");
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
		//Profesor p101 = new Profesor(51350111, modules(1), new Preference(1,6,0));
		//profesors.add(p101);
		return profesors;
	}
	
	ArrayList<Course> dataLoaderCourses(){
		//Load data
				ArrayList<Course> courses = new ArrayList<Course>();
				// Course(int moduleID, int semesterID, String name, int duration, int hoursPerWeek, String group, int profesorID, char courseID)
				// 1st semester Modules
				Course m10 = new Course(96110, 1, "CÁLCULO DIFERENCIAL", 2, 6, "Ciencias Básicas",51350111, 'A');
				courses.add(m10);
				Course m11 = new Course(96111, 1, "ÁLGEBRA LINEAL", 2, 6, "Ciencias Básicas",51350222, 'A');
				courses.add(m11);
				Course m12 = new Course(40020, 1, "INTRODUCCIÓN A LA INGENIERÍA", 2, 6, "Circuitos y electrónica",80876111, 'A');
				courses.add(m12);
				Course m12B = new Course(40020, 1, "INTRODUCCIÓN A LA INGENIERÍA", 2, 6, "Circuitos y electrónica",80876111, 'B');
				courses.add(m12B);
				Course m13 = new Course(95139, 1, "SEGUNDO IDIOMA I", 2, 4, "Idiomas",6542222, 'A');
				courses.add(m13);
				Course m14 = new Course(95108, 1, "FILOSOFÍA INSTITUCIONAL", 2, 4, "Humanidades",20305111, 'A');
				courses.add(m14);
				
				// 2nd semester Modules
				Course m20 = new Course(96113, 2, "CÁLCULO INTEGRAL", 2, 6, "Ciencias Básicas", 51350111, 'A');
				courses.add(m20);
				Course m21 = new Course(96303, 2, "LÓGICA DE PROGRAMACIÓN", 2, 6, "Sistemas digitales y programación",51350222, 'A');
				courses.add(m21);
				Course m22 = new Course(14201, 2, "CIRCUITOS Y ELECTRÓNICA I", 2, 6, "Circuitos y electrónica",80876111, 'A');
				courses.add(m22);
				Course m23 = new Course(95284, 2, "TALLER LECTO-ESCRITURA", 2, 4, "Humanidades",20305222, 'A');
				courses.add(m23);
				Course m24 = new Course(95140, 2, "SEGUNDO IDIOMA II", 2, 4, "Idiomas",6542111, 'A');
				courses.add(m24);
				Course m25 = new Course(95109, 2, "ANTROPOLOGÍA", 2, 4, "Humanidades",20305111, 'A');
				courses.add(m25);
			
				// 3rd semester Modules
				Course m30 = new Course(96115, 3, "CÁLCULO VECTORIAL", 2, 6, "Ciencias Básicas", 51350111, 'A');
				courses.add(m30);
				Course m30B = new Course(96115, 3, "CÁLCULO VECTORIAL", 2, 6, "Ciencias Básicas", 51350333, 'B');
				courses.add(m30B);
				Course m31 = new Course(96112, 3, "FÍSICA MECÁNICA", 2, 6, "Ciencias Básicas",51350333, 'A');
				courses.add(m31);
				Course m32 = new Course(96117, 3, "ECUACIONES DIFERENCIALES", 2, 5, "Ciencias Básicas",51350222, 'A');
				courses.add(m32);
				Course m33 = new Course(96304, 3, "PROGRAMACIÓN ORIENTADA A OBJETO", 2, 6, "Sistemas digitales y programación",80876333, 'A');
				courses.add(m33);
				Course m34 = new Course(14303, 3, "CIRCUITOS Y ELECTRÓNICA II", 2, 6, "Circuitos y electrónica",80876222, 'A');
				courses.add(m34);
				Course m35 = new Course(95141, 3, "SEGUNDO IDIOMA III", 2, 4, "Idiomas",6542111, 'A');
				courses.add(m35);
				return courses;
	}

}
