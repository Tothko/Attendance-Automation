/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.Student;
import attendance.automation.bll.AAManager;
import java.io.IOException;
import java.net.URL;
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
public class MainController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Label welcomeLabel;
    private AAManager manager;
    private Student st;
    @FXML
    private Label dateLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            manager = AAManager.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        st = manager.getStudent();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
        welcomeLabel.setText("Welcome, "+st.getName()+". Have nice day!");
        dateLabel.setText(localDate.toString());
    }    

    @FXML
    private void attendanceButton(ActionEvent event) {
    }
    
}
