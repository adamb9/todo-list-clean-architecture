package com.example.business.usecases

import com.example.business.repositories.TaskRepository
import com.example.entity.Task

class RemoveTask(private val taskRepository: TaskRepository) {

    fun invoke(task: Task) = taskRepository.removeTask(task)
}