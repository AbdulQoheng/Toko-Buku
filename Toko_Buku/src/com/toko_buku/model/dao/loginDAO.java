/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.model.implement.implementLogin;
import com.toko_buku.database.koneksi;
import com.toko_buku.model.login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author qoheng
 */
public class loginDAO implements implementLogin {

    @Override
    public boolean masukadmin(String userid, String pass) {
       
        try {

            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "select useradmin, password from admin where useradmin = ? and password = ?");
            statement.setString(1, userid);
            statement.setString(2, pass);
            ResultSet result = statement.executeQuery();
            
            if (result.next()){
                login.setUserid(userid);
                login.setPass(pass);
                login.setBagian("admin");
                login.setStatus("aktif");
                return true;      
            
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean masukkasir(String userid, String pass) {
       
        try {

            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "select userkasir, password from kasir where userkasir = ? and password = ?");
            statement.setString(1, userid);
            statement.setString(2, pass);
            ResultSet result = statement.executeQuery();
            
            if (result.next()){
                login.setBagian("kasir");
                return true;      
            
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
