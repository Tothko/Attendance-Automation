/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import attendance.automation.dal.ConnectionProvider;
import attendance.automation.dal.DALException;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Revy
 */
public class Student extends RecursiveTreeObject<Student>{
    
    private String name;
    private int classNum;
    private int id;
    private String className;
    private ConnectionProvider cp;
    private List<AttendanceUnit> listOfAttendance;
    
    public Student(String name, int classNum, int id) throws IOException, DALException{
        this.name=name;
        this.classNum=classNum;
        this.id=id;
        cp = new ConnectionProvider();
        listOfAttendance = new ArrayList<>();
        loadStudentContent();
    }
    public void loadStudentContent() throws DALException, IOException{
    try{
        Connection con = cp.getConnection();
        Statement statement = con.createStatement();
        String str = "SELECT * FROM Student, StudentAttendance WHERE Student.ID=StudentID AND UserName='"+name+"'";
        ResultSet rs = statement.executeQuery(str);
        while(rs.next()){
             Date date1 = rs.getDate("Date");
             int number = rs.getInt("StudentID");
             listOfAttendance.add(new AttendanceUnit(number,date1));}
        }
        
        catch (SQLException ex) {
            throw new DALException (ex);
        }
    }
    
    
    
    
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public int getClassNum()
    {
        return classNum;
    }

    public void setClassNum(int classNum)
    {
        this.classNum = classNum;
    }
    
    public void setClassName(String className){
        this.className=className;
    }
    public String getClassName(){
        return className;
    }
    public List<AttendanceUnit> getAttendence(){
        return listOfAttendance;
    }
}
