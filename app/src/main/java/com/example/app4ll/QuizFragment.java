package com.example.app4ll;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment implements View.OnClickListener {

    TextView totalquestions, question;
    Button ansA, ansB, ansC, ansD, submitBtn;
    int score = 0;
    int totalQuestion = questions.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    public static String questions[] = {
            "What are the main races in Malaysia?",
            "What kind of instrument is tabla? ",
            "What is Guzheng made out of?",
            "Thosai is a traditional food that belongs to which ethnic group?",
            "What festival that Chinese people celebrate every year?",
            "Which ethnic group does kompang belongs to?",
            "What is Roti Canai?",
            "Which ethnic group does Roti Canai belongs to?",
            "What color is Nasi Kerabu naturally?",
            "What malay instruments commonly used in weddings?"
    };

    public static String choices[][] = {
            {"Malay, Chinese, Indian", "Malay, Chinese ", "Malay, Iban, Chinese", "Malay, Indian, Iban"},
            {"Wind instrument", "Percussion", "String instrument", "Solid instrument"},
            {"Bamboo with strings of copper", "Wood with strings of copper", "Bamboo with strings of nylon/steel", "Wood with strings of nylon/steel"},
            {"Iban, Chinese, Indian, Malay:"},
            {"Chinese New Year", "Hari Raya Aidilfitri", "Deepavali", "Pesta Kaamatan"},
            {"Chinese", "Malay", "Indian", "Others"},
            {"Fermented rice-and-lentil pancake, Aromatic dish made with Basmathi rice, Flatbread that is crispy outside but soft inside, Flatbread that is crispy inside but soft outside"},
            {"Indian, Chinese, Malay, Kadazandusun"},
            {"pink, red, blue, rainbow"},
            {"marwas, gendang, guzheng, kompang"}
    };

    public static String correctAnswer[] = {
            "Malay, Chinese, Indian",
            "Percussion",
            "Wood with strings of nylon/steel",
            "Indian",
            "Chinese New Year",
            "Malay",
            "Flatbread that is crispy outside but soft inside",
            "Indian",
            "blue",
            "kompang"


    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        totalquestions = view.findViewById(R.id.total_question);
        question = view.findViewById(R.id.question);
        ansA = view.findViewById(R.id.ans_A);
        ansB = view.findViewById(R.id.ans_B);
        ansC = view.findViewById(R.id.ans_C);
        ansD = view.findViewById(R.id.ans_D);
        submitBtn = view.findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalquestions.setText("Total Questions: " + totalQuestion);
        loadnewQuestion();

        return view;
    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (selectedAnswer.equals(correctAnswer[currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            loadnewQuestion();

        } else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GRAY);
        }
    }

    private void loadnewQuestion() {

        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        question.setText(questions[currentQuestionIndex]);
        ansA.setText(choices[currentQuestionIndex][0]);
        ansB.setText(choices[currentQuestionIndex][1]);
        ansC.setText(choices[currentQuestionIndex][2]);
        ansD.setText(choices[currentQuestionIndex][3]);
        selectedAnswer = ""; // Reset selected answer for the new question
    }

    private void finishQuiz() {
        String passStatus = "";
        if (score > totalQuestion * 0.6) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }

        new AlertDialog.Builder(getContext())
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
