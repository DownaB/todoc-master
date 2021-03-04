package com.cleanup.todoc.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Query;
import androidx.room.Relation;

final public class Task_Project {

    @Embedded
    Project project;

    @Relation(
            parentColumn = project "id",
            entityColumn = task "projectId"

    )
    List<Task> task;
}
