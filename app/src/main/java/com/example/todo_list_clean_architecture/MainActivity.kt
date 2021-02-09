package com.example.todo_list_clean_architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.db.DatabaseService
import com.example.entity.Task
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val tasks: ArrayList<Task> = ArrayList<Task>()
    private var db: DatabaseService? = null
    private var roomTaskDataSource: RoomTaskDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseService.getInstance(context = this)
        roomTaskDataSource = RoomTaskDataSource(context = this)

        val dbTasks: List<Task>? = roomTaskDataSource?.getAllUncomplete()

        if (dbTasks != null) {
            for(task in dbTasks){
                addTasks(task)
            }
        }

        if(intent.extras != null) {
            val addedTask: Task? = intent.extras?.get("task") as Task
            addTasks(addedTask)
        }

        rvTaskList.layoutManager = LinearLayoutManager( this )

        var adapter = TaskAdapter(tasks, this)
        rvTaskList.adapter = adapter

        btnNewTask.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }


        btnFilterTasks.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,btnFilterTasks)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.sort_date_desc -> {
                        var sortedList: ArrayList<Task> = ArrayList(tasks.sortedWith(compareBy({it.dateCreated})).reversed())
                        adapter.update(sortedList)
                    }
                    R.id.sort_date_asc ->{
                        var sortedList: ArrayList<Task> = ArrayList(tasks.sortedWith(compareBy({it.dateCreated})))
                        adapter.update(sortedList)
                    }
                    R.id.sort_priority_desc ->{
                        var sortedList: ArrayList<Task> = ArrayList(tasks.sortedWith(compareBy({it.priority})).reversed())
                        adapter.update(sortedList)
                    }
                    R.id.sort_priority_asc ->{
                        var sortedList: ArrayList<Task> = ArrayList(tasks.sortedWith(compareBy({it.priority})))
                        adapter.update(sortedList)
                    }
                }
                true
            })
            popupMenu.show()
        }

    }



    fun addTasks(addedTask: Task? = null){
        if (addedTask != null) {
            tasks.add(addedTask)
        }
    }


}