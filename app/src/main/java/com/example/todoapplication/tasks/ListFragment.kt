package com.example.todoapplication.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapplication.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var listBinding: FragmentListBinding
    lateinit var Adapter: TasksAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listBinding = FragmentListBinding.inflate(inflater,container,false)
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var list = listOf<String>("aaaaa*aaa","bbbbbbb","ccccccccc","aaaaaaaa","bbbbbbb","ccccccccc","aaaaaaaa","bbbbbbb","ccccccccc","aaaaaaaa","bbbbbbb","ccccccccc","aaaaaaaa","bbbbbbb","ccccccccc","ddddddd","aaaaaaaa","bbbbbbb","ccccccccc")
        Adapter = TasksAdapter(list)
        listBinding.taskItemRecycler.adapter = Adapter
    }
}