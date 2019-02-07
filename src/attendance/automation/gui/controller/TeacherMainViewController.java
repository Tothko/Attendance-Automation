/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.Class;
import attendance.automation.be.Student;
import attendance.automation.be.Teacher;
import attendance.automation.bll.AAManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.awt.Frame;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Revy
 */
public class TeacherMainViewController implements Initializable {

    @FXML
    private Label welcomeLabel;
    @FXML
    private AnchorPane calendarPane;
    @FXML
    private JFXComboBox<Class> selectClass;
    @FXML
    private JFXTextField studentSearch;
    @FXML
    private JFXTreeTableView<Student> tableView;
    private Teacher te;
    private AAManager manager;
    private List<Class> listOfClasses;
    private ObservableList<Class> observableClasses;
    @FXML
    private JFXButton btnExit;
    @FXML
    private JFXButton btnMinimize;
    @FXML
    private ImageView pic;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            manager = AAManager.getInstance();
            te = manager.getTeacher();
            welcomeLabel.setText("Welcome, "+te.getName()+"!");
            listOfClasses = te.getClassesList();
            observableClasses = FXCollections.observableArrayList(listOfClasses);
            selectClass.setItems(observableClasses);
            selectClass.setPromptText(observableClasses.get(0).getName());
            setTableView();
            loadDataToTable(FXCollections.observableArrayList(observableClasses.get(0).getStudentsList()));
        } catch (IOException ex) {
            Logger.getLogger(TeacherMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        fadeIn(btnExit);
        fadeIn(btnMinimize);
        fadeIn(pic);
        
    }    
    public void setTableView(){
        JFXTreeTableColumn<Student,String> studentName = new JFXTreeTableColumn<>("Student");
        JFXTreeTableColumn<Student,String> studentID = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Student,String> studentClass = new JFXTreeTableColumn<>("Class");
        studentName.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        studentID.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        studentClass.setCellValueFactory(new TreeItemPropertyValueFactory<>("className"));
        tableView.getColumns().addAll(studentName,studentID,studentClass);
        studentSearch.textProperty().addListener((o,oldVal,newVal)->{
        tableView.setPredicate(student -> String.valueOf(student.getValue().getId()).toLowerCase().contains(newVal.toLowerCase())
                || student.getValue().getName().toLowerCase().contains(newVal.toLowerCase())
                || student.getValue().getClassName().toLowerCase().contains(newVal.toLowerCase()));
                                                                });
    }
    
    public void loadDataToTable(ObservableList<Student> list){
        TreeItem<Student> root;
        root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }

    @FXML
    private void comboBoxOnAction(ActionEvent event) {
        loadDataToTable(FXCollections.observableArrayList(selectClass.getSelectionModel().getSelectedItem().getStudentsList()));
    }

    @FXML
    private void exitButton(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimizeButton(ActionEvent event) {
        Stage stage = (Stage)welcomeLabel.getScene().getWindow();
        stage.setIconified(true);
    }
    
    private void fadeIn(Node node)
    {
        FadeTransition exitFade = new FadeTransition(Duration.seconds(2), node);
        exitFade.setFromValue(0);
        exitFade.setToValue(1);
        exitFade.play();
    }
    
}
