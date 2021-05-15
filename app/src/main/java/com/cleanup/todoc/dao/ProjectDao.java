package com.cleanup.todoc.dao;

import com.cleanup.todoc.model.Project;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM Project")
    List<Project> getAllProject();

}
