package com.example.expergt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class Registration extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private Button regButton,verifyButton;
    private TextView backLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupViews();

        firebaseAuth = FirebaseAuth.getInstance();

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(Registration.this);
                progressDialog.setMessage("We are sending verification mail...");
                progressDialog.setTitle("Wait..");
                progressDialog.show();
                if(validate()){
                    final String user_email = userEmail.getText().toString().trim();
                    final String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                if(user_email.isEmpty()||user_password.isEmpty()){
                                    Toast.makeText(Registration.this,"Please enter all the details.",Toast.LENGTH_SHORT).show();
                                }else {
                                    verifyButton.setEnabled(false);
//                                    Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
//                                    finish();
//                                    startActivity(new Intent(Registration.this, SurActivity.class));
                                }
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(Registration.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
                        Boolean emailFlag = firebaseUser.isEmailVerified();
                        if(emailFlag){
                            progressDialog.dismiss();
                            finish();
                            Toast.makeText(Registration.this,"Registration Successful..",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registration.this,SurActivity.class));
                        }else{

                            Toast.makeText(Registration.this,"Verify your email.",Toast.LENGTH_SHORT).show();
                            firebaseAuth.signOut();
                        }
                    }
                });

            }
        });


        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Registration.this,login.class));
            }
        });
    }

    private void setupViews(){
        userEmail = (EditText)findViewById(R.id.etNewEmail);
        userPassword = (EditText)findViewById(R.id.etNewPassword);
        regButton = (Button)findViewById(R.id.btnRegister);
        regButton.setEnabled(false);
        verifyButton = (Button)findViewById(R.id.btVerify);
        backLogin = (TextView)findViewById(R.id.tvLoginpage);
    }

    private Boolean validate(){
        Boolean result = false;
        String name = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if(name.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please enter the all details", Toast.LENGTH_SHORT);
        }else{
            result = true;
        }
        return result;
    }


    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Registration.this," Verification email is sent.",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();

                        progressDialog.dismiss();
                       regButton.setEnabled(true);
                    }else{
                        Toast.makeText(Registration.this,"Verification email is not sent.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
