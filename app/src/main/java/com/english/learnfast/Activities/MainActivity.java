package com.english.learnfast.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.english.learnfast.Adapters.MainAdapter;
import com.english.learnfast.AlertDialogsApp.DialogFragmentFirstUserEntranceExam;
import com.english.learnfast.AlertDialogsApp.DialogFragmentNeedToPass;
import com.english.learnfast.Models.ConstantsApp;
import com.english.learnfast.Models.ItemClickSupport;
import com.english.learnfast.Models.LessonMain;
import com.english.learnfast.Models.PreferencesApp;
import com.english.learnfast.Presenter.PresenterDb;
import com.english.learnfast.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {

    private List<LessonMain> list = new ArrayList<>();
    private RecyclerView mRecycler;
    private PresenterDb presenterDb;
    private PreferencesApp preferencesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        setInitialData();
        setupRecyclerview();

        onItemRecyclerViewClickListerner();


        if (!preferencesApp.onGetBoolean(ConstantsApp.IS_SHOW_ALERT_DIALOG_ON_FIRST_USER_ENTRANCE_EXAM_KEY)) {

            DialogFragment newFragment = new DialogFragmentFirstUserEntranceExam();
            newFragment.show(getSupportFragmentManager(), "missiles");

        }
    }

//    @Override
//    public Resources.Theme getTheme() {
//        Resources.Theme theme = super.getTheme();
//
//        theme.applyStyle(R.style.DarkAppTheme, true);
//
//        // you could also use a switch if you have many themes that could apply
//        return theme;
//    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        presenterDb = new PresenterDb();
        preferencesApp = new PreferencesApp(getApplicationContext());
    }

    private void setInitialData() {

        String[] lesson_numbers = getResources().getStringArray(R.array.lesson_number);
        String[] lesson_description = getResources().getStringArray(R.array.lesson_description);

        for (int i = 0; i < lesson_numbers.length; i++) {
            list.add(new LessonMain(lesson_numbers[i], lesson_description[i], 4.5));


        }

    }

    private void setupRecyclerview() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(linearLayoutManager);

        MainAdapter myAdapter = new MainAdapter(this, list);
        mRecycler.setAdapter(myAdapter);
    }

    private void onItemRecyclerViewClickListerner() {
        ItemClickSupport.addTo(mRecycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {


                if (position == 0) {
                    preferencesApp.onSaveInt(ConstantsApp.LESSON_ID, position + 1);
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else
                //uncomment if you need only show to 1 and 2 positions to show this AlertDiaglog
//                    if (position == 1 || position == 2)
                    {
                    if (presenterDb.getPassRatingByLessonId(position)) {
                        //uncomment if you need all lessons to be accessible
//                    if (true) {
                        preferencesApp.onSaveInt(ConstantsApp.LESSON_ID, position + 1);
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else{
                        DialogFragment newFragment = new DialogFragmentNeedToPass();
                        newFragment.show(getSupportFragmentManager(), "missiles");
                    }

                }
                //If you need alertDialog "Need to buy to access this Lesson" when higher than 3-rd position is clicked
//                else {
//                    DialogFragment newFragment = new DialogFragmentNeedToBuy();
//                    newFragment.show(getSupportFragmentManager(), "missiles");
//
//                }
            }
        });
    }
}
