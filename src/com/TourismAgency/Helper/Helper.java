package com.TourismAgency.Helper;
import javax.swing.*;
import java.awt.*;
public class Helper {
    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException  | InstantiationException | IllegalAccessException |UnsupportedLookAndFeelException e ) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }
    // JOptionPane dilini Türkçe'ye ayarlar
    public static void optionPageTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");

    }
    // JTextField'in boş olup olmadığını kontrol eder
    public static boolean isFieldEmpty (JTextField field) {
        // field.getText().trim().isEmpty();
        return field.getText().trim().isEmpty();
    }
    // Kullanıcıya mesaj gösterir
    public static void showMsg(String str) {
        optionPageTR();
        String msg;
        String title;
        switch (str){
            case "fill":
                msg = "Lütfen tüm alanları doldurun !";
                title = "Hata!";
                break;
            case "done":
                msg = "İşlem Başarılı !";
                title = "Sonuç";
                break;
            case "eror":
                msg = "Bir Hata Oluştu !";
                title = "Hata";
                break;
            default:
                msg = str;
                title = "Mesaj";
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Pencereyi ekranın ortasına yerleştirir
    public static int screenCenterPoint(String axis, Dimension size) {
        int point = 0;
        switch (axis) {
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width-size.width)/2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height-size.height)/2;
                break;
            default:
                point =0;
                break;
        }
        return point;
    }

    // Kullanıcıya onay mesajı gösterir
    public static boolean confirm(String str){
        optionPageTR();
        String msg;
        switch (str){
            case "sure":
                msg = "Bu işlemden emin misiniz?";
                break;
            default:
                msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Son Kararın mı?", JOptionPane.YES_NO_OPTION) == 0;
    }
}