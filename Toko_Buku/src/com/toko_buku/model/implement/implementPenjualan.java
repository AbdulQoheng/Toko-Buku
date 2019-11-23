/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.implement;

import com.toko_buku.model.penjualan;
import java.util.List;

/**
 *
 * @author qoheng
 */
public interface implementPenjualan {
    
    public List<penjualan> getAllpenjualan(); 

    public List<penjualan> getcari(String idstruk, String userkasir);

    public boolean delete(String kodeStruk);
    
}
