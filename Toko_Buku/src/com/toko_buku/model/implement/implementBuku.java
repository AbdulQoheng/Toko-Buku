/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.implement;

import com.toko_buku.model.buku;
import java.util.List;

/**
 *
 * @author qoheng
 */
public interface implementBuku {
    
    public List<buku> getAll();

    public boolean insert(String kodebuku, String nama, String jenis, String harga, String stok);

    public int jumlahdata();

    public boolean delete(String kodebuku);

    public boolean update(String kodebuku, String nama, String jenis, String harga, String stok);

    public List<buku> getcari(String kodebuku, String nama, String jenis, String harga, String stok);
    
}
