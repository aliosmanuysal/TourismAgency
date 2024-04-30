package com.TourismAgency.Model;

import com.TourismAgency.Helper.DBConnector;
import com.TourismAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Roomfeatures {
    private int id;
    private String name;
    private int room_id;

    public Roomfeatures(int id, String name, int room_id) {
        this.id = id;
        this.name = name;
        this.room_id = room_id;
    }
    public Roomfeatures(){

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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    // Oda özelliği eklemek için statik bir metot
    public static boolean add ( String name, int room_id ){
        String query = "INSERT INTO roomfeatures (name,room_id)" +
                " VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setInt(2,room_id);

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
    // Belirli bir odaya ait özellikleri getirmek için statik bir metot
    public static ArrayList<Roomfeatures> getFetch(int id){
        ArrayList<Roomfeatures> abc  = new ArrayList<>();
        Roomfeatures obj = null;
        String query = "SELECT name FROM roomfeatures WHERE room_id =?";
        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, String.valueOf(id));
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Roomfeatures();
                obj.setName(rs.getString("name"));
                abc.add(obj);
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return abc;
    }

}
