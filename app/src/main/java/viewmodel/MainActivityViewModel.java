package viewmodel;

import androidx.appcompat.app.AlertDialog;

import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import service.TaskApiService;

public class MainActivityViewModel extends ViewModel {


    public LiveData<Object> tasks;

    public void initList() {
    }

    public void initProject() {
    }
}
