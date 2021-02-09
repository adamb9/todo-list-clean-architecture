package com.example.business.repositories

import com.example.entity.Task

interface TaskDataSource{
    fun add(task: Task)

    fun get(id: Int): Task?

    fun getAll(): List<Task>

    fun remove(task: Task)
}