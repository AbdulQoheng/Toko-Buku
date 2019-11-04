/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.tabel;

import com.toko_buku.model.user;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author qoheng
 */
public class TabelModelKasir extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<user> list;

    public TabelModelKasir(List<user> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getUserid();
            case 1:
                return list.get(rowIndex).getNama();
            case 2:
                return list.get(rowIndex).getTtl();
            case 3:
                return list.get(rowIndex).getPassword();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "USER ID";
            case 1:
                return "NAMA";
            case 2:
                return "TTL";
            case 3:
                return "PASSWORD";
            default:
                return null;
        }
    }

}
