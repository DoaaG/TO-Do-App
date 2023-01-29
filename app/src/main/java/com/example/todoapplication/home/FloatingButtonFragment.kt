package com.example.todoapplication.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapplication.databinding.FragmentFloatingButtonBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FloatingButtonFragment : Fragment() {
    lateinit var FloatinButtonbinding:FragmentFloatingButtonBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FloatinButtonbinding = FragmentFloatingButtonBinding.inflate(inflater,container,false)
        return FloatinButtonbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
    }
}