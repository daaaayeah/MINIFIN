package com.example.kustim_v01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RecommendActivity extends AppCompatActivity implements View.OnClickListener {
Button config_button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_activity);

        config_button = findViewById(R.id.config_button);
        config_button.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.config_button){

            Intent intent = new Intent(this,ConfigmenuActivity.class);
            startActivity(intent);
        }
}
}