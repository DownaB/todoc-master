package service;

import android.support.annotation.NonNull;

import com.cleanup.todoc.model.Task;

import java.util.List;

public interface ApiService {

    void onDeleteTask(Task task);

    void addTask(@NonNull Task task);

    List<Task> getTasks();
}
