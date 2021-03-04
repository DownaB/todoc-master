package com.cleanup.todoc.database;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.entity.Project;
import com.cleanup.todoc.entity.Task;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, {Project.class})
public abstract class TaskDataBase extends RoomDatabase {

    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    final TaskDataBase taskDataBase = Room.databaseBuilder(context,TaskDataBase.class, "database").allowMainThreadQueries().build();

    taskDataBase.taskDao().getAllTask();
    taskDataBase.taskDao().addTask();
    taskDataBase.taskDao().deleteTask();
    taskDataBase.taskDao().updateTask();

    taskDataBase.projectDao().getAllProject();
    taskDataBase.projectDao().addProject();
    taskDataBase.projectDao().deteleProject();
    taskDataBase.projectDao().updateProject();
}
