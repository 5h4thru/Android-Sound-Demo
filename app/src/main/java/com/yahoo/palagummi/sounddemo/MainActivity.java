package com.yahoo.palagummi.sounddemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the audio file
        mediaPlayer = MediaPlayer.create(this, R.raw.laugh);
    }

    public void startSound(View view) {
        mediaPlayer.start();
    }
    public void stopSound(View view) {
        mediaPlayer.pause();
    }
}
