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
import javafx.stage.Stage;

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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            manager = AAManager.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        st = manager.getStudent();
        welcomeLabel.setText("Welcome "+st.getName());
    
    }    

    @FXML
    private void closeButton(ActionEvent event) {
        Stage stage = (Stage)welcomeLabel.getScene().getWindow();
        stage.close();
    }

    
    
}
