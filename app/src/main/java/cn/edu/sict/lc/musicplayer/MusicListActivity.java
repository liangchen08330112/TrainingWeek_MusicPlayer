package cn.edu.sict.lc.musicplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import cn.edu.sict.lc.R;

public class MusicListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButton_back,imageButton_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initView();
    }

    private void initView() {
        imageButton_back = findViewById(R.id.imageButton_back);
        imageButton_search = findViewById(R.id.imageButton_search);

        imageButton_back.setOnClickListener(this);
//        imageButton_search.setOnClickListener(this);
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