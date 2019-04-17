package com.english.learnfast.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.english.learnfast.Interfaces.IExam;
import com.english.learnfast.Models.ConstantsApp;
import com.english.learnfast.Models.PreferencesApp;
import com.english.learnfast.Models.Rating;
import com.english.learnfast.Presenter.PresenterDb;
import com.english.learnfast.Presenter.PresenterExam;
import com.english.learnfast.R;

import java.util.List;
import java.util.Random;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener, IExam, View.OnTouchListener {

    private TextView mExamRusTv;
    private TextView mExamEngTv;
    private TextView mVariant1Tv;
    private TextView mVariant2Tv;
    private TextView mVariant3Tv;
    private TextView mVariant4Tv;
    private TextView mVariant5Tv;
    private TextView mVariant6Tv;
    private TextView mVariant7Tv;
    private TextView mVariant8Tv;

    private TextView actionBarHeartText;
    private TextView actionBarQuestionText;
    private ImageView actionBarImageViewHelp;
    private ActionBar actionBar;

    private PreferencesApp preferencesApp;
    private PresenterExam presenterExam;
    private int lessonId;
    private TextView mCongratsTv;
    private LinearLayout mLayoutInclude;

    private PresenterDb presenterDb;
    private Rating rating;
    private boolean isContinue;
    private int currentHeart;
    private int currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initView();
        getIntents();
        setupActionBar();

        onLoadRating();
        presenterDb.insertRating(rating);



    }

    //load saved rating
    private void onLoadRating() {

        //check if the level is continued or new
        if (isContinue) {
            currentHeart = getCurrentHeartRating();
            currentQuestion = getCurrentQuestionRating();
        } else {
            currentHeart = getResources().getInteger(R.integer.heart_rating_default);
            currentQuestion = getResources().getInteger(R.integer.questions_rating_default);
        }

        //check if the level was Passed earlier
        if (presenterDb.getPassRatingByLessonId(lessonId)) {
            rating = new Rating(lessonId, currentHeart, currentQuestion, true);
        } else {
            rating = new Rating(lessonId, currentHeart, currentQuestion, false);
        }

        actionBarHeartText.setText(String.valueOf(currentHeart));
        actionBarQuestionText.setText(String.valueOf(currentQuestion));
    }

    private void getIntents() {
        isContinue = getIntent().getBooleanExtra(ConstantsApp.CONTINUE, false);
    }

    private void setupActionBar() {
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar_exam);
        //set heart and questions
        actionBarHeartText = actionBar.getCustomView().findViewById(R.id.action_bar_correct_text);
        actionBarQuestionText = actionBar.getCustomView().findViewById(R.id.action_bar_incorrect_text);
        actionBarImageViewHelp = actionBar.getCustomView().findViewById(R.id.action_bar_help);

        actionBarImageViewHelp.setOnClickListener(this);
        //setTitle to ActionBar
        ((TextView) actionBar.getCustomView().findViewById(R.id.tv_title)).setText("Урок " + preferencesApp.onGetInt(ConstantsApp.LESSON_ID));
        //onBack arrow clicked
        (actionBar.getCustomView().findViewById(R.id.iv_back_arrow)).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterExam.askQuestion();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onSaveRatings();
    }

    private void onSaveRatings() {
        presenterDb.updateRatingHeartByLessonId(lessonId, Integer.valueOf(actionBarHeartText.getText().toString()));
        presenterDb.updateRatingQuestionByLessonId(lessonId, Integer.valueOf(actionBarQuestionText.getText().toString()));
    }

    @Override
    public void setmExamRusTv(String mExamRusTv) {
        this.mExamRusTv.setText(mExamRusTv);
    }

    @Override
    public void setmExamEngTv(String mExamEngTv) {
        this.mExamEngTv.setText(mExamEngTv);
    }

    @Override
    public String getmExamEngTv() {
        return this.mExamEngTv.getText().toString();
    }

    @Override
    public void onDoActionOnCorrectAnswer() {

        onShowSplashText(true, true);
    }

    @Override
    public void onDoActionOnNotCorrectAnswer() {
        onShowSplashText(true, false);
    }

    private void onShowSplashText(boolean isShow, boolean isCorrect) {

        mCongratsTv.setTextColor(getResources().getColor(R.color.primary));

        if (!isShow) {
            mCongratsTv.setVisibility(View.GONE);
            mLayoutInclude.setVisibility(View.VISIBLE);
        } else if (isQuestionsFinished()) {
            mLayoutInclude.setVisibility(View.GONE);
            mCongratsTv.setVisibility(View.VISIBLE);
            mCongratsTv.setText(getResources().getString(R.string.questions_rating_ended));
        } else if (isHeartFinished()) {
            mCongratsTv.setTextColor(getResources().getColor(R.color.error));
            mLayoutInclude.setVisibility(View.GONE);
            mCongratsTv.setVisibility(View.VISIBLE);
            mCongratsTv.setText(getResources().getString(R.string.heart_rating_ended));
        } else if (!isHeartFinished() && isCorrect) {
            mLayoutInclude.setVisibility(View.GONE);
            mCongratsTv.setVisibility(View.VISIBLE);

            mCongratsTv.setText(
                    getResources().getStringArray(R.array.congratulations)[
                            getRandomNumber(0, getResources().getStringArray(R.array.congratulations).length - 1)]);
        } else {
            mLayoutInclude.setVisibility(View.GONE);
            mCongratsTv.setVisibility(View.VISIBLE);

            mCongratsTv.setTextColor(getResources().getColor(R.color.error));
            mCongratsTv.setText(
                    getResources().getStringArray(R.array.errors)[
                            getRandomNumber(0, getResources().getStringArray(R.array.errors).length - 1)]);
        }
    }

    private int getRandomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private boolean isHeartFinished() {
        return getCurrentHeartRating() < 1;
    }

    private boolean isQuestionsFinished() {
        return getCurrentQuestionRating() < 1;
    }


    @Override
    public void onDecrementHeart() {

        currentHeart--;
        presenterDb.updateRatingHeartByLessonId(lessonId, currentHeart);
        actionBarHeartText.setText(String.valueOf(currentHeart));
    }

    private int getCurrentHeartRating() {
        return presenterDb.getRatingByLessonId(lessonId).getHearts();
    }

    @Override
    public void onDecrementQuestion() {
        currentQuestion--;
        presenterDb.updateRatingQuestionByLessonId(lessonId, currentQuestion);
        actionBarQuestionText.setText(String.valueOf(currentQuestion));
    }

    private int getCurrentQuestionRating() {
        return presenterDb.getRatingByLessonId(lessonId).getQuestions();
    }


    @Override
    public void setRandomMultipleAnswers(List<String> list) {
        mVariant1Tv.setText(list.get(0));
        mVariant2Tv.setText(list.get(1));
        mVariant3Tv.setText(list.get(2));
        mVariant4Tv.setText(list.get(3));
        mVariant5Tv.setText(list.get(4));
        mVariant6Tv.setText(list.get(5));
        mVariant7Tv.setText(list.get(6));
        mVariant8Tv.setText(list.get(7));
    }


    private void initView() {
        mExamRusTv = (TextView) findViewById(R.id.tv_exam_rus);
        mExamEngTv = (TextView) findViewById(R.id.tv_exam_eng);
        mVariant1Tv = (TextView) findViewById(R.id.tv_variant_1);
        mVariant1Tv.setOnClickListener(this);
        mVariant2Tv = (TextView) findViewById(R.id.tv_variant_2);
        mVariant2Tv.setOnClickListener(this);
        mVariant3Tv = (TextView) findViewById(R.id.tv_variant_3);
        mVariant3Tv.setOnClickListener(this);
        mVariant4Tv = (TextView) findViewById(R.id.tv_variant_4);
        mVariant4Tv.setOnClickListener(this);
        mVariant5Tv = (TextView) findViewById(R.id.tv_variant_5);
        mVariant5Tv.setOnClickListener(this);
        mVariant6Tv = (TextView) findViewById(R.id.tv_variant_6);
        mVariant6Tv.setOnClickListener(this);
        mVariant7Tv = (TextView) findViewById(R.id.tv_variant_7);
        mVariant7Tv.setOnClickListener(this);
        mVariant8Tv = (TextView) findViewById(R.id.tv_variant_8);
        mVariant8Tv.setOnClickListener(this);
        preferencesApp = new PreferencesApp(getApplicationContext());

        mCongratsTv = (TextView) findViewById(R.id.tv_congrats);
        mCongratsTv.setOnTouchListener(this);

        mLayoutInclude = (LinearLayout) findViewById(R.id.include_layout);
        actionBar = getSupportActionBar();

        presenterDb = new PresenterDb();
        lessonId = preferencesApp.onGetInt(ConstantsApp.LESSON_ID);
        presenterExam = new PresenterExam(this, lessonId);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_variant_1:
                presenterExam.variantClicked(mVariant1Tv.getText().toString());
                break;
            case R.id.tv_variant_2:
                presenterExam.variantClicked(mVariant2Tv.getText().toString());
                break;
            case R.id.tv_variant_3:
                presenterExam.variantClicked(mVariant3Tv.getText().toString());
                break;
            case R.id.tv_variant_4:
                presenterExam.variantClicked(mVariant4Tv.getText().toString());
                break;
            case R.id.tv_variant_5:
                presenterExam.variantClicked(mVariant5Tv.getText().toString());
                break;
            case R.id.tv_variant_6:
                presenterExam.variantClicked(mVariant6Tv.getText().toString());
                break;
            case R.id.tv_variant_7:
                presenterExam.variantClicked(mVariant7Tv.getText().toString());
                break;
            case R.id.tv_variant_8:
                presenterExam.variantClicked(mVariant8Tv.getText().toString());
                break;
            case R.id.iv_back_arrow:
                finish();
                break;
            case R.id.action_bar_help:
                Intent intent = new Intent(this, HelpActivity.class);
                intent.putExtra(ConstantsApp.LESSON_ID, lessonId);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        if (isHeartFinished()) {
            finish();
        } else if (isQuestionsFinished()) {
            presenterDb.updateRatingPassByLessonId(lessonId, true);
            finish();
        } else {
            onShowSplashText(false, false);
            mExamEngTv.setText("");
            presenterExam.askQuestion();
        }

        return false;
    }


}
