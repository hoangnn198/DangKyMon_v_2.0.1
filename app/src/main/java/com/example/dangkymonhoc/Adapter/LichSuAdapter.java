package com.example.dangkymonhoc.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
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
        final LinearLayout layout =convertView.findViewById(R.id.layout);

        tvMonHoc.setText(listLichSu.get(position).getMonHoc());
        tvTrangThai.setText(listLichSu.get(position).getTrangThai());

        if (Integer.parseInt(listLichSu.get(position).getIdTrangThai()) == 2) {
            layout.setBackgroundColor(Color.parseColor("#336633"));
        }else if (Integer.parseInt(listLichSu.get(position).getIdTrangThai()) == 1){
            layout.setBackgroundColor(Color.parseColor("#663366"));
        }

        return convertView;
    }
}
