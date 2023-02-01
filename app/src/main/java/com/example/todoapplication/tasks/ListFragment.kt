package com.example.todoapplication.tasks

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapplication.database.MyDataBase
import com.example.todoapplication.database.model.Task
import com.example.todoapplication.databinding.FragmentListBinding
import com.example.todoapplication.home.OnDismissListener
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

class ListFragment : Fragment() {
    lateinit var listBinding: FragmentListBinding
    lateinit var Adapter: TasksAdapter
    val currentdate  = Calendar.getInstance()
    init {
        // because of epoch time
        // ignore
        currentdate.set(Calendar.HOUR,0)
        currentdate.set(Calendar.MINUTE,0)
        currentdate.set(Calendar.SECOND,0)
        currentdate.set(Calendar.MILLISECOND,0)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listBinding = FragmentListBinding.inflate(inflater,container,false)
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Adapter = TasksAdapter(null)
        listBinding.taskItemRecycler.adapter = Adapter
        listBinding.calendarView.setOnDateChangedListener{widget ,date ,selected ->
            if(selected){
                currentdate.set(Calendar.YEAR,date.year)
                currentdate.set(Calendar.MONTH, date.month - 1)
                currentdate.set(Calendar.DAY_OF_MONTH, date.day)
                loadTasks()
            }
        }
        listBinding.calendarView.selectedDate = CalendarDay.today()

//        onClickCheckBox()
//        loadTasks()
    }

    private fun loadTasks() {
      val listOfTasks = context?.let { MyDataBase.getDataBase(it).tasksDao().getTasksByDate(currentdate.timeInMillis) }
        Adapter.changeData(listOfTasks)
    }
//    private fun onClickCheckBox() {
//        Adapter.itemclicked = object : onItemClick {
//            override fun onCheckClick(position: Int) {
//            }
//        }
//    }
}