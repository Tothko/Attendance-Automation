/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll;

import attendance.automation.be.Student;
import attendance.automation.be.Teacher;
import attendance.automation.dal.DALException;
import attendance.automation.dal.DateDAO;
import attendance.automation.dal.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;

/**
 *
 * @author Revy
 */
public class AAManager {
    private UserDAO ud;
    private DateDAO dd;
    private static AAManager manager;
    private Student st;
    private Teacher te;
    
    public AAManager() throws IOException{
        ud = new UserDAO();
        dd = new DateDAO();
        st = null;
        te = null;
    }
    
    public static AAManager getInstance() throws IOException{
    if(manager==null){
        manager = new AAManager();
        return manager;
    }
    else
        return manager;   
    }
    
    public boolean checkLogin(String login, String password) throws DALException, IOException{
        return ud.checkLogin(login, password);
    }
    
    public void setUser(){
        this.st=ud.getStudent();
        this.te=ud.getTeacher();
    }
    public boolean isTeacher(){
        if(te==null)
            return false;
        else
            return true;
    
    }
    
    public Student getStudent(){
        return st;
    }
    public Teacher getTeacher(){
        return te;
    }
    public boolean markAttendance(int studentID) throws DALException{
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return dd.markAttendance(studentID, date);
    }
    
    
}
