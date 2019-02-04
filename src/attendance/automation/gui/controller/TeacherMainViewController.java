/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.Teacher;
import attendance.automation.bll.AAManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author leopo
 */
public class TeacherMainViewController 
{

    private Teacher te;
    private AAManager manager;
    
    public TeacherMainViewController() throws IOException{
        manager = AAManager.getInstance();
        te = manager.getTeacher();
    }

    
    
    
}
