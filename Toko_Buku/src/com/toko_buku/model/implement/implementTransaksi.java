/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.implement;

import com.toko_buku.model.buku;
import com.toko_buku.model.transaksi;
import java.util.List;

/**
 *
 * @author qoheng
 */
public interface implementTransaksi {
    
    public List<String> ambilnamabuku ();
    
    public List<transaksi> getAll(String kodebu, String jumlah);
    
    public int jumlahdata();
    
    public void insertstruk(String kodestruk, String tanggal, String waktu, String userkasir, String totalbayar, String uangbayar, String uangkembali);
    
    public void insertdetail(String jumlah, String totalharga, String kodebuku, String kodestruk);
    
    public String ambilstok(String namabarang);
    
}