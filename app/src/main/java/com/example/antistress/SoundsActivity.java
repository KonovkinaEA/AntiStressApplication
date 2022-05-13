package com.example.antistress;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SoundsActivity extends AppCompatActivity {

    enum Sound { NONE, RAIN, WATER, FIRE }
    boolean soundPlay = false;
    Sound chosenSound = Sound.NONE;
    MediaPlayer curSound;
    boolean soundWasModified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds);

        curSound = MediaPlayer.create(this, R.raw.test);
        curSound.setOnCompletionListener(mp -> play());
    }

    public void playRain(View v) {
        ImageView image = findViewById(R.id.rain);
        if (!soundPlay)
            soundWasModified = true;
        switch (chosenSound) {
            case RAIN: {
                chosenSound = Sound.NONE;
                image.setBackgroundResource(R.drawable.sound_rain);
                if(soundPlay)
                    stop();
            }
            break;
            case FIRE: {
                chosenSound = Sound.RAIN;
                image.setBackgroundResource(R.drawable.chose_rain);
                ImageView i = findViewById(R.id.fire);
                i.setBackgroundResource(R.drawable.sound_fire);
                if(soundPlay)
                    play();
            }
            break;
            case WATER:
            {
                chosenSound = Sound.RAIN;
                image.setBackgroundResource(R.drawable.chose_rain);
                ImageView i = findViewById(R.id.water);
                i.setBackgroundResource(R.drawable.sound_water);
                if(soundPlay)
                    play();
            }
            break;
            default: {
                chosenSound = Sound.RAIN;
                image.setBackgroundResource(R.drawable.chose_rain);
            }
        }
    }
    public void playWater(View v) {
        ImageView image = findViewById(R.id.water);
        if (!soundPlay)
            soundWasModified = true;
        switch (chosenSound) {
            case WATER: {
                chosenSound = Sound.NONE;
                image.setBackgroundResource(R.drawable.sound_water);
                if(soundPlay)
                    stop();
            }
            break;
            case FIRE: {
                chosenSound = Sound.WATER;
                image.setBackgroundResource(R.drawable.chose_water);
                ImageView i = findViewById(R.id.fire);
                i.setBackgroundResource(R.drawable.sound_fire);
                if(soundPlay)
                    play();
            }
            break;
            case RAIN:
            {
                chosenSound = Sound.WATER;
                image.setBackgroundResource(R.drawable.chose_water);
                ImageView i = findViewById(R.id.rain);
                i.setBackgroundResource(R.drawable.sound_rain);
                if(soundPlay)
                    play();
            }
            break;
            default: {
                chosenSound = Sound.WATER;
                image.setBackgroundResource(R.drawable.chose_water);
            }
        }
    }
    public void playFire(View v) {
        ImageView image = findViewById(R.id.fire);
        if (!soundPlay)
            soundWasModified = true;
        switch (chosenSound) {
            case FIRE: {
                chosenSound = Sound.NONE;
                image.setBackgroundResource(R.drawable.sound_fire);
                if(soundPlay)
                    stop();
            }
                break;
            case WATER: {
                chosenSound = Sound.FIRE;
                image.setBackgroundResource(R.drawable.chose_fire);
                ImageView i = findViewById(R.id.water);
                i.setBackgroundResource(R.drawable.sound_water);
                if(soundPlay)
                    play();
            }
                break;
            case RAIN:
            {
                chosenSound = Sound.FIRE;
                image.setBackgroundResource(R.drawable.chose_fire);
                ImageView i = findViewById(R.id.rain);
                i.setBackgroundResource(R.drawable.sound_rain);
                if(soundPlay)
                    play();
            }
                break;
            default: {
                chosenSound = Sound.FIRE;
                image.setBackgroundResource(R.drawable.chose_fire);
            }
        }
    }

    public void playback(View v) {
        if (soundPlay){
            pause();
        }
        else if(chosenSound != Sound.NONE) {
            play();
        }
    }

    private void play() {
        if (soundWasModified || soundPlay) {
            curSound.stop();
            switch (chosenSound) {
                case FIRE:
                    curSound = MediaPlayer.create(this, R.raw.fire);
                    break;
                case WATER:
                    curSound = MediaPlayer.create(this, R.raw.water);
                    break;
                default:
                    curSound = MediaPlayer.create(this, R.raw.rain);
                    break;
            }
        }
        soundWasModified = false;
        soundPlay = true;
        ImageView image = findViewById(R.id.play);
        image.setBackgroundResource(R.drawable.button_stop);
        curSound.start();
    }

    private void pause() {
        curSound.pause();
        soundPlay = false;
        ImageView image = findViewById(R.id.play);
        image.setBackgroundResource(R.drawable.button_play);
    }

    private void stop() {
        curSound.stop();
        soundPlay = false;
        ImageView image = findViewById(R.id.play);
        image.setBackgroundResource(R.drawable.button_play);
    }

    public void closeSoundsActivity(View v) {
        curSound.stop();
        finish();
    }
}