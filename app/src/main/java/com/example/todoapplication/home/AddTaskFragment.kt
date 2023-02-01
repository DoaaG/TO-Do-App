package com.example.todoapplication.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todoapplication.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddTaskFragment : BottomSheetDialogFragment() {
    lateinit var bottomsheetbinding: FragmentAddTaskBinding
    var currentDate = Calendar.getInstance()    //return obj of calender type

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

    }

    private fun setDate() {
        // Date Formatter
        bottomsheetbinding.dateEditText.setText("" + currentDate.get(Calendar.DAY_OF_MONTH)
                +"/"+currentDate.get(Calendar.MONTH) +"/"+currentDate.get(Calendar.YEAR))
    }

    private fun showDatePicker() {
        // to handel null exception  use context?.let instead of require activity
        // can remove DatePickerDialog.OnDateSetListener and use only lambda ex
        context?.let {
            DatePickerDialog(
                it,
                DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                    currentDate.set(Calendar.YEAR,year)
                    currentDate.set(Calendar.MONTH,month+1)  // index of month starts with zero
                    currentDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                    setDate()
                },
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
            ).show()

        }
    }
}