package com.example.flashcard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswer = true;
    FlashcardDatabase flashcardDatabase;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        findViewById(R.id.question).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                View questionSideView = findViewById(R.id.question);
                View answerSideView = findViewById(R.id.answer);

// get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(2000);
                anim.start();
            }
        });

        findViewById(R.id.answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.question).setVisibility(View.VISIBLE);
                findViewById(R.id.answer).setVisibility(View.INVISIBLE);
            }
        });


        findViewById(R.id.choice1).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                findViewById(R.id.choice1).setBackgroundColor(getResources().getColor(R.color.red, null));
                findViewById(R.id.choice2).setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        findViewById(R.id.choice2).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                findViewById(R.id.choice2).setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        findViewById(R.id.choice3).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                findViewById(R.id.choice3).setBackgroundColor(getResources().getColor(R.color.red, null));;
                findViewById(R.id.choice2).setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });


        findViewById(R.id.hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowingAnswer == true) {
                    findViewById(R.id.choice1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.choice2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.choice3).setVisibility(View.INVISIBLE);
                    isShowingAnswer = false;
                    ((ImageView) findViewById(R.id.hide)).setImageResource(R.drawable.ic_iconmonstr_eye_5);
                } else {
                    findViewById(R.id.choice1).setVisibility(View.VISIBLE);
                    findViewById(R.id.choice2).setVisibility(View.VISIBLE);
                    findViewById(R.id.choice3).setVisibility(View.VISIBLE);
                    isShowingAnswer = true;
                    ((ImageView) findViewById(R.id.hide)).setImageResource(R.drawable.hide_answers);
                }
            }
        });



        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                        findViewById(R.id.question).startAnimation(leftOutAnim);
                        findViewById(R.id.question).setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                        findViewById(R.id.question2).setVisibility(View.VISIBLE);
                        findViewById(R.id.question2).startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }


                });
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
        } else {
            if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
                String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
                String string2 = data.getExtras().getString("string2");
                String string3 = data.getExtras().getString("string3");
                String string4 = data.getExtras().getString("string4");

                ((TextView) findViewById(R.id.question)).setText(string1);

                ((TextView) findViewById(R.id.answer)).setText(string2);

                ((TextView) findViewById(R.id.choice1)).setText(string3);

                ((TextView) findViewById(R.id.choice2)).setText(string2);

                ((TextView) findViewById(R.id.choice3)).setText(string4);
            }
        }
    }
}
