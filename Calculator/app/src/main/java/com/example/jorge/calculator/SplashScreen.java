package com.example.jorge.calculator;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by jrf254 on 9/28/2015.
 */
public class SplashScreen extends Activity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mp = MediaPlayer.create(getBaseContext(), R.raw.sound); /*Gets your soundfile from res/raw/sound.ogg */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    mp.start(); //Starts your sound
                    sleep(4000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,Calculator.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    protected void onStop() {
        super.onStop();
        if(mp.isPlaying()){ //Must check if it's playing, otherwise it may be a NPE
            mp.pause(); //Pauses the sou
        }
    }
}