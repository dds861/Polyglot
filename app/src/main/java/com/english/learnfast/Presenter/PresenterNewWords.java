package com.english.learnfast.Presenter;

import android.util.Log;

import com.english.learnfast.Interfaces.INewWords;
import com.english.learnfast.Models.NewWords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PresenterNewWords {
    private INewWords iNewWords;
    private PresenterDb presenterDb;
    private int correctPosition;
    private NewWords newWords;

    public PresenterNewWords(INewWords iNewWords) {
        this.iNewWords = iNewWords;
        presenterDb = new PresenterDb();

    }


    public void askQuestion() {
        correctPosition = getRandomNumber();
        newWords = presenterDb.getNewWordsById(correctPosition);

        iNewWords.setmmNewWordTv(newWords.getEng());
        iNewWords.setRandomMultipleAnswers(getRandomMultipleAnswers());
    }

    private int getRandomNumber() {
        int max = presenterDb.getNewWordsCountOfRows();
        int min = 1;
        return new Random().nextInt((max - min) + 1) + min;
    }

    private boolean isAnswerCorrect(String answer) {
        return newWords.getRus().equals(answer);
    }

    private List<String> getRandomMultipleAnswers() {

        List<String> multipleAnswers = new ArrayList<>();
        multipleAnswers.add(newWords.getRus());
        //add random words to list
        for (int i = 0; i < 5; i++) {
            try {
                String temp = presenterDb.getNewWordsById(getRandomNumber()).getRus();
                //String temp = randomNewWord.getRandomWord().replaceAll("[^a-zA-Z0-9]", "");
                //check if the list do not contain similar random words.
                if (multipleAnswers.contains(temp)) {
                    i--;
                } else {
                    multipleAnswers.add(temp);
                }
            } catch (Exception e) {
                Log.i("autolog", "e: " + e);
                getRandomMultipleAnswers();
            }

        }

        Collections.shuffle(multipleAnswers);

        return multipleAnswers;
    }

    public void variantClicked(String answer) {

        if (isAnswerCorrect(answer)) {
            iNewWords.onIncrementCorrect();
            askQuestion();
            iNewWords.onDoActionOnCorrectAnswer();
        } else {
            iNewWords.onIncrementIncorrect();
            iNewWords.setRandomMultipleAnswers(getRandomMultipleAnswers());
            iNewWords.onDoActionOnNotCorrectAnswer();
        }

    }
}

