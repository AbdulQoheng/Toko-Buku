/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.database.koneksi;
import com.toko_buku.model.detail;
import com.toko_buku.model.implement.implementDetail;
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
public class DetailDAO implements implementDetail{
    private List<detail> list;
    @Override
    public List<detail> getAll(String kodestruk){
        list = new ArrayList<detail>();
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                    "select detail_struck.iddetail, detail_struck.kodebuku, buku.nama_buku, buku.harga, detail_struck.total_harga, detail_struck.jumlah "
                    + "from detail_struck natural join buku  "
                    + "where buku.kodebuku = detail_struck.kodebuku "
                    + "and detail_struck.kodestruck = ?");
            
            statement.setString(1, kodestruk);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                detail detail = new detail();
                detail.setKodeBuku(result.getString("kodebuku"));
                detail.setNamaBuku(result.getString("nama_buku"));
                detail.setHarga(result.getString("harga"));
                detail.setJumlah(result.getString("jumlah"));
                detail.setTotalharga(result.getString("total_harga"));
                
                list.add(detail);  
            }
            statement.close();
            return list;
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public int getjumlah(String kodekasir) {
       int jumlah = 0;
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "select sum(total_harga) as jumlah from detail_struck where kodestruck = ?");
            
            statement.setString(1, kodekasir);
            ResultSet result = statement.executeQuery();
            
            if (result.next()){
                jumlah = result.getInt("jumlah");
            }
            
            return jumlah;
        } catch (Exception e) {
            e.printStackTrace();
            return jumlah;
        }
    }
    
}
