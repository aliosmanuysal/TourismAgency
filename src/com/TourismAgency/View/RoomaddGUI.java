package com.TourismAgency.View;

import com.TourismAgency.Helper.Config;
import com.TourismAgency.Helper.Helper;
import com.TourismAgency.Model.Hotel;
import com.TourismAgency.Model.Room;
import com.TourismAgency.Model.Season;
import com.TourismAgency.Model.Hostel;

import javax.swing.*;

public class RoomaddGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_room_name;
    private JTextField fld_room_hotel_id;
    private JTextField fld_adult_price;
    private JTextField fld_room_stock;
    private JButton btn_add;
    private JTextField fld_child_price;
    private JComboBox cmb_room_season_id;
    private JComboBox cmb_room_hostel_id;
    private JLabel lbl_welcome;
    private JButton btn_addroom_logout;
    private int hotel_id = EmployeeGUI.getSelected_hotelId();
    private int addHotelid ;
    private int addSeasonid;
    private int addHostelid;



    public RoomaddGUI () {
        add(wrapper);
        setSize(600, 600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        // Otel, mevsim ve pansiyon bilgilerini kullanarak comboBox'ları doldurma
        for (Hotel obj : Hotel.getlistByid(hotel_id)) {
            String hotel_name = obj.getName();
            addHotelid = obj.getId();
            fld_room_hotel_id.setText(hotel_name);
        }
        for (Season obj : Season.getListByid(hotel_id)) {
            String season_name = obj.getName();
            addSeasonid = obj.getId();
            cmb_room_season_id.addItem(season_name);
        }
        for (Hostel obj : Hostel.getListByid(hotel_id)) {
            String hostel_name = obj.getName();
            addHostelid = obj.getId();
            cmb_room_hostel_id.addItem(hostel_name);
        }

        //Oda ekleme metodu
        btn_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_name)
                    || Helper.isFieldEmpty(fld_room_stock)
                    || Helper.isFieldEmpty(fld_adult_price)
                    || Helper.isFieldEmpty(fld_child_price)) {
                Helper.showMsg("fill");
            } else {
                String name = fld_room_name.getText();
                int stock = Integer.parseInt(fld_room_stock.getText());
                int adult_price = Integer.parseInt(fld_adult_price.getText());
                int child_price = Integer.parseInt(fld_child_price.getText());
                Room.add(name,addHotelid,addSeasonid,addHostelid,stock,adult_price,child_price);
                Helper.showMsg("done");
            }

        });
        //çıkış yap butonu
        btn_addroom_logout.addActionListener(e -> {
            dispose();
            EmployeeGUI empGUI = new EmployeeGUI();

        });
    }
}
