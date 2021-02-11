package viewmodel;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;

import adapter.TasksRecyclerViewAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import service.ApiService;
import service.TaskApiService;

public class MainActivityViewModel extends ViewModel {

    public enum LoadingState{

        Loading, Loaded
    }

    public MutableLiveData<List<Task>> tasks = new MutableLiveData<>();

    public MutableLiveData<LoadingState> loadingState = new MutableLiveData<>();

    public void initList(){
        loadingState.postValue((LoadingState.Loading));

        new Handler().postDelayed(()-> {
            tasks.postValue(new TaskApiService().getTasks());
            loadingState.postValue(LoadingState.Loaded);
        },);
    }

}
