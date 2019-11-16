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
       
        try{
            String sql = "update admin set "
                    +"password ='"+passbaru
                    +"' where useradmin = '"+userid+"'";
            
//            String sql1 = "update kasir set "
//                    +",password ='"+passbaru
//                    +"'where userkasir= '"+userid+"'";
            Connection conn = (Connection)koneksi.koneksiDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
//            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt.execute();
//            stmt1.execute();
            return  true;
        }catch (SQLException | HeadlessException e){
            e.printStackTrace();
        }
        return false;
    }
    
}
