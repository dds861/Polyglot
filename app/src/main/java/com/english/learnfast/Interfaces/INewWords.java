package com.english.learnfast.Interfaces;

import java.util.List;

public interface INewWords {
    void setmmNewWordTv(String mNewWordTv);

    void onDoActionOnCorrectAnswer();

    void onDoActionOnNotCorrectAnswer();

    void setRandomMultipleAnswers(List<String> list);

    void onIncrementCorrect();

    void onIncrementIncorrect();
}
