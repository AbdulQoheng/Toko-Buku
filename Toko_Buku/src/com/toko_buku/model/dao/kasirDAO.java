/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.model.implement.implementkasir;
import com.toko_buku.database.koneksi;
import com.toko_buku.model.user;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qoheng
 */
public class kasirDAO implements implementkasir {

    private List<user> list;

    @Override
    public List<user> getAllkasir() {
        list = new ArrayList<user>();
        try {
            
            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select* from kasir");

            while (result.next()) {
                user user = new user();
                user.setUserid(result.getString("userkasir"));
                user.setNama(result.getString("nama"));
                user.setTtl(result.getString("ttl"));
                user.setPassword(result.getString("password"));
                list.add(user);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

}
