package com.example.kustim_v01;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class SigninActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "[FIRESTORE_TAG]";
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth mAuth;
    Button btn_signin;
    Button btn_signup;
    ProgressDialog customProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        customProgressDialog = new ProgressDialog(this);
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(this);

        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(this);
//자동로그인?        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }


    public void onClick(View v) {
        if (v.getId() == R.id.btn_signin){
            login();
        }
        else if (v.getId() == R.id.btn_signup) {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        }
    }

    private void login() {

        final String email = ((EditText) findViewById(R.id.signin_id)).getText().toString();
        String password = ((EditText) findViewById(R.id.signin_pw)).getText().toString();
        if (email.length() > 0 && password.length() > 0) {
            customProgressDialog.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            customProgressDialog.dismiss();
                            if (task.isSuccessful()) {
                                final FirebaseFirestore db = FirebaseFirestore.getInstance();


                                db.collection("users")
                                        .whereEqualTo("email", email)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (!task.isSuccessful()) {
                                                    Log.d("No", "Error getting documents: ", task.getException());
                                                } else {

                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                        User user = new User();
                                                        user.email = document.getData().get("email").toString();
                                                        user.uid = document.getData().get("uid").toString();
                                                        user.name = document.getData().get("name").toString();
                                                        user.a = document.getData().get("a").toString();
                                                        user.score = document.getData().get("score").toString();
                                                        user.promise = (boolean) document.getData().get("promise");
                                                        user.money = (boolean) document.getData().get("money");
                                                        user.wakeup = (boolean) document.getData().get("wakeup");
                                                        user.promise2 = (boolean) document.getData().get("promise2");

                                                            if (GeofenceTransitionsJobIntentService.success_promise == true) {
                                                                db.collection("users").document(user.a).update("promise2", true);
                                                            }

                                                    }
                                                }
                                            }
                                        });




                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                if (task.getException() != null) {
                                    Toast.makeText(getApplicationContext(), "형식이 안 맞거나 알 수 없는 오류입니다".toString(), Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        }

                    });
        } else {
            Toast.makeText(getApplicationContext(), "이메일 또는 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateUI(FirebaseUser account){

        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,MainActivity.class));

        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }

    }
}