package com.example.app4ll;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalquestions, question;
    Button ansA, ansB, ansC, ansD, submitBtn;
    int score = 0;
    int totalQuestion = QuizFragment.questions.length;
    int currentQuestionIndex = 0;
    String selectedAnswer="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Only add the fragment if the activity is newly created
            // to avoid overlapping fragments on configuration changes.
            Fragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, homeFragment);
            fragmentTransaction.commit();
        }
        totalquestions = findViewById(R.id.total_question);
        question = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalquestions.setText("Total Questions: " + totalQuestion);
        loadnewQuestion();

    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if (clickedButton.getId() == R.id.submit_btn) {
            if(selectedAnswer.equals(QuizFragment.correctAnswer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadnewQuestion();

        }else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GRAY);
        }
    }
    private void loadnewQuestion() {

        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        question.setText(QuizFragment.questions[currentQuestionIndex]);
        ansA.setText(QuizFragment.choices[currentQuestionIndex][0]);
        ansB.setText(QuizFragment.choices[currentQuestionIndex][1]);
        ansC.setText(QuizFragment.choices[currentQuestionIndex][2]);
        ansD.setText(QuizFragment.choices[currentQuestionIndex][3]);

    }

    private void finishQuiz() {
        String passStatus = "";
        if (score > totalQuestion*0.6) {
            passStatus = "Passed";
        }else {
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your score is: " + score + " out of " + totalQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadnewQuestion();
    }


}
