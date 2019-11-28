/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.model.implement.implementUser;
import com.toko_buku.database.koneksi;
import com.toko_buku.model.user;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qoheng
 */
public class userDAO implements implementUser{
    private List<user> list;

    @Override
    public List<user> datauser(String userid) {
        list = new ArrayList<user>();
        try {
            user user= new user();

            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select* from admin where useradmin='" + userid + "'");
            Statement stmt1 = conn.createStatement();
            ResultSet result1 = stmt1.executeQuery("select* from kasir where userkasir='" + userid + "'");

            if (result.next()) {
                user.setUserid(result.getString("useradmin"));
                user.setNama(result.getString("nama"));
                user.setTtl(result.getString("ttl"));
                user.setPassword(result.getString("password"));
                list.add(user);
                return list;
                
            } else if (result1.next()) {
                user.setUserid(result1.getString("userkasir"));
                user.setNama(result1.getString("nama"));
                user.setTtl(result1.getString("ttl"));
                user.setPassword(result1.getString("password"));
                list.add(user);
                return list;
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    @Override
    public boolean rubahprof(String userid, String nama, String ttl) {
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "update admin set nama = ? ,ttl  = ? where useradmin = ?");
            
            statement.setString(1, nama);
            statement.setString(2, ttl);
            statement.setString(3, userid);
            statement.executeUpdate();
       
            return true;
        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
}
