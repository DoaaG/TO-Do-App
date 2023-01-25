package com.example.todoapplication.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapplication.database.MyDataBase
import com.example.todoapplication.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var listBinding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listBinding = FragmentListBinding.inflate(inflater,container,false)
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {  //step(4) read from database
        super.onViewCreated(view, savedInstanceState)
        MyDataBase.getDataBase(requireActivity()).tasksDao().getAllTasks()
    }
}