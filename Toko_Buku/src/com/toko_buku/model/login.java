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
    public static String status;
    public static String namatoko = "JAYA ABADI";
    

    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
        login.userid = userid;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        login.pass = pass;
    }    

    public static String getBagian() {
        return bagian;
    }

    public static void setBagian(String bagian) {
        login.bagian = bagian;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        login.status = status;
    }

    public static String getNamatoko() {
        return namatoko;
    }
    
    
    
    public static void logout(){
        login.setStatus(null);
        login.setBagian(null);
        login.setUserid(null);
        login.setPass(null);
    }
    
    
    
}
