package cn.edu.sict.lc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.edu.sict.lc.R;
import cn.edu.sict.lc.bean.MyGridBean;

public class MyGridAdapter extends BaseAdapter {
    Context context;
    ArrayList<MyGridBean> list;

    public MyGridAdapter() {
    }

    public MyGridAdapter(Context context, ArrayList<MyGridBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.griditem,null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        MyGridBean bean = list.get(position);
        imageView.setImageResource(bean.getImgSrc());
        textView.setText(bean.getTitle());
        return view;
    }
}
