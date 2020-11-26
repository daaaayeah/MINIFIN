package com.example.kustim_v01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PopupresultActivity extends AppCompatActivity implements View.OnClickListener{
Button promise_check;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_result);

        promise_check = findViewById(R.id.promise_check);
        promise_check.setOnClickListener(this);
    }


    public void onClick(View v) {
        if (v.getId() == R.id.promise_check) {

            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
        }
    }
}
