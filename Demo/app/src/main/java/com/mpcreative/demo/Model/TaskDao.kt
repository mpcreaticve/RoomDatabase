package com.mpcreative.demo.Model

import androidx.room.*
import com.google.android.gms.tasks.Task


@Dao
interface TaskDao {

    @Query("SELECT * From task_entity")
    fun getAll(): List<Task_entity>

    @Query("SELECT * From task_entity where id =:id")
    fun getUpdate(id: Int): Task_entity

    @Insert
    fun insert(taskEntity: Task_entity)

    @Delete
    fun delete(taskEntity: Task_entity)

    @Update
    fun update(taskEntity: Task_entity)

}