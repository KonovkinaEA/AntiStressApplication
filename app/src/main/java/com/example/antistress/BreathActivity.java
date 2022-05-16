package com.example.antistress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BreathActivity extends AppCompatActivity {

    ImageView imageCircle;
    TextView textB;
    Animation anim;
    EditText repeatCount;
    int setValue; // значение, набранное в строке задания повторов (количество вдохов)
    int currentValue = -1; // текущее значение циклов
    final int duration = 5400; // длительность периода анимации (длительность вдоха)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath);

        repeatCount = findViewById(R.id.repeatCount);

        textB = findViewById(R.id.textBreathCircle);

        imageCircle = findViewById(R.id.imageCircle);
        anim = AnimationUtils.loadAnimation(this, R.anim.anim);
        imageCircle.setOnClickListener(v -> {
            if (repeatCount.getText().toString().isEmpty() || repeatCount.getText().toString().equals("Введите число!")) {
                repeatCount.setText("Введите число!");
            } else {
                if (currentValue == -1) {
                    setValue = Integer.parseInt(repeatCount.getText().toString());
                    currentValue = setValue - 1;
                } else {
                    currentValue = -1;
                    textB.setAlpha(0.0f);
                }
                if (currentValue == -1) {
                    imageCircle.clearAnimation();
                    textB.clearAnimation();
                } else {
                    ScaleAnimation increase = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    ScaleAnimation decrease = new ScaleAnimation(1.0f, 0.25f, 1.0f, 0.25f,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    increase.setRepeatCount(currentValue);
                    increase.setDuration(duration);
                    decrease.setRepeatCount(currentValue);
                    decrease.setDuration(duration);
                    AnimationSet setImage = new AnimationSet(false);
                    setImage.addAnimation(increase);
                    setImage.addAnimation(decrease);

                    textB.setAlpha(1.0f);
                    AlphaAnimation appearance = new AlphaAnimation(0.0f, 2.0f);
                    AlphaAnimation disappearance = new AlphaAnimation(2.0f, 0.0f);
                    appearance.setRepeatCount(currentValue);
                    appearance.setDuration(duration);
                    disappearance.setRepeatCount(currentValue);
                    disappearance.setDuration(duration);
                    AnimationSet setText = new AnimationSet(false);
                    setText.addAnimation(appearance);
                    setText.addAnimation(disappearance);

                    AlphaAnimation textAnimation = new AlphaAnimation(1.0f, 1.0f);
                    textAnimation.setDuration(duration/2);
                    textAnimation.setRepeatCount(currentValue * 2 + 1);
                    setText.addAnimation(textAnimation);

                    textAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            textB.setText("Вдох");
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            currentValue = -1;
                            textB.setAlpha(0.0f);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            if (textB.getText().toString().equals("Вдох")) {
                                textB.setText("Выдох");
                            } else {
                                textB.setText("Вдох");
                            }
                        }
                    });

                    imageCircle.startAnimation(setImage);
                    textB.startAnimation(setText);
                }
            }
        });
    }

    public void closeBreathActivity(View v) {
        finish();
    }
}