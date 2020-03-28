package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String passedQ = ((EditText) findViewById(R.id.new_question)).getText().toString();
                String passedA = ((EditText) findViewById(R.id.new_answer)).getText().toString();
                String passedW1 = ((EditText) findViewById(R.id.wrong1)).getText().toString();
                String passedW2 = ((EditText) findViewById(R.id.wrong2)).getText().toString();

                if(passedA.equals("") || passedQ.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter both question and answer!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent data = new Intent(); // create a new Intent, this is where we will put our data
                    data.putExtra("string1", passedQ); // puts one string into the Intent, with the key as 'string1'
                    data.putExtra("string2", passedA); // puts another string into the Intent, with the key as 'string2
                    data.putExtra("string3", passedW1);
                    data.putExtra("string4", passedW2);
                    setResult(RESULT_OK, data); // set result code and bundle data for response
                    finish(); // closes this activity and pass data to the original activity that launched this activity
                }
            }
        });

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
