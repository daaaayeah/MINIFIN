package com.example.kustim_v01;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    ProgressDialog customProgressDialog;
    public static User user = new User();
    private Button btn_signin;
    private EditText et_id;
    private EditText et_pw;
    private Button btn_signup;
    private CheckBox cb_save;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);

        mContext = this; // 이거 필수!
        btn_signin = (Button) findViewById(R.id.btn_signin);
        et_id = (EditText) findViewById(R.id.signin_id);
        et_pw = (EditText) findViewById(R.id.signin_pw);
        cb_save = (CheckBox) findViewById(R.id.cb_save);

        boolean boo = PreferenceManager.getBoolean(mContext, "check"); // 로그인 정보 기억하기 체크 유무 확인
        if (boo) { // 체크가 되어있다면 아래 코드를 수행
            // 저장된 아이디와 암호를 가져와 셋팅한다.
            et_id.setText(PreferenceManager.getString(mContext, "id"));
            et_pw.setText(PreferenceManager.getString(mContext, "pw"));
            cb_save.setChecked(true); // 체크박스는 여전히 체크 표시 하도록 셋팅
        }
        btn_signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // 로그인 버튼 눌렀을 때 동작
                // 아이디 암호 입력창에서 텍스트를 가져와 PreferenceManager에 저장함
                PreferenceManager.setString(mContext, "id", et_id.getText().toString()); // id라는 키값으로 저장
                PreferenceManager.setString(mContext, "pw", et_pw.getText().toString()); // pw라는 키값으로 저장
                login();
            }
        });

        //로그인 기억하기 체크박스 유무에 따른 동작 구현
        cb_save.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) { // 체크박스 체크 되어 있으면
                    // editText에서 아이디와 암호 가져와 PreferenceManager에 저장한다.
                    PreferenceManager.setString(mContext, "id", et_id.getText().toString()); // id 키값으로 저장
                    PreferenceManager.setString(mContext, "pw", et_pw.getText().toString()); // pw 키값으로 저장
                    PreferenceManager.setBoolean(mContext, "check", cb_save.isChecked()); // 현재 체크박스 상태 값 저장
                } else { // 체크박스가 해제되어있으면
                    PreferenceManager.setBoolean(mContext, "check", cb_save.isChecked()); // 현재 체크박스 상태 값 저장
                    PreferenceManager.clear(mContext); //로그인 정보를 모두 날림
                }
            }
        });

        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(this);

        customProgressDialog = new ProgressDialog(this);
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_signup) {
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


                                                        if (PopupresultActivity.success_promise == true) {
                                                            db.collection("users").document(user.a).update("promise2", true);
                                                        }

                                                        user.promise2 = (boolean) document.getData().get("promise2");

                                                        user.email = document.getData().get("email").toString();
                                                        user.uid = document.getData().get("uid").toString();
                                                        user.name = document.getData().get("name").toString();
                                                        user.a = document.getData().get("a").toString();
                                                        user.score = document.getData().get("score").toString();
                                                        user.promise = (boolean) document.getData().get("promise");
                                                        user.money = (boolean) document.getData().get("money");
                                                        user.wakeup = (boolean) document.getData().get("wakeup");

                                                    }
                                                }
                                            }
                                        });

                                Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                if (task.getException() != null) {
                                    Toast.makeText(getApplicationContext(), "잘못된 형식입니다".toString(), Toast.LENGTH_SHORT).show();
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