/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.database.koneksi;
import com.toko_buku.model.buku;
import com.toko_buku.model.implement.implementTransaksi;
import com.toko_buku.model.transaksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qoheng
 */
public class transaksiDAO implements implementTransaksi{
    private List<buku> list;
    private List<transaksi> listtransaksi;
    
    @Override
    public List<buku> ambilnamabuku() {
        list = new ArrayList<buku>();
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "select nama_buku from buku");
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                buku buku = new buku();
                buku.setNama(result.getString("nama_buku"));
                list.add(buku);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    @Override
    public List<transaksi> getAll(String namabuku, String jumlah){
        listtransaksi = new ArrayList<transaksi>();
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "select* from buku where nama_buku = '"+namabuku+"'");
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                transaksi transaksi = new transaksi();
                transaksi.setKodebuku(result.getString("kodebuku"));
                transaksi.setNama(result.getString("nama_buku"));
                transaksi.setJenis(result.getString("jenis_buku"));
                transaksi.setHarga(result.getString("harga"));
                transaksi.setJumlah(jumlah);
                transaksi.setTotalharga();
                listtransaksi.add(transaksi);
            }

            return listtransaksi;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    
    
    }
}