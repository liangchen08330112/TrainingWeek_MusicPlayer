package cn.edu.sict.lc.musicplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.sict.lc.R;

public class FirstScreen extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        initView();
        AsyncTaskClass taskClass = new AsyncTaskClass();
        taskClass.execute(3);
    }

    private void initView() {
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstScreen.this,LoginActivity.class));
            }
        });
    }

    class AsyncTaskClass extends AsyncTask<Integer,Integer,Boolean> {

        @Override
        protected Boolean doInBackground(Integer... integers) {
            int seconds = integers[0].intValue();
            while (seconds > 0) {
                publishProgress(seconds--);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finish();
            startActivity(new Intent(FirstScreen.this,LoginActivity.class));
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean==true){
                startActivity(new Intent(FirstScreen.this,LoginActivity.class));
            }
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            button.setText("跳过 0"+values[0].intValue());
        }
    }
}