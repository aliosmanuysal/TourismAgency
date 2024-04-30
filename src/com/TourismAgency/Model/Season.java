package com.TourismAgency.Model;

import com.TourismAgency.Helper.DBConnector;
import com.TourismAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Season {
    private int id;
    private String name;
    private int hotel_id;
    private String start_date;
    private String end_date;
    private Hotel hotel;
    public Season(){
    }

    public Season(int id, String name, int hotel_id, String start_date, String end_date) {
        this.id = id;
        this.name = name;
        this.hotel_id = hotel_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.hotel = Hotel.getFetch(hotel_id);
    }
    // Bir sezonun adını almak için statik bir metot
    public static String seasonName(int id){
        String query = "SELECT name FROM season WHERE id = ?";
        String name = "";
        Season obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Season();
                obj.setName(rs.getString("name"));
                name += obj.getName();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    // Yeni bir sezon eklemek için statik bir metot
    public static boolean add ( String name, int hotel_id,String start_date,String end_date){
        String query = "INSERT INTO season (name,hotel_id,start_date,end_date)" +
                " VALUES (?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setInt(2,hotel_id);
            pr.setString(3,start_date);
            pr.setString(4,end_date);

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

    // Tüm sezonları getirmek için statik bir metot
    public static ArrayList<Season> getList(){
        ArrayList<Season> seasonList = new ArrayList<>();
        Season obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM season");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int hotel_id = rs.getInt("hotel_id");
                String start_date = rs.getString("start_date");
                String end_date = rs.getString("end_date");
                obj = new Season(id, name, hotel_id, start_date, end_date);
                seasonList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seasonList;
    }
    // Belirli bir otel için sezonları getirmek için statik bir metot
    public static ArrayList<Season> getListByid(int hotelId){
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM season WHERE hotel_id =? ";
        Season obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int hotel_id = rs.getInt("hotel_id");
                String start_date = rs.getString("start_date");
                String end_date = rs.getString("end_date");
                obj = new Season(id, name, hotel_id, start_date, end_date);
                seasonList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seasonList;
    }
    // Belirli bir sezonu getirmek için statik bir metot
    public static Season getFetch(int id){
        Season obj = null;
        String query = "SELECT * FROM season WHERE id =?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, String.valueOf(id));
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Season();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setStart_date(rs.getString("start_date"));
                obj.setEnd_date(rs.getString("end_date"));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return obj;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    private Season getSeason() {
        return null;
    }
}


