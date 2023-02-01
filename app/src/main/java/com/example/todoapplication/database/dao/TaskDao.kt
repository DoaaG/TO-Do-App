package com.example.todoapplication.database.dao

import androidx.room.*
import com.example.todoapplication.database.model.Task

@Dao  // step(2) add operations
interface TaskDao {
    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("select * from tasks")
    fun getAllTasks():List<Task>
}