package com.example.todoapplication.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.databinding.TaskItemBinding

class TasksAdapter(var list: List<String>?) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    inner class TasksViewHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val bind = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(bind)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {

        holder.binding.tasksTextView.text = list?.get(position)
        holder.binding.descriptionTextView.text = list?.get(position)
//        holder.viewBinding.title.text = items?.get(position)?.title
//        holder.viewBinding.desc.text = items?.get(position)?.description
    }
}