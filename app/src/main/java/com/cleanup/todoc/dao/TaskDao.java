package com.cleanup.todoc.dao;

import com.cleanup.todoc.model.Task;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllTask();

    @Delete
    void deleteTask(Task task);

    @Insert
    void addTask (Task task);
}
