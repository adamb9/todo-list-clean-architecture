package com.example.todo_list_clean_architecture

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import com.example.data.db.DatabaseService
import com.example.entity.Task
import kotlinx.android.synthetic.main.activity_add_task.*
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private var db: DatabaseService? = null
    private var roomTaskDataSource: RoomTaskDataSource? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        db = DatabaseService.getInstance(context = this)
        roomTaskDataSource = RoomTaskDataSource(context = this)

        var priority: Int? = 0
        sbPriority.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                priority = i
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

        })


        tvDueDate.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
        val currentDate = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        var dueDate = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.UK)
            tvDueDate.text = sdf.format(cal.time)
            dueDate = sdf.format(cal.time)
            println(dueDate)

        }

        tvDueDate.setOnClickListener {
            DatePickerDialog(this@AddTaskActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }



        btnAddTask.setOnClickListener{
            val taskname = etNewTaskName.text.toString()
            val taskdesc = etNewTaskDesc.text.toString()

            val newTask = Task(
                name = taskname,
                priority = priority,
                desc = taskdesc,
                dateCreated = currentDate,
                dateDue = dueDate
            )
            roomTaskDataSource?.add(newTask)
            println(newTask.name)
            println(newTask.desc)
            println(newTask.priority)
            println(newTask.dateCreated)
            println(newTask.dateDue)

            val intent = Intent(this, MainActivity::class.java)
            /*.apply {
            putExtra("task", new_task)
        }*/
            startActivity(intent)

        }
    }
}