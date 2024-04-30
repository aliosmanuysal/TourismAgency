package com.TourismAgency.View;

import com.TourismAgency.Helper.Config;
import com.TourismAgency.Helper.Helper;
import com.TourismAgency.Model.Roomfeatures;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomFeatures extends JFrame {
    private JPanel wrapper;
    private JCheckBox cb_romm_tv;
    private JCheckBox cb_room_minibar;
    private JCheckBox cb_room_till;
    private JCheckBox cb_room_projection;
    private JCheckBox cb_room_game_console;
    private JButton btn_room_features_add;
    private  int selected_room_id = EmployeeGUI.getSelected_roomId();

    public AddRoomFeatures(){
        add(wrapper);
        setSize(500,300);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        //Odamıza özellik ekleme
        btn_room_features_add.addActionListener(e -> {

            // Kullanıcının boş bir alan bırakıp bırakmadığını kontrol eder
            if (areFieldsEmptyRoomFeatures()) {
                Helper.showMsg("fill");
                return;
            }
            if(cb_romm_tv.isSelected()){
                String tv = "TV";
                Roomfeatures.add(tv,selected_room_id);
            }
            if(cb_room_minibar.isSelected()){
                String minibar = "Minibar";
                Roomfeatures.add(minibar,selected_room_id);
            }
            if(cb_room_till.isSelected()){
                String till = "Kasa";
                Roomfeatures.add(till,selected_room_id);
            }
            if(cb_room_projection.isSelected()){
                String projectin = "Projesiyon";
                Roomfeatures.add(projectin,selected_room_id);
            }
            if(cb_room_game_console.isSelected()){
                String game = "Oyun Konsolu";
                Roomfeatures.add(game,selected_room_id);
            }
            Helper.showMsg("done");
        });
    }
    // Kullanıcının boş bir alan bırakıp bırakmadığını kontrol etmek için yardımcı metot
    private boolean areFieldsEmptyRoomFeatures() {
        return !(cb_romm_tv.isSelected() || cb_room_minibar.isSelected() || cb_room_till.isSelected() ||
                cb_room_projection.isSelected() || cb_room_game_console.isSelected());
    }
}
