/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Revy
 */
public class DateDAO {
    private final ConnectionProvider cp;
     public DateDAO() throws IOException {
        cp = new ConnectionProvider();
    }
     
    public boolean markAttendance(int studentID, java.util.Date date) throws DALException{
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        if(!isMarkedAlready(studentID,sqlDate)){
            markIt(studentID,sqlDate);
            return true;
        }
        else
            System.out.println("Attendance already marked!");
        return false;
    }
    public boolean isMarkedAlready(int studentID, java.sql.Date sqlDate) throws DALException{
        try {
            String sql = "SELECT * FROM StudentAttendance WHERE studentID=? AND Date=?";
            Connection con = cp.getConnection();
            PreparedStatement ppst = con.prepareStatement(sql);
            ppst.setDate(2, sqlDate);
            ppst.setInt(1, studentID);
            ppst.execute();
            ResultSet rs = ppst.getResultSet();
            while (rs.next()){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            throw new DALException(ex);
        }
       
    }
    public void markIt(int studentID, java.sql.Date sqlDate) throws DALException{
    try{
             
            Connection con = cp.getConnection();
            String sql = "INSERT INTO StudentAttendance VALUES(?,?)";
            PreparedStatement ppst = con.prepareStatement(sql);
            ppst.setDate(1, sqlDate);
            ppst.setInt(2, studentID);
            ppst.execute();}
        
       catch (SQLException ex) {
            throw new DALException(ex);
        }
    }
}
