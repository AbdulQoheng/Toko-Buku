/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.tabel;

import com.toko_buku.model.buku;
import com.toko_buku.model.transaksi;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author qoheng
 */
public class TabelModelTransaksi extends AbstractTableModel {

    private List<transaksi> list;

    public TabelModelTransaksi(List<transaksi> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getKodebuku();
            case 1:
                return list.get(rowIndex).getNama();
            case 2:
                return list.get(rowIndex).getJenis();
            case 3:
                return list.get(rowIndex).getHarga();
            case 4:
                return list.get(rowIndex).getJumlah();
            case 5:
                return list.get(rowIndex).getTotalharga();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "KODE BUKU";
            case 1:
                return "NAMA";
            case 2:
                return "JENIS";
            case 3:
                return "HARGA";
            case 4:
                return "Jumlah";
            case 5:
                return "Total Harga";
            default:
                return null;
        }
    }

}

