/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.AttendanceUnit;
import attendance.automation.be.Student;
import attendance.automation.be.Teacher;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Tothko
 */

public class CalendarViewController implements Initializable {

    private URL url;
    private ResourceBundle rb;
    @FXML
    private GridPane GridCalendar;
    @FXML
    private Label labelDate;
    private Date date = new Date();
    private int x;
    private int y;
    private LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    @FXML
    private Button buttonPreviousMonth;
    @FXML
    private Button buttonNextMonth;
    private List<AttendanceUnit> attendance = new ArrayList();
    private String buttonColor = "-fx-background-color: Grey";
    private StudentMainViewController SMWC;
    private Student student;
    public CalendarViewController(StudentMainViewController controller){
        SMWC = controller;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        y = 1;
        this.url = url;
        this.rb = rb;
        labelDate.setText(localDate.getMonth() + "/" + localDate.getYear());
        String someDay = (localDate.minusDays(localDate.getDayOfMonth() - 1)).getDayOfWeek().toString();
        x = localDate.getDayOfWeek().getValue()-1;

        for (int i = 1; i <= localDate.lengthOfMonth(); ++i) {
            (localDate.minusDays(localDate.getDayOfMonth())).getDayOfWeek();

            if (x == 7) {
                x = 0;
                y++;
            }
            if (y == 6) {
                y = 1;
            }
            for (AttendanceUnit attendanceUnit : attendance) {
                System.out.println(""+attendanceUnit.getAttendanceDate().getDay());
                if(attendanceUnit.getAttendanceDate().getDay() == i){
                    buttonColor = "-fx-background-color: Green";
                }
                else if((attendanceUnit.getAttendanceDate().getDay() != i) && (attendanceUnit.getAttendanceDate().getDay()<= localDate.getDayOfMonth())){
                    buttonColor = "-fx-background-color: Grey";
                }
                else{
                    buttonColor = "-fx-background-color: Red";
                }
                
                
                
            }
            Button butt = new Button();
            butt.setText("" + i);
            butt.setStyle(buttonColor);
            butt.setOnAction(new EventHandler<ActionEvent>() {
            
                @Override
                public void handle(ActionEvent e) {
                    // TO DO CODE
                }
            });
            GridCalendar.add(butt, x, y);
            x++;
        }
    }

    @FXML
    private void pressButtonPreviousMonth(ActionEvent event) {
        localDate = localDate.minusMonths(1);
        GridCalendar.getChildren().clear();
        initialize(url, rb);
    }

    @FXML
    private void pressButtonnNextMonth(ActionEvent event) {
        localDate = localDate.plusMonths(1);
        GridCalendar.getChildren().clear();
        initialize(url, rb);
    }
    private void setStudent(){
        this.student = SMWC.getStudent();
        
        
    }

}
