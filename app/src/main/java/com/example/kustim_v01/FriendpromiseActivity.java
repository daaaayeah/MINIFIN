package com.example.kustim_v01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.kustim_v01.SigninActivity.user;

public class FriendpromiseActivity extends AppCompatActivity implements View.OnClickListener{
    Button promise5;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_friend);
        User.promise = true;

        promise5 = findViewById(R.id.promise5);
        promise5.setOnClickListener(this);
    }


    public void onClick(View v) {
        if (v.getId() == R.id.promise5) {

            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users")
                    .whereEqualTo("email", user.email)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (!task.isSuccessful()) {
                                Log.d("No", "Error getting documents: ", task.getException());
                            } else {

                                for (QueryDocumentSnapshot document : task.getResult()) {


                                    user.a = document.getData().get("a").toString();
                                    if (PopupresultActivity.success_promise == true) {
                                        db.collection("users").document(user.a).update("friendemail", false);
                                    }


                                }
                            }
                        }
                    });



            Intent intent = new Intent(FriendpromiseActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
