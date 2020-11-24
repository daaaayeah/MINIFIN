package com.example.kustim_v01;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompletedQuestActivity extends AppCompatActivity {
    TextView completed_first;
    TextView completed_first_num;
    boolean complete = User.promise2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_quest_activity);

        completed_first = findViewById(R.id.completed_first);
        completed_first_num = findViewById(R.id.completed_first_num);

        if (User.promise2==true) {
            completed_first.setText("약속왕이 되어라!");
            completed_first_num.setText("1회");
        }
        else{}
    }

}
