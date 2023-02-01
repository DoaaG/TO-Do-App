package com.example.todoapplication.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.database.model.Task
import com.example.todoapplication.databinding.TaskItemBinding

class TasksAdapter(var list: List<Task>?) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    inner class TasksViewHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val bind = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(bind)
    }

    override fun getItemCount(): Int = list?.size ?: 0
    var itemclicked : onItemClick? = null
    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.binding.tasksTextView.text = list?.get(position)?.title
        holder.binding.descriptionTextView.text = list?.get(position)?.description
        holder.binding.checkBtn.setOnClickListener{
            itemclicked?.onCheckClick(position)
        }
//        holder.viewBinding.title.text = items?.get(position)?.title        ?.title    because of  List<Task>?  "Task"
//        holder.viewBinding.desc.text = items?.get(position)?.description  ?.description   because of  List<Task>?  "Task"
    }
    fun changeData(newListOfTasks :List<Task>?){
         list = newListOfTasks
        notifyDataSetChanged()   // to refresh the adapter

    }
}