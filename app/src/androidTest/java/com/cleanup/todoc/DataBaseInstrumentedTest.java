package com.cleanup.todoc;

import android.content.Context;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.TaskDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DataBaseInstrumentedTest {

    private ProjectDao projectDao;
    private TaskDao taskDao;
    private TaskDataBase taskDataBase;

    static{
        BuildConfig.IS_TESTING.set(true);
    }

    @Rule
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();

    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        taskDataBase = Room.inMemoryDatabaseBuilder(context, TaskDataBase.class).build();
        projectDao = taskDataBase.projectDao();
        taskDao = taskDataBase.taskDao();
    }
    @After
    public void closeDb(){
        taskDataBase.close();
    }

    @Test
    public void getAllProject() {
        List<Project> allProjects = TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).projectDao().getAllProject();
        assertEquals(3,allProjects.size());
    }

    @Test
    public void addTask() throws InterruptedException {
        LiveData<List<Task>> tasks = TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().getAllTask();
        assertEquals(0,LiveDataTestUtil.getValue(tasks).size());

        Task task = new Task(1, Project.getProjectById(1),"test",1);
        TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().addTask(task);
        tasks = TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().getAllTask();
        assertEquals(1,LiveDataTestUtil.getValue(tasks).size());

        TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().deleteTask(task);
    }

    @Test
    public void deleteTask() throws Exception{
        LiveData<List<Task>> tasks = TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().getAllTask();
        assertEquals(0,LiveDataTestUtil.getValue(tasks).size());

        Task task = new Task (1, Project.getProjectById(1), "test",1);
       TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().addTask(task);
       tasks = TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().getAllTask();
       assertEquals(1,LiveDataTestUtil.getValue(tasks).size());

       TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().deleteTask(task);
       tasks=TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().getAllTask();
       assertEquals(0,LiveDataTestUtil.getValue(tasks).size());

    }

    @Test
    public void getAllTask() throws Exception {
        LiveData<List<Task>> tasks = TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().getAllTask();
        assertEquals(0,LiveDataTestUtil.getValue(tasks).size());

        Task task = new Task (1, Project.getProjectById(1), "test",1);
        Task task2 = new Task(2,Project.getProjectById(2),"test 2",1);
        TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().addTask(task);
        tasks = TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().getAllTask();
        assertEquals(2,LiveDataTestUtil.getValue(tasks).size());

        TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().deleteTask(task);
        TaskDataBase.getTaskDatabase(ApplicationProvider.getApplicationContext()).taskDao().deleteTask(task2);


    }
}
