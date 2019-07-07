package com.example.expergt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private QuesionBacnk mQuestion = new QuesionBacnk();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ArrayList<Integer> m5 = new ArrayList<>();
    DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference().child("answer");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);


        final RecyclerView programmingList = findViewById(R.id.programmingList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().hide();
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener( this);

        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int array[][] = new int[14][4];
                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c0").child(String.valueOf(i)).getValue();
                    array[0][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c1").child(String.valueOf(i)).getValue();
                    array[1][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c2").child(String.valueOf(i)).getValue();
                    array[2][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c3").child(String.valueOf(i)).getValue();
                    array[3][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c4").child(String.valueOf(i)).getValue();
                    array[4][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c5").child(String.valueOf(i)).getValue();
                    array[5][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c6").child(String.valueOf(i)).getValue();
                    array[6][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c7").child(String.valueOf(i)).getValue();
                    array[7][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c8").child(String.valueOf(i)).getValue();
                    array[8][i]= (int) a;
                }
                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c9").child(String.valueOf(i)).getValue();
                    array[9][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c10").child(String.valueOf(i)).getValue();
                    array[10][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c11").child(String.valueOf(i)).getValue();
                    array[11][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c12").child(String.valueOf(i)).getValue();
                    array[12][i]= (int) a;
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c13").child(String.valueOf(i)).getValue();
                    array[13][i]= (int) a;
                }





                programmingList.setLayoutManager(new LinearLayoutManager(RecycleActivity.this));
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
                int arr[] = new int[4];

                for(int i=0;i<array.length;i++)
                {
                    int max = array[i][0],t=0;
                    for(int j=0;j<array[i].length;j++)
                    {
                        if(max < array[i][j])
                        {
                            max = array[i][j];
                            t=j;
                        }
                    }

                    m5.add(t);
                }

                for (int i = 0; i < 14; i++) {
                    str[i] = mQuestion.getChoice(i, (m5.get(i) + 1));
                }
                programmingList.setAdapter(new ProgrammingAdapter(str2, str));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("You caught with an error...");
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id==R.id.logout){
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(RecycleActivity.this,WelcomeActivity.class));
        }
        return false;
    }
}
