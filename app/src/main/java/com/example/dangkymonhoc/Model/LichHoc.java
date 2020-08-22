package com.example.dangkymonhoc.Model;

public class LichHoc {

    String ngayHoc;
    String lopHoc;
    String monHoc;
    String giangVien;
    String caHoc;
    public LichHoc(){

    }
    public LichHoc( String ngayHoc, String lopHoc, String monHoc, String giangVien, String caHoc) {
        this.ngayHoc = ngayHoc;
        this.lopHoc = lopHoc;
        this.monHoc = monHoc;
        this.giangVien = giangVien;
        this.caHoc = caHoc;
    }



    public String getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(String ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    public String getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(String lopHoc) {
        this.lopHoc = lopHoc;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
    }

    public String getCaHoc() {
        return caHoc;
    }

    public void setCaHoc(String caHoc) {
        this.caHoc = caHoc;
    }
}
