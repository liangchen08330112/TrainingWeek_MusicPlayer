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
import cn.edu.sict.lc.bean.MyConfigGridBean;

public class MyConfigGridAdapter extends BaseAdapter {
    Context context;
    ArrayList<MyConfigGridBean> list;

    public MyConfigGridAdapter() {
    }

    public MyConfigGridAdapter(Context context, ArrayList<MyConfigGridBean> list) {
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
        View view = inflater.inflate(R.layout.grid_config,null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        MyConfigGridBean bean = list.get(position);
        imageView.setImageResource(bean.getImgSrc_config());
        textView.setText(bean.getTitle_config());
        return view;
    }
}
