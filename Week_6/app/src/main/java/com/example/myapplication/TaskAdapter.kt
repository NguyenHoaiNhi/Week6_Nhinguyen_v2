package com.example.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.room.Task
import kotlinx.android.synthetic.main.item_task.view.*


class TaskAdapter(var items: ArrayList<Task>, val context: Context) : RecyclerView.Adapter<TaskViewHolder>(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.item_task,parent, false ))
    }

    override fun onBindViewHolder(taskViewHolder: TaskViewHolder, position: Int) {
       taskViewHolder.tvTask.text = " $position  ${items[position].description}"
    }
    fun appenData(newTaskAdded: Task){
        this.items.add(newTaskAdded)
        notifyItemInserted(items.size-1)
    }

}

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var tvTask = view.tvTask
}