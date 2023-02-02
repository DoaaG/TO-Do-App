package com.example.todoapplication.tasks

import com.example.todoapplication.database.model.Task

interface onItemClick {
    fun onCheckClick(task: Task)
}