package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA,ansB;
    Button submitbtn;

    int score = 0;
    int totalQuestions = QuestionAnswer.questions.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        totalQuestionsTextView=findViewById(R.id.totalq);
        questionTextView=findViewById(R.id.question);
        ansA=findViewById(R.id.ansA);
        ansB=findViewById(R.id.ansB);
        submitbtn=findViewById(R.id.submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        submitbtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total Questions "+totalQuestions);

        loadNewQuestions();


    }

    @Override
    public void onClick(View v) {

        ansA.setBackground(getDrawable(R.drawable.ans_background));
        ansB.setBackground(getDrawable(R.drawable.ans_background));

        Button clickedButton = (Button) v;
        if (clickedButton.getId()==R.id.submit){

            if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                int s=score++;
            }
            currentQuestionIndex++;
            loadNewQuestions();


        }else {

            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackground(getDrawable(R.drawable.selected_bg));

        }

    }

    void loadNewQuestions(){

        if (currentQuestionIndex==totalQuestions){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.questions[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);

    }

    private void finishQuiz() {

        new AlertDialog.Builder(this).setTitle("End Of Quiz")
                .setMessage("Score is "+score+" Out of "+totalQuestions+" Questions")
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){
        Intent intent=new Intent(Homepage.this,MainActivity.class);
        startActivity(intent);
    }


}