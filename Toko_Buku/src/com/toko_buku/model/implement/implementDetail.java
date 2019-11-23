/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.implement;

import com.toko_buku.model.detail;
import java.util.List;

/**
 *
 * @author qoheng
 */
public interface implementDetail {
    
    public List<detail> getAll(String kodestruk);

    public int getjumlah(String kodestruk);
    
}
