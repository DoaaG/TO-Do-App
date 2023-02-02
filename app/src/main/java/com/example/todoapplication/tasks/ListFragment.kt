package com.example.todoapplication.tasks

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapplication.Constants.TASK
import com.example.todoapplication.base.BaseFragment
import com.example.todoapplication.database.MyDataBase
import com.example.todoapplication.database.model.Task
import com.example.todoapplication.databinding.FragmentListBinding
import com.example.todoapplication.settings.EditTaskActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*


class ListFragment : BaseFragment() {
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
                currentdate.set(Calendar.MONTH, date.month)
                currentdate.set(Calendar.DAY_OF_MONTH, date.day)
                loadTasks()
            }
        }
        listBinding.calendarView.selectedDate = CalendarDay.today()

        onClickCheckBox()

    }

    private fun loadTasks() {
      val listOfTasks = context?.let { MyDataBase.getDataBase(it).tasksDao().getTasksByDate(currentdate.timeInMillis) }
        Adapter.changeData(listOfTasks)
    }
    private fun onClickCheckBox() {
        Adapter.itemclicked = object : onItemClick {
            override fun onCheckClick(task: Task) {
                showMessage("What do you want to do?" ,"Update",{_,i -> updateTodo(task)},"Done",{_,i -> makeTodoDone(task)})
            }
        }
    }

    private fun updateTodo(task : Task) {
        var intent = Intent(requireContext(),EditTaskActivity::class.java)
        intent.putExtra(TASK,task)
        startActivity(intent)
    }
    private fun makeTodoDone(task :Task){
        task.isDone = true
        context?.let { MyDataBase.getDataBase(it).tasksDao().updateTask(task) }
        refreshRecycler()
    }

    private fun refreshRecycler() {
        Adapter.changeData(context?.let { MyDataBase.getDataBase(it).tasksDao().getTasksByDate(currentdate.timeInMillis) })
        Adapter.notifyDataSetChanged()
    }
}