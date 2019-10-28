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
public class user {
    private static String userid;
    private static String nama;
    private static String ttl;
    private static String bagianuser;
    private static String password;

    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
        user.userid = userid;
    }

    public static String getNama() {
        return nama;
    }

    public static void setNama(String nama) {
        user.nama = nama;
    }

    public static String getTtl() {
        return ttl;
    }

    public static void setTtl(String ttl) {
        user.ttl = ttl;
    }

    public static String getBagianuser() {
        return bagianuser;
    }

    public static void setBagianuser(String bagianuser) {
        user.bagianuser = bagianuser;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        user.password = password;
    }
    
    
    
}
