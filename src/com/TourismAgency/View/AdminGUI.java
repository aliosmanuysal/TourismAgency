package com.TourismAgency.View;

import com.TourismAgency.Helper.Config;
import com.TourismAgency.Helper.Helper;
import com.TourismAgency.Model.User;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_user;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_email;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JTextField fls_sh_user_name;
    private JTextField fld_sh_user_uname;
    private JComboBox cmb_sh_user_type;
    private JButton btn_user_sh;
    private JButton btn_refresh_admin;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;


    public AdminGUI(){

        add(wrapper);
        setSize(1000,600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(true);
        setVisible(true);


        //ModelUserList
        mdl_user_list = new DefaultTableModel(){
            @Override // Tablodaki id değerini değiştirilemez yapar.
            public boolean isCellEditable(int row, int column) {
                if(column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID" , "Ad Soyad" , "Kullanıcı Adı" ,"email", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        loadUserModel();
        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        //Seçilen bir satır üzerinden işlem yapabilme
        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selected_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString();
                fld_user_id.setText(selected_user_id);
            }catch (Exception exception){
            }
        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE){
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString();
                String user_uname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();
                String user_email = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();
                String user_pass = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();
                String user_type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),5).toString();

                if(User.update(user_id, user_name, user_uname, user_email, user_pass, user_type)){
                    Helper.showMsg("done");

                }else {
                    Helper.showMsg("error");
                }
                loadUserModel();
            }
        });

        //Kullanıcı ekleme
        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname)|| Helper.isFieldEmpty(fld_user_email) || Helper.isFieldEmpty(fld_user_pass)){
                Helper.showMsg("fill");
            }else{
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String email = fld_user_email.getText();
                String pass = fld_user_pass.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if (User.add(name,uname,email,pass,type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    // ekleme işlemi başarılı olduktan sonra textfld'ları temizler.
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_email.setText(null);
                    fld_user_pass.setText(null);
                }
            }
        });
        //kullanıcı id'sine göre silme
        btn_user_delete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_user_id)){
                Helper.showMsg("fill");
            }else {
                int user_id = Integer.parseInt(fld_user_id.getText());
                if(User.delete(user_id)){
                    Helper.showMsg("done");
                    loadUserModel(); // silme işleminden sonra tablonun günceli oluşur.

                }else {
                    Helper.showMsg("error");
                }
            }
        });
        //Çıkış yap metodu
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });
        //kulllanıcıyı arama yapma metodu
        btn_user_sh.addActionListener(e -> {
            String name = fls_sh_user_name.getText();
            String uname = fld_sh_user_uname.getText();
            String type = cmb_sh_user_type.getSelectedItem().toString();
            String query = User.searchQuery(name,uname,type);
            ArrayList<User> searchingUser = User.searchUserList(query);
            loadUserModel(searchingUser);
        });
        //Ekran yenilenir
        btn_refresh_admin.addActionListener(e ->
                loadUserModel());
    }
    public void loadUserModel(ArrayList<User> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for (User obj : list) {
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getEmail();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadUserModel(){
        //Manuel eklenen yeni kullanıcıyı tabloda gösterir
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for(User obj : User.getList()){
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getEmail();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }

    }
}

