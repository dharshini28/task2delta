package com.example.task2delta;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

    public class MainActivity extends AppCompatActivity {

        private Button play;
        private Button Instructions;
        private EditText editTextRows, editTextColumns;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate ( savedInstanceState );
            setContentView ( R.layout.activity_main );

            play = (Button) findViewById ( R.id.b1 );
            Instructions = (Button) findViewById ( R.id.b4 );
            editTextRows = (EditText) findViewById ( R.id.et1 );
            editTextColumns = (EditText) findViewById ( R.id.et2 );


            final MediaPlayer mp = MediaPlayer.create ( this,R.raw.click );

            play.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    mp.start();
                    startGame ();
                }
            } );

            Instructions.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    mp.start();
                    startInstructions ();
                }
            } );
        }

        private void startGame() {
            Intent intent = new Intent ( this, GameActivity.class );

            int rows = Integer.parseInt ( editTextRows.getText ().toString () );
            int columns = Integer.parseInt (editTextColumns.getText ().toString () );

            intent.putExtra ( "rows", rows );
            intent.putExtra ( "columns", columns );

            startActivity ( intent );
        }

        private void startInstructions() {
            Intent t = new Intent ( this, InstructionsActivity.class );
            startActivity ( t );
        }
    }
