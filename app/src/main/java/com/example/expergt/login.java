package com.example.expergt;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;

public class login extends AppCompatActivity {

    private EditText Name, Password;
    private Button btnLogin;
    private TextView attempts,NewRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
//        progressDialog = new ProgressDialog(this);

        if(user != null){
//            finish();
//            startActivity(new Intent(login.this,SurActivity.class));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(login.this,"Hello registered.",Toast.LENGTH_SHORT).show();
//                System.out.print("Hello registered.");
                if(Name.getText().toString().isEmpty() || Password.getText().toString().isEmpty())
                {
                    Toast.makeText(login.this,"Please enter all the details.",Toast.LENGTH_SHORT).show();
                }else{
                    validate(Name.getText().toString(),Password.getText().toString());
//                checkEmailExistsOrNot();
                }


            }
        });

        NewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(login.this,Registration.class));
            }
        });

    }


   private void checkEmailExistsOrNot(){
        firebaseAuth.fetchSignInMethodsForEmail(Name.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                progressDialog.setMessage("We let you in...after you are verified...");

                if(task.isSuccessful()){
                    SignInMethodQueryResult result = task.getResult();
                    List<String> signInMethods = result.getSignInMethods();
                    if(signInMethods.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD))
                    {
                        Toast.makeText(login.this,"Already visited.",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        startActivity(new Intent(login.this,ResultActivity.class));
//                    validate(Name.getText().toString(),Password.getText().toString());

                    }else{
                        Toast.makeText(login.this,"Not visited.",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        validate(Name.getText().toString(),Password.getText().toString());
//                    startActivity(new Intent(login.this,ResultActivity.class));
                    }

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }


    private void setupUIViews(){
        Name = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnlogin);
        attempts = (TextView)findViewById(R.id.tvAttempts);
        NewRegistration = (TextView)findViewById(R.id.tvSignup);
//        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
//        navigationView.setNavigationItemSelectedListener( this);
        attempts.setText("No. of remaining attempts: 5");
    }

    private void validate(String userName, String userPassword){

        progressDialog = new ProgressDialog(login.this);
        progressDialog.setMessage("We let you in...after you are verified...");
        progressDialog.setTitle("Wait..");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    checkEMailVerification();
//                    Toast.makeText(login.this,"Login Successful..",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(login.this,ResultActivity.class));
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(login.this,"Login Failed.",Toast.LENGTH_SHORT).show();
                    counter--;
                    attempts.setText("NO. of remaining attempts: " + counter);
                    if(counter==0){
                        progressDialog.dismiss();
                        btnLogin.setEnabled(false);
                    }
                }
            }
        });

    }


    private void checkEMailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();

        if(emailFlag){
            finish();
            Toast.makeText(login.this,"Login Successful..",Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(login.this,SurActivity.class));
        }else{
            Toast.makeText(this,"Verify your email.",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
