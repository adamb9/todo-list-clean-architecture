package com.example.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.entity.Task

@Entity(tableName = "Task")
data class TaskEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var name: String,
    var priority: Int? = 0,
    var desc: String,
    val dateCreated: String,
    var dateDue: String,
    var dateCompleted: String? = null,
    var isCompleted: Boolean = false

)
{
    companion object {
        fun fromTask(task: Task) = TaskEntity(task.id,
                                            task.name,
                                            task.priority,
                                            task.desc,
                                            task.dateCreated,
                                            task.dateDue,
                                            task.dateCompleted,
                                            task.isCompleted)
    }

    fun toTask() = Task(id,
                        name,
                        priority,
                        desc,
                        dateCreated,
                        dateDue,
                        dateCompleted,
                        isCompleted)
}