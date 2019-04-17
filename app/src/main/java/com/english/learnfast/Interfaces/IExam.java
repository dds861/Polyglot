package com.english.learnfast.Interfaces;

import java.util.List;

public interface IExam {
    void setmExamRusTv(String mExamRusTv);

    void setmExamEngTv(String mExamEngTv);

    String getmExamEngTv();

    void onDoActionOnCorrectAnswer();

    void onDecrementHeart();

    void onDecrementQuestion();

    void setRandomMultipleAnswers(List<String> list);

    void onDoActionOnNotCorrectAnswer();
}
