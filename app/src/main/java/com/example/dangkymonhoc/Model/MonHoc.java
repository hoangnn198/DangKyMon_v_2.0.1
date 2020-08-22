package com.example.dangkymonhoc.Model;

public class MonHoc {

    int idMonHoc;
    String MaMon;
    String MonHoc;

    public MonHoc(){

    }

    public MonHoc( int idMonHoc, String maMon, String monHoc) {

        this.idMonHoc = idMonHoc;
        MaMon = maMon;
        MonHoc = monHoc;
    }


    public int getIdMonHoc() {
        return idMonHoc;
    }

    public void setIdMonHoc(int idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String maMon) {
        MaMon = maMon;
    }

    public String getMonHoc() {
        return MonHoc;
    }

    public void setMonHoc(String monHoc) {
        MonHoc = monHoc;
    }
}
