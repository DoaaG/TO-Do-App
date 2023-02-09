package com.example.todoapplication.settings


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapplication.Constants.TASK
import com.example.todoapplication.database.MyDataBase
import com.example.todoapplication.database.model.Task
import com.example.todoapplication.databinding.ActivityEditTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class EditTaskActivity : AppCompatActivity() {
    private lateinit var editBinding: ActivityEditTaskBinding
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(editBinding.root)
        task = intent.getSerializableExtra(TASK) as Task
        showData(task)
        editBinding.includeEditTask.saveChangesButton.setOnClickListener {
            updateTodo()
            finish()
        }
        editBinding.includeEditTask.dateEditText.setOnClickListener {
//            showDatePicker()
        }
        editBinding.backArrow.setOnClickListener {
            finish()
        }

    }

    private fun updateTodo() {
        if (isValid()) {
            task.title = editBinding.includeEditTask.titleEditText.text.toString()
            task.description = editBinding.includeEditTask.detailsEditText.text.toString()
//            task.date = editBinding.includeEditTask.dateEditText.text.toString().toLong()
            MyDataBase.getDataBase(this).tasksDao().updateTask(task)
        }
    }

    private fun showData(task: Task) {
        editBinding.includeEditTask.titleEditText.setText(task.title)
        editBinding.includeEditTask.detailsEditText.setText(task.description)
        var date = convertLongtoTime(task.date)
        editBinding.includeEditTask.dateEditText.setText(date)

    }

    private fun convertLongtoTime(date: Long?): String {
        val date = Date(date!!)
        val formate = SimpleDateFormat("dd MM yyyy  HH:mm.a")  // must be MM-1
        return formate.format(date)

    }

    private fun isValid(): Boolean {
        var valid = true
        val title = editBinding.includeEditTask.titleEditText.text.toString()
        val description = editBinding.includeEditTask.detailsEditText.text.toString()
        val date = editBinding.includeEditTask.dateEditText.text.toString()
        if (title.isNullOrBlank()) {
            valid = false
            editBinding.includeEditTask.titleEditText.error = "Please Enter a Valid Title"
        } else {
            editBinding.includeEditTask.titleEditText.error = null
        }

        if (description.isNullOrBlank()) {
            valid = false
            editBinding.includeEditTask.detailsEditText.error = "Please Enter a Valid Description"
        } else {
            editBinding.includeEditTask.detailsEditText.error = null
        }

        if (date.isNullOrBlank()) {
            valid = false
            editBinding.includeEditTask.dateEditText.error = "Please Select a Valid Date"
        } else {
            editBinding.includeEditTask.dateEditText.error = null
        }

        return valid
    }

}