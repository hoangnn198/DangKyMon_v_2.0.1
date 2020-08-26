package com.example.dangkymonhoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dangkymonhoc.Model.LichSu;
import com.example.dangkymonhoc.Model.MonHoc;
import com.example.dangkymonhoc.R;

import java.util.ArrayList;

public class LichSuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<LichSu> listLichSu;
    TextView tvMonHoc,tvTrangThai;
    public LichSuAdapter(Context context, int layout, ArrayList<LichSu> listLichSu) {
        this.context = context;
        this.layout = layout;
        this.listLichSu =listLichSu;
    }



    @Override
    public int getCount() {
        return listLichSu.size();
    }

    @Override
    public Object getItem(int position) {
        return listLichSu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        tvMonHoc = convertView.findViewById(R.id.tv_MONHOC);
        tvTrangThai = convertView.findViewById(R.id.tv_TRANGTHAI);

        tvMonHoc.setText(listLichSu.get(position).getMonHoc());
        tvTrangThai.setText(listLichSu.get(position).getTrangThai());

        return convertView;
    }
}
