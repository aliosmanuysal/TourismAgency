package com.TourismAgency.Model;

import com.TourismAgency.Helper.DBConnector;
import com.TourismAgency.Helper.Helper;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class Room {
    private int id;
    private String name;
    private int hotel_id;
    private int season_id;
    private int hostel_id;
    private int stock;
    private int adult_price;
    private int child_price;
    private Hotel hotel;
    private Season season;
    private Hostel hostel;
    public Room() {
    }
    public Room(int id, String name, int hotel_id, int season_id, int hostel_id, int stock, int adult_price, int child_price) {
        this.id = id;
        this.name = name;
        this.hotel_id = hotel_id;
        this.season_id = season_id;
        this.hostel_id = hostel_id;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.hotel = Hotel.getFetch(hotel_id);
        this.season = Season.getFetch(season_id);
        this.hostel = Hostel.getFetch(hostel_id);

    }

    // Veritabanından tüm odaları getiren metot
    public static ArrayList<Room> getList() {
        ArrayList<Room> roomList = new ArrayList<>();
        Room obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM room");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int hotel_id = rs.getInt("hotel_id");
                int season_id = rs.getInt("season_id");
                int hostel_id = rs.getInt("hostel_id");
                int stock = rs.getInt("stock");
                int adult_price = rs.getInt("adult_price");
                int child_price = rs.getInt("child_price");
                obj = new Room(id, name, hotel_id, season_id, hostel_id, stock, adult_price, child_price);
                roomList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roomList;
    }

    // Belirli bir oda adını getiren metot
    public static String roomName(int id) {
        String query = "SELECT name FROM room WHERE id = ?";
        String name = "";
        Room obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Room();
                obj.setName(rs.getString("name"));
                name += obj.getName();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
    // Oda arama sorgusunu oluşturan metot
    public static String searchQueryRoom(String hotel_name, String hotel_city) {

        String query = "SELECT * FROM hotel RIGHT OUTER JOIN room ON id = hotel_id " +
                "WHERE hotel_name LIKE '%{{name}}%' AND " +
                "hotel_city LIKE '%{{city}}%'";
        query = query.replace("{{name}}", hotel_name);
        query = query.replace("{{city}}", hotel_city);

        System.out.println(query);
        return query;

    }

    // Belirli bir odayı getiren metot
    public static Room getFetch(int id) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, String.valueOf(id));
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setHostel_id(rs.getInt("hostel_id"));
                obj.setStock(rs.getInt("stock"));
                obj.setAdult_price(rs.getInt("adult_price"));
                obj.setChild_price(rs.getInt("child_price"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }

    // Belirli bir otelin odalarını getiren metot
    public static ArrayList<Room> getRoomList(int id) {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room WHERE id=?";
        Room obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, String.valueOf(id));
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setHostel_id(rs.getInt("hostel_id"));
                obj.setStock(rs.getInt("stock"));
                obj.setAdult_price(rs.getInt("adult_price"));
                obj.setChild_price(rs.getInt("child_price"));
                roomList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roomList;
    }


    // Belirli bir odayı silen metot
    public static boolean delete(int id) {
        String query = "DELETE FROM room WHERE id =?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static int getHotelIdByRoomId(int room_id) {
        int i = 0;
        String query = "SELECT hotel_id FROM room WHERE id =?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, room_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int hotel_id = rs.getInt("hotel_id");
                i = hotel_id;
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return i;
    }

    public static boolean add(String name, int hotel_id, int season_id, int hostel_id, int stock, int adult_price, int child_price) {
        String query = "INSERT INTO room (name,hotel_id,season_id,hostel_id,stock,adult_price,child_price)"
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, hotel_id);
            pr.setInt(3, season_id);
            pr.setInt(4, hostel_id);
            pr.setInt(5, stock);
            pr.setInt(6, adult_price);
            pr.setInt(7, child_price);

            int responce = pr.executeUpdate();
            if (responce == -1) {
                Helper.showMsg("error");
            }
            return responce != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Rezervasyon yapıldıktan sonra mevcut odanın stoğunu 1 azaltan metod.
    public static void updateStock(int roomId, int bookedQuantity) {
        Room room = getFetch(roomId);
        int currentStock = room.getStock();
        int updatedStock = currentStock - bookedQuantity;
        // Güncelleme sorgusu
        String updateQuery = "UPDATE room SET stock = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(updateQuery);
            pr.setInt(1, updatedStock);
            pr.setInt(2, roomId);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Oda arama sorgusunu gerçekleştiren metot
    public static ArrayList<Room> search(JTextField fldRegionHotelName, JTextField fldRommCity, JTextField fldChecIn, JTextField fldChecOut) {
        String query = " SELECT * FROM room WHERE hotel_id =" + Hotel.getFetch(fldRegionHotelName.getText());
        Room room = null;
        //28 October, 2018
        ArrayList<Room> searchfilter = new ArrayList<>();
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            // otel adi gore
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int hotel_id = rs.getInt("hotel_id");
                int season_id = rs.getInt("season_id");
                int hostel_id = rs.getInt("hostel_id");
                int stock = rs.getInt("stock");
                int adult_price = rs.getInt("adult_price");
                int child_price = rs.getInt("child_price");
                room = new Room(id, name, hotel_id, season_id, hostel_id, stock, adult_price, child_price);
                roomList.add(room);
            }
            roomList.stream().filter(rooms -> rooms.getSeason().getStart_date().equals(fldChecIn.getText()) && rooms.getSeason().getEnd_date().equals(fldChecOut.getText())).forEach(searchfilter::add);



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return searchfilter;
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

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getHostel_id() {
        return hostel_id;
    }

    public void setHostel_id(int hostel_id) {
        this.hostel_id = hostel_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}
