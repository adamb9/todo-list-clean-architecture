package com.example.business.usecases

import com.example.business.repositories.TaskRepository

class GetTask(private val taskRepository: TaskRepository) {

    fun invoke(id: Int) = taskRepository.getTask(id)

}