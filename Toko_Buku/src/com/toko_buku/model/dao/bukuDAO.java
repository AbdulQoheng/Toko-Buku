/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.database.koneksi;
import com.toko_buku.model.buku;
import com.toko_buku.model.implement.implementBuku;
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
public class bukuDAO implements implementBuku{
    private List<buku> list;
    
    @Override
    public List<buku> getAll(){
        list = new ArrayList<buku>();
        try {
            
            com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select* from buku");

            while (result.next()) {
                buku buku = new buku();
                buku.setKodebuku(result.getString("kodebuku"));
                buku.setNama(result.getString("nama_buku"));
                buku.setJenis(result.getString("jenis_buku"));
                buku.setHarga(result.getString("harga"));
                buku.setStok(result.getString("stok"));
                list.add(buku);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
