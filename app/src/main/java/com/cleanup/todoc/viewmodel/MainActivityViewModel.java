package com.cleanup.todoc.viewmodel;



import android.app.Application;

import androidx.annotation.NonNull;

import com.cleanup.todoc.database.TaskDataBase;
import com.cleanup.todoc.entity.TaskWithProject;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MainActivityViewModel extends AndroidViewModel {

    public final List<Project> allProjects = TaskDataBase.getTaskDatabase(getApplication()).projectDao().getAllProject();

    @NonNull
    private final ArrayList<TaskWithProject> _tasks = new ArrayList<>();
    public MutableLiveData<ArrayList<TaskWithProject>> tasks = new MutableLiveData<>();
    @NonNull
    public SortMethod sortMethod = SortMethod.NONE;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

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

    public void onDeleteTask(TaskWithProject task) {
        _tasks.remove(task);
        updateTasks();
        TaskDataBase.getTaskDatabase(getApplication()).taskDao().deleteTask(task.task);
    }

    public void addTask(@NonNull Task task) {
        _tasks.add(task);
        updateTasks();
        TaskDataBase.getTaskDatabase(getApplication()).taskDao().addTask(task);
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

