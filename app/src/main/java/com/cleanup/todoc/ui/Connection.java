package com.cleanup.todoc.ui;

import com.cleanup.todoc.database.TaskDataBase;
import com.cleanup.todoc.model.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class BddConnection {

    private String url ="jdbc:odbc:database";

    private static Connection connect;

    private BddConnection(){
       try{
           connect = DriverManager.getConnection(url);

       } catch (SQLException e){
           e.printStackTrace();
       }
    }

    public static Connection getInstance(){
        if(connect == null){
            new BddConnection();
        }
        return connect;
    }

    final TaskDataBase taskDataBase = Room.databaseBuilder(this,TaskDataBase.class, "database").allowMainThreadQueries()
            .addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    for (Project allProject : Project.getAllProjects()) {
                        db.execSQL ("INSERT INTO Project (id,name, color)VALUES("+allProject.getId()+",'"+allProject.getName()+"',"+allProject.getColor()+")");

                    }
                }
            }).build();
}
