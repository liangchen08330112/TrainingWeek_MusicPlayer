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
import cn.edu.sict.lc.bean.Music;

public class MusicListAdapter extends BaseAdapter {

    ArrayList<Music> list;
    Context context;

    public MusicListAdapter() {
    }

    public MusicListAdapter(ArrayList<Music> list, Context context) {
        this.list = list;
        this.context = context;
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
        ViewHolder holder;
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.music_list_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView_song.setText(list.get(position).getName());
        holder.textView_singer.setText(list.get(position).getSinger());
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView textView_song;
        public TextView textView_singer;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.textView_song = rootView.findViewById(R.id.textView_song);
            this.textView_singer = rootView.findViewById(R.id.textView_singer);
        }
    }
}
