package com.english.learnfast.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class LessonExam {

    @PrimaryKey
    private int id_;
    private String lesson, eng, rus;

    public LessonExam(int id_, String lesson, String eng, String rus) {
        this.id_ = id_;
        this.lesson = lesson;
        this.eng = eng;
        this.rus = rus;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getRus() {
        return rus;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }
}
