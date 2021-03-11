package com.cleanup.todoc.database;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class, Project.class}, version = 1)
public abstract class TaskDataBase extends RoomDatabase {

    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();


}
