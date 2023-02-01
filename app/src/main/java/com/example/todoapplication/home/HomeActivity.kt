package com.example.todoapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todoapplication.R
import com.example.todoapplication.databinding.ActivityHomeBinding
import com.example.todoapplication.settings.SettingsFragment
import com.example.todoapplication.tasks.ListFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        initviews()
        homeBinding.includeFloatingBtn.homeBottomNavigationView.selectedItemId = R.id.nav_tasks_list
    }

    private fun initviews() {
        homeBinding.includeFloatingBtn.homeBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_settings ->{
                    showFragment(SettingsFragment())
                    //to change title
                }
                R.id.nav_tasks_list ->{
                    showFragment(ListFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        homeBinding.includeFloatingBtn.addTaskFloatingButton.setOnClickListener{
            val fragment = AddTaskFragment()
            fragment.show(supportFragmentManager,"")
        }

    }


    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }
}