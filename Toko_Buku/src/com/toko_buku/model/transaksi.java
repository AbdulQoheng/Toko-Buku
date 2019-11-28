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
public class transaksi {
    private String kodebuku;
    private String nama;
    private String jenis;
    private String harga;
    private String jumlah;
    private String totalharga;

    public String getKodebuku() {
        return kodebuku;
    }

    public void setKodebuku(String kodebuku) {
        this.kodebuku = kodebuku;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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

    public void setTotalharga() {
        int totalharga1 = Integer.parseInt(harga)*Integer.parseInt(jumlah);
        this.totalharga = String.valueOf(totalharga1);
    }
    
}
