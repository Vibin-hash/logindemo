package com.example.loginappdk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class RegistrationActivity extends AppCompatActivity {

    EditText Name,Email,Password;
    Button regButton;
    TextView UserLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Name=findViewById(R.id.ra_name);
        Email=findViewById(R.id.ra_email);
        Password=findViewById(R.id.ra_password);
        regButton=findViewById(R.id.btn_signup);
        UserLogin=findViewById(R.id.ra_gotologinpage);

        firebaseAuth=FirebaseAuth.getInstance();


        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    String userEmail=Email.getText().toString().trim();
                    String userPassword=Password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registration Successfull ", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }

    private Boolean validate()
    {
        Boolean result=false;
        String name=Name.getText().toString();
        String password=Password.getText().toString();
        String email=Email.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty())
        {
            Toast.makeText(RegistrationActivity.this,"Enter all the details",Toast.LENGTH_SHORT);
        }
        else
        {
            result=true;
        }
        return result;

    }
}