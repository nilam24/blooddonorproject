package com.example.admin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AvailableActivity extends AppCompatActivity {

    TextView textavailable,textsearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doner);

        textavailable=(TextView)findViewById(R.id.text);
        textsearch=(TextView)findViewById(R.id.text2);

        textavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AvailableActivity.this,PieChartDisplay.class);
                startActivity(intent);
            }
        });

        textsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AvailableActivity.this,Main4Activity.class);
                startActivity(intent);

            }
        });

    }
}
