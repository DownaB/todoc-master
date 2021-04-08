package com.cleanup.todoc.dao;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

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

    @Query("SELECT * FROM Project ORDER BY projectName ASC")
    List<Project> projectByName();
}
