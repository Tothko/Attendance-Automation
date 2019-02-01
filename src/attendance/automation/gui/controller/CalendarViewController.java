/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import java.net.URL;
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
    private int daysInMonth = 31;
    private int x = 0;
    private int y = 1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (int i = 1; i <= daysInMonth; ++i) 
{
    x++;
    if(x==7){
        x=0;
        y++;
            }
    if(y==6) y=1;  
    
    Button butt = new Button();
    butt.setText(""+i);
    butt.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        System.out.println("Kuba is awesome guy!!!");
    }
});
    GridCalendar.add(butt, x, y);
}
    }    
    
}
