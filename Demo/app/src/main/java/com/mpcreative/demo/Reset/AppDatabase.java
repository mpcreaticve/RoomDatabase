package com.mpcreative.demo.Reset;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mpcreative.demo.Model.TaskDao;
import com.mpcreative.demo.Model.Task_entity;

@Database(entities = {Task_entity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
