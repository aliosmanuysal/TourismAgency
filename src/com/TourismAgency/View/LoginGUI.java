package com.TourismAgency.View;
import com.TourismAgency.Helper.Config;
import com.TourismAgency.Helper.Helper;
import com.TourismAgency.Model.User;
import javax.swing.*;

public class LoginGUI extends JFrame {
    private User user = new User();
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_uname;
    private JTextField fld_user_pass;
    private JButton btn_login;

    public LoginGUI () {
        add(wrapper);
        setSize(500,500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_user_pass)) {
                Helper.showMsg("fill");
            } else {
                User u = User.getCredentials(fld_uname.getText(), fld_user_pass.getText());
                if (u != null) {
                    switch (u.getType()) {
                        case "admin":
                            AdminGUI adGUI = new AdminGUI();
                            adGUI.setVisible(true);
                            dispose();
                            break;
                        case "employee":
                            EmployeeGUI empGUI = new EmployeeGUI();
                            empGUI.setVisible(true);
                            dispose();
                            break;
                        default:
                            break;
                    }
                }else{
                    Helper.showMsg("error");
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI loginGUI = new LoginGUI();

    }

}