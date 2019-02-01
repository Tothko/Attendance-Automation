/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Revy
 */
public class Class {
    private String name;
    private int id;
    private List<Class> listOfStudents;
    
    public Class(String name, int id){
        this.name=name;
        this.id=id;
        listOfStudents = new ArrayList<>();
    }
   
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setClassesList(List<Class> list){
        listOfStudents.addAll(list);
    }
    public List<Class> getClassesList(){
         return listOfStudents;
    }
}
