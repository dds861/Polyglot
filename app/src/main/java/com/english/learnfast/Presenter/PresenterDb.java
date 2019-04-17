package com.english.learnfast.Presenter;

import com.english.learnfast.Models.LessonExam;
import com.english.learnfast.Models.NewWords;
import com.english.learnfast.Models.Rating;
import com.english.learnfast.Models.RatingNewWords;
import com.english.learnfast.RoomDatabase.App;
import com.english.learnfast.RoomDatabase.AppDatabase;
import com.english.learnfast.RoomDatabase.LessonExamDao;
import com.english.learnfast.RoomDatabase.NewWordsDao;
import com.english.learnfast.RoomDatabase.RatingDao;
import com.english.learnfast.RoomDatabase.RatingNewWordsDao;

import java.util.List;

public class PresenterDb {
    private AppDatabase db;
    private LessonExamDao lessonExamDao;
    private NewWordsDao newWordsDao;
    private RatingDao ratingDao;
    private RatingNewWordsDao ratingNewWordsDao;

    public PresenterDb() {
        this.db = App.getInstance().getDatabase();
        this.lessonExamDao = db.lessonExamDao();
        this.newWordsDao = db.newWordsDao();
        this.ratingDao = db.ratingDao();
        this.ratingNewWordsDao = db.ratingNewWordsDao();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Lesson
    ///////////////////////////////////////////////////////////////////////////
    public void insertLesson(LessonExam githubUserRoom) {
        if (githubUserRoom != null) {
            lessonExamDao.insert(githubUserRoom);
        }
    }

    public void updateLesson(LessonExam githubUserRoom) {
        if (githubUserRoom != null) {
            lessonExamDao.update(githubUserRoom);
        }
    }

    public void deleteLesson(LessonExam githubUser) {
        if (githubUser != null) {
            lessonExamDao.delete(githubUser);
        }
    }

    public List<LessonExam> getAllLessonExam() {
        return lessonExamDao.getAll();
    }

    public List<LessonExam> getByLesson(int data) {
        return lessonExamDao.getByLesson(data);
    }

    public LessonExam getLessonById(int id_) {
        return lessonExamDao.getLessonById(id_);
    }

    public int getLessonCountOfRows(int lessonId) {
        return lessonExamDao.getCountOfRowsByLesson(lessonId);
    }

    ///////////////////////////////////////////////////////////////////////////
    // NewWords
    ///////////////////////////////////////////////////////////////////////////

    public List<NewWords> getAllNewWords() {
        return newWordsDao.getAll();
    }

    public NewWords getNewWordsById(int id) {
        return newWordsDao.getById(id);
    }

    public int getNewWordsCountOfRows() {
        return newWordsDao.getCountOfRows();
    }

    ///////////////////////////////////////////////////////////////////////////
    // RatingExam
    ///////////////////////////////////////////////////////////////////////////

    public Rating getRatingByLessonId(int lessonId) {
        return ratingDao.getRatingByLessonId(lessonId);
    }

    public int getHeartRatingByLessonId(int lessonId) {
        return ratingDao.getHeartRatingByLessonId(lessonId);
    }

    public int getQuestionsRatingByLessonId(int lessonId) {
        return ratingDao.getQuestionsRatingByLessonId(lessonId);
    }

    public void updateRatingHeartByLessonId(int lessonId, int heart) {
        ratingDao.updateRatingHeartByLessonId(lessonId, heart);
    }

    public void updateRatingQuestionByLessonId(int lessonId, int heart) {
        ratingDao.updateRatingQuestionByLessonId(lessonId, heart);
    }

    public void insertRating(Rating rating) {
        if (rating != null) {
            ratingDao.insert(rating);
        }
    }

    public void updateRatingPassByLessonId(int lessonId, boolean pass) {
        ratingDao.updateRatingPassByLessonId(lessonId,pass);
    }

    public boolean getPassRatingByLessonId(int lessonId) {
        return ratingDao.getPassRatingByLessonId(lessonId);
    }

    ///////////////////////////////////////////////////////////////////////////
    // RatingNewWords
    ///////////////////////////////////////////////////////////////////////////

    public RatingNewWords getRatingNewWordsByLessonId(int lessonId) {
        return ratingNewWordsDao.getById(lessonId);
    }

    public void updateRatingNewWordsCorrectByLessonId(int lessonId, int heart) {
        ratingNewWordsDao.updateRatingHeartByLessonId(lessonId, heart);
    }

    public void updateRatingNewWordsIncorrectByLessonId(int lessonId, int question) {
        ratingNewWordsDao.updateRatingQuestionByLessonId(lessonId, question);
    }

    public void insertRatingNewWords(RatingNewWords rating) {
        if (rating != null) {
            ratingNewWordsDao.insert(rating);
        }
    }



}
