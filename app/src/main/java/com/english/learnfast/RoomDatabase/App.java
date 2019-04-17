package com.english.learnfast.RoomDatabase;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class App extends Application {

    public static App instance;
    private AppDatabase database;
    private final String DB_NAME = "database.db";


    @Override
    public void onCreate() {
        super.onCreate();

        copyDatabaseFile();

        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, DB_NAME)

                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    //Copy database.db file to place where RoomDatabase library can read
    private void copyDatabaseFile() {
        final File dbPath = getDatabasePath(DB_NAME);

        // If the database already exists, return
        if (dbPath.exists()) {
            Log.d("autolog", "db Path Exists");
            return;
        }

        // Make sure we have a path to the file
        dbPath.getParentFile().mkdirs();

        // Try to copy database file
        try {
            final InputStream inputStream = getAssets().open(DB_NAME);
            final OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[8192];
            int length;

            while ((length = inputStream.read(buffer, 0, 8192)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            inputStream.close();
            Log.d("autolog", "database copied successfully");

        } catch (IOException e) {
            Log.d("autolog", "Failed to open file", e);
            e.printStackTrace();
        }
    }

}
