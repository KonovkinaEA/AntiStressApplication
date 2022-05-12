package com.example.antistress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class TextsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texts);

        ListView listView = findViewById(R.id.listView);
        String[] elements = getResources().getStringArray(R.array.texts_elements);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.texts_el,
                elements);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int pos, long id) {
                Intent intent = new Intent(
                        TextsActivity.this, DetailedTextActivity.class
                );
                intent.putExtra("texts_el", pos);
                startActivity(intent);
            }
        });
    }

    public void closeTextsActivity(View v) {
        finish();
    }
}