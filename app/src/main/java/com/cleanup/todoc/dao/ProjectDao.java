package com.cleanup.todoc.dao;

import com.cleanup.todoc.model.Project;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM Project")
    List<Project> getAllProject();

    @Delete
    void deleteProject(Project project);

    @Insert
    void addProject (Project project);

    @Update
    void updateProject(Project project);

    @Query("SELECT * FROM Project ORDER BY name ASC")
    List<Project> taskByName();

    @Query("SELECT * FROM Project")
    @Transaction
    List<Task> getTaskWithProject();
}
