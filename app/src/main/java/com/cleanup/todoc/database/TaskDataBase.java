package com.cleanup.todoc.database;

import android.content.Context;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.ui.MainActivity;
import com.cleanup.todoc.viewmodel.MainActivityViewModel;

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
        INSTANCE = Room.databaseBuilder(context,TaskDataBase.class, "database").allowMainThreadQueries()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        for (Project allProject : Project.getAllProjects()) {
                            db.execSQL ("INSERT INTO Project (id,name, color)VALUES("+allProject.getId()+",'"+allProject.getName()+"',"+allProject.getColor()+")");

                        }
                        for (Task allTask : )
                            db.execSQL("INSERT INTO Task (id,projectId,name) VALUES("+allTask.getId()+",'"+allTask.getProjectId()+"',"+allTask.getName()+")");
                    }
                }).build();
    }
        return INSTANCE;
    }

}
