package com.example.listmaker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class listviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val listposition = itemView.findViewById<TextView>(R.id.id)
    val title = itemView.findViewById<TextView>(R.id.name)


}