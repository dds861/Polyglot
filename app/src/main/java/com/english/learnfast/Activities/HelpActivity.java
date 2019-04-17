package com.english.learnfast.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.english.learnfast.Models.ConstantsApp;
import com.english.learnfast.R;

import java.util.Objects;

public class HelpActivity extends AppCompatActivity {

    private WebView mWebview;
    private int lessonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initView();
        lessonId = getIntent().getIntExtra(ConstantsApp.LESSON_ID, 0);
        onLoadWebBiew(lessonId);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Помощь");

    }

    private void onLoadWebBiew(int lessonId) {
        switch (lessonId) {
            case 1:
                mWebview.loadUrl("file:///android_asset/lesson1_about.html");
                break;
            case 2:
                mWebview.loadUrl("file:///android_asset/lesson2_about.html");
                break;
            case 3:
                mWebview.loadUrl("file:///android_asset/lesson3_about.html");
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            default:
                break;
        }
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
    }
}
