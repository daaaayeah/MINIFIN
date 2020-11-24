package com.example.kustim_v01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button signup_send_btn;
    final String TAG = "[FIRESTORE_TAG]";
    static String a ;
    Button btn_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        signup_send_btn = (Button) findViewById(R.id.signup_send);

        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_signin) {
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        signup_send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signup_send:
                        signUp();
                        break;
                }
            }
        });
    }

    private void signUp() {
        final String email = ((EditText) findViewById(R.id.signup_id)).getText().toString();
        final String password = ((EditText) findViewById(R.id.signup_pw)).getText().toString();
        final String passwordCheck = ((EditText) findViewById(R.id.signup_pw2)).getText().toString();
        final String name = ((EditText) findViewById(R.id.signup_name)).getText().toString();
        final String phone = ((EditText) findViewById(R.id.signup_phone)).getText().toString();
        final String uid = (String) mAuth.getCurrentUser().getUid();
        final int score = 0;
        if (email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0) {
            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                if (task.isSuccessful()) {

                                    Map<String, Object> user = new HashMap<>();
                                    user.put("name", name);
                                    user.put("phone", phone);
                                    user.put("email",email);
                                    user.put("uid", uid);
                                    user.put("score",score);
                                    user.put("promise",false);
                                    user.put("money",false);
                                    user.put("wakeup",false);
                                    user.put("promise2",false);
                                    db.collection("users")
                                            .add(user)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                                    a=documentReference.getId();
                                                    Log.d(TAG, "함수 속에서의 a는??? :  " + a);

                                                    FirebaseFirestore db2 = FirebaseFirestore.getInstance();
                                                    db2.collection("users").document(a).update("a",a);

                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "Error adding document", e);
                                                }
                                            });
                                    Toast.makeText(getApplicationContext(), "회원가입에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignupActivity.this,SigninActivity.class);
                                    startActivity(intent);

                                } else {
                                    if (task.getException() != null) {
                                        Toast.makeText(getApplicationContext(),"형식이 안 맞거나 알 수 없는 오류입니다".toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            } else {
                Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "이메일 또는 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}