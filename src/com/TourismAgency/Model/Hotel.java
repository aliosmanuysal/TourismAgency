package com.TourismAgency.Model;

import com.TourismAgency.Helper.DBConnector;
import com.TourismAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String region;
    private String address;
    private String email;
    private String phone_number;
    private String star;

    public Hotel(){
    }
    // Constructor
    public Hotel(int id, String name,String address, String city, String region, String email,
                 String phone_number, String star) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.region = region;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.star = star;
    }

    // Veritabanından tüm otelleri getiren metot
    public static ArrayList<Hotel> getList(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM hotel");
            while (rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone_number(rs.getString("phone_number"));
                obj.setStar(rs.getString("star"));
                hotelList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelList;
    }

    // Belirli bir oteli ID'ye göre getiren metot
    public static ArrayList<Hotel> getlistByid(int hotel_id){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel WHERE id =? ";
        Hotel obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String region = rs.getString("region");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String star = rs.getString("star");
                obj = new Hotel(id, name, city, region,address, email, phone_number, star);
                hotelList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelList;
    }

    // Yeni otel ekleme metodu
    public static boolean add(String name,String address, String city, String region, String email,
                              String phone_number, String star ) {
        String query = "INSERT INTO hotel (name,city,region,address,email,phone_number,star)" +
                " VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,city);
            pr.setString(3,region);
            pr.setString(4,address);
            pr.setString(5,email);
            pr.setString(6,phone_number);
            pr.setString(7,star);

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
    public static Hotel getFetch(int id){
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE id =?";
        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, String.valueOf(id));
            ResultSet rs = pr.executeQuery();
            if(rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone_number(rs.getString("phone_number"));
                obj.setStar(rs.getString("star"));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return obj;
    }

    // Belirli bir oteli isme göre getiren metot
    public static String getFetch(String name){
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE name =?";
        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            ResultSet rs = pr.executeQuery();
            if(rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone_number(rs.getString("phone_number"));
                obj.setStar(rs.getString("star"));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return obj.getName();
    }
    // Belirli bir oteli silen metot
    public static boolean delete(int id) {
        String query = "DELETE FROM hotel WHERE id =?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Bir oteli güncellemek için metot
    public static boolean update(int id,String name,String address, String city, String region, String email, String phone_number, String star) {
        String query = "UPDATE hotel SET name=?, address=?, city=?, region=?, email=?, phone_number=?, star=? WHERE id =?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,address);
            pr.setString(3,city);
            pr.setString(4,region);
            pr.setString(5,email);
            pr.setString(6,phone_number);
            pr.setString(7,star);
            pr.setInt(8, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    // Verilen sorguya göre otel listesi getiren metot
    public static ArrayList<Hotel> searchHotelList(String query){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone_number(rs.getString("phone_number"));
                obj.setStar(rs.getString("star"));
                hotelList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelList;
    }
    // Verilen isim, şehir, bölge ve yıldıza göre arama sorgusu oluşturan metot
    public static String searchQuery(String name, String city, String region, String star){
        String query = "SELECT * FROM hotel WHERE name LIKE '%{{name}}%' AND city LIKE '%{{city}}%' AND region LIKE '%{{region}}%' AND star LIKE '%{{star}}%'";
        query = query.replace("{{name}}" ,name);
        query = query.replace("{{city}}" ,city);
        query = query.replace("{{region}}" ,region);
        query = query.replace("{{star}}" ,star);
        System.out.println(query);
        return query;
    }

    // Verilen otel ID'sine göre otel adını getiren metot
    public static String hotelName(int id){
        String query = "SELECT name FROM hotel WHERE id = ?";
        String name = "";
        Hotel obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hotel();
                obj.setName(rs.getString("name"));
                name += obj.getName();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
    // Getter ve Setter metotları


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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    private Hotel getHotel() {
        return null;
    }
}

