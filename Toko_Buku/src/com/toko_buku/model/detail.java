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
public class detail {
    private static String kodestruk;
    private String kodeBuku;
    private String namaBuku;
    private String harga;
    private String jumlah;
    private String totalharga;

    public static String getKodestruk() {
        return kodestruk;
    }

    public static void setKodestruk(String kodestruk) {
        detail.kodestruk = kodestruk;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public void setKodeBuku(String kodeBuku) {
        this.kodeBuku = kodeBuku;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTotalharga() {
        return totalharga;
    }

    public void setTotal(){
        int jumlah = Integer.parseInt(this.harga)*Integer.parseInt(this.jumlah);
        this.totalharga = String.valueOf(jumlah);
    }
    
    public void setTotalharga(String totalharga){
        this.totalharga = totalharga;
    }
}
