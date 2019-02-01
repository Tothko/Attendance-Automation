/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

/**
 *
 * @author Revy
 */
public class Student {
    
    private String name;
    private int classNum;
    private int id;
    
    public Student(String name, int classNum, int id){
    this.name=name;
    this.classNum=classNum;
    this.id=id;
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
    public int getClassNum()
    {
        return classNum;
    }

    public void setClassNum(int classNum)
    {
        this.classNum = classNum;
    }
}
