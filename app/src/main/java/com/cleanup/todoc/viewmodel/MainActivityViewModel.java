package com.cleanup.todoc.viewmodel;



import androidx.annotation.NonNull;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.Collections;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MainActivityViewModel extends ViewModel {

    public final Project[] allProjects = Project.getAllProjects();

    @NonNull
    private final ArrayList<Task> _tasks = new ArrayList<>();
    public MutableLiveData<ArrayList<Task>> tasks = new MutableLiveData<>();
    @NonNull
    public SortMethod sortMethod = SortMethod.NONE;

    public void setSortMethod(@NonNull SortMethod sortMethod) {
        this.sortMethod = sortMethod;
        updateTasks();
    }

    /**
     * List of all possible sort methods for task
     */
    public enum SortMethod {
        /**
         * Sort alphabetical by name
         */
        ALPHABETICAL,
        /**
         * Inverted sort alphabetical by name
         */
        ALPHABETICAL_INVERTED,
        /**
         * Lastly created first
         */
        RECENT_FIRST,
        /**
         * First created first
         */
        OLD_FIRST,
        /**
         * No sort
         */
        NONE
    }

    public void onDeleteTask(Task task) {
        _tasks.remove(task);
        updateTasks();
    }

    public void addTask(@NonNull Task task) {
        _tasks.add(task);
        updateTasks();
    }

    private void updateTasks() {
            switch (sortMethod) {
                case ALPHABETICAL:
                    Collections.sort(_tasks, new Task.TaskAZComparator());
                    break;
                case ALPHABETICAL_INVERTED:
                    Collections.sort(_tasks, new Task.TaskZAComparator());
                    break;
                case RECENT_FIRST:
                    Collections.sort(_tasks, new Task.TaskRecentComparator());
                    break;
                case OLD_FIRST:
                    Collections.sort(_tasks, new Task.TaskOldComparator());
                    break;
            }
            tasks.setValue(_tasks);
        }
}

