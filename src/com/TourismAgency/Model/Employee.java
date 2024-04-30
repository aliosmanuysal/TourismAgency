package com.TourismAgency.Model;

import com.TourismAgency.View.EmployeeGUI;

public class Employee extends  User{
    public Employee() {

    }
    public Employee(int id, String name, String uname,String email, String pass, String type){
        super(id, name, uname, email, pass, type);
    }
}
