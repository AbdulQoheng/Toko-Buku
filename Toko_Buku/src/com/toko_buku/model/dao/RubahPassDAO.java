/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.dao;

import com.toko_buku.database.koneksi;
import com.toko_buku.model.implement.implementRubahPass;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author qoheng
 */
public class RubahPassDAO implements implementRubahPass{

    @Override
    public boolean rubahpass(String userid, String passbaru) {
       
        try {
            
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
            "update admin set password = ? where useradmin = ?");
            
            statement.setString(1, passbaru);
            statement.setString(2, userid);
            statement.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }
    
}
