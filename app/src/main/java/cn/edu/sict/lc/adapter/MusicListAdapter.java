package cn.edu.sict.lc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.edu.sict.lc.R;
import cn.edu.sict.lc.bean.Music;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    Context context;
    ArrayList<Music> list;

    public MusicListAdapter(Context context, ArrayList<Music> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.music_list_item, null);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //返回ViewHolder对象
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Music music = list.get(position);
        holder.textView_song.setText(music.getName());
        holder.textView_singer.setText(music.getSinger());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView textView_song;
        public TextView textView_singer;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.textView_song = view.findViewById(R.id.textView_song);
            this.textView_singer = view.findViewById(R.id.textView_singer);
        }
    }
}