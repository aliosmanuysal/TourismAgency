package com.TourismAgency.Model;

import com.TourismAgency.Helper.DBConnector;
import com.TourismAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Facility {
    private int id;
    private String name;
    private int hotel_id;

    public Facility(int id, String name, int hotel_id) {
        this.id = id;
        this.name = name;
        this.hotel_id = hotel_id;

    }
    public Facility(){
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    // Yeni bir tesis eklemek için statik metod
    public static boolean add ( String name, int hotel_id ){
        String query = "INSERT INTO facility (name,hotel_id)" +
                " VALUES (?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setInt(2,hotel_id);

            int responce = pr.executeUpdate();
            if(responce == -1){
                Helper.showMsg("error");
            }
            return responce != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // hotel_id'ye bağlı tesisleri getirmek için statik metod
    public static ArrayList<Facility> getFetch(int id){
        ArrayList<Facility> abc  = new ArrayList<>();
        Facility obj = null;
        String query = "SELECT name FROM facility WHERE hotel_id =?";
        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, String.valueOf(id));
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Facility();
                obj.setName(rs.getString("name"));
                abc.add(obj);
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return abc;
    }
}
