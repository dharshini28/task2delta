package com.example.task2delta;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int rows = intent.getExtras().getInt("rows");
        int columns = intent.getExtras().getInt("columns");

      if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ) {
            setContentView(new ConnectGrid(this, rows, columns));
        }

    }

}

