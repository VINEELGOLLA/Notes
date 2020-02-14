package com.example.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class tasklistAdapter( var list: TaskList):  RecyclerView.Adapter<tasklistviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tasklistviewholder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.task_view_holder,parent,false)
        return  tasklistviewholder(view)
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }

    override fun onBindViewHolder(holder: tasklistviewholder, position: Int) {
        holder.tasktextview?.text  = list.tasks[position]
    }
}