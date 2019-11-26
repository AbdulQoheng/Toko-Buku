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
public interface implementUser {
    
    public List<user> datauser(String userid);
    
    public boolean rubahprof(String userid, String nama, String ttl);
    
}
