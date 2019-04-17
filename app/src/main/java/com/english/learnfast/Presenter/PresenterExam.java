package com.english.learnfast.Presenter;

import com.english.learnfast.Interfaces.IExam;
import com.english.learnfast.Models.LessonExam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PresenterExam {
    private IExam iExam;
    private LessonExam lessonExams;
    private PresenterDb presenterDb = new PresenterDb();
    //    private RandomData randomData;
    private int correctPosition;
    private int iteratorOfWords = 0;
    private String[] correctAnswerWords;
    private int lessonId;

    public PresenterExam(IExam iExam, int lessonId) {
        this.iExam = iExam;

        this.lessonId = lessonId;

//        randomData = new RandomData(lessonExams);
    }


    public void askQuestion() {

        correctPosition = getRandomNumber();
        lessonExams = presenterDb.getLessonById(getRandomNumber());

        //set question
        iExam.setmExamRusTv(lessonExams.getRus());

        //get correctWord
        correctAnswerWords = lessonExams.getEng().split(" ");

        iExam.setRandomMultipleAnswers(getRandomEngWords());
    }


    private boolean isAnswerCorrect(String answer) {
        return correctAnswerWords[iteratorOfWords].replaceAll("[^a-zA-Z0-9]", "").equals(answer);
    }

    private List<String> getRandomEngWords() {

        List<String> engWords = new ArrayList<>();

        //add correctWord
        engWords.add(correctAnswerWords[iteratorOfWords].replaceAll("[^a-zA-Z0-9]", ""));

        //add randomWords
        for (int i = 0; i < 7; i++) {
            String randomEngWord = getRandomWord();
            //check duplicates
            if (engWords.contains(randomEngWord)) {
                i--;
            } else {
                engWords.add(randomEngWord);
            }
        }

        Collections.shuffle(engWords);
        return engWords;
    }


    public String getRandomWord() {

        LessonExam lessonExam = presenterDb.getLessonById(getRandomNumber());
        String engSentence = lessonExam.getEng();
        String[] engWords = engSentence.split(" ");
        String randomEngWord = engWords[getRandomNumber(0, engWords.length - 1)];

        return randomEngWord.replaceAll("[^a-zA-Z0-9]", "");
    }

    private int getRandomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private int getRandomNumber() {

        int min = 0;
        for (int i = 1; i < lessonId; i++) {
            min += presenterDb.getLessonCountOfRows(i);
        }

        int max = min + presenterDb.getLessonCountOfRows(lessonId);
        int i = new Random().nextInt((max - min) + 1) + min;
        return i;
    }

    public void variantClicked(String answer) {
        if (isAnswerCorrect(answer)) {
            iteratorOfWords++;
            iExam.setmExamEngTv(iExam.getmExamEngTv() + " " + answer);
            if (iteratorOfWords < correctAnswerWords.length) {

                iExam.setRandomMultipleAnswers(getRandomEngWords());

            } else {
                iteratorOfWords = 0;

                iExam.onDecrementQuestion();
                iExam.onDoActionOnCorrectAnswer();
            }
        } else {
            iExam.onDecrementHeart();
            iExam.setRandomMultipleAnswers(getRandomEngWords());
            iExam.onDoActionOnNotCorrectAnswer();
        }

    }
}

