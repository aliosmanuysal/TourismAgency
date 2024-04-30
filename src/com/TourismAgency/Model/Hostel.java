package com.TourismAgency.Model;

import com.TourismAgency.Helper.DBConnector;
import com.TourismAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hostel {
    private int id;
    private String name;
    private int hotel_id;
    private Hotel hotel;
    public Hostel(){
    }
    public Hostel(int id, String name, int hotel_id) {
        this.id = id;
        this.name = name;
        this.hotel_id = hotel_id;
        this.hotel = Hotel.getFetch(hotel_id);
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    // Tüm hostelleri getirmek için statik metod
    public static ArrayList<Hostel> getList() {
        ArrayList<Hostel> hostelList = new ArrayList<>();
        Hostel obj;
        try{
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM hostel");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int hotel_id = rs.getInt("hotel_id");
                obj = new Hostel(id,name,hotel_id);
                hostelList.add(obj);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hostelList;

    }
    // Belirli bir hostelin adını getirmek için statik metod
    public static String hostelName(int id){
        String query = "SELECT name FROM hostel WHERE id = ?";
        String name = "";
        Hostel obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hostel();
                obj.setName(rs.getString("name"));
                name += obj.getName();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    // Belirli bir otelin hostellerini getirmek için statik metod
    public static ArrayList<Hostel> getListByid(int hotelId) {
        ArrayList<Hostel> hostelList = new ArrayList<>();
        String query = "SELECT * FROM hostel WHERE hotel_id =? ";
        Hostel obj;
        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int hotel_id = rs.getInt("hotel_id");
                obj = new Hostel(id,name,hotel_id);
                hostelList.add(obj);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hostelList;

    }
    // Yeni bir hostel eklemek için statik metod
    public static boolean add ( String name, int hotel_id ){
        String query = "INSERT INTO hostel (name,hotel_id)" +
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
    // Belirli bir hostel bilgisini getirmek için statik metod
    public static Hostel getFetch(int id){
        Hostel obj = null;
        String query = "SELECT * FROM hostel WHERE id =?";
        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, String.valueOf(id));
            ResultSet rs = pr.executeQuery();
            if(rs.next()) {
                obj = new Hostel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setHotel_id(rs.getInt("hotel_id"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return obj;
    }

    private Hostel getHostel() {
        return null;
    }
}
