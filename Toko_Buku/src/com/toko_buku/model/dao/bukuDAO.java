/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.database.koneksi;
import com.toko_buku.model.buku;
import com.toko_buku.model.implement.implementBuku;
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
public class bukuDAO implements implementBuku{
    private List<buku> list;
    
    @Override
    public List<buku> getAll(){
        list = new ArrayList<buku>();
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "select* from buku");
            
            ResultSet result = statement.executeQuery();
            
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

    @Override
    public boolean insert(String kodebuku, String nama, String jenis, String harga, String stok) {
        
        try {
            
            int bukuke = jumlahdata() + 1;
            
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "insert into buku values (?,?,?,?,?,?)");
            
            statement.setString(1, kodebuku);
            statement.setString(2, nama);
            statement.setString(3, jenis);
            statement.setString(4, harga);
            statement.setString(5, stok);
            statement.setInt(6, bukuke);
            
            statement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    @Override
    public int jumlahdata() {
        int jumlah = 0;
        try {
            
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "SELECT max(bukuke) as jumlah FROM buku");
            
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

    @Override
    public boolean delete(String kodebuku) {
        
        try {
            
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "delete from buku where kodebuku =?");
            
            statement.setString(1, kodebuku);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(String kodebuku, String nama, String jenis, String harga, String stok) {
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "update buku set nama_buku = ? ,jenis_buku = ? ,harga = ? ,stok = ? where kodebuku = ?");
            
            statement.setString(1, nama);
            statement.setString(2, jenis);
            statement.setString(3, harga);
            statement.setString(4, stok);
            statement.setString(5, kodebuku);
            statement.executeUpdate();
       
            return true;
        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    @Override
    public List<buku> getcari(String kodebuku, String nama, String jenis, String harga, String stok) {
        list = new ArrayList<buku>();
        try {
            
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "select* from buku where kodebuku like ?"
                    + " or nama_buku like ?"
                    + " or jenis_buku like ?"
                    + " or harga like ?"
                    + " or stok like ?");
            
            statement.setString(1, kodebuku);
            statement.setString(2, nama);
            statement.setString(3, jenis);
            statement.setString(4, harga);
            statement.setString(5, stok);
            
            ResultSet result = statement.executeQuery();

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
