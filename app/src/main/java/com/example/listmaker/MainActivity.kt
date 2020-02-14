package com.example.listmaker

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity(),listAdapter.ClickListener {

    lateinit var recyclerView: RecyclerView
    val list = arrayListOf<String>()
    val list1 = arrayListOf<TaskList>()

    private val ListDataManager: ListDataManager = ListDataManager(this)

    constructor(parcel: Parcel) : this() {

    }

    companion object{
         const val Intent_Key = "list"
         const val Listdetail_Requestcode = 123
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val lists = ListDataManager.readlists()


        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter(lists,this)


        fab.setOnClickListener { _ ->
             listdialog()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun listdialog(){
        val dialogtitle = "add new note"
        val positivebuttontitle = "create"

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(dialogtitle)
        val listedittext = EditText(this)
        listedittext.inputType = InputType.TYPE_CLASS_TEXT
        dialog.setView(listedittext)

        dialog.setPositiveButton(positivebuttontitle) { dialog, _ ->
            val adapter = recyclerView.adapter as listAdapter
            val list = TaskList(listedittext.text.toString())
            ListDataManager.savelist(list)
            adapter.addlist(list)
            dialog.dismiss()
            showtasklistitem(list)


        }
        dialog.create().show()

    }

    private fun showtasklistitem(task: TaskList){
        val listitem = Intent(this,DetailActivity::class.java)
        listitem.putExtra(Intent_Key,task)
        startActivityForResult(listitem, Listdetail_Requestcode)
    }


    override fun itemclicked(task: TaskList) {
        showtasklistitem(task)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            val list = data.getParcelableExtra<TaskList>(Intent_Key)!!
            ListDataManager.savelist(list)
            updatelists()
        }
    }

    private fun updatelists() {
        val lists = ListDataManager.readlists()
        recyclerView.adapter = listAdapter(lists,this)
    }
}
