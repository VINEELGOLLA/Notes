package com.example.listmaker

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class ListDataManager(private val context: Context) {

    fun savelist(list: TaskList){
        val sharedlist = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedlist.putStringSet(list.name,list.tasks.toHashSet())
        sharedlist.apply()
    }

    fun readlists(): ArrayList<TaskList> {
        val sharedprefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedprefs.all
        val taskList = ArrayList<TaskList>()

        for (tasklist in contents){
            val taskitems = ArrayList(tasklist.value as HashSet<String>)
            val list = TaskList(tasklist.key,taskitems)
            taskList.add(list)
        }
        return taskList
    }
}
