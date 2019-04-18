package com.english.learnfast.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.english.learnfast.Models.ConstantsApp;
import com.english.learnfast.Models.PreferencesApp;
import com.english.learnfast.Presenter.PresenterDb;
import com.english.learnfast.R;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mNewLessonNumberTv;
    private TextView mNewWordsNumberTv;
    private TextView mNewWordsDescriptionTv;
    private ImageView mInfoIv;
    private TextView mInfoTv;
    private ActionBar actionBar;
    private PreferencesApp preferencesApp;
    private ImageView mLabelIv;
    private RelativeLayout mLayoutLabelRl;
    private ImageView mLabelLessonIv;
    private RelativeLayout mLayoutLabelLessonRl;
    private ImageView mLabelNewWordsIv;
    private RelativeLayout mLayoutLabelNewWordsRl;
    private RelativeLayout mLayoutLabelInfoRl;
    private ImageView mLabelLessonContinueIv;
    private TextView mNewLessonContinueTv;
    private RelativeLayout mLayoutLabelLessonContinueRl;
    private PresenterDb presenterDb;
    private int lessonId;
    private TextView mAboutTv;
    private LinearLayout mContentSecondLabelDescriptionLl;
    private LinearLayout mContentSecondLabelLessonContinueLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();

        actionBar.setTitle("Урок " + lessonId);
        mAboutTv.setText(getResources().getStringArray(R.array.lesson_description)[lessonId - 1]);


    }

    @Override
    protected void onResume() {
        super.onResume();

        onSetContinueTextColor();
        onSetLessonText();
        onShowInfo();

    }

    private void onShowInfo() {
        if (lessonId > 3) {
            mContentSecondLabelDescriptionLl.setVisibility(View.INVISIBLE);

        } else {
            mContentSecondLabelDescriptionLl.setVisibility(View.VISIBLE);
        }
    }

    private void onSetContinueTextColor() {

        if (isLessonNew()) {
            mNewLessonContinueTv.setTextColor(getResources().getColor(R.color.divider));
            mContentSecondLabelLessonContinueLl.setBackgroundResource(R.drawable.borders_grey);
            mLabelLessonContinueIv.setImageResource(R.drawable.item_x);
        } else {
            mLabelLessonContinueIv.setImageResource(R.drawable.item_check);
            mNewLessonContinueTv.setTextColor(getResources().getColor(R.color.primary_text));
            mContentSecondLabelLessonContinueLl.setBackgroundResource(R.drawable.borders_green);
        }

    }

    private void onSetLessonText() {

        if (isLessonNew()) {
            mNewLessonNumberTv.setText(getResources().getString(R.string.new_lesson));

        } else {
            mNewLessonNumberTv.setText(getResources().getString(R.string.new_lesson_from_beginning));
        }

    }

    private boolean isLessonNew() {

        if (presenterDb.getRatingByLessonId(lessonId) == null) {

            return true;
        } else {
            return presenterDb.getHeartRatingByLessonId(lessonId) == 0 ||
                    presenterDb.getQuestionsRatingByLessonId(lessonId) == 0;
        }
    }

    private void initView() {

        mNewLessonNumberTv = (TextView) findViewById(R.id.tv_new_lesson_number);
        mNewLessonNumberTv.setOnClickListener(this);
        mNewWordsNumberTv = (TextView) findViewById(R.id.tv_new_words_number);
        mNewWordsNumberTv.setOnClickListener(this);
        mNewWordsDescriptionTv = (TextView) findViewById(R.id.tv_new_words_description);
        mAboutTv = (TextView) findViewById(R.id.tv_about);

        actionBar = getSupportActionBar();
        preferencesApp = new PreferencesApp(this);


        mLabelLessonIv = (ImageView) findViewById(R.id.iv_label_lesson);
        mLabelLessonIv.setOnClickListener(this);
        mLayoutLabelLessonRl = (RelativeLayout) findViewById(R.id.rl_layout_label_lesson);
        mLayoutLabelLessonRl.setOnClickListener(this);
        mLabelNewWordsIv = (ImageView) findViewById(R.id.iv_label_new_words);
        mLabelNewWordsIv.setOnClickListener(this);
        mLayoutLabelNewWordsRl = (RelativeLayout) findViewById(R.id.rl_layout_label_new_words);
        mLayoutLabelNewWordsRl.setOnClickListener(this);
        mLayoutLabelInfoRl = (RelativeLayout) findViewById(R.id.rl_layout_label_info);
        mLayoutLabelInfoRl.setOnClickListener(this);
        mInfoIv = (ImageView) findViewById(R.id.iv_info);
        mInfoIv.setOnClickListener(this);
        mInfoTv = (TextView) findViewById(R.id.tv_info);
        mInfoTv.setOnClickListener(this);


        mLabelLessonContinueIv = (ImageView) findViewById(R.id.iv_label_lesson_continue);
        mLabelLessonContinueIv.setOnClickListener(this);
        mNewLessonContinueTv = (TextView) findViewById(R.id.tv_new_lesson_continue);
        mNewLessonContinueTv.setOnClickListener(this);
        mLayoutLabelLessonContinueRl = (RelativeLayout) findViewById(R.id.rl_layout_label_lesson_continue);
        mLayoutLabelLessonContinueRl.setOnClickListener(this);

        presenterDb = new PresenterDb();
        lessonId = preferencesApp.onGetInt(ConstantsApp.LESSON_ID);

        mContentSecondLabelDescriptionLl = (LinearLayout) findViewById(R.id.ll_content_second_label_description);
        mContentSecondLabelLessonContinueLl = (LinearLayout) findViewById(R.id.ll_content_second_label_lesson_continue);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //Lesson
            case R.id.rl_layout_label_lesson:
            case R.id.iv_label_lesson:
            case R.id.tv_new_lesson_number:
                Intent intent = new Intent(this, ExamActivity.class);
                startActivity(intent);
                break;

            //New Words
            case R.id.rl_layout_label_new_words:
            case R.id.iv_label_new_words:
            case R.id.tv_new_words_description:
            case R.id.tv_new_words_number:
                Intent intent2 = new Intent(this, NewWordsActivity.class);
                startActivity(intent2);
                break;


            //Info
            case R.id.rl_layout_label_info:
            case R.id.iv_info:
            case R.id.tv_info:
                Intent intent4 = new Intent(this, HelpActivity.class);
                intent4.putExtra(ConstantsApp.LESSON_ID, lessonId);
                startActivity(intent4);
                break;

            //Lesson continue
            case R.id.iv_label_lesson_continue:
            case R.id.tv_new_lesson_continue:
            case R.id.rl_layout_label_lesson_continue:
                if (!isLessonNew()) {
                    Intent intent3 = new Intent(this, ExamActivity.class);
                    intent3.putExtra(ConstantsApp.CONTINUE, true);
                    startActivity(intent3);
                }

                break;
            default:
                break;
        }
    }
}
