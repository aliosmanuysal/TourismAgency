package com.TourismAgency.View;

import com.TourismAgency.Helper.Config;
import com.TourismAgency.Helper.Helper;
import com.TourismAgency.Model.Facility;
import com.TourismAgency.Model.Hostel;
import com.TourismAgency.Model.Season;

import javax.swing.*;

public class AddHotelFeatures extends JFrame {

    private JPanel wrapper;
    private JCheckBox cb_inclusive;
    private JCheckBox cb_breakfast;
    private JCheckBox cb_just_bed;
    private JCheckBox cb_half_hostel;
    private JCheckBox cb_full_hostel;
    private JCheckBox cb_credit_wo_alcohol;
    private JCheckBox cb_ultra_inclusive;
    private JButton btn_hostel_add;
    private JCheckBox cb_free_wifi;
    private JCheckBox cb_free_carpark;
    private JCheckBox cb_swim;
    private JCheckBox cb_fitness_center;
    private JCheckBox cb_spa;
    private JButton btn_facility_add;
    private JCheckBox cb_room_service;
    private JPanel pnl_hostel_feature;
    private JPanel pnl_facility_features;
    private JPanel pnl_season_type;
    private JButton btn_season_add;
    private JCheckBox cb_summer;
    private JCheckBox cb_autumn;
    private JCheckBox cb_winter;
    private JCheckBox cb_spring;
    private int selected_hotel_id = EmployeeGUI.getSelected_hotelId();

    public AddHotelFeatures(){
        add(wrapper);
        setSize(800,400);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        //Otelimize pansiyon Özelliği ekleme metodu
        btn_hostel_add.addActionListener(e -> {
            if (areFieldsEmptyHostelFeatures()) {
                Helper.showMsg("fill");
                return;
            }
            if(cb_ultra_inclusive.isSelected()){
                String ultrainclusive = "Ultra Herşey Dahil";
                Hostel.add(ultrainclusive,selected_hotel_id);
            }
            if(cb_inclusive.isSelected()){
                String allinclusive = "Herşey Dahil";
                Hostel.add(allinclusive,selected_hotel_id);
            }
            if(cb_breakfast.isSelected()){
                String breakfast = "Oda Kahvaltı";
                Hostel.add(breakfast,selected_hotel_id);
            }
            if(cb_full_hostel.isSelected()){
                String fullpencion = "Tam Pansiyon";
                Hostel.add(fullpencion,selected_hotel_id);
            }
            if(cb_half_hostel.isSelected()){
                String halfhostel = "Yarım Pansiyon";
                Hostel.add(halfhostel,selected_hotel_id);
            }
            if(cb_just_bed.isSelected()){
                String justbed = "Sadece Yatak";
                Hostel.add(justbed,selected_hotel_id);
            }
            if(cb_credit_wo_alcohol.isSelected()){
                String fullcredit = "Sadece Yatak";
                Hostel.add(fullcredit,selected_hotel_id);
            }
            Helper.showMsg("done");

        });
        //Otelimize tesis özelliği ekleme
        btn_facility_add.addActionListener(e -> {
            if (areFieldsEmptyFacilityFeatures()) {
                Helper.showMsg("fill");
                return;
            }
            if(cb_free_wifi.isSelected()){
                String freewifi = "Ücretsiz Wifi";
                Facility.add(freewifi,selected_hotel_id);
            }

            if(cb_free_carpark.isSelected()){
                String freewifi = "Ücretsiz Otopark";
                Facility.add(freewifi,selected_hotel_id);
            }
            if(cb_swim.isSelected()){
                String swim = "Yüzme Havuzu";
                Facility.add(swim,selected_hotel_id);
            }
            if(cb_fitness_center.isSelected()){
                String fitness = "Fitness Center";
                Facility.add(fitness,selected_hotel_id);
            }
            if(cb_spa.isSelected()){
                String spa = "SPA";
                Facility.add(spa,selected_hotel_id);
            }
            if(cb_room_service.isSelected()){
                String roommservice = "7/24 Oda Servisi";
                Facility.add(roommservice,selected_hotel_id);
            }
            Helper.showMsg("done");
        });

        //Sezon bilgilerini ekleme metodu
        btn_season_add.addActionListener(e -> {

            if (areFieldsEmptySeasonFeatures()) {
                Helper.showMsg("fill");
                return;
            }
            if(cb_summer.isSelected()){
                String sum = "Yaz Dönemi";
                String start = "2024-06-01";
                String end = "2024-09-01";
                Season.add(sum, selected_hotel_id ,start ,end);
            }
            if(cb_autumn.isSelected()){
                String autumn = "Son Bahar Dönemi";
                String start = "2024-09-02";
                String end = "2024-11-30";
                Season.add(autumn , selected_hotel_id,start,end);
            }
            if(cb_spring.isSelected()){
                String spring = "İlk Bahar Dönemi";
                String start = "2024-03-01";
                String end = "2024-05-31";
                Season.add(spring , selected_hotel_id,start,end);
            }
            if(cb_winter.isSelected()){
                String winter = "Kış Dönemi";
                String start = "2024-03-01";
                String end = "2024-05-31";
                Season.add(winter , selected_hotel_id,start,end);
            }
            Helper.showMsg("done");
        });
    }
    private boolean areFieldsEmptySeasonFeatures() {
        return !(cb_summer.isSelected() || cb_autumn.isSelected() || cb_spring.isSelected() ||
                cb_winter.isSelected());
    }
    private boolean areFieldsEmptyFacilityFeatures() {
        return !(cb_free_wifi.isSelected() || cb_free_carpark.isSelected() || cb_swim.isSelected() ||
                cb_fitness_center.isSelected() ||cb_spa.isSelected() || cb_room_service.isSelected());
    }
    private boolean areFieldsEmptyHostelFeatures() {
        return !(cb_ultra_inclusive.isSelected() || cb_inclusive.isSelected() || cb_breakfast.isSelected() ||
                cb_full_hostel.isSelected() ||cb_half_hostel.isSelected() || cb_just_bed.isSelected()
                || cb_credit_wo_alcohol.isSelected());
    }
}
