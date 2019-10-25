/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toko_Buku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author qoheng
 */
public class fungsi extends user {

    public fungsi() {

    }
    
    protected void login(String userid, String pass){
        try {
            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select useradmin, password from admin where useradmin='" + userid + "' and password='" + pass + "'");
            Statement stmt1 = conn.createStatement();
            ResultSet result1 = stmt1.executeQuery("select userkasir, password from kasir where userkasir='" + userid + "' and password='" + pass + "'");

            if (result.next()) {
                if (userid.equals(result.getString("useradmin")) && pass.equals(result.getString("password"))) {
                    fungsi ob = new fungsi();
                    ob.inputuser(userid, pass);
                    FormAdmin n = new FormAdmin();
                    n.setVisible(true);
                    
                }

            }else if(result1.next()){
                if (userid.equals(result1.getString("userkasir")) && pass.equals(result1.getString("password"))) {
                    fungsi ob = new fungsi();
                    ob.inputuser(userid, pass);
                    FormKasir n = new FormKasir();
                    n.setVisible(true);
                    
                }
            }else {
                JOptionPane.showMessageDialog(null, "Maaf!, Masuukkan user dan password lagi");
                new login().setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    protected void inputuser(String userid, String pass) {
        try {
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select* from admin where useradmin='" + userid + "' and password='" + pass + "'");
            Statement stmt1 = conn.createStatement();
            ResultSet result1 = stmt1.executeQuery("select* from kasir where userkasir='" + userid + "' and password='" + pass + "'");

            if (result.next()) {
                if (userid.equals(result.getString("useradmin")) && pass.equals(result.getString("password"))) {
                    user.setUserid(result.getString("useradmin"));
                    user.setNama(result.getString("nama"));
                    user.setTtl(result.getString("ttl"));
                    user.setBagianuser("admin");
                    user.setPassword(result.getString("password"));
                }

            } else if (result1.next()) {
                if (userid.equals(result1.getString("userkasir")) && pass.equals(result1.getString("password"))) {
                    user.setUserid(result1.getString("userkasir"));
                    user.setNama(result1.getString("nama"));
                    user.setTtl(result1.getString("ttl"));
                    user.setBagianuser("kasir");
                    user.setPassword(result1.getString("password"));
                }
            } else {

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    protected void rubahpass(String userid, String passlama, String passbaru, String passkonf) {
        if (passbaru.equals(passkonf) && user.getPassword().equals(passlama)) {
            try {
                String sql = ("update admin set password ='" + passbaru + "' where useradmin ='" + userid + "'and password ='"+passlama +"'");
                String sql1 = ("update kasir set password ='" + passbaru + "' where userkasir ='" + userid + "'and password ='"+passlama +"'");
                
                Connection conn = (Connection)koneksi.koneksiDB();
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt.execute();
                stmt1.execute();
                
                JOptionPane.showMessageDialog(null, "Passowrd telah di ganti!");
                
                fungsi ob = new fungsi();
                ob.inputuser(userid, passbaru);
                
                if (user.getBagianuser().equals("admin")) {
                    FormAdmin n = new FormAdmin();
                    n.setVisible(true);
                }else{
                    FormKasir n = new FormKasir();
                    n.setVisible(true);
                }
                
                
                
                
                    
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                new RubahPass(userid).setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Perikasa password");
            RubahPass n = new RubahPass(userid);
            n.setVisible(true);
        }

    }
    
    protected void rubahprof(String useridbaru, String namabaru, String ttlbaru){
            try {
                String sql = ("update admin set useradmin ='"+useridbaru+"', nama ='" + namabaru + "', ttl ='" + ttlbaru +"' where useradmin ='" + user.getUserid() + "'");
                String sql1 = ("update kasir set userkasir ='"+useridbaru+"', nama ='" + namabaru + "', ttl ='"+ ttlbaru +"' where userkasir ='" + user.getUserid() + "'");
                
                Connection conn = (Connection)koneksi.koneksiDB();
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt.execute();
                stmt1.execute();
                
                JOptionPane.showMessageDialog(null, "Profile telah di ganti!");
                
                fungsi ob = new fungsi();
                ob.inputuser(useridbaru, user.getPassword());
                    
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                
            }
        
    }

}
