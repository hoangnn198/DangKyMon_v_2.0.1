package com.example.dangkymonhoc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dangkymonhoc.GiaoDienDangKy.FormDangKyActivity;
import com.example.dangkymonhoc.Model.LichHoc;
import com.example.dangkymonhoc.R;

import java.util.ArrayList;

public class LichHocAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<LichHoc> listLichHoc;
    TextView tvNgayHoc,tvLopHoc,tvMonHoc,tvGiangVien,tvCaHoc;

    public LichHocAdapter(Context context, int layout, ArrayList<LichHoc> listLichHoc) {
        this.context = context;
        this.layout = layout;
        this.listLichHoc = listLichHoc;
    }

    @Override
    public int getCount() {
        return listLichHoc.size();
    }

    @Override
    public Object getItem(int i) {
        return listLichHoc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(layout,null);

        tvNgayHoc = view.findViewById(R.id.tvNgayHoc);
        tvLopHoc = view.findViewById(R.id.tvLopHoc);
        tvMonHoc = view.findViewById(R.id.tvMonHoc);
        tvGiangVien = view.findViewById(R.id.tvGiangVien);
        tvCaHoc = view.findViewById(R.id.tvCaHoc);
        Log.d("DateInAdapter: ",listLichHoc.get(i).getNgayHoc());

        tvNgayHoc.setText(listLichHoc.get(i).getNgayHoc());
//        Log.d(listLichHoc.get(i).getNgayHoc());
        tvLopHoc.setText((listLichHoc.get(i).getLopHoc()));
        tvMonHoc.setText(listLichHoc.get(i).getMonHoc());
        tvGiangVien.setText(listLichHoc.get(i).getGiangVien());
        tvCaHoc.setText(listLichHoc.get(i).getCaHoc());

        return view;
    }
}
