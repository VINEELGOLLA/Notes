package com.example.listmaker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class tasklistviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tasktextview = itemView?.findViewById<TextView>(R.id.task_textView) as TextView
}