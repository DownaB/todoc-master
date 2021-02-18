package service;

import androidx.annotation.NonNull;

import com.cleanup.todoc.model.Task;

import java.util.List;

public interface ApiService {

    void onDeleteTask(Task task);

    void addTask(@NonNull Task task);

    List<Task> getTasks();
}
