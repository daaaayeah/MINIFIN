package com.example.kustim_v01;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MoneyDashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_register1);
    }

    public void mOnClose(View v) {

        if (v.getId() == R.id.money_set2) {

            //액티비티(팝업) 닫기
            finish();
        }
    }
}
