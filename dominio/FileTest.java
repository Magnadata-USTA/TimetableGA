package dominio;
 
public class FileTest {
 
    private String moduleID;
    private String profesorID;
    private String groupID;
    private String day;
    private String startTime;
    private String endTime;
 
    public FileTest(String moduleID, String profesorID, String groupID, String day, String startTime, String endTime) {
        setModuleID(moduleID);
        setProfesorID(profesorID);
        setGroupID(groupID);
        setDay(day);
        setStartTime(startTime);
        setEndTime(endTime);
    }
 
    public FileTest(){
		
	}
    
    public String getModuleID() {
        return moduleID;
    }
 
    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }
 
    public String getProfesorID() {
        return profesorID;
    }
 
    public void setProfesorID(String profesorID) {
        this.profesorID = profesorID;
    }
 
    public String getGroupID() {
        return groupID;
    }
 
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
 
    public String getDay() {
        return day;
    }
 
    public void setDay (String day) {
        this.day = day;
    }
    
    public String getStartTime() {
        return startTime;
    }
 
    public void setStartTime (String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
 
    public void setEndTime (String endTime) {
        this.endTime = endTime;
    }
}