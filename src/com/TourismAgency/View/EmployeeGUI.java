package com.TourismAgency.View;

import com.TourismAgency.Helper.Config;
import com.TourismAgency.Helper.Helper;
import com.TourismAgency.Helper.Item;
import com.TourismAgency.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.TourismAgency.Model.Room.searchQueryRoom;

public class EmployeeGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane pnl_hotel_list;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JScrollPane scrl_hotel_list;
    private JTable tbl_hotel_list;
    private JPanel pnl_hotel_form;
    private JTextField fld_hotel_name;
    private JTextField fld_city_name;
    private JTextField fld_region_name;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_star;
    private JComboBox cmb_facility_type;
    private JButton btn_hotel_add;
    private JPanel pnl_top;
    private JTable tbl_season_list;
    private JComboBox cmb_hotel_name;
    private JTextField fld_hotel_id;
    private JButton btn_hotel_delete;
    private JScrollPane scrl_room_list;
    private JTable tbl_room_list;
    private JTextField fld_room_stock;
    private JTextField fld_adult_price;
    private JComboBox cmb_room_hotel_name;
    private JComboBox cmb_room_season_name;
    private JComboBox cmb_room_hostel_name;
    private JButton btn_room_add;
    private JTextField fld_sh_hotel_name;
    private JTextField fld_sh_hotel_city;
    private JTextField fld_sh_hotel_region;
    private JTextField fld_sh_hotel_star;
    private JButton btn_hotel_sh;
    private JPanel pnl_room_form;
    private JPanel pnl_hotel_sh;
    private JButton btn_hotel_features;
    private JTextField fld_room_name;
    private JTextField fld_room_id;
    private JButton btn_room_features;
    private JTextField fld_child_price;
    private JButton btn_room_delete;
    private JButton btn_reservation_add;
    private JButton btn_hotel_romm_add;
    private JPanel pnl_room_sh;
    private JTextField fld_sh_room_hotel_name;
    private JTextField fld_chec_in;
    private JButton btn_sh_room;
    private JTextField fld_chec_out;
    private JButton btn_refresh_room;
    private JTable tbl_reservation_list;
    private JTextField fld_res_id;
    private JButton btn_res_delete;
    private JButton btn_refresh_reservation;
    private JButton btn_hotel_refresh;
    private JTextField fld_region_hotelName;
    private JTextField fld_romm_city;
    private JComboBox cmb_hotel_season;
    private JComboBox cmb_hotel_hostel_type;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private ArrayList<Hotel> list;
    private DefaultTableModel mdl_season_list;
    private Object[] row_season_list;
    private DefaultTableModel mdl_room_list;
    private Object[] row_room_list;
    private static int selected_hotelId;
    private static int selected_roomId;
    private static int selected_reservationId;
    private DefaultTableModel mdl_reservation_list;
    private Object[] row_reservation_list;
    private int room_hotel_id_add;
    private static String selectedSeasonName ;
    private static String selectedHostelType;
    private static int selectedAdultPrice;
    private static int selectedChildPrice;
    private String check_in;
    private String check_out;

    public EmployeeGUI(){
        add(wrapper);
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(true);
        setVisible(true);

        //Çıkış yap metodu
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });

        //*************ModelHotelListStart*************
        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list = {"ID","Otel Adı","Şehir","Bölge","Adres","E-Mail","Phone Number","Star"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelList();
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        //Seçilen bir satır üzerinden işlem yapabilme
        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                String selected_hotel_id = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString();
                selected_hotelId = Integer.parseInt(selected_hotel_id); //seçtiğim otel
                fld_hotel_id.setText(selected_hotel_id);

            }catch (Exception exception){
            }
        });

        //Otel tablosu güncelleme
        tbl_hotel_list.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE){
                int hotel_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString());
                String hotel_name = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),1).toString();
                String hotel_city = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),2).toString();
                String hotel_region = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),3).toString();
                String hotel_address = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),4).toString();
                String hotel_email = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),5).toString();
                String hotel_phone_number = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),6).toString();
                String hotel_star = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),7).toString();
                if (Hotel.update(hotel_id,hotel_name,hotel_address,hotel_city,hotel_region,hotel_email,hotel_phone_number,hotel_star)) {
                    Helper.showMsg("done");
                    loadHotelList();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        //Otel ekleme
        btn_hotel_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_hotel_name) ||
                    Helper.isFieldEmpty(fld_city_name) ||
                    Helper.isFieldEmpty(fld_region_name) ||
                    Helper.isFieldEmpty(fld_hotel_address) ||
                    Helper.isFieldEmpty(fld_hotel_email) ||
                    Helper.isFieldEmpty(fld_hotel_phone) ||
                    Helper.isFieldEmpty(fld_hotel_star)) {
                Helper.showMsg("fill");
            }else{
                String name = fld_hotel_name.getText();
                String city = fld_city_name.getText();
                String region = fld_region_name.getText();
                String adress = fld_hotel_address.getText();
                String email = fld_hotel_email.getText();
                String phone_number = fld_hotel_phone.getText();
                String star = fld_hotel_star.getText();
                Hotel.add(name, adress,city,region,email,phone_number,star);
                    Helper.showMsg("done");
                    loadHotelList();
                    fld_hotel_name.setText(null);
                    fld_city_name.setText(null);
                    fld_region_name.setText(null);
                    fld_hotel_address.setText(null);
                    fld_hotel_email.setText(null);
                    fld_hotel_phone.setText(null);
                    fld_hotel_star.setText(null);
            }
        });

        //Oteli ID'sine göre silme
        btn_hotel_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_id)){
                Helper.showMsg("fill");
            }else{
                int hotel_id = Integer.parseInt(fld_hotel_id.getText());
                if ( Hotel.delete(hotel_id)){
                    Helper.showMsg("done");
                    loadHotelList();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        //Otel filtreleme  metodu
        btn_hotel_sh.addActionListener(e -> {
            String name = fld_sh_hotel_name.getText();
            String city = fld_sh_hotel_city.getText();
            String region = fld_sh_hotel_region.getText();
            String star = fld_sh_hotel_star.getText();
            String query = Hotel.searchQuery(name,city,region,star);
            ArrayList<Hotel> searchingHotel = Hotel.searchHotelList(query);
            loadHotelModel(searchingHotel);
        });

        btn_hotel_refresh.addActionListener(e -> {
            loadHotelList();;
        });

        //Seçili Otele Özellik ekleme metodu
        btn_hotel_features.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_id)){
                Helper.showMsg("fill");
            }else{
                AddHotelFeatures features = new AddHotelFeatures();
            }
        });

        //Otele Oda ekleme metodu
        btn_hotel_romm_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_hotel_id)){
                Helper.showMsg("error");
            }else{
                RoomaddGUI rm = new RoomaddGUI();
                loadRoomModel();
                loadRoomList();
            }
        });

        //*************ModelRoomListStart*************
        mdl_room_list = new DefaultTableModel();
        Object[] col_roomList = {"ID","Oda Adı","Otel","Sezon","Pansiyon Tipi","Stok","Yetişkin ücreti","Çocuk Ücreti"};
        mdl_room_list.setColumnIdentifiers(col_roomList);
        row_room_list = new Object[col_roomList.length];
        loadRoomList();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);

        tbl_room_list.getSelectionModel().addListSelectionListener(e ->{
            try {
                String selected_room_id = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0).toString();
                selectedSeasonName = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),3).toString();
                selectedHostelType = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),4).toString();
                selectedAdultPrice = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),6);
                selectedChildPrice = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),7);
                selected_roomId = Integer.parseInt(selected_room_id);


                fld_room_id.setText(selected_room_id);

            }catch (Exception exception){
            }
        });

        //Oda Silme metodu
        btn_room_delete.addActionListener(e -> {
            if ( Helper.isFieldEmpty(fld_room_id)){
                Helper.showMsg("fill");
            }else{
                int room_id = Integer.parseInt(fld_room_id.getText());
                if(Room.delete(room_id)){
                    Helper.showMsg("done");
                    loadRoomModel();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        //Oda Yönetim Tablosunu Yenileme
        btn_refresh_room.addActionListener(e -> {
            loadRoomModel();
        });

        /*
        //Oda Arama metodu
        btn_sh_room.addActionListener(e -> {
            String name = fld_region_hotelName.getText();
            String city = fld_romm_city.getText();
            String query = searchQueryRoom(name,city);
            ArrayList<Hotel>
        });

         */


        //Seçili Odaya Özellik ekleme metodu
        btn_room_features.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_room_id)){
                Helper.showMsg("fill");
                System.out.println("hata");
            }else{
                AddRoomFeatures features = new AddRoomFeatures();
            }
        });

        //Seçili Odaya Rezervasyon Yapma
        btn_reservation_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_room_id)){
                Helper.showMsg("error");
            }else{
                ReservationGUI res = new ReservationGUI();
            }
        });

        //*************ModelRoomListEnd*************


        //*************ReservationModelListStart*************
        mdl_reservation_list = new DefaultTableModel();
        Object[] col_reservationList = {"ID","Müşteri Adı","Telefon Numarası","E-mail","Müşteri Notu","Otel Adı","Oda Adı","GÜN","Toplam Ücret"};
        mdl_reservation_list.setColumnIdentifiers(col_reservationList);
        row_reservation_list = new Object[col_reservationList.length];
        loadReservationList();
        tbl_reservation_list.setModel(mdl_reservation_list);
        tbl_reservation_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_reservation_list.getTableHeader().setReorderingAllowed(false);
        //Seçilen bir satır üzerinden işlem yapabilme
        tbl_reservation_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                String selected_reservation_id = tbl_reservation_list.getValueAt(tbl_reservation_list.getSelectedRow(),0).toString();
                selected_reservationId = Integer.parseInt(selected_reservation_id); //seçtiğim otel
                fld_res_id.setText(selected_reservation_id);
            }catch (Exception exception){
            }
        });

        //Seçili Rezervasyonu Silme
        btn_res_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_res_id)){
                Helper.showMsg("error");
            }else{
                int reservation_id = Integer.parseInt(fld_res_id.getText());
                if (Reservation.delete(reservation_id)){
                    Helper.showMsg("done");
                    loadReservationModel();
                }
            }
        });
        ArrayList<Room> roomArrayList = new ArrayList<>();

        //Rezervasyon Listesini Yenileme
        btn_refresh_reservation.addActionListener(e -> {

            loadReservationModel();
        });
        //*************ReservationModelListEnd*************

        btn_sh_room.addActionListener(e -> {
            if (!Helper.isFieldEmpty(fld_region_hotelName) && ! Helper.isFieldEmpty(fld_romm_city)
                    && ! Helper.isFieldEmpty(fld_chec_in) && ! Helper.isFieldEmpty(fld_chec_out)) {
                if (roomArrayList.addAll(Room.search(fld_region_hotelName, fld_romm_city, fld_chec_in, fld_chec_out))){
                    Helper.showMsg("fill");
                }else {
                    Helper.showMsg("error");
                }
            }else{
                Helper.showMsg("error");
            }
        });
    }
    private void loadReservationModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reservation_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for(Reservation obj : Reservation.getList()){
            i = 0;
            row_reservation_list[i++] = obj.getId();
            row_reservation_list[i++] = obj.getClient_name();
            row_reservation_list[i++] = obj.getClient_phone();
            row_reservation_list[i++] = obj.getClient_email();
            row_reservation_list[i++] = obj.getClient_note();
            row_reservation_list[i++] = obj.getHotel() != null ? obj.getHotel().getName() : " ";
            row_reservation_list[i++] = obj.getRoom() != null ? obj.getRoom().getName() : " ";
            row_reservation_list[i++] = obj.getDay();
            row_reservation_list[i++] = obj.getTotal_price();
            mdl_reservation_list.addRow(row_reservation_list);
        }
    }
    private void loadReservationList(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reservation_list.getModel();
        clearModel.setRowCount(0);
        for(Reservation obj : Reservation.getList()){
            int i = 0;
            row_reservation_list[i++] = obj.getId();
            row_reservation_list[i++] = obj.getClient_name();
            row_reservation_list[i++] = obj.getClient_phone();
            row_reservation_list[i++] = obj.getClient_email();
            row_reservation_list[i++] = obj.getClient_note();
            row_reservation_list[i++] = Hotel.hotelName(obj.getHotel_id());
            row_reservation_list[i++] = Room.roomName(obj.getRoom_id());
            row_reservation_list[i++] = obj.getDay();
            row_reservation_list[i++] = obj.getTotal_price();
            mdl_reservation_list.addRow(row_reservation_list);
        }
    }
    private void loadRoomList() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        for (Room obj : Room.getList()) {
            int i = 0;
            row_room_list[i++] = obj.getId();
            row_room_list[i++] = obj.getName();
            row_room_list[i++] = Hotel.hotelName(obj.getHotel_id());
            row_room_list[i++] = Season.seasonName(obj.getSeason_id());
            row_room_list[i++] = Hostel.hostelName(obj.getHostel_id());
            row_room_list[i++] = obj.getStock();
            row_room_list[i++] = obj.getAdult_price();
            row_room_list[i++] = obj.getChild_price();
            mdl_room_list.addRow(row_room_list);
        }
    }
    private void loadRoomModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for(Room obj : Room.getList()) {
            i = 0;
            row_room_list[i++] = obj.getId();
            row_room_list[i++] = obj.getName();
            row_room_list[i++] = obj.getHotel() != null ? obj.getHotel().getName() : " ";
            row_room_list[i++] = obj.getSeason()!= null ? obj.getSeason().getName() : " ";
            row_room_list[i++] = obj.getHostel()!= null ? obj.getHostel().getName() : " ";
            row_room_list[i++] = obj.getStock();
            row_room_list[i++] = obj.getAdult_price();
            row_room_list[i++] = obj.getChild_price();
            mdl_room_list.addRow(row_room_list);
        }
    }
    private void loadRoomModel(ArrayList<Room> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for(Room obj : Room.getList()) {
            i = 0;
            row_room_list[i++] = obj.getId();
            row_room_list[i++] = obj.getName();
            row_room_list[i++] = obj.getHotel() != null ? obj.getHotel().getName() : " ";
            row_room_list[i++] = obj.getSeason()!= null ? obj.getSeason().getName() : " ";
            row_room_list[i++] = obj.getHostel()!= null ? obj.getHostel().getName() : " ";
            row_room_list[i++] = obj.getStock();
            row_room_list[i++] = obj.getAdult_price();
            row_room_list[i++] = obj.getChild_price();
            mdl_room_list.addRow(row_room_list);
        }
    }
    public void loadHotelModel(ArrayList<Hotel> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        for (Hotel obj : list) {
            int i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getRegion();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getEmail();
            row_hotel_list[i++] = obj.getPhone_number();
            row_hotel_list[i++] = obj.getStar();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }
    private void loadHotelList() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Hotel obj : Hotel.getList()){
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getRegion();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getEmail();
            row_hotel_list[i++] = obj.getPhone_number();
            row_hotel_list[i++] = obj.getStar();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

/*
    public void loadSeasonCombo(){
        cmb_hotel_name.removeAllItems();
        for(Hotel obj : Hotel.getList()){
            cmb_hotel_name.addItem(new Item(obj.getId(),obj.getName()));
        }
    }

 */
    public int getRoom_hotel_id_add() {
        return room_hotel_id_add;
    }
    public void setRoom_hotel_id_add(int room_hotel_id_add) {
        this.room_hotel_id_add = room_hotel_id_add;
    }
    public static int getSelected_hotelId() {
        return selected_hotelId;
    }
    public static void setSelected_hotelId(int selected_hotelId) {
        EmployeeGUI.selected_hotelId = selected_hotelId;
    }
    public static int getSelected_roomId() {
        return selected_roomId;
    }
    public static void setSelected_roomId(int selected_roomId) {
        EmployeeGUI.selected_roomId = selected_roomId;
    }

    public static String getSelectedSeasonName() {
        return selectedSeasonName;
    }

    public static void setSelectedSeasonName(String selectedSeasonName) {
        EmployeeGUI.selectedSeasonName = selectedSeasonName;
    }

    public static int getSelectedAdultPrice() {
        return selectedAdultPrice;
    }

    public static void setSelectedAdultPrice(int selectedAdultPrice) {
        EmployeeGUI.selectedAdultPrice = selectedAdultPrice;
    }

    public static int getSelectedChildPrice() {
        return selectedChildPrice;
    }

    public static void setSelectedChildPrice(int selectedChildPrice) {
        EmployeeGUI.selectedChildPrice = selectedChildPrice;

    }

    public static String getSelectedHostelType() {
        return selectedHostelType;
    }

    public static void setSelectedHostelType(String selectedHostelType) {
        EmployeeGUI.selectedHostelType = selectedHostelType;
    }
}