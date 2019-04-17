package com.english.learnfast.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.english.learnfast.Models.Rating;

import java.util.List;

@Dao
public interface RatingDao {

    @Query("SELECT * FROM Rating")
    List<Rating> getAll();

    @Query("SELECT * FROM Rating WHERE id_ = :id_")
    Rating getRatingByLessonId(int id_);

    @Query("SELECT hearts FROM Rating WHERE id_ = :id_")
    int getHeartRatingByLessonId(int id_);

    @Query("SELECT questions FROM Rating WHERE id_ = :id_")
    int getQuestionsRatingByLessonId(int id_);

    @Query("SELECT pass FROM Rating WHERE id_ = :id_")
    boolean getPassRatingByLessonId(int id_);

    @Query("SELECT COUNT(*) FROM Rating")
    int getCountOfRows();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Rating data);

    @Update
    void updateRating(Rating data);

    @Query("UPDATE Rating SET hearts = :hearts WHERE id_ = :id")
    void updateRatingHeartByLessonId(int id, int hearts);

    @Query("UPDATE Rating SET  questions = :questions WHERE id_ = :id")
    void updateRatingQuestionByLessonId(int id, int questions);

    @Delete
    void delete(Rating data);

    @Query("UPDATE Rating SET pass = :pass WHERE id_ = :id")
    void updateRatingPassByLessonId(int id, boolean pass);


}
