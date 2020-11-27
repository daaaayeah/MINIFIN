package com.example.kustim_v01;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

//import android.support.v7.app.AppCompatActivity;

public class WakeupRegisterActivity extends AppCompatActivity implements View.OnClickListener{
Button wakeup_set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.wakeup_registerm);


        wakeup_set = findViewById(R.id.wakeup_set);
        wakeup_set.setOnClickListener(this);
 }


    public void onClick(View v){


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (v.getId() == R.id.wakeup_set){
            db.collection("users").document(User.a).update("wakeup",true);
            User.wakeup = true;

            Toast.makeText(getApplicationContext(), "성사되었습니다. Quest를 Dashboard에서 확인해주세요!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}


