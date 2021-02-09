package com.example.todo_list_clean_architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.data.db.DatabaseService
import com.example.entity.Task
import kotlinx.android.synthetic.main.activity_full_task.*


class FullTaskActivity : AppCompatActivity() {

    private var db: DatabaseService? = null
    private var roomTaskDataSource: RoomTaskDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_task)

        db = DatabaseService.getInstance(context = this)
        roomTaskDataSource = RoomTaskDataSource(context = this )

        var selectedTask: Task? = null
        if(intent.extras != null) {
            selectedTask = intent.extras?.get("task") as Task
        }

        tvFullTaskTitle.text = selectedTask?.name
        tvFullTaskDateCreated.text = selectedTask?.dateCreated
        tvFullTaskDateDue.text = selectedTask?.dateDue
        tvFullTaskDesc.text = selectedTask?.desc

        when(selectedTask?.priority){
            0 -> tvFullTaskPriority.text = "Low"
            1 -> tvFullTaskPriority.text = "Medium"
            2 -> tvFullTaskPriority.text = "High"
        }


        btnFullTaskBin.setOnClickListener(){

            if (selectedTask != null) {
                roomTaskDataSource?.remove(selectedTask)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }


        btnFullTaskTick.setOnClickListener(){
            selectedTask?.isCompleted = true
            if (selectedTask != null) {
                roomTaskDataSource?.add(selectedTask)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }


        btnFullTaskBack.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnEditTask.setOnClickListener(){
            val intent = Intent(this, EditTaskActivity::class.java).apply {
                putExtra("task", selectedTask)
            }
            startActivity(intent)
        }

        supportActionBar?.hide()


    }
}