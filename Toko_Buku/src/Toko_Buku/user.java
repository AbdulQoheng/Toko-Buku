/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toko_Buku;



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
    

    protected user(String userid, String pass) {
        user.userid = userid;
        user.password = pass;
    }
    
    protected user(){
        
    }

    protected static String getUserid() {
        return userid;
    }

    protected static void setUserid(String userid) {
        user.userid = userid;
    }

    protected static String getNama() {
        return nama;
    }

    protected static void setNama(String nama) {
        user.nama = nama;
    }

    protected static String getTtl() {
        return ttl;
    }

    protected static void setTtl(String ttl) {
        user.ttl = ttl;
    }

    public static String getBagianuser() {
        return bagianuser;
    }

    public static void setBagianuser(String bagianuser) {
        user.bagianuser = bagianuser;
    }

    
    
    protected static String getPassword() {
        return password;
    }

    protected static void setPassword(String password) {
        user.password = password;
    }
    
}
