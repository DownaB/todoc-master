package com.cleanup.todoc;

import android.content.res.Resources;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.TaskDataBase;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.viewmodel.MainActivityViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

@RunWith(JUnit4.class)
class ViewModelInstrumentedTest {
    @Rule
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();
    private MainActivityViewModel viewModel;

    @Before
    public void init() {
        viewModel = new MainActivityViewModel();
    }
    @Test
    public void test(){
     LiveData<List<Task>> tasks = MainActivityViewModel.
    }
}
