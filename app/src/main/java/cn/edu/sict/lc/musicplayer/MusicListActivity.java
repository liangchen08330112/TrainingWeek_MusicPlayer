package cn.edu.sict.lc.musicplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import cn.edu.sict.lc.R;
import cn.edu.sict.lc.adapter.MusicListAdapter;
import cn.edu.sict.lc.bean.Music;
import cn.edu.sict.lc.util.MusicUtil;

public class MusicListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButton_back,imageButton_search;
    private RecyclerView recyclerView;
    ArrayList<Music> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        initView();
    }

    private void initView() {
        imageButton_back = findViewById(R.id.imageButton_back);
        imageButton_search = findViewById(R.id.imageButton_search);
        recyclerView = findViewById(R.id.recyclerView);


//        imageButton_search.setOnClickListener(this);
        initList();
        initListener();
    }

    private void initList() {
        initData();
        MusicListAdapter adapter = new MusicListAdapter(this,list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
    }

    private void initData() {
        list = MusicUtil.scan(this);
    }

    private void initListener(){
        imageButton_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton_back:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}