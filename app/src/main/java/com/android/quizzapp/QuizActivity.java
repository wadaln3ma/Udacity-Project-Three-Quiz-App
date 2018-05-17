package com.android.quizzapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    int total;
    int correct;
    int counter = 0;
    RadioGroup[] radioGroup = new RadioGroup[8];
    CheckBox checkBoxOne;
    CheckBox checkBoxTwo;
    CheckBox checkBoxThree;
    CheckBox checkBoxFour;
    EditText editText;
    boolean checkOptionOne;
    boolean checkOptionTwo;
    boolean checkOptionThree;
    boolean checkOptionFour;
    Questions[] question = new Questions[8];
    private int backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        checkBoxOne = findViewById(R.id.q2op1);
        checkBoxTwo = findViewById(R.id.q2op2);
        checkBoxThree = findViewById(R.id.q2op3);
        checkBoxFour = findViewById(R.id.q2op4);
        radioGroup[0] = findViewById(R.id.question_one);
        radioGroup[1] = findViewById(R.id.question_three);
        radioGroup[2] = findViewById(R.id.question_four);
        radioGroup[3] = findViewById(R.id.question_five);
        radioGroup[4] = findViewById(R.id.question_six);
        radioGroup[5] = findViewById(R.id.question_seven);
        radioGroup[6] = findViewById(R.id.question_eight);
        radioGroup[7] = findViewById(R.id.question_nine);
        editText = findViewById(R.id.question_ten_edit);

        question[0] = new Questions(getString(R.string.question1), getString(R.string.q1op1), getString(R.string.q1op2), getString(R.string.q1op3), getString(R.string.q1op4), new Answer(getString(R.string.q1op2)));
        question[1] = new Questions(getString(R.string.question3), getString(R.string.q3op1), getString(R.string.q3op2), getString(R.string.q3op3), getString(R.string.q3op4), new Answer(getString(R.string.q3op3)));
        question[2] = new Questions(getString(R.string.question4), getString(R.string.q4op1), getString(R.string.q4op2), getString(R.string.q4op3), getString(R.string.q4op4), new Answer(getString(R.string.q4op4)));
        question[3] = new Questions(getString(R.string.question5), getString(R.string.q5op1), getString(R.string.q5op2), getString(R.string.q5op3), getString(R.string.q5op4), new Answer(getString(R.string.q5op2)));
        question[4] = new Questions(getString(R.string.question6), getString(R.string.q6op1), getString(R.string.q6op2), "", "", new Answer(getString(R.string.q6op2)));
        question[5] = new Questions(getString(R.string.question7), getString(R.string.q7op1), getString(R.string.q7op2), getString(R.string.q7op3), getString(R.string.q7op4), new Answer(getString(R.string.q7op1)));
        question[6] = new Questions(getString(R.string.question8), getString(R.string.q8op1), getString(R.string.q8op2), getString(R.string.q8op3), getString(R.string.q8op4), new Answer(getString(R.string.q8op4)));
        question[7] = new Questions(getString(R.string.question9), getString(R.string.q9op1), getString(R.string.q9op2), getString(R.string.q9op3), getString(R.string.q9op4), new Answer(getString(R.string.q9op2)));

    }

    void startQuiz() {
        String answers[] = new String[8];
        for (RadioGroup r : radioGroup) {
            int selectedId = r.getCheckedRadioButtonId();
            if (selectedId == -1) {
                RadioButton selectedRadioButton = findViewById(R.id.q1op1);
                selectedRadioButton.setError("you have to answer this question");
            } else {
                RadioButton selectedRadioButton = findViewById(selectedId);
                answers[counter] = selectedRadioButton.getText().toString();
            }

            counter++;
        }

        counter = 0;
        for (Questions q : question) {
            if (q.getAnswer1().getAnswer().equals(answers[counter])) {
                correct++;
            }
            total++;
            counter++;
        }

        checkOptionOne = checkBoxOne.isChecked();
        checkOptionTwo = checkBoxTwo.isChecked();
        checkOptionThree = checkBoxThree.isChecked();
        checkOptionFour = checkBoxFour.isChecked();
        if (!checkOptionOne && checkOptionTwo && !checkOptionThree && checkOptionFour) {
            correct++;
        }
        total++;

        String questionTenAnswer = editText.getText().toString();
        if (questionTenAnswer.equals(getString(R.string.question_ten_answer))) {
            correct++;
        }
        total++;
    }

    public void submitAnswers(View view) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Submit Answers")
                .setMessage("Are you sure you want to submit these answers?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startQuiz();
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("correct", correct);
                        intent.putExtra("total", total);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info).show();
    }

    @Override
    public void onBackPressed() {
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

}

