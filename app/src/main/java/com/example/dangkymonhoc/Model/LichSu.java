package com.example.dangkymonhoc.Model;

public class LichSu {
    String MonHoc;
    String TrangThai;

    public String getMonHoc() {
        return MonHoc;
    }

    public void setMonHoc(String monHoc) {
        MonHoc = monHoc;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public LichSu(String monHoc, String trangThai) {
        MonHoc = monHoc;
        TrangThai = trangThai;
    }

    public LichSu() {
    }
}
