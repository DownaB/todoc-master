package com.cleanup.todoc.viewmodel;



import android.app.Application;

import androidx.annotation.NonNull;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.database.TaskDataBase;
import com.cleanup.todoc.entity.TaskWithProject;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


public class MainActivityViewModel extends AndroidViewModel {

    public final List<Project> allProjects = TaskDataBase.getTaskDatabase(getApplication()).projectDao().getAllProject();

    @NonNull
    private LiveData<List<TaskWithProject>> tasks = TaskDataBase.getTaskDatabase(getApplication()).projectDao().getTaskWithProject();
    public LiveData<List<TaskWithProject>> sortedTasks = Transformations.map(tasks, new Function<List<TaskWithProject>, List<TaskWithProject>>() {

        @Override
        public List<TaskWithProject> apply(List<TaskWithProject> input) {
            return updateTasks(input);
        }
    });

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
        TaskDataBase.getTaskDatabase(getApplication()).taskDao().deleteTask(task.task);
    }

    public void addTask(@NonNull Task task) {
        TaskDataBase.getTaskDatabase(getApplication()).taskDao().addTask(task);
    }

    private List<TaskWithProject> updateTasks(List<TaskWithProject> input) {
            switch (sortMethod) {
                case ALPHABETICAL:
                    Collections.sort(input, new TaskWithProject.TaskAZComparator());
                    break;
                case ALPHABETICAL_INVERTED:
                    Collections.sort(input, new TaskWithProject.TaskZAComparator());
                    break;
                case RECENT_FIRST:
                    Collections.sort(input, new TaskWithProject.TaskRecentComparator());
                    break;
                case OLD_FIRST:
                    Collections.sort(input, new TaskWithProject.TaskOldComparator());
                    break;
            }
            return input;
        }
}

