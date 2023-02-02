package com.example.todoapplication.tasks

import com.example.todoapplication.database.model.Task

interface onItemDeleteClick {
    fun onDeleteClick(task: Task)
}