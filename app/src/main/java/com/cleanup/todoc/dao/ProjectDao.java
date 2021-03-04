package com.cleanup.todoc.dao;

import com.cleanup.todoc.entity.Project;
import com.cleanup.todoc.entity.Task;
import com.cleanup.todoc.entity.Task_Project;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM Project")
    List<Task> getAllProject();

    @Delete
    void deleteProject(Project project);

    @Insert
    void addProject (Project project);

    @Update
    void updateProject(Project project);

    @Query("SELECT * FROM Project ORDER BY name ASC")
    List<Project> taskByName();

    @Query("SELECT * FROM Task")
    List<Task_Project> getTaskWithProject();
}
