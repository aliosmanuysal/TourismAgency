package com.TourismAgency.Model;

import com.TourismAgency.Helper.DBConnector;
import com.TourismAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Boş kurucu metod
public class User {
    private int id;
    private String name;
    private String uname;
    private String email;
    private String pass;
    private String type;

    // Boş kurucu metod
    public User () {
    }

    // Parametreli kurucu metod
    public User(int id, String name, String uname, String email ,String pass, String type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.email = email;
        this.pass = pass;
        this.type = type;
    }

    // Getter ve Setter metodları
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String username) {
        this.uname = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String password) {
        this.pass = pass;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    // Kullanıcı listesi getiren metod
    public static ArrayList<User> getList(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setEmail(rs.getString("email"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    // Yeni kullanıcı ekleyen metod
    public static boolean add(String name, String uname, String email,String pass, String type) {
        String query = "INSERT INTO user (name,uname,email,pass,type) VALUES (?,?,?,?,?)";
        User findUser = User.getFetch(uname);
        if(findUser != null){
            Helper.showMsg("Bu kullanıcı adı daha önceden eklenmiş. Lütfen farklı bir kullanıcı adı giriniz.!");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,email);
            pr.setString(4,pass);
            pr.setString(5,type);
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

    // Kullanıcıyı kullanıcı adına göre getiren metod
    public static User getFetch(String uname){
        User obj = null;
        String query = "SELECT * FROM user WHERE uname = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,uname);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setEmail(rs.getString("email"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }

    // Kullanıcı kimlik bilgilerini kontrol eden metod
    public static User getCredentials (String uname, String pass) {
        User obj =null ;
        String query = "SELECT * FROM user WHERE uname = ? AND pass = ? ";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,uname);
            pr.setString(2,pass);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Kullanıcıyı silen metod
    public static boolean delete(int id){
        String query = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Kullanıcıyı güncelleyen metod
    public static boolean update(int id, String name, String uname, String email,String pass, String type){
        String query = "UPDATE user SET name=?, uname=?, email=?, pass=?, type=? WHERE id = ?";
        User findUser = User.getFetch(uname);
        if(findUser != null && findUser.getId() != id) {
            Helper.showMsg("Bu kullanıcı adı daha önceden eklenmiş. Lütfen farklı bir kullanıcı adı giriniz.!");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,email);
            pr.setString(4,pass);
            pr.setString(5,type);
            pr.setInt(6,id);
            return  pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Kullanıcıları verilen sorguya göre arayan metod
    public static ArrayList<User> searchUserList(String query) {
        ArrayList<User> userList = new ArrayList<>();
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setEmail(rs.getString("email"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    // Kullanıcıları aramak için veritabanı sorgusu oluşturan metod
    public static String searchQuery(String name, String uname , String type){
        String query = "SELECT * FROM user WHERE uname LIKE '%{{uname}}%' AND name LIKE '%{{name}}%'";
        query = query.replace("{{uname}}" , uname);
        query = query.replace("{{name}}" , name);
        if (!type.isEmpty()){
            query += " AND type='{{type}}'";
            query = query.replace("{{type}}" , type);
        }
        System.out.println(query);
        return query;
    }
}


