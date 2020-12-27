package com.bma.loksewaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SetActivity extends AppCompatActivity {
    AppCompatButton set1btn,set2btn,set3btn,set4btn,set5btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        Intent intent = getIntent();
       String fromsets = intent.getExtras().getString("fromname");
        String ads1 = intent.getExtras().getString("ads1");
        String ads2 = intent.getExtras().getString("ads2");
        String ads3 = intent.getExtras().getString("ads3");
        String ads4 = intent.getExtras().getString("ads4");
        String ads5 = intent.getExtras().getString("ads5");

        set1btn = findViewById(R.id.set1btn);
        set2btn = findViewById(R.id.set2btn);
        set3btn = findViewById(R.id.set3btn);
        set4btn = findViewById(R.id.set4btn);
        set5btn = findViewById(R.id.set5btn);

        String named1 = set1btn.getText().toString();
        String named2 = set2btn.getText().toString();
        String named3 = set3btn.getText().toString();
        String named4 = set4btn.getText().toString();
        String named5 = set5btn.getText().toString();

        String names1 = fromsets+named1;
        String names2 = fromsets+named2;
        String names3 = fromsets+named3;
        String names4 = fromsets+named4;
        String names5 = fromsets+named5;

        set1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, QuestionsActivity.class);
                intent.putExtra("fromset", names1);
                intent.putExtra("interstital",ads1);
                startActivity(intent);
                finish();

            }
        });

        set2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, QuestionsActivity.class);
                intent.putExtra("fromset", names2);
                intent.putExtra("interstital",ads2);
                startActivity(intent);
                finish();

            }
        });

        set3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, QuestionsActivity.class);
                intent.putExtra("fromset", names3);
                intent.putExtra("interstital",ads3);
                startActivity(intent);
                finish();

            }
        });

        set4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, QuestionsActivity.class);
                intent.putExtra("fromset", names4);
                intent.putExtra("interstital",ads4);
                startActivity(intent);
                finish();

            }
        });

        set5btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, QuestionsActivity.class);
                intent.putExtra("fromset", names5);
                intent.putExtra("interstital",ads5);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SetActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}