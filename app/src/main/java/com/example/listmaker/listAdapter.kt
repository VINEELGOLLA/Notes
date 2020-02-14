package com.example.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class listAdapter(private val lists: ArrayList<TaskList>, val Clicklistener: ClickListener) : RecyclerView.Adapter<listviewholder>() {

    interface ClickListener{
         fun itemclicked(task: TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listviewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listlayout, parent, false)

        return listviewholder(view)

    }

    override fun getItemCount(): Int {
        return lists.size

    }

    override fun onBindViewHolder(holder: listviewholder, position: Int) {
        holder.listposition.text = (position+1).toString()
        holder.title.text = lists[position].name
        holder.itemView.setOnClickListener{
            Clicklistener.itemclicked(lists[position])
        }
    }

    fun addlist(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size-1)

    }
}