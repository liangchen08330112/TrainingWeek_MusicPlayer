package cn.edu.sict.lc.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import cn.edu.sict.lc.R;
import cn.edu.sict.lc.adapter.MusicListAdapter;
import cn.edu.sict.lc.bean.Music;
import cn.edu.sict.lc.util.MusicUtil;

public class MusicListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButton_back,imageButton_search,imageButton_play,imageButton_previous,imageButton_next;
    private ListView listView;
    private TextView textView_songName,textView_singer,textView_currentTime,textView_totalTime;
    ArrayList<Music> list;

    int playNow;
    MediaPlayer player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        initView();
    }

    private void initView() {
        imageButton_back = findViewById(R.id.imageButton_back);
        imageButton_search = findViewById(R.id.imageButton_search);
        imageButton_play = findViewById(R.id.imageButton_play);
        imageButton_previous = findViewById(R.id.imageButton_previous);
        imageButton_next = findViewById(R.id.imageButton_next);
        textView_songName = findViewById(R.id.textView_songName);
        textView_singer = findViewById(R.id.textView_singer);
        textView_currentTime = findViewById(R.id.textView_currentTime);
        textView_totalTime = findViewById(R.id.textView_totalTime);
        listView = findViewById(R.id.listView);


//        imageButton_search.setOnClickListener(this);
        initList();
        initListener();
    }
    //利用Activity生命周期来消除二重唱的bug
    //在播放页进入后台，即用户看不到时，调用onStop()
    @Override
    protected void onStop() {
        if (player.isPlaying()){
            super.onStop();
        }
        //释放资源
        player.release();
    }

    private void initList() {
        initData();
        MusicListAdapter adapter = new MusicListAdapter(list,this);
        listView.setAdapter(adapter);
    }

    private void initData() {
        list = MusicUtil.scan(this);
    }

    private void initListener(){
        imageButton_back.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                play(position);
                setMusicInfo(position);
                imageButton_play.setImageResource(R.drawable.icon_pause2);
            }
        });
        imageButton_previous.setOnClickListener(this);
        imageButton_next.setOnClickListener(this);
        imageButton_play.setOnClickListener(this);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playNext();
            }
        });
    }

    private void setMusicInfo(int position) {
        Music music = list.get(position);
        textView_songName.setText("歌曲名："+music.getName());
        textView_singer.setText("歌手："+music.getSinger());
        textView_totalTime.setText(getDecimalFormatTime(player.getDuration()));
    }

    private String getDecimalFormatTime(int duration) {
        DecimalFormat format = new DecimalFormat("#00");
        int minute = duration/1000/60;
        int second = duration%60000/1000;
        return format.format(minute)+":"+format.format(second);
    }

    private void play(int position) {
        playNow = position;
        player.reset();
        try {
            player.setDataSource(list.get(position).getPath());
            player.prepare();
        } catch (IOException e) {
            Toast.makeText(this,"啊哦，播放失败了~",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        player.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton_back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.imageButton_next:
                playNext();
                setMusicInfo(playNow);
                break;
            case R.id.imageButton_previous:
                playPrevious();
                setMusicInfo(playNow);
                break;
            case R.id.imageButton_play:
                if (player.isPlaying()) {
                    pause();
                    imageButton_play.setImageResource(R.drawable.ic_play);
                } else {
                    start();
                    imageButton_play.setImageResource(R.drawable.icon_pause2);
                }
        }
    }

    private void start() {
        player.start();
    }

    private void pause() {
        player.pause();
    }

    private void playPrevious() {
        if (playNow==0){
            playNow = list.size()-1;
        }else {
            playNow--;
        }
        imageButton_play.setImageResource(R.drawable.icon_pause2);
        play(playNow);
    }

    private void playNext() {
        if (playNow==0){
            playNow = list.size()+1;
        }else {
            playNow++;
        }
        imageButton_play.setImageResource(R.drawable.icon_pause2);
    }
}