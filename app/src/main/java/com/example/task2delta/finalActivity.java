package com.example.task2delta;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
public class finalActivity extends AppCompatActivity {

    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        back= (Button) findViewById ( R.id.b3 );
        final MediaPlayer mp = MediaPlayer.create ( this,R.raw.click );
        char input = getIntent().getCharExtra("Winner", ' ');

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Winner" + String.valueOf(input) + '!');

        back.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                mp.start();
                goback();
            }
        } );
    }
    private void goback() {
        Intent e = new Intent ( this, MainActivity.class );
        startActivity ( e );
    }
}

