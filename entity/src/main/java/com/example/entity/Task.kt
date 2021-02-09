package com.example.entity

import java.io.Serializable

data class Task(
    val id: Int? = null,
    var name: String,
    var priority: Int? = 0,
    var desc: String,
    val dateCreated: String,
    var dateDue: String,
    var dateCompleted: String? = null,
    var isCompleted: Boolean = false
) : Serializable, BusinessEntity