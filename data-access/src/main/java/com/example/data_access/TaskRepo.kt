package com.example.data_access

import androidx.annotation.AnyThread
import com.example.entity.Task

@AnyThread
interface TaskRepo : DataRepo {

    fun initialize()

    fun getTask(id: Long): DataRequest<Task>
}