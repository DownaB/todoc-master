package com.cleanup.todoc.database;

import android.content.Context;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class, Project.class}, version = 1)
public abstract class TaskDataBase extends RoomDatabase {

    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();
    private static TaskDataBase INSTANCE;

    public static TaskDataBase getTaskDatabase(Context context){
        if (INSTANCE == null){
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TaskDataBase.class,"database").allowMainThreadQueries().build();
    }
        return INSTANCE;
    }
public static void destroyInstance(){
        INSTANCE = null;
}

}
