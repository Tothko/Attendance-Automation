/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.Student;
import attendance.automation.bll.AAManager;
import attendance.automation.dal.DALException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Tothko
 */
public class StudentMainViewController implements Initializable {
    
    @FXML
    private Label welcomeLabel;
    private AAManager manager;
    private Student st;
    @FXML
    private Label dateLabel;
    @FXML
    private Label attendanceFeedback;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            manager = AAManager.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        st = manager.getStudent();
	LocalDate localDate = LocalDate.now();
        welcomeLabel.setText("Welcome, "+st.getName()+". Have nice day!");
        dateLabel.setText(localDate.toString());
    }    

    @FXML
    private void attendanceButton(ActionEvent event) throws DALException {
        if(manager.markAttendance(st.getId()))
            attendanceFeedback.setText("Attendance marked successfully!");
        else
            attendanceFeedback.setText("Attendance already marked!");
    }
    
}
