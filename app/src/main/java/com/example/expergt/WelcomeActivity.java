package com.example.expergt;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    private TextView welcomeNotes;
    private Button Gts,Gtr;
    private ArrayList<Integer> m6 = new ArrayList<>();
    DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference().child("answer");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setupUIViews();
        onStart2();
        Gts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation hyperspaceJump = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.first_anim);
                Gts.startAnimation(hyperspaceJump);
                 startActivity(new Intent(WelcomeActivity.this,login.class));
            }
        });

        Gtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onStart2();
                Animation hyperspaceJump = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.first_anim);
                Gtr.startAnimation(hyperspaceJump);
                Intent intent = new Intent(WelcomeActivity.this,RecycleActivity.class);
                intent.putIntegerArrayListExtra("score",m6);
                startActivity(intent);
//                startActivity(new Intent(WelcomeActivity.this,ResultActivity.class));
            }
        });
    }


    protected void onStart2() {

        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int array[][] = new int[14][4];
                int arr[] = new int[4];
                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c0").child(String.valueOf(i)).getValue();
                    array[0][i]= (int) a;

                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c1").child(String.valueOf(i)).getValue();
                    array[1][i]= (int) a;
//                    mQuestionLibrary.setClick(1, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c2").child(String.valueOf(i)).getValue();
                    array[2][i]= (int) a;
//                    mQuestionLibrary.setClick(2, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c3").child(String.valueOf(i)).getValue();
                    array[3][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c4").child(String.valueOf(i)).getValue();
                    array[4][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c5").child(String.valueOf(i)).getValue();
                    array[5][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c6").child(String.valueOf(i)).getValue();
                    array[6][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c7").child(String.valueOf(i)).getValue();
                    array[7][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c8").child(String.valueOf(i)).getValue();
                    array[8][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c9").child(String.valueOf(i)).getValue();
                    array[9][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c10").child(String.valueOf(i)).getValue();
                    array[10][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c11").child(String.valueOf(i)).getValue();
                    array[11][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c12").child(String.valueOf(i)).getValue();
                    array[12][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }

                for (int i = 0; i < 4; i++) {

                    long a = (long) dataSnapshot.child("c13").child(String.valueOf(i)).getValue();
                    array[13][i]= (int) a;
//                    mQuestionLibrary.setClick(3, (i + 1), (int) a);
                }


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

                    m6.add(t);
//                    m2[i]=max;
                }


//                Intent intent = new Intent(WelcomeActivity.this,ResultActivity.class);
//                intent.putIntegerArrayListExtra("score",m6);
//                startActivity(intent);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("You caught with an error...");
            }
        });
    }





        private void setupUIViews(){
        welcomeNotes = (TextView)findViewById(R.id.tvIntro);
        Gts = (Button)findViewById(R.id.btnGts);
        Gtr = (Button)findViewById(R.id.btnGtr);
    }
}
