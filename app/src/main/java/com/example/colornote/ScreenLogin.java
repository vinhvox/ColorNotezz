package com.example.colornote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.colornote.View.MainActivity;
import com.example.colornote.databinding.ActivityScreenLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ScreenLogin extends AppCompatActivity {
    ActivityScreenLoginBinding loginBinding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityScreenLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        init();
    }

    private void init() {
        loginBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateAccount();
            }
        });
        loginBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignIn();
            }
        });
    }

    private void onSignIn() {
        String email = loginBinding.edtEmail.getText().toString().trim();
        String pass = loginBinding.edtPass.getText().toString().trim();
        if (TextUtils.isEmpty(email)  || TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Thông tin nhập không đúng", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ScreenLogin.this, "Tạp tài khoản thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ScreenLogin.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(ScreenLogin.this, "Tạp tài khoản thất bại", Toast.LENGTH_LONG).show();
                        if (loginBinding.btnSignUp.getVisibility() == View.GONE){
                            loginBinding.btnSignUp.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }

    }

    private void onCreateAccount() {
        String email = loginBinding.edtEmail.getText().toString().trim();
        String pass = loginBinding.edtPass.getText().toString().trim();
        if (TextUtils.isEmpty(email)  || TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Thông tin nhập không đúng", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ScreenLogin.this, "Tạp tài khoản thành công", Toast.LENGTH_LONG).show();
                                loginBinding.btnSignUp.setVisibility(View.GONE);
                            }
                            else {
                                Toast.makeText(ScreenLogin.this, "Tạp tài khoản thất bại", Toast.LENGTH_LONG).show();
                                Log.e( "onComplete: ", task.getException().getMessage());
                            }
                        }
                    });
        }
    }
}