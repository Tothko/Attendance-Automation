/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll;

import attendance.automation.be.Student;
import attendance.automation.be.Teacher;
import attendance.automation.dal.DALException;
import attendance.automation.dal.UserDAO;
import java.io.IOException;

/**
 *
 * @author Revy
 */
public class AAManager {
    private UserDAO ud;
    private static AAManager manager;
    private Student st;
    private Teacher te;
    
    public AAManager() throws IOException{
        ud = new UserDAO();
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
    
    public boolean checkLogin(String login, String password) throws DALException{
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
}
