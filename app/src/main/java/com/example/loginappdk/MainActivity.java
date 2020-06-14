package com.example.loginappdk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Email,Password;
    private Button login;
    private TextView attempts,Signup;
    private int count=5;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email=findViewById(R.id.etName);
        Password=findViewById(R.id.etPassword);
        login=findViewById(R.id.btn_login);
        Signup=findViewById(R.id.tv_signup);
        attempts=findViewById(R.id.tvattempts);

        String useremailma=Email.getText().toString().trim();
        String userpasswordma=Password.getText().toString().trim();


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });
        attempts.setText("No of attempts remaining : "+String.valueOf(count));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(),Password.getText().toString());
            }
        });

    }

    private void  validate(String Username,String Password)
    {
        if(Username.equals("Vibin") | Password.equals("1234"))
        {
            Intent i=new Intent(MainActivity.this,Secondactivity.class);
            startActivity(i);
        }
        else
        {
            count--;
            attempts.setText("No of attempts remaining : "+String.valueOf(count));
            if(count==0)
            {
                login.setEnabled(false);
            }

        }
    }
}