package com.yahoo.palagummi.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = null;
    AudioManager audioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the audio file
        mediaPlayer = MediaPlayer.create(this, R.raw.laugh);
        // get the audio services
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // determine the max volume
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // essentially the media volume

        // get the current system volume
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        // get control of the seekbar
        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);

        // set the max and curr volume for seekBar
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.i("seekBar value", Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // audio seekbar controls the playback of the audio
        final SeekBar audioSeekBar = (SeekBar) findViewById(R.id.audioSeekBar);
        // set max of the audioSeekBar
        audioSeekBar.setMax(mediaPlayer.getDuration()); // set the max to the length of the audio file

        // control the playback of the audio file
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                audioSeekBar.setProgress(mediaPlayer.getCurrentPosition()); // update the position of audioSeekBar as the audio is played
            }
        }, 0, 100); // scheduled every tenth of a second (just to make the progress smooth)

        audioSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.i("audioSeekBar value", Integer.toString(progress));
                if(fromUser) mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void startSound(View view) {
        mediaPlayer.start();
    }
    public void stopSound(View view) {
        mediaPlayer.pause();
    }
}
