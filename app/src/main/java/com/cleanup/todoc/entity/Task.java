package com.cleanup.todoc.entity;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
final public class Task implements Serializable {

    @PrimaryKey (autoGenerate = true)
    public long id;

    @ColumnInfo
    public long projectId;
    public String name;
    public long creationTimeStamp;
}
