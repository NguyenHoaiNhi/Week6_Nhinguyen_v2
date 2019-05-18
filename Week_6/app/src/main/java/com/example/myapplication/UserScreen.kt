package com.example.myapplication

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.user_screen.*
import android.widget.ImageView
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.Task
import com.example.myapplication.room.User
import com.example.myapplication.room.UserDAO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_screen.*
import java.util.*
import kotlin.concurrent.schedule


class UserScreen : AppCompatActivity() {
    var Users: ArrayList<User> = ArrayList()
    lateinit var userAdapter: UserAdapter
    lateinit var daoUser: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_screen)
        initRoomDatabase()
        setupRecyclerView()
        getUser()
        btnAdd.setOnClickListener {
            // db1 = AppDatabase.invoke(this)
            var user = User()
            user.name = edtUserID.text.toString()

            val id = daoUser.insert(user)
            user.id = id.toInt()
            userAdapter.appenData(user)

        }
    }

    private fun initRoomDatabase() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
        daoUser = db.UserDAO()
    }

    private fun setupRecyclerView() {
        rvUser.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        userAdapter = UserAdapter(Users, this)
        rvUser.adapter = userAdapter
        userAdapter.setListener(btRemove)
        userAdapter.notifyDataSetChanged() // notify data changed

    }

    private val btRemove = object : ItemClickListener {
        override fun btRemoveClicked(position: Int) {
            Log.e("Users[position]", Users[position].toString())
            daoUser.delete(Users[position])

            userAdapter.removeItem(Users[position], position)

            Timer(false).schedule(400) {
                runOnUiThread {
                    userAdapter.notifyDataSetChanged()
                }
            }

        }

    }

    private fun getUser() {
        val users = daoUser.getAll() // get Tasks from ROOM database
        Log.i("User: ", users.toString())
        this.Users.addAll(users) // add to task list
        userAdapter.notifyDataSetChanged() // notify data changed
    }


}