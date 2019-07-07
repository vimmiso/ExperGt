package com.example.expergt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SurActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private QuesionBacnk mQuestionLibrary = new QuesionBacnk();
    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private Button logout,next;
    private FirebaseDatabase mRootf;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference().child("answer");

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private int mclick= 0;
    private int m1[]=new int[14];
    private int m2[]=new int[14];
     private ArrayList<Integer> m3 = new ArrayList<>();

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sur);
        init();
        updateQuestion();
        onStart1();
        //updateScore(mScore);
    }


    protected void onStart1() {

        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c0").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(0,(i+1), (int) a);
//                    int b = (int) dataSnapshot.child("array").child("c1").child(String.valueOf(i)).getValue();
//                    mQuestionLibrary.setClick(i,2,b);
//                    int c = (int) dataSnapshot.child("array").child("c2").child(String.valueOf(i)).getValue();
//                    mQuestionLibrary.setClick(i,3,c);
//                    int d = (int) dataSnapshot.child("array").child("c3").child(String.valueOf(i)).getValue();
//                    mQuestionLibrary.setClick(i,4,d);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c1").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(1,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c2").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(2,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c3").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c4").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c5").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c6").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c7").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c8").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c9").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c10").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c11").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c12").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }

                for(int i=0;i<4;i++) {

                    long a = (long) dataSnapshot.child("c13").child(String.valueOf(i)).getValue();
                    mQuestionLibrary.setClick(3,(i+1),(int) a);
                }



            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("You caught with an error...");
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuestionNumber=15;
                updateQuestion();
//                startActivity(new Intent(SurActivity.this,RecycleActivity.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mQuestionNumber++;
                updateQuestion();
            }
        });
    }



    public void init()
    {
        mScoreView = (TextView)findViewById(R.id.txtScore);
        mQuestionView = (TextView)findViewById(R.id.txtQuestion);
        mButtonChoice1 = (Button)findViewById(R.id.btnOption1);
        mButtonChoice2 = (Button)findViewById(R.id.btnOption2);
        mButtonChoice3 = (Button)findViewById(R.id.btnOption3);
        mButtonChoice4 = (Button)findViewById(R.id.btnOption4);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener( this);
        logout = (Button)findViewById(R.id.btnlogout);
        next = (Button)findViewById(R.id.btnNext);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateQuestion(){
        if(mQuestionNumber<mQuestionLibrary.getLength()){
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber,1));
            mButtonChoice1.setTag(1);
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber,2));
            mButtonChoice2.setTag(2);
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber,3));
            mButtonChoice3.setTag(3);
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber,4));
            mButtonChoice4.setTag(4);
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
//            mclick = mQuestionLibrary.getClick(mQuestionNumber,)
            mQuestionNumber++;

//            for(int i=0;i<4;i++)
//            {
//                Button btn = new Button(getApplicationContext());
//                btn.setTag(i);
//            }
        }
        else {

            int array[][] = new int[14][4];
            int arr[] = new int[4];

            for(int i=0;i<array.length;i++)
            {
                for(int j=1;j<5;j++) {
                    array[i][j-1] = mQuestionLibrary.getClick(i, j);
                }
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

                m3.add(t);
                m2[i]=max;
            }


            for(int i=0;i<4;i++)
            {
                mdatabase.child("c0").child(String.valueOf(i)).setValue(array[0][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c1").child(String.valueOf(i)).setValue(array[1][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c2").child(String.valueOf(i)).setValue(array[2][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c3").child(String.valueOf(i)).setValue(array[3][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c4").child(String.valueOf(i)).setValue(array[4][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c5").child(String.valueOf(i)).setValue(array[5][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c6").child(String.valueOf(i)).setValue(array[6][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c7").child(String.valueOf(i)).setValue(array[7][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c8").child(String.valueOf(i)).setValue(array[8][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c9").child(String.valueOf(i)).setValue(array[9][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c10").child(String.valueOf(i)).setValue(array[10][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c11").child(String.valueOf(i)).setValue(array[11][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c12").child(String.valueOf(i)).setValue(array[12][i]);
            }
            for(int i=0;i<4;i++)
            {
                mdatabase.child("c13").child(String.valueOf(i)).setValue(array[13][i]);
            }


            Toast.makeText(SurActivity.this, "It was the last question.", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
            finish();
            Intent intent = new Intent(SurActivity.this,ResultActivity.class);
//            intent.putIntegerArrayListExtra("score",m3);
            startActivity(intent);
        }
    }

//    private void updateScore(int point)
//    {
//        mScoreView.setText("Score: "+mclick+"/"+mQuestionLibrary.getLength());
//    }

    public void onClick(View view)
    {
        Button answer = (Button) view;
//        if(answer.getText()==mAnswer){
            mScore=mScore+1;
            mclick = mQuestionLibrary.getClick((mQuestionNumber-1), (Integer) answer.getTag());
            mclick=mclick+1;
            mQuestionLibrary.setClick((mQuestionNumber-1), (Integer) answer.getTag(),mclick);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mdatabase = firebaseDatabase.getReference();
        mdatabase.child("User").child(firebaseAuth.getUid()).child(String.valueOf(mQuestionNumber)).setValue((Integer)answer.getTag());
        updateQuestion();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id==R.id.logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(SurActivity.this,login.class));
        }
        return false;
    }
}
