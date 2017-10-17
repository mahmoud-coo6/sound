package com.example2.acer.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mplayer;
    AudioManager audioManager;
    public void playAudio(View view){

        mplayer.start();

    }
    public void pauseAudio(View view){
        mplayer.pause();
      //  mplayer.stop();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mplayer=MediaPlayer.create(this,R.raw.laugh);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxValume= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curentValum=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumControl=(SeekBar)findViewById(R.id.seekBar);
        final SeekBar scrubber=(SeekBar)findViewById(R.id.scrubber);
        volumControl.setMax(maxValume);
        scrubber.setMax(mplayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
              scrubber.setProgress(mplayer.getCurrentPosition());
            }
        },0,100);
        volumControl.setProgress(curentValum);
        volumControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("SeekBar Value", String.valueOf(i));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Scrubber Value", String.valueOf(i));
                mplayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
