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
    @FXML
    private Label monday;
    @FXML
    private Label tuesday;
    @FXML
    private Label wednesday;
    @FXML
    private Label thursday;
    @FXML
    private Label friday;
    @FXML
    private Label saturday;
    @FXML
    private Label sunday;

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

    public CalendarViewController(StudentMainViewController controller) {
        SMWC = controller;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setStudent();
        attendance = student.getAttendance();

        y = 1;
        this.url = url;
        this.rb = rb;
        labelDate.setText(localDate.getMonth() + "/" + localDate.getYear());
        String someDay = (localDate.minusDays(localDate.getDayOfMonth() - 1)).getDayOfWeek().toString();
        x = localDate.getDayOfWeek().getValue() - 1;

        for (int i = 1; i <= localDate.lengthOfMonth(); ++i) {
            (localDate.minusDays(localDate.getDayOfMonth())).getDayOfWeek();

            if (x == 7) {
                x = 0;
                y++;
            }
            if (y == 6) {
                y = 1;
            }
//            System.out.println("Je to?" + attendance);
            for (AttendanceUnit attendanceUnit : attendance) {
//                System.out.println("GetDay" + ((attendanceUnit.getAttendanceDate().getDate()) == i));
//                System.out.println("GetMonth" + ((attendanceUnit.getAttendanceDate().getMonth() + 1) == localDate.getMonthValue()));
//                System.out.println("GetYear" + ((attendanceUnit.getAttendanceDate().getYear() + 1900) == localDate.getYear()));
//                System.out.println("my" + (attendanceUnit.getAttendanceDate().getDate()) + "" + ((attendanceUnit.getAttendanceDate().getMonth() + 1) + "" + ((attendanceUnit.getAttendanceDate().getYear() + 1900))));

                if ((attendanceUnit.getAttendanceDate().getDate() != i) && (attendanceUnit.getAttendanceDate().getDate() <= localDate.getDayOfMonth()) && (((attendanceUnit.getAttendanceDate().getMonth() + 1) != localDate.getMonthValue()) && ((attendanceUnit.getAttendanceDate().getYear() + 1900) == localDate.getYear()))) {
                    buttonColor = "-fx-background-color: Red";
                    break;

                } else if ((attendanceUnit.getAttendanceDate().getDate() == i) && (((attendanceUnit.getAttendanceDate().getMonth() + 1) == localDate.getMonthValue()) && ((attendanceUnit.getAttendanceDate().getYear() + 1900) == localDate.getYear()))) {
                    buttonColor = "-fx-background-color: Green";
                    break;
                } else {
                    buttonColor = "-fx-background-color: Grey";

                }

            }

            Button butt = new Button();
            butt.setText("" + i);
            butt.setStyle(buttonColor);
            butt.setMinSize(32, 32);
            butt.setMaxSize(32, 32);
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
        loadDaysLabel();
    }

    @FXML
    private void pressButtonnNextMonth(ActionEvent event) {
        localDate = localDate.plusMonths(1);
        GridCalendar.getChildren().clear();
        initialize(url, rb);
        loadDaysLabel();
    }

    public void setStudent() {
        this.student = SMWC.getStudent();

    }

    public void loadDaysLabel() {
        GridCalendar.add(monday, 0, 0);
        monday.setText("Mo");
       // monday.getText().
        GridCalendar.add(tuesday, 1, 0);
        tuesday.setText("Tu");
        GridCalendar.add(wednesday, 2, 0);
        wednesday.setText("We");
        GridCalendar.add(thursday, 3, 0);
        thursday.setText("Th");
        GridCalendar.add(friday, 4, 0);
        friday.setText("Fr");
        GridCalendar.add(saturday, 5, 0);
        saturday.setText("Sa");
        GridCalendar.add(sunday, 6, 0);
        sunday.setText("Su");

    }

}
