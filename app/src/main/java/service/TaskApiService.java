package service;

import android.support.annotation.NonNull;

import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskApiService implements ApiService {

    List<Task> tasks = new ArrayList<>();

    public void onDeleteTask(Task task) {
        tasks.remove(task);
    }

    public void addTask(@NonNull Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks(){
        return tasks;
    }
}
