package com.example.todoapplication.tasks

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
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
    var itemclicked: onItemClick? = null
    var deleteClick : onItemDeleteClick? = null

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.binding.tasksTextView.text =
            list?.get(position)?.title  //      ?.title    because of  List<Task>?  "Task"
        holder.binding.descriptionTextView.text = list?.get(position)?.description
        holder.binding.checkBtn.setOnClickListener {
            itemclicked?.onCheckClick(list!!.get(position))
        }
        holder.binding.cardDelete.setOnClickListener{
            deleteClick?.onDeleteClick(list!!.get(position))
        }
        // update isDone color
        if (list!![position].isDone) {
            holder.binding.checkBtn.setBackgroundResource(R.drawable.done_button)
            holder.binding.tasksTextView.setTextColor(Color.GREEN)
            holder.binding.divider.setBackgroundColor(Color.GREEN)
            holder.binding.checkBtn.setImageResource(R.drawable.ic_done_all)
        }

    }

    fun changeData(newListOfTasks: List<Task>?) {
        list = newListOfTasks
        notifyDataSetChanged()   // to refresh the adapter

    }
}