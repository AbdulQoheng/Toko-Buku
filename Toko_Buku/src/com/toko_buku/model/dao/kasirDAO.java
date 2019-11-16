/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.model.implement.implementkasir;
import com.toko_buku.database.koneksi;
import com.toko_buku.model.user;
import java.sql.Connection;
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
public class kasirDAO implements implementkasir {

    private List<user> list;

    @Override
    public List<user> getAllkasir() {
        list = new ArrayList<user>();
        try {

            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select userkasir, nama ,ttl , password from kasir ORDER BY userkasir");

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

    @Override
    public List<user> getcari(String userkasir, String nama, String ttl, String pass) {
        list = new ArrayList<user>();
        try {
            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select* from kasir where userkasir like '%" + userkasir + "%' or nama like '%" + nama + "%' or ttl like '%" + ttl + "%' or password like '%" + pass + "%'");

            while (result.next()) {
                user user = new user();
                user.setUserid(result.getString("userkasir"));
                user.setNama(result.getString("nama"));
                user.setTtl(result.getString("ttl"));
                user.setPassword(result.getString("password"));
                list.add(user);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int jumlahdata() {
        int jumlah = 0;
        try {
            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT max(kasirke) as jumlah FROM kasir");
            
            if (result.next()){
                
                jumlah = result.getInt("jumlah");
                
            }
            

            return jumlah;
        } catch (Exception e) {
            e.printStackTrace();
            return jumlah;
        }

    }

    @Override
    public boolean insert(String userkasir, String nama, String ttl, String pass) {
        try {
            int kasirke = jumlahdata() + 1;
            String sql = "insert into kasir "
                    + " values ("
                    + "'" + userkasir + "', "
                    + "'" + nama + "',"
                    + "'" + ttl + "',"
                    + "'" + pass + "',"
                    + "'" + kasirke + "'"
                    + ")";

            Connection conn = (Connection) koneksi.koneksiDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String userid) {
        try {
            String sql = "delete from kasir where userkasir ='" + userid + "'";

            Connection conn = (Connection) koneksi.koneksiDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(String userkasir, String nama, String ttl, String pass) {
        try {
            String sql = "update kasir set "
                    +"nama ='"+nama
                    +"',ttl ='"+ttl
                    +"',password ='"+pass
                    +"'where userkasir = '"+userkasir+"'";

            Connection conn = (Connection) koneksi.koneksiDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    
    }

}
