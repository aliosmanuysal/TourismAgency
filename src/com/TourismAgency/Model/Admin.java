package com.TourismAgency.Model;

public class Admin extends User{
    private static Object DBConnector;

    public Admin(){
    }
    public Admin(int id, String name, String uname,String email, String pass, String type){
        super(id, name, uname, email, pass, type);
    }
}
