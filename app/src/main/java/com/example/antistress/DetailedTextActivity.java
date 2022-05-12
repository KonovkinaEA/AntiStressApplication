package com.example.antistress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailedTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_text);

        TextView textView = findViewById(R.id.detailedTextView);
        int position = getIntent().getExtras().getInt("texts_el");
        String textNumber = "text_" + position;
        String text = getStringResourceByName(textNumber);
        textView.setText(text);
    }

    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    public void closeDetailedText (View v) {
        finish();
    }
}