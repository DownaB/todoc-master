package com.cleanup.todoc.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
final public class Project {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String name;
    public int color; 
}
