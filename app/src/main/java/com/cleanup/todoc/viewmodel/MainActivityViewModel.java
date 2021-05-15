package com.cleanup.todoc.viewmodel;


import android.app.Application;

import com.cleanup.todoc.database.TaskDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


public class MainActivityViewModel extends AndroidViewModel {

    public final List<Project> allProjects = TaskDataBase.getTaskDatabase(getApplication()).projectDao().getAllProject();

    public LiveData<List<Task>> tasks = TaskDataBase.getTaskDatabase(getApplication()).taskDao().getAllTask();

    @NonNull
    public MediatorLiveData<List<Task>> sortedTask = new MediatorLiveData<>();

    @NonNull
    private final MutableLiveData<SortMethod> sortMethod = new MutableLiveData<>(SortMethod.NONE);

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        sortedTask.addSource(tasks, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                sortedTask.setValue(updateTasks(tasks));
            }
        });

        sortedTask.addSource(sortMethod, sortMethod -> sortedTask.setValue(updateTasks(tasks.getValue())));
    }

    public void setSortMethod(@NonNull SortMethod sortMethod) {
        this.sortMethod.setValue(sortMethod);
    }

    public void onDeleteTask(Task task) {
        TaskDataBase.getTaskDatabase(getApplication()).taskDao().deleteTask(task);
    }

    public void addTask(@NonNull Task task) {
        TaskDataBase.getTaskDatabase(getApplication()).taskDao().addTask(task);
    }

    private List<Task> updateTasks(List<Task> tasks) {
        switch (sortMethod.getValue()) {
            case ALPHABETICAL:
                Collections.sort(tasks, new Task.TaskAZComparator());
                break;
            case ALPHABETICAL_INVERTED:
                Collections.sort(tasks, new Task.TaskZAComparator());
                break;
            case RECENT_FIRST:
                Collections.sort(tasks, new Task.TaskRecentComparator());
                break;
            case OLD_FIRST:
                Collections.sort(tasks, new Task.TaskOldComparator());
                break;
        }
        return tasks;
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
}

