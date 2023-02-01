package com.example.todoapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapplication.database.dao.TaskDao
import com.example.todoapplication.database.model.Task

//step(3) what database need
@Database(
    entities = [Task::class],
    version = 2, //changed when add new column
    exportSchema = false  // save schema in jason file instead of generating every time
)
abstract class MyDataBase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao

    // to be static use companion object
    companion object {
        private var myDataBase: MyDataBase? = null
        val dataBasename = "RouteTasksDataBase"

        fun getDataBase(context: Context): MyDataBase {
            // singleton pattern
            if (myDataBase == null) {
                // initialize else use it
                myDataBase = Room.databaseBuilder(context, MyDataBase::class.java, dataBasename)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build()
            }
            return myDataBase!!
        }
    }
}
//allowMainThreadQueries() ---> respond to user interface
// main thread  respond to user