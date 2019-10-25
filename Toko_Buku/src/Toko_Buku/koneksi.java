/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toko_Buku;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author qoheng
 */
public class koneksi {
    private static Connection mysqlkonek;
    public static void main(String[] args) throws SQLException{
        koneksiDB();
    }
    
    public static Connection koneksiDB() throws SQLException {
        if(mysqlkonek==null){
            try {
                String DB="jdbc:mysql://localhost:3306/tokobuku"; // tokobuku database
                String user="root"; // user database
                String pass=""; // password database
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                mysqlkonek = (Connection) DriverManager.getConnection(DB,user,pass);
          
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Koneksi Gagal");
            }
        }
        return mysqlkonek;
    }
    
}
