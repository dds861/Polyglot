package com.english.learnfast.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Rating {

    @PrimaryKey
    private int id_;
    private int hearts, questions;
    private boolean pass;

    public Rating(int id_, int hearts, int questions, boolean pass) {
        this.id_ = id_;
        this.hearts = hearts;
        this.questions = questions;
        this.pass = pass;
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

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
