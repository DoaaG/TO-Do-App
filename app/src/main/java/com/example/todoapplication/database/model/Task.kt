package com.example.todoapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")            //step(1) create table
data class Task(
    @PrimaryKey(autoGenerate = true) var id:Int? =null,
    @ColumnInfo var title:String? =null,    // add as a column
    @ColumnInfo var description:String? =null,
    @ColumnInfo var date:Long? =null,
    @ColumnInfo var isDone:Boolean =false
){

}