package com.example.todoapplication.home

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todoapplication.R
import com.example.todoapplication.database.MyDataBase
import com.example.todoapplication.database.model.Task
import com.example.todoapplication.databinding.FragmentAddTaskBinding
import com.example.todoapplication.tasks.ListFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskFragment : BottomSheetDialogFragment() {
    lateinit var bottomsheetbinding: FragmentAddTaskBinding
    var currentDate = Calendar.getInstance()    //return obj of calender type
    var listfragment = ListFragment()    // use it for delay
    init {
        // because of epoch time
        // ignore
        currentDate.set(Calendar.HOUR,0)
        currentDate.set(Calendar.MINUTE,0)
        currentDate.set(Calendar.SECOND,0)
        currentDate.set(Calendar.MILLISECOND,0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomsheetbinding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return bottomsheetbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDate()
        bottomsheetbinding.dateEditText.setOnClickListener {
            showDatePicker() // to show date picker when add new task
        }
        bottomsheetbinding.saveAddtaskButton.setOnClickListener {
            addTask()
        }
    }

//    var onDismissListener:OnDismissListener? = null
//    override fun onDismiss(dialog: DialogInterface) {  // for refreshing reycycler data
//        super.onDismiss(dialog)
//        onDismissListener?.onDismiss()
//    }

    private fun confirmTaskInserted() {
        val alertDialogBuilder =
            AlertDialog.Builder(context).setMessage(getString(R.string.TaskInsertedSuccessfully))
                .setPositiveButton(
                    getString(R.string.ok),
                    { dialog, which -> dialog.dismiss(); dismiss() }).setCancelable(false) // must close the dialog
        // dismiss() for bottomsheet
        alertDialogBuilder.show()
//        listfragment.refreshRecycler()  // use it for delay
    }

    private fun addTask() {
        if (isValid() == false) {
            return
        }
        val title = bottomsheetbinding.entertaskEditText.text.toString()
        val description = bottomsheetbinding.descriptiontaskEditText.text.toString()
        context?.let {
            MyDataBase.getDataBase(it).tasksDao().insertTask(
                Task(title = title, description = description, date = currentDate.timeInMillis)
            )
            confirmTaskInserted()
        }
    }

    private fun isValid(): Boolean {
        var valid = true
        val title = bottomsheetbinding.entertaskEditText.text.toString()
        val description = bottomsheetbinding.descriptiontaskEditText.text.toString()
        if (title.isNullOrBlank()) {
            valid = false
            bottomsheetbinding.entertaskEditText.error = "Please Enter a Valid Title"
        } else {
            bottomsheetbinding.entertaskEditText.error = null
        }

        if (description.isNullOrBlank()) {
            valid = false
            bottomsheetbinding.descriptiontaskEditText.error = "Please Enter a Valid Description"
        } else {
            bottomsheetbinding.descriptiontaskEditText.error = null
        }
        return valid
    }

    private fun setDate() {
        // Date Formatter
        bottomsheetbinding.dateEditText.setText(
            "" + currentDate.get(Calendar.DAY_OF_MONTH)
                    + "/" + currentDate.get(Calendar.MONTH) + "/" + currentDate.get(Calendar.YEAR)
        )
    }

    private fun showDatePicker() {
        // to handel null exception  use context?.let instead of require activity
        // can remove DatePickerDialog.OnDateSetListener and use only lambda ex
        context?.let {
            DatePickerDialog(
                it,
                DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                    currentDate.set(Calendar.YEAR, year)
                    currentDate.set(Calendar.MONTH, month + 1)  // index of month starts with zero
                    currentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    setDate()
                },
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
            ).show()

        }
    }
}