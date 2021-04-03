package com.cleanup.todoc.entity;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Comparator;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Query;
import androidx.room.Relation;

final public class TaskWithProject {

    @Embedded
    public Project project;

    @Relation(
            parentColumn = "projectId",
            entityColumn = "id"

    ) public Task task;

    /**
     * Comparator to sort task from A to Z
     */
    public static class TaskAZComparator implements Comparator<TaskWithProject> {
        @Override
        public int compare(TaskWithProject left, TaskWithProject right) {
            return left.task.getName().compareTo(right.task.getName());
        }
    }

    /**
     * Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<TaskWithProject> {
        @Override
        public int compare(TaskWithProject left, TaskWithProject right) {
            return right.task.getName().compareTo(left.task.getName());
        }
    }

    /**
     * Comparator to sort task from last created to first created
     */
    public static class TaskRecentComparator implements Comparator<TaskWithProject> {
        @Override
        public int compare(TaskWithProject left, TaskWithProject right) {
            return (int) (right.task.getCreationTimestamp() - left.task.getCreationTimestamp());
        }
    }

    /**
     * Comparator to sort task from first created to last created
     */
    public static class TaskOldComparator implements Comparator<TaskWithProject> {
        @Override
        public int compare(TaskWithProject left, TaskWithProject right) {
            return (int) (left.task.getCreationTimestamp() - right.task.getCreationTimestamp());
        }
    }
}
