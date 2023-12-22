package com.example.ontapandroid2_5.DTO;

public class DichVuDTO {

    private int madv;
    private String noiDung;
    private String ngay;
    private int thanhTien;

    public DichVuDTO(int madv, String noiDung, String ngay, int thanhTien) {
        this.madv = madv;
        this.noiDung = noiDung;
        this.ngay = ngay;
        this.thanhTien = thanhTien;
    }

    public DichVuDTO() {
    }

    public DichVuDTO(String noiDung, String ngay, int thanhTien) {
        this.noiDung = noiDung;
        this.ngay = ngay;
        this.thanhTien = thanhTien;
    }

    public int getMadv() {
        return madv;
    }

    public void setMadv(int madv) {
        this.madv = madv;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
