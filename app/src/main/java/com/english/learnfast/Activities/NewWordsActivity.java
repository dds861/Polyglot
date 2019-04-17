package com.english.learnfast.Activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.english.learnfast.AlertDialogsApp.DialogFragmentFirstUserEntranceNewWords;
import com.english.learnfast.Interfaces.INewWords;
import com.english.learnfast.Models.ConstantsApp;
import com.english.learnfast.Models.PreferencesApp;
import com.english.learnfast.Models.RatingNewWords;
import com.english.learnfast.Presenter.PresenterDb;
import com.english.learnfast.Presenter.PresenterNewWords;
import com.english.learnfast.R;

import java.util.List;
import java.util.Random;

public class NewWordsActivity extends AppCompatActivity implements View.OnClickListener, INewWords, View.OnTouchListener {

    private TextView mNewWordTv;
    private TextView mVariant1Tv;
    private TextView mVariant2Tv;
    private TextView mVariant3Tv;
    private TextView mVariant4Tv;
    private TextView mVariant5Tv;
    private TextView mVariant6Tv;
    private PresenterNewWords presenterNewWords;
    private PresenterDb presenterDb;
    private PreferencesApp preferencesApp;
    private ActionBar actionBar;
    private TextView actionBarHeartText;
    private TextView actionBarQuestionText;
    private TextView mNewWordsCongratsTv;
    private LinearLayout mLayoutNewWordInclude;

    private int lessonId;
    private int defaultHeart;
    private int defaultQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_words);
        initView();

        if (presenterDb.getRatingNewWordsByLessonId(lessonId) != null) {
            defaultHeart = presenterDb.getRatingNewWordsByLessonId(lessonId).getHearts();
            defaultQuestion = presenterDb.getRatingNewWordsByLessonId(lessonId).getQuestions();
        } else {
            defaultHeart = 0;
            defaultQuestion = 0;
        }

        presenterNewWords.askQuestion();

        setupActionBar();


        RatingNewWords rating = new RatingNewWords(lessonId, defaultHeart, defaultQuestion);
        presenterDb.insertRatingNewWords(rating);


        if (!preferencesApp.onGetBoolean(ConstantsApp.IS_SHOW_ALERT_DIALOG_ON_FIRST_USER_ENTRANCE_EXAM_KEY)) {
            DialogFragment newFragment = new DialogFragmentFirstUserEntranceNewWords();
            newFragment.show(getSupportFragmentManager(), "missiles");

        }
    }

    private void setupActionBar() {
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar_new_words);
        actionBarHeartText = actionBar.getCustomView().findViewById(R.id.action_bar_correct_text);
        actionBarQuestionText = actionBar.getCustomView().findViewById(R.id.action_bar_incorrect_text);
        actionBarHeartText.setText(String.valueOf(defaultHeart));
        actionBarQuestionText.setText(String.valueOf(defaultQuestion));

        //setTitle to ActionBar
        ((TextView) actionBar.getCustomView().findViewById(R.id.tv_title)).setText("Урок " + preferencesApp.onGetInt(ConstantsApp.LESSON_ID));
        //onBack arrow clicked
        (actionBar.getCustomView().findViewById(R.id.iv_back_arrow)).setOnClickListener(this);
    }

    @Override
    public void setmmNewWordTv(String mNewWordTv) {
        this.mNewWordTv.setText(mNewWordTv);
    }

    @Override
    public void onDoActionOnCorrectAnswer() {
        mNewWordTv.setVisibility(View.GONE);
        mLayoutNewWordInclude.setVisibility(View.GONE);

        mNewWordsCongratsTv.setTextColor(getResources().getColor(R.color.primary));
        mNewWordsCongratsTv.setText(
                getResources().getStringArray(R.array.congratulations)[
                        getRandomNumber(0, getResources().getStringArray(R.array.congratulations).length - 1)]);

        mNewWordsCongratsTv.setVisibility(View.VISIBLE);
    }

    private int getRandomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    @Override
    public void onDoActionOnNotCorrectAnswer() {
        mNewWordTv.setVisibility(View.GONE);
        mLayoutNewWordInclude.setVisibility(View.GONE);

        mNewWordsCongratsTv.setTextColor(getResources().getColor(R.color.error));
        mNewWordsCongratsTv.setText(
                getResources().getStringArray(R.array.errors)[
                        getRandomNumber(0, getResources().getStringArray(R.array.errors).length - 1)]);

        mNewWordsCongratsTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setRandomMultipleAnswers(List<String> list) {
        mVariant1Tv.setText(list.get(0));
        mVariant2Tv.setText(list.get(1));
        mVariant3Tv.setText(list.get(2));
        mVariant4Tv.setText(list.get(3));
        mVariant5Tv.setText(list.get(4));
        mVariant6Tv.setText(list.get(5));

    }

    private void initView() {
        mNewWordTv = (TextView) findViewById(R.id.tv_new_word);
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
        presenterNewWords = new PresenterNewWords(this);
        preferencesApp = new PreferencesApp(getApplicationContext());
        presenterDb = new PresenterDb();
        actionBar = getSupportActionBar();

        mNewWordsCongratsTv = (TextView) findViewById(R.id.tv__new_words_congrats);
        mNewWordsCongratsTv.setOnTouchListener(this);
        mLayoutNewWordInclude = (LinearLayout) findViewById(R.id.include_layout_new_word);
        lessonId = preferencesApp.onGetInt(ConstantsApp.LESSON_ID);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        mNewWordsCongratsTv.setVisibility(View.GONE);
        mNewWordTv.setVisibility(View.VISIBLE);
        mLayoutNewWordInclude.setVisibility(View.VISIBLE);

        presenterNewWords.askQuestion();

        return false;
    }


    @Override
    public void onIncrementCorrect() {
        int currentHeart = presenterDb.getRatingNewWordsByLessonId(lessonId).getHearts();
        currentHeart++;
        presenterDb.updateRatingNewWordsCorrectByLessonId(lessonId, currentHeart);
        actionBarHeartText.setText(String.valueOf(currentHeart));
    }

    @Override
    public void onIncrementIncorrect() {
        int currentQuestion = presenterDb.getRatingNewWordsByLessonId(lessonId).getQuestions();
        currentQuestion++;
        presenterDb.updateRatingNewWordsIncorrectByLessonId(lessonId, currentQuestion);
        actionBarQuestionText.setText(String.valueOf(currentQuestion));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_variant_1:
                presenterNewWords.variantClicked(mVariant1Tv.getText().toString());
                break;
            case R.id.tv_variant_2:
                presenterNewWords.variantClicked(mVariant2Tv.getText().toString());
                break;
            case R.id.tv_variant_3:
                presenterNewWords.variantClicked(mVariant3Tv.getText().toString());
                break;
            case R.id.tv_variant_4:
                presenterNewWords.variantClicked(mVariant4Tv.getText().toString());
                break;
            case R.id.tv_variant_5:
                presenterNewWords.variantClicked(mVariant5Tv.getText().toString());
                break;
            case R.id.tv_variant_6:
                presenterNewWords.variantClicked(mVariant6Tv.getText().toString());
                break;
            case R.id.iv_back_arrow:
                finish();
                break;
            default:
                break;
        }
    }
}
