package com.example.expergt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private QuesionBacnk mQuestionLibrary = new QuesionBacnk();

    TextView txtScore;
    TextView txtHiscore;
    TextView editTextTags;
    Button btnGoback,btnUpdate,btnRetest;
    private FirebaseAuth firebaseAuth;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ProgressDialog progressDialog;
    private int mclick[]= new int[4];
    private ArrayList<Integer> m4 = new ArrayList<>();
    private ArrayList<Integer> m7 = new ArrayList<>();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference mdatabase =  FirebaseDatabase.getInstance().getReference().child("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        progressDialog = new ProgressDialog(ResultActivity.this);
        progressDialog.setMessage("We let you in...");
        progressDialog.setTitle("Wait..");
        progressDialog.show();
        btnGoback = (Button)findViewById(R.id.btnGoback);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        final RecyclerView programmingList = findViewById(R.id.Listself);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().hide();
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener( this);




        btnGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(ResultActivity.this,RecycleActivity.class);
               startActivity(intent);
            }
        });



        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                programmingList.setLayoutManager(new LinearLayoutManager(ResultActivity.this));
                String[] str2 = {
                        "1.What were your difficulties during preparation?",
                        "2.How much time did you devote to each subject?",
                        "3.What did you do in your leisure time during preparation days?",
                        "4.How did you manage time for the preparation?",
                        "5.Syllabus completion date (from self study)?",
                        "6. At exam times how we complete whole syllabus?",
                        "7.How important are mock tests ?",
                        "8.How many mock tests you have prepared ?",
                        "9. How did the mock tests help you in the final exams?",
                        "10. How many times full syllabus should be revised?",
                        "11.How did you convert theoretical knowledge to application oriented knowledge to crack the exam?",
                        "12.What was your last minute strategy for the Gate exam?",
                        "13.What was your strategy on the examination day?",
                        "14.What is your message for future aspirants?"};
                String[] str = new String[14];
                for(int i=1;i<=14;i++) {


                    if(dataSnapshot.child(user.getUid()).child(String.valueOf(i)).getValue() != null) {
                        long a = (long) dataSnapshot.child(user.getUid()).child(String.valueOf(i)).getValue();
                        str[i-1] = mQuestionLibrary.getChoice((i-1), (int) a);
//                        m7.add((int) a);
                    }else{
                        str[i-1]="You have not attempted. ";
                    }
                }
//                for(int i=0;i<4;i++) {
//                    str[i] = mQuestionLibrary.getChoice(i,m7.get(i));
//                }

                programmingList.setAdapter(new ProgrammingAdapter(str2,str));
                progressDialog.dismiss();

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("You caught with an error...");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id==R.id.logout){
            finish();
            startActivity(new Intent(ResultActivity.this,WelcomeActivity.class));
        }

        return false;
    }

}
