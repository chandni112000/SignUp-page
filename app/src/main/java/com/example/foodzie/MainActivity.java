package com.example.foodzie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText customerName , PhoneNo , gmail, password  ;
    EditText cfpassword;
    Button register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText customerName , PhoneNo , gmail, password  ;
        EditText cfpassword;
        Button register;
        ProgressBar progressBar;
        FirebaseAuth firebaseAuth;


//        getSupportActionBar().setTitle("Sign Up");
//        DBHelper db;


            register = findViewById(R.id.btnRegister);
            customerName = (EditText) findViewById(R.id.UserName);
            PhoneNo = findViewById(R.id.etPhone);
            gmail = findViewById(R.id.Gmail1);
            password = findViewById(R.id.etPassword);
            cfpassword = findViewById(R.id.confirmPassword);


            firebaseAuth = FirebaseAuth.getInstance();


            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String customergmail = gmail.getText().toString();
                    String pass = password.getText().toString();
                    String repass = cfpassword.getText().toString();


                    if(TextUtils.isEmpty(customergmail)){
                        Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(pass)){
                        Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(repass)){
                        Toast.makeText(MainActivity.this, "Please confirm password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (password.length()<3){
                        Toast.makeText(MainActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                    }



                    if(pass.equals(repass)){
                        firebaseAuth.createUserWithEmailAndPassword(customergmail, pass)
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {



                                        if (!task.isSuccessful()) {
                                            Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                                        } else {

                                            Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        }
                                    }
                                });
                    }



                }
            });
    }
}