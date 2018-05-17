package com.android.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    int total;
    int correct;
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
    Button button;
    private int backButtonCount = 0;
    IResult result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        checkBoxOne = findViewById(R.id.question_2_option_1_cb);
        checkBoxTwo = findViewById(R.id.question_2_option_2_cb);
        checkBoxThree = findViewById(R.id.question_2_option_3_cb);
        checkBoxFour = findViewById(R.id.question_2_option_4_cb);
        radioGroup[0] = findViewById(R.id.question_one_rg);
        radioGroup[1] = findViewById(R.id.question_three_rg);
        radioGroup[2] = findViewById(R.id.question_four_rg);
        radioGroup[3] = findViewById(R.id.question_five_rg);
        radioGroup[4] = findViewById(R.id.question_six_rg);
        radioGroup[5] = findViewById(R.id.question_seven_rg);
        radioGroup[6] = findViewById(R.id.question_eight_rg);
        radioGroup[7] = findViewById(R.id.question_nine_rg);
        editText = findViewById(R.id.question_ten_et);
        button = findViewById(R.id.submit_button);
        result = new Results();

        question[0] = new Questions(getString(R.string.question1_rg), getString(R.string.question1_option1_rb), getString(R.string.question1_option2_rb), getString(R.string.question1_option3_rb), getString(R.string.question1_option4_rb), new Answer(getString(R.string.question1_option2_rb)));
        question[1] = new Questions(getString(R.string.question3_rg), getString(R.string.question3_option1_rb), getString(R.string.question3_option2_rb), getString(R.string.question3_option3_rb), getString(R.string.question3_option4_rb), new Answer(getString(R.string.question3_option3_rb)));
        question[2] = new Questions(getString(R.string.question4_rg), getString(R.string.question4_option1_rb), getString(R.string.question4_option2_rb), getString(R.string.question4_option3_rb), getString(R.string.question4_option4_rb), new Answer(getString(R.string.question4_option4_rb)));
        question[3] = new Questions(getString(R.string.question5_rg), getString(R.string.question5_option1_rb), getString(R.string.question5_option2_rb), getString(R.string.question5_option3_rb), getString(R.string.question5_option4_rb), new Answer(getString(R.string.question5_option2_rb)));
        question[4] = new Questions(getString(R.string.question6_rg), getString(R.string.question6_option1_rb), getString(R.string.question6_option2_rb), "", "", new Answer(getString(R.string.question6_option2_rb)));
        question[5] = new Questions(getString(R.string.question7_rg), getString(R.string.question7_option1_rb), getString(R.string.question7_option2_rb), getString(R.string.question7_option3_rb), getString(R.string.question7_option4_rb), new Answer(getString(R.string.question7_option1_rb)));
        question[6] = new Questions(getString(R.string.question8_rg), getString(R.string.question18_option1_rb), getString(R.string.question8_option2_rb), getString(R.string.question8_option3_rb), getString(R.string.question8_option4_rb), new Answer(getString(R.string.question8_option4_rb)));
        question[7] = new Questions(getString(R.string.question9_rg), getString(R.string.question9_option1_rb), getString(R.string.question9_option2_rb), getString(R.string.question9_option3_rb), getString(R.string.question9_option4_rb), new Answer(getString(R.string.question9_option2_rb)));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
                result.showResult(correct, total);
                String msg = result.getMessage();
                Toast.makeText(QuizActivity.this, msg, Toast.LENGTH_LONG).show();
                total = 0;
                correct = 0;
            }
        });

    }

    void startQuiz() {
        String answers[] = new String[8];
        for (int i = 0 ; i < radioGroup.length; i++) {
            int selectedId = radioGroup[i].getCheckedRadioButtonId();
            if (selectedId == -1) {
                RadioButton selectedRadioButton = findViewById(R.id.q1op1);
                selectedRadioButton.setError("you have to answer this question");
            } else {
                RadioButton selectedRadioButton = findViewById(selectedId);
                answers[i] = selectedRadioButton.getText().toString();
            }
        }

        int counter = 0;
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

