/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.Student;
import attendance.automation.bll.AAManager;
import attendance.automation.dal.DALException;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Tothko
 */
public class StudentMainViewController implements Initializable {
    
    @FXML
    private Label welcomeLabel;
    private AAManager manager;
    private Student st;
    private Label dateLabel;
    @FXML
    private Label attendanceLabel;
    @FXML
    private Label attendanceRate;
    Calendar mCalendar;
    @FXML
    private JFXButton btnExit;
    @FXML
    private JFXButton btnLogin;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane paneCalendar;
    ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        
    
        
        try {
            manager = AAManager.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        st = manager.getStudent();
        welcomeLabel.setText("Welcome "+st.getName());
        mCalendar = Calendar.getInstance();
        try {
            calculateAttendanceRate();
        } catch (DALException ex) {
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        fadeIn(btnExit);
        fadeIn(btnLogin);
        try {
            CalendarViewController kokot = new CalendarViewController(this);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(kokot);
            Pane newLoadedPane = new Pane(); 
            newLoadedPane = fxmlLoader.load(getClass().getResource("/attendance/automation/gui/view/CalendarView.fxml"));
            paneCalendar.getChildren().clear();
            paneCalendar.getChildren().add(newLoadedPane); 
        } catch (Exception ex) {
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void closeButton(ActionEvent event) {
        Stage stage = (Stage)welcomeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void attendanceButton(ActionEvent event) throws DALException {
        if (mCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && mCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
            checkAttendance();
        else
            attendanceLabel.setText("Today is weekend!");
        
    }

    public void calculateAttendanceRate() throws DALException{   
        String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        String string = month+" - "+(int)(manager.attendanceRate(st.getId())*100)+"%";
        attendanceRate.setText(string);
    }
    
    public void checkAttendance() throws DALException{
        if(manager.markAttendance(st.getId())){
            attendanceLabel.setText("Attendance marked successfully!");
            calculateAttendanceRate();}
        else
            attendanceLabel.setText("Attendance already marked!");
    }
    
    private void fadeIn(Node node)
    {
        FadeTransition exitFade = new FadeTransition(Duration.seconds(2), node);
        exitFade.setFromValue(0);
        exitFade.setToValue(1);
        exitFade.play();
    }
    public Student getStudent(){
        return st;
    }

    
}
