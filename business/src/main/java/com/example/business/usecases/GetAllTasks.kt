package com.example.business.usecases

import com.example.business.repositories.TaskRepository

class GetAllTasks(private val taskRepository: TaskRepository) {

    fun invoke() = taskRepository.getAllTasks()

}