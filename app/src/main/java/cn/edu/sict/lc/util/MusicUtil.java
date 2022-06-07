package cn.edu.sict.lc.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;

import cn.edu.sict.lc.bean.Music;

public class MusicUtil {
    public static ArrayList<Music> scan(Context context){
        ArrayList<Music> list = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DATA},null,null,null);
        while (cursor.moveToNext()){
            int songNameIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            String songName = cursor.getString(songNameIndex);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            String artist = cursor.getString(artistIndex);
            int dataIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            String data = cursor.getString(dataIndex);

            Music music = new Music(songName,artist,data);
            list.add(music);
        }
        return list;
    }
}
