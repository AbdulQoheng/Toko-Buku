/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.implement;

import com.toko_buku.model.user;
import java.util.List;

/**
 *
 * @author qoheng
 */
public interface implementkasir {
    
    public List<user> getAllkasir();

    public List<user> getcari(String userkasir, String nama, String ttl, String pass);

    public void insert(String userkasir, String nama, String ttl, String pass);

    public int jumlahdata();

    public void delete(String userid);

    public void update(String userkasir, String nama, String ttl, String pass);
    
}
