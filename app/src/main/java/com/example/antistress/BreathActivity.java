package com.example.antistress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class BreathActivity extends AppCompatActivity {

    ImageView imageCircle;
    Animation anim;
    EditText repeatCount;
    int setValue;
    int currentValue = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath);

        repeatCount = findViewById(R.id.repeatCount);

        imageCircle = findViewById(R.id.imageCircle);
        anim = AnimationUtils.loadAnimation(this, R.anim.anim);
        imageCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentValue == -1) {
                    setValue = Integer.parseInt(repeatCount.getText().toString());
                    currentValue = setValue - 1;
                } else {
                    currentValue = -1;
                }
                if (currentValue == -1) {
                    imageCircle.clearAnimation();
                } else {
                    ScaleAnimation scale = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    ScaleAnimation scale1 = new ScaleAnimation(1.0f, 0.25f, 1.0f, 0.25f,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale.setRepeatCount(currentValue);
                    scale.setDuration(4200);
                    scale1.setRepeatCount(currentValue);
                    scale1.setDuration(4200);
                    AnimationSet set = new AnimationSet(false);
                    set.addAnimation(scale);
                    set.addAnimation(scale1);
                    imageCircle.startAnimation(set);

                    long sleepingTime = setValue;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask(){
                        @Override
                        public void run() {
                            currentValue = -1;
                        }
                    }, 4200L * sleepingTime);
                }
            }
        });
    }

    public void openMainActivity(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}