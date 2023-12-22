package com.example.ontapandroid2_5.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ontapandroid2_5.DTO.DichVuDTO;
import com.example.ontapandroid2_5.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class DichVuDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;

    public DichVuDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<DichVuDTO> getList() {
        ArrayList<DichVuDTO> listDV = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from dichvu", null);
        if (c.getCount()>0&&c!=null) {
            c.moveToFirst();
            do {
               int madv = c.getInt(0);
               String noidung = c.getString(1);
               String ngay = c.getString(2);
               int thanhtien = c.getInt(3);
               DichVuDTO dichVuDTO = new DichVuDTO(madv, noidung, ngay, thanhtien);
               listDV.add(dichVuDTO);
            } while (c.moveToNext());
        }
        return listDV;
    }

    public int addRow(DichVuDTO dichVuDTO) {
        ContentValues values = new ContentValues();
        values.put("noidung", dichVuDTO.getNoiDung());
        values.put("ngay", dichVuDTO.getNgay());
        values.put("thanhtien", dichVuDTO.getThanhTien());
        return (int) db.insert("dichvu", null, values);
    }

    public int updateRow(DichVuDTO dichVuDTO) {
        ContentValues values = new ContentValues();
        values.put("noidung", dichVuDTO.getNoiDung());
        values.put("ngay", dichVuDTO.getNgay());
        values.put("thanhtien", dichVuDTO.getThanhTien());
        String[] dk = new String[]{String.valueOf(dichVuDTO.getMadv())};
        return  db.update("dichvu", values, "madv=?", dk);
    }

    public int deleteRow(DichVuDTO dichVuDTO) {
        String[] dk = new String[]{String.valueOf(dichVuDTO.getMadv())};
        return db.delete("dichvu", "madv=?", dk);
    }
}
