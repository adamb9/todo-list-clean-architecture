package com.example.todo_list_clean_architecture

import android.content.Context
import com.example.business.repositories.TaskDataSource
import com.example.data.db.DatabaseService
import com.example.data.db.TaskEntity
import com.example.entity.Task

class RoomTaskDataSource(context: Context): TaskDataSource {
    val taskDao = DatabaseService.getInstance(context).taskDao()

    override fun add(task: Task) = taskDao.insertTask(TaskEntity.fromTask(task))

    override fun get(id: Int): Task? = taskDao.getTaskById(id)?.toTask()

    override fun getAll(): List<Task> = taskDao.getTasks().map{ it.toTask() }

    override fun getAllUncomplete(): List<Task> = taskDao.getCompletedTasks().map { it.toTask() }

    override fun remove(task: Task) = taskDao.deleteTask(TaskEntity.fromTask(task))

}