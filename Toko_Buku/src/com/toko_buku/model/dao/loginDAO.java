/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.model.implement.implementLogin;
import com.toko_buku.database.koneksi;
import com.toko_buku.model.login;
import com.toko_buku_view.admin.FormAdmin;
import com.toko_buku.view.kasir.FormKasir;
import com.toko_buku.view.FormLogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author qoheng
 */
public class loginDAO implements implementLogin {

    @Override
    public int masuk(String userid, String pass) {
        int nilai = 0;
        try {

            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select useradmin, password from admin where useradmin='" + userid + "' and password='" + pass + "'");
            Statement stmt1 = conn.createStatement();
            ResultSet result1 = stmt1.executeQuery("select userkasir, password from kasir where userkasir='" + userid + "' and password='" + pass + "'");

            if (result.next()) {
                if (userid.equals(result.getString("useradmin")) && pass.equals(result.getString("password"))) {
                    login.setUserid(userid);
                    login.setPass(pass);
                    login.setBagian("admin");
                    nilai = 1;
                    
                }

            } else if (result1.next()) {
                if (userid.equals(result1.getString("userkasir")) && pass.equals(result1.getString("password"))) {
                    login.setUserid(userid);
                    login.setPass(pass);
                    login.setBagian("kasir");
                    nilai = 2;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nilai;
    }

}
