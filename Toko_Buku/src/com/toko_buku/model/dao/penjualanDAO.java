/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.database.koneksi;
import com.toko_buku.model.implement.implementPenjualan;
import com.toko_buku.model.penjualan;
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
public class penjualanDAO implements implementPenjualan{
    
    private List<penjualan> list;

    @Override
    public List<penjualan> getAllpenjualan() {
        list = new ArrayList<penjualan>();
        try {
            
            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select* from struk");

            while (result.next()) {
                penjualan penjualan = new penjualan();
                penjualan.setKodeStruk(result.getString("kodestruk"));
                penjualan.setTanggal(result.getString("tanggal"));
                penjualan.setWaktu(result.getString("waktu"));
                penjualan.setTotalbayar(result.getString("total_bayar"));
                penjualan.setUangbayar(result.getString("uangbayar"));
                penjualan.setUangkembali(result.getString("uangkembali"));
                penjualan.setUserKasir(result.getString("userkasir"));
                list.add(penjualan);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public List<penjualan> getcari(String idstruk, String userkasir) {
        list = new ArrayList<penjualan>();
        try {
            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select* from struk where kodestruk like '%" 
                    + idstruk + "%' or userkasir like '%" 
                    + userkasir + "%'");

            while (result.next()) {
                penjualan penjualan = new penjualan();
                penjualan.setKodeStruk(result.getString("kodestruk"));
                penjualan.setTanggal(result.getString("tanggal"));
                penjualan.setWaktu(result.getString("waktu"));
                penjualan.setUserKasir(result.getString("userkasir"));
                list.add(penjualan);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(String kodeStruk) {
        try {
            String sql = "delete from struk where kodestruk ='" + kodeStruk + "'";

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
