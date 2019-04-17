package com.english.learnfast.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.english.learnfast.Models.LessonExam;
import com.english.learnfast.Models.NewWords;
import com.english.learnfast.Models.Rating;
import com.english.learnfast.Models.RatingNewWords;

@Database(entities = {LessonExam.class, NewWords.class, Rating.class, RatingNewWords.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LessonExamDao lessonExamDao();
    public abstract NewWordsDao newWordsDao();
    public abstract RatingDao ratingDao();
    public abstract RatingNewWordsDao ratingNewWordsDao();


}

