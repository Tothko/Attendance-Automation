/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.bll.AAManager;
import attendance.automation.dal.DALException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Tothko
 */
public class LoginViewController implements Initializable {

    @FXML
    private AnchorPane hroot;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    private AAManager manager;
    private double xOffset = 0;
    private double yOffset = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            manager = AAManager.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void loginMethod(ActionEvent event) throws DALException, IOException, InterruptedException {
        String login = loginField.getText();
        String password = passwordField.getText();
        Thread.sleep(200);
        if(manager.checkLogin(login, password)){
            manager.setUser();
            if(!manager.isTeacher()){
                studentMainViewInitialization();
            } else
            {
                teacherMainViewInitialization();
            }
        
        Stage stage2 = (Stage)loginField.getScene().getWindow();
        stage2.close();}
    }
    
    private void studentMainViewInitialization() throws IOException{
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentMainView.fxml"));
        root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        
        Scene scene = new Scene(root1);
        
        stage.setScene(scene);
        stage.show();
        
        root1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    
    }
    
    private void teacherMainViewInitialization() throws IOException
    {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherMainView.fxml"));
        root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        
        Scene scene = new Scene(root1);
        
        stage.setScene(scene);
        stage.show();
        
        root1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }
    
}
