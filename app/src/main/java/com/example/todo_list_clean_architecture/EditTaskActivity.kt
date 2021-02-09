package com.example.todo_list_clean_architecture

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.data.db.DatabaseService
import com.example.entity.Task
import kotlinx.android.synthetic.main.activity_edit_task.*
import java.util.*


class EditTaskActivity : AppCompatActivity() {

    private var db: DatabaseService? = null
    private var roomTaskDataSource: RoomTaskDataSource? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        db = DatabaseService.getInstance(context = this)
        roomTaskDataSource = RoomTaskDataSource(context = this)

        var selectedTask: Task? = null
        if(intent.extras != null) {
            selectedTask = intent.extras?.get("task") as Task
        }

        etEditTaskName.setText(selectedTask?.name, TextView.BufferType.EDITABLE)
        etEditTaskDesc.setText(selectedTask?.desc, TextView.BufferType.EDITABLE)

        var priority: Int? = selectedTask?.priority
        sbEditPriority.setProgress(priority!!)
        sbEditPriority.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

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

        tvEditDueDate.text = selectedTask?.dateDue

        var cal = Calendar.getInstance()


        var dueDate = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.UK)

            tvEditDueDate.text = sdf.format(cal.time)
            dueDate = sdf.format(cal.time)
            println(dueDate)

        }

        tvEditDueDate.setOnClickListener {
            DatePickerDialog(this@EditTaskActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnConfirmEditTask.setOnClickListener(){
            val taskname = etEditTaskName.text.toString()
            val taskdesc = etEditTaskDesc.text.toString()

            selectedTask?.name = taskname
            selectedTask?.desc = taskdesc
            selectedTask?.priority = priority
            selectedTask?.dateDue = dueDate

            if (selectedTask != null) {
                roomTaskDataSource?.add(selectedTask)
                val intent = Intent(this, FullTaskActivity::class.java).apply {
                    putExtra("task", selectedTask)
                }

                startActivity(intent)
            }

        }

    }
}