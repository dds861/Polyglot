package com.english.learnfast.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.english.learnfast.Models.RatingNewWords;

import java.util.List;

@Dao
public interface RatingNewWordsDao {

    @Query("SELECT * FROM RatingNewWords")
    List<RatingNewWords> getAll();

    @Query("SELECT * FROM RatingNewWords WHERE id_ = :id_")
    RatingNewWords getById(int id_);

    @Query("SELECT COUNT(*) FROM RatingNewWords")
    int getCountOfRows();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RatingNewWords data);

    @Update
    void updateRatingByLessonId(RatingNewWords data);

    @Query("UPDATE RatingNewWords SET hearts = :hearts WHERE id_ = :id")
    void updateRatingHeartByLessonId(int id, int hearts);

    @Query("UPDATE RatingNewWords SET questions = :questions WHERE id_ = :id")
    void updateRatingQuestionByLessonId(int id, int questions);

    @Delete
    void delete(RatingNewWords data);


}
