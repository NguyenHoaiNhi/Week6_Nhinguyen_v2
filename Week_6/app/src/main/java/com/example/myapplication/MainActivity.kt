package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.content.Intent


import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.Task
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule
import android.app.Activity
import android.util.Log
import android.view.View
import com.example.myapplication.room.TaskDAO
import com.example.myapplication.UserScreen
import com.facebook.stetho.Stetho
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var tasks: ArrayList<Task> = ArrayList()
    lateinit var taskAdapter: TaskAdapter
    lateinit var dao: TaskDAO
    //lateinit var db1: AppDatabase
    var task = Task()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
        initRoomDatabase()
        setupRecyclerView()
        getTask()
        btAdd.setOnClickListener{
           // db1 = AppDatabase.invoke(this)
            task.description = edtTask.text.toString()
            dao.insert(task)
            //task.id = id.toInt()
            taskAdapter.appenData(task)

        }
        btPlus.setOnClickListener(toUserScreen)

    }
    private val toUserScreen = View.OnClickListener {
        val intent = Intent(this, UserScreen::class.java)
        startActivityForResult(intent, REQUEST_SETTING_USER_SCREEN)
    }
    companion object {
        const val REQUEST_SETTING_USER_SCREEN = 1099
    }
    private fun initRoomDatabase(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME).allowMainThreadQueries()
            .build()
        dao = db.TaskDAO()
    }
    /**
     * setup layout manager and recyclerview
     */
    private fun setupRecyclerView(){
        rvTask.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        taskAdapter = TaskAdapter(tasks, this)
        rvTask.adapter = taskAdapter

    }
    private fun getTask(){
        val tasks = dao.getAll() // get Tasks from ROOM database
        Log.i("Task: ", tasks.toString())
        this.tasks.addAll(tasks) // add to task list
        taskAdapter.notifyDataSetChanged() // notify data changed
    }

}
