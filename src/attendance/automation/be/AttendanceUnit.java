/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import java.util.Date;

/**
 *
 * @author Revy
 */
public class AttendanceUnit {
    private int studentID;
    private Date attendanceDate;
    
    public AttendanceUnit(int studentID, Date attendanceDate){
        this.attendanceDate=attendanceDate;
        this.studentID=studentID;
    }
}
