package com.example.business.repositories

import com.example.entity.Task

class TaskRepository(private val dataSource: TaskDataSource){

    fun addTask(task: Task) = dataSource.add(task)

    fun getTask(id: Int) = dataSource.get(id)

    fun getAllTasks() = dataSource.getAll()

    fun removeTask(task: Task) = dataSource.remove(task)

}