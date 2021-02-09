package com.example.data.db

import androidx.room.*


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task : TaskEntity)

    @Update
    fun updateTask(task: TaskEntity)

    @Delete
    fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM Task WHERE id == :id")
    fun getTaskById(id : Int): TaskEntity?

    @Query("SELECT * FROM Task")
    fun getTasks(): List<TaskEntity>

    @Query("SELECT * FROM Task WHERE isCompleted == 0")
    fun getCompletedTasks(): List<TaskEntity>

    @Query("SELECT * FROM Task WHERE isCompleted == 1")
    fun getUncompletedTasks(): List<TaskEntity>
}