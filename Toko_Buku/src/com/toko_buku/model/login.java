/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model;

/**
 *
 * @author qoheng
 */
public class login {
    public static String userid;
    public static String pass;
    public static String bagian;
    

    protected static String getUserid() {
        return userid;
    }

    protected static void setUserid(String userid) {
        login.userid = userid;
    }

    protected static String getPass() {
        return pass;
    }

    protected static void setPass(String pass) {
        login.pass = pass;
    }    

    protected static String getBagian() {
        return bagian;
    }

    protected static void setBagian(String bagian) {
        login.bagian = bagian;
    }
    
    
    
}
