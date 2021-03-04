package com.cleanup.todoc.dao;

import com.cleanup.todoc.entity.Task;

import java.io.LineNumberInputStream;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    List<Task> getAllTask();

    @Delete
    void deleteTask(Task task);

    @Insert
    void addTask (Task task);

    @Update
    void updateTask(Task task);

    @Query("SELECT * FROM Task ORDER BY name ASC")
    List<Task> taskByName();


}
