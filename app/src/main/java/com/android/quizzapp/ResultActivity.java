package com.android.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {
    TextView result;
    IResult res = new Results();
    private int correct;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = findViewById(R.id.result);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            correct = extras.getInt("correct");
            total = extras.getInt("total");
        }

        res.showResult(correct, total);
        String msg = res.getMessage();
        result.setText(msg);
    }

    public void restartQuiz(View view) {
        Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    public void onBackPressed(){
        //do nothing
    }

}