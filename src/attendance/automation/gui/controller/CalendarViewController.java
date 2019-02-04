/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    @FXML
    private GridPane GridCalendar;
    @FXML
    private Label labelDate;
    private Date date = new Date();
    private int x = 0;
    private int y = 1;
    private LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelDate.setText(localDate.getMonth()+"/"+localDate.getYear());
        String someDay = (localDate.minusDays(localDate.getDayOfMonth()-1)).getDayOfWeek().toString();
        
        if(someDay.equals("MONDAY")) x += 0;
        else if(someDay.equals("TUESDAY")) x += 1;
        else if(someDay.equals("WEDNESDAY")) x += 2;
        else if(someDay.equals("THURSDAY")) x += 3;
        else if(someDay.equals("FRIDAY")) x += 4; 
        else if(someDay.equals("SATURDAY")) x += 5;
        else if(someDay.equals("SUNDAY"))  x += 6;
        
        for (int i = 1; i <= localDate.lengthOfMonth(); ++i) 
{   
    (localDate.minusDays(localDate.getDayOfMonth())).getDayOfWeek();
    
    if(x==7){
        x=0;
        y++;
            }
    if(y==6) y=1;  
    
    Button butt = new Button();
    butt.setText(""+i);
    butt.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
           // TO DO CODE
    }
});
    GridCalendar.add(butt, x, y);
    x++;
}
    }    
    
}
