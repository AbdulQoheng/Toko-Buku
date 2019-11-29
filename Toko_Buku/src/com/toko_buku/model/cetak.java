/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model;

import javax.swing.JOptionPane;

/**
 *
 * @author qoheng
 */
public class cetak {
    private String tampilanawal;
    private String tampiltengah;
    private String tampilanbawah;

    protected String getTampilanawal() {
        return tampilanawal;
    }

    protected void setTampilanawal(String tampilanawal) {
        this.tampilanawal = tampilanawal;
    }

    protected String getTampiltengah() {
        return tampiltengah;
    }

    protected void setTampiltengah(String tampiltengah) {
        this.tampiltengah = tampiltengah;
    }

    protected String getTampilanbawah() {
        return tampilanbawah;
    }

    protected void setTampilanbawah(String tampilanbawah) {
        this.tampilanbawah = tampilanbawah;
    }
    
    protected void warning (String pesan){
        JOptionPane.showMessageDialog(null, pesan, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    protected void informasi (String pesan){
        JOptionPane.showMessageDialog(null, pesan, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
    
    protected boolean konfirmasi(String pesan){
        if (JOptionPane.showConfirmDialog(null, pesan, "Warning", 2) == JOptionPane.YES_OPTION){
            return true;
        }
        return false;
    }
    
}
