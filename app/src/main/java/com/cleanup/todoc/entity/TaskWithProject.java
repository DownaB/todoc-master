package com.cleanup.todoc.entity;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Query;
import androidx.room.Relation;

final public class TaskWithProject {

    @Embedded
    public Project project;

    @Relation(
            parentColumn = "id",
            entityColumn = "projectId"

    ) public Task task;
}
