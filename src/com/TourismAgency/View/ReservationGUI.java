package com.TourismAgency.View;

import com.TourismAgency.Helper.Config;
import com.TourismAgency.Helper.Helper;
import com.TourismAgency.Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_res_hotel_name;
    private JTextField fld_res_adress;
    private JTextField fld_res_facility;
    private JTextField fld_res_room_name;
    private JTextField fld_res_phone;
    private JTextField fld_res_season;
    private JTextField fld_res_room_features;
    private JTextField fld_res_hostel_type;
    private JTextField fld_res_adult_price;
    private JTextField fld_res_child_price;
    private JTextField fld_cust_name;
    private JTextField fld_cust_phone;
    private JTextField fld_cust_email;
    private JTextField fld_cust_note;
    private JLabel Gün;
    private JTextField fld_cust_day;
    private JButton btn_res_add;
    private JTextField fld_res_total_price;
    private JComboBox cmb_cust_day;
    private JButton btn_price_cal;
    private int selected_roomId = EmployeeGUI.getSelected_roomId();
    private  int getSelected_hotelId ;
    private int getSelected_roomId;
    private String seasonName = EmployeeGUI.getSelectedSeasonName();
    private String hostelType = EmployeeGUI.getSelectedHostelType();
    private int adultPrice = EmployeeGUI.getSelectedAdultPrice();
    private int childPrice = EmployeeGUI.getSelectedChildPrice();
    private int total ;
    private int day;

    public ReservationGUI(){
        add(wrapper);
        setSize(700,500);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(true);
        setVisible(true);

        getSelected_hotelId = Room.getHotelIdByRoomId(selected_roomId);


        ArrayList<Hotel> hotel = new ArrayList<>();
        hotel.add(Hotel.getFetch(getSelected_hotelId));
        for(Hotel obj : hotel){
            fld_res_hotel_name.setText(obj.getName());
            fld_res_adress.setText(obj.getAddress());
            fld_res_phone.setText(obj.getPhone_number());
        }

        ArrayList<Facility> facility = Facility.getFetch(getSelected_hotelId);
        String facilityName = "";
        for (Facility obj : facility){
            facilityName += obj.getName() + " ,";
            String x = facilityName;
            fld_res_facility.setText(x);
        }

        ArrayList<Roomfeatures> roomfeatures = Roomfeatures.getFetch(selected_roomId);
        String featuresName = "";
        for(Roomfeatures obj : roomfeatures){
            featuresName += obj.getName() + " ,";
            String y = featuresName;
            fld_res_room_features.setText(y);
        }

        ArrayList<Room> room = new ArrayList<>();
        room.add(Room.getFetch(selected_roomId));
        for(Room obj : room){
            fld_res_room_name.setText(obj.getName());
        }
        fld_res_season.setText(seasonName);
        fld_res_hostel_type.setText(hostelType);
        fld_res_adult_price.setText(String.valueOf(adultPrice));
        fld_res_child_price.setText(String.valueOf(childPrice));

        //Rezervasyon Ekleme
        btn_res_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_cust_name) || Helper.isFieldEmpty(fld_cust_email)
                    || Helper.isFieldEmpty(fld_cust_phone)
                    || Helper.isFieldEmpty(fld_cust_note) || Helper.isFieldEmpty(fld_res_total_price)) {
                Helper.showMsg("fill");
            }else{
                String name = fld_cust_name.getText();
                String email = fld_cust_email.getText();
                String phone = fld_cust_phone.getText();
                int hotel_id = getSelected_hotelId;
                String note = fld_cust_note.getText();

                Reservation.add(name,phone,email,note,hotel_id,selected_roomId,day,total);
                Room.updateStock(selected_roomId, 1);
            }
            Helper.showMsg("done");
        });
        btn_price_cal.addActionListener(e -> {
            int sum = adultPrice + childPrice;
            day = cmb_cust_day.getSelectedIndex() +1;
            total = day * sum;

            fld_res_total_price.setText(String.valueOf(total));


        });
    }


}


