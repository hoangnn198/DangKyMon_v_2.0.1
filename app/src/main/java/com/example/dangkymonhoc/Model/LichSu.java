package com.example.dangkymonhoc.Model;

public class LichSu {
    String MonHoc;
    String idTrangThai;
    String TrangThai;

    public LichSu() {
    }

    public LichSu(String monHoc, String idTrangThai, String trangThai) {
        MonHoc = monHoc;
        this.idTrangThai = idTrangThai;
        TrangThai = trangThai;
    }

    public String getMonHoc() {
        return MonHoc;
    }

    public void setMonHoc(String monHoc) {
        MonHoc = monHoc;
    }

    public String getIdTrangThai() {
        return idTrangThai;
    }

    public void setIdTrangThai(String idTrangThai) {
        this.idTrangThai = idTrangThai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
