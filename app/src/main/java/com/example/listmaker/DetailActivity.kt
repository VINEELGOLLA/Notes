package com.example.listmaker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

     lateinit var list: TaskList
    lateinit var tasklistrecyclerview: RecyclerView
    lateinit var addtask: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        list = intent.getParcelableExtra(MainActivity.Intent_Key) as TaskList
        title = list.name

        addtask = findViewById(R.id.addtask)

        tasklistrecyclerview = findViewById(R.id.task_recyclerview)
        tasklistrecyclerview.layoutManager = LinearLayoutManager(this)
        tasklistrecyclerview.adapter = tasklistAdapter(list)

        addtask.setOnClickListener{
            ShowCreateTaskDialog()
        }

    }

    private fun ShowCreateTaskDialog() {
        val taskEditTask = EditText(this)
        taskEditTask.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle("What is the task")
            .setView(taskEditTask)
            .setPositiveButton("ADD"){
                dialog, _ ->
                    val task = taskEditTask.text.toString()
                    list.tasks.add(task)
                    System.out.println(list.tasks)
                    dialog.dismiss()
            }
            .create()
            .show()
    }
    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.Intent_Key, list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)

        super.onBackPressed()
    }
}
