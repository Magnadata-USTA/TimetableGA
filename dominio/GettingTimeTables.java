package dominio;
 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.csvreader.CsvReader; 
import dominio.FileTest;
 
public class GettingTimeTables {
 
    public static void main(String[] args) {
     
        try {
         
        List<FileTest> usuarios = new ArrayList<FileTest>();
         
        CsvReader usuarios_import = new CsvReader("D:\\Users\\lenovo\\Desktop\\firstsemester.csv");
        usuarios_import.readHeaders();
         
        while (usuarios_import.readRecord())
        {
        	String moduleID = usuarios_import.get("moduleID");
        	String profesorID = usuarios_import.get("profesorID");
        	String groupID = usuarios_import.get("groupID");
        	String day = usuarios_import.get("day");
        	String startTime = usuarios_import.get("startTime");
        	String endTime = usuarios_import.get("endTime");
        	             
            usuarios.add(new FileTest(moduleID, profesorID, groupID, day, startTime, endTime));    
        }
         
        usuarios_import.close();
         
        for(FileTest us : usuarios){
        	
            System.out.println(us.getModuleID() + " - " + us.getProfesorID() + " - "
            + us.getGroupID() + " - " + us.getDay()+ " - " + us.getStartTime()+ " - " + us.getEndTime());
        }
         
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}