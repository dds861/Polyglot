package com.english.learnfast.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RatingNewWords {

    @PrimaryKey
    private int id_;
    private int hearts, questions;

    public RatingNewWords(int id_, int hearts, int questions) {
        this.id_ = id_;
        this.hearts = hearts;
        this.questions = questions;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }
}
