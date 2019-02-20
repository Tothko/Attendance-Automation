/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.AttendanceUnit;
import attendance.automation.be.Student;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXButton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

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
    private Label monday;
    private Label tuesday;
    private Label wednesday;
    private Label thursday;
    private Label friday;
    private Label saturday;
    private Label sunday;

    private Date date = new Date();
    private int x;
    private int y;
    private Calendar calendar;
    private Calendar today;
    private Calendar firstDay;
    private List<AttendanceUnit> attendance = new ArrayList<>();
    private List<Date> dateList = new ArrayList<>();
    private String buttonColor;
    private StudentMainViewController SMWC = null;
    private TeacherMainViewController TMWC = null;
    private Student student;
    HashMap<Integer, String> map = new HashMap<>();
    HashMap<Integer, Date> map2 = new HashMap<>();

    public CalendarViewController(StudentMainViewController studentController, TeacherMainViewController teacherController, Student student) {
        SMWC = studentController;
        TMWC = teacherController;
        this.student = student;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        calendar = Calendar.getInstance();
        today = (Calendar) calendar.clone();
        firstDay = (Calendar) calendar.clone();
        firstDay.setTime(student.getAttendance().get(0).getAttendanceDate());
        attendance = student.getAttendance();
        attendanceUnitToCalendarList();
        setMonthlyCalendar(calendar);
        System.out.println("TUTAJJJJ 1" + SMWC);
        System.out.println("TUTAJJJJ 2" + TMWC);
    }

    @FXML
    private void pressButtonPreviousMonth(ActionEvent event) {
        GridCalendar.getChildren().clear();
        calendar.add(Calendar.MONTH, -1);
        setMonthlyCalendar(calendar);
    }

    @FXML
    private void pressButtonNextMonth(ActionEvent event) {
        GridCalendar.getChildren().clear();
        calendar.add(Calendar.MONTH, 1);
        setMonthlyCalendar(calendar);
    }

    public void setStudent(Student student) {
        this.student = student;

    }

    public void loadDaysLabel() {
        monday = new Label();
        GridCalendar.add(monday, 0, 0);
        monday.setText("Mo");
        tuesday = new Label();
        GridCalendar.add(tuesday, 1, 0);
        tuesday.setText("Tu");
        wednesday = new Label();
        GridCalendar.add(wednesday, 2, 0);
        wednesday.setText("We");
        thursday = new Label();
        GridCalendar.add(thursday, 3, 0);
        thursday.setText("Th");
        friday = new Label();
        GridCalendar.add(friday, 4, 0);
        friday.setText("Fr");
        saturday = new Label();
        GridCalendar.add(saturday, 5, 0);
        saturday.setText("Sa");
        sunday = new Label();
        GridCalendar.add(sunday, 6, 0);
        sunday.setText("Su");

    }

    public void attendanceUnitToCalendarList() {
        for (AttendanceUnit attendanceUnit : attendance) {
            System.out.println(attendanceUnit.getAttendanceDate());
            dateList.add(attendanceUnit.getAttendanceDate());
        }

    }

    public void setMonthlyCalendar(Calendar cal2) {
        double redButtons = 0;
        double greenButtons = 0;

        Calendar cal = (Calendar) cal2.clone();
        loadDaysLabel();

        cal.set(Calendar.DAY_OF_MONTH, 1);
        int daysInTheMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        int z = cal.get(Calendar.DAY_OF_WEEK);
        if (z == 1) {
            x = 6;
        } else {
            x = z - 2;
        }

        y = 1;
        for (int i = 1; i <= daysInTheMonth; ++i) {

            if (x == 7) {
                x = 0;
                y++;
            }
            
            if (checkDateList(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))) {
                buttonColor = "-fx-background-color: Green; -fx-font-size: 13px; -fx-background-radius: 0";
                addButton(x, y, i);
                greenButtons++;
                map.put(i, "Green");
                
                
            } else if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.before(today) && cal.after(firstDay)) {
                buttonColor = "-fx-background-color: Red; -fx-font-size: 13px; -fx-background-radius: 0";
                addButton(x, y, i);
                redButtons++;
                map.put(i, "Red");
            } else {
                buttonColor = "-fx-background-color: Grey; -fx-font-size: 13px; -fx-background-radius: 0";
                addButton(x, y, i);
                map.put(i, "Grey");
            }

            cal.add(Calendar.DAY_OF_MONTH, 1);
            x++;
        }
        labelDate.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + "/" + cal.get(Calendar.YEAR) + " <" + (int) ((greenButtons / (greenButtons + redButtons)) * 100) + "%>");
        labelDate.setAlignment(Pos.TOP_RIGHT);
    }

    public void addButton(int x, int y, int i) {
        JFXButton butt = new JFXButton();
        butt.setText("" + i);
        butt.setStyle(buttonColor);
        butt.setMinSize(36, 36);
        butt.setMaxSize(36, 36);
        butt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (TMWC != null && !map.get(Integer.parseInt(butt.getText())).equals("Grey")) {
                    System.out.println(TMWC != null && map.get(Integer.parseInt(butt.getText())).equals("Green") || map.get(Integer.parseInt(butt.getText())).equals("Red"));
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to change student attendance for this day?", "Attendance changer", dialogButton);
                    if (dialogResult == 0) {
                       if(map.get(Integer.parseInt(butt.getText())).equals("Green")){
                           buttonColor="-fx-background-color: Red; -fx-font-size: 13px; -fx-background-radius: 0";
                           map.put(i, "Red");
                           butt.setStyle(buttonColor);
                       }
                       else if(map.get(Integer.parseInt(butt.getText())).equals("Red")){
                        buttonColor="-fx-background-color: Green; -fx-font-size: 13px; -fx-background-radius: 0";
                        map.put(i, "Green");
                           butt.setStyle(buttonColor);
                    }
                    } 
                }
            }
        });
        GridCalendar.add(butt, x, y);
    }

    public boolean checkDateList(int day, int month, int year) {
        Date date2 = new GregorianCalendar(year, month, day).getTime();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2);
        for (Date date : dateList) {
            if (date.equals(date2)) {
                return true;
            }
        }
        return false;
    }

}
