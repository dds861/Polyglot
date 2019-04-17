package com.english.learnfast.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.english.learnfast.Models.NewWords;

import java.util.List;

@Dao
public interface NewWordsDao {

    @Query("SELECT * FROM NewWords")
    List<NewWords> getAll();

    @Query("SELECT * FROM NewWords WHERE id_ = :id_")
    NewWords getById(int id_);

    @Query("SELECT COUNT(*) FROM NewWords")
    int getCountOfRows();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewWords data);

    @Update
    void update(NewWords data);

    @Delete
    void delete(NewWords data);
}
