package com.example.kustim_v01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class SNSRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {

        Button money_set;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_register);

        money_set = findViewById(R.id.money_set);
        money_set.setOnClickListener(this);

    }


    public void onClick(View v){


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (v.getId() == R.id.money_set){
            db.collection("users").document(User.a).update("money",true);
            User.money = true;

            Log.e("User는 어떻게 됐을까? :",User.a);
            Toast.makeText(getApplicationContext(), "성사되었습니다. Quest를 Dashboard에서 확인해주세요!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

}
}