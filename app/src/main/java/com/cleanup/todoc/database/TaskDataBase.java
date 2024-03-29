package com.cleanup.todoc.database;

import android.content.Context;

import com.cleanup.todoc.BuildConfig;
import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TaskDataBase extends RoomDatabase {

    private static TaskDataBase INSTANCE;

    public static TaskDataBase getTaskDatabase(Context context) {
        if (INSTANCE == null) {
            if (!BuildConfig.IS_TESTING.get()) {
                INSTANCE = Room.databaseBuilder(context, TaskDataBase.class, "database").allowMainThreadQueries()
                        .addCallback(new RoomDatabase.Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);

                                for (Project allProject : Project.getAllProjects()) {
                                    db.execSQL("INSERT INTO Project (projectId,projectName, color)VALUES(" + allProject.getId() + ",'" + allProject.getName() + "'," + allProject.getColor() + ")");
                                }
                            }
                        }).build();
            } else {
                INSTANCE = Room.inMemoryDatabaseBuilder(context, TaskDataBase.class).allowMainThreadQueries()
                        .addCallback(new RoomDatabase.Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);

                                for (Project allProject : Project.getAllProjects()) {
                                    db.execSQL("INSERT INTO Project (projectId,projectName, color)VALUES(" + allProject.getId() + ",'" + allProject.getName() + "'," + allProject.getColor() + ")");
                                }
                            }
                        }).build();
            }

        }
        return INSTANCE;
    }

    public abstract TaskDao taskDao();

    public abstract ProjectDao projectDao();
}

