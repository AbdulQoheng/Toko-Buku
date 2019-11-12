/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.database.koneksi;
import com.toko_buku.model.detail;
import com.toko_buku.model.implement.implementDetail;
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
    public List<detail> getAll(){
        list = new ArrayList<detail>();
        try {
            
            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select iddetail, kodebuku, nama_buku, harga, jumlah from detail_struck natural join buku where = ");

            while (result.next()) {
                detail detail = new detail();
                detail.setIddetail(result.getString(""));
                detail.setKodeBuku(result.getString(""));
                detail.setNamaBuku(result.getString(""));
                detail.setHarga(result.getString(""));
                detail.setJumlah(result.getString(""));
                detail.setTotalharga(result.getString(""));
                
                list.add(detail);
            }
            return list;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
