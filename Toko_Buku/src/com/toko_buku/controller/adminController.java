/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.dao.implementUser;
import com.toko_buku.model.user;
import com.toko_buku.view.FormAdmin;


/**
 *
 * @author qoheng
 */
public class adminController extends user{
    private static FormAdmin adminpanel;
    private static implementUser implementuser;
    
    public adminController(FormAdmin adminpanel){
        this.adminpanel = adminpanel;
        
    }
}
