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
    
    public static boolean ceklogin(){
        if (getUserid() != null){
            return true;
        }else{
            return false;
        }
        
    }
    
}
