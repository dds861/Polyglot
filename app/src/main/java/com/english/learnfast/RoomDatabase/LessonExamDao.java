package com.english.learnfast.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.english.learnfast.Models.LessonExam;

import java.util.List;

@Dao
public interface LessonExamDao {

    @Query("SELECT * FROM LessonExam")
    List<LessonExam> getAll();

    @Query("SELECT * FROM LessonExam WHERE lesson = :data")
    List<LessonExam> getByLesson(int data);

    @Query("SELECT * FROM LessonExam WHERE id_ = :id_")
    LessonExam getLessonById(int id_);


    @Query("SELECT COUNT(*) FROM LessonExam WHERE lesson = :data")
    int getCountOfRowsByLesson(int data);
//
//    @Query("SELECT * FROM lessons WHERE login = :login")
//    LessonExam getByLogin(String login);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LessonExam data);

    @Update
    void update(LessonExam data);

    @Delete
    void delete(LessonExam data);
}
