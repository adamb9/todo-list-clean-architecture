package com.example.todo_list_clean_architecture

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.db.DatabaseService
import com.example.entity.Task
import kotlinx.android.synthetic.main.single_task.view.*


class TaskAdapter(var tasks: ArrayList<Task>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private var db: DatabaseService? = null
    private var roomTaskDataSource: RoomTaskDataSource? = null

    // Gets the number of tasks in the list
    override fun getItemCount(): Int {
        return tasks.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_task, parent, false))
    }

    // Binds each task in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        db = DatabaseService.getInstance(context)
        roomTaskDataSource = RoomTaskDataSource(context)

        holder?.tvTaskName?.text = tasks.get(position).name
        when(tasks.get(position).priority) {
            2 -> holder?.ivTaskColor?.setImageResource(R.drawable.redcircle_foreground)
            1 -> holder?.ivTaskColor?.setImageResource(R.drawable.orangecircle_foreground)
            0 -> holder?.ivTaskColor?.setImageResource(R.drawable.greencircle_foreground)
        }
        holder?.btnCompleteTask.setOnClickListener{
            var selectedTask = tasks[position]
            selectedTask.isCompleted = true
            roomTaskDataSource?.add(selectedTask)
            tasks.removeAt(position)
            notifyDataSetChanged()
        }
        holder?.taskView.setOnClickListener{
            val intent = Intent(context, FullTaskActivity::class.java).apply {
                putExtra("task", tasks[position])
            }

            context.startActivity(intent)
        }
    }

    fun update(newList: ArrayList<Task>){
        /*for(task in newList){
            println(task.name)
        }*/
        tasks = newList
        notifyDataSetChanged()
        //println("herherhehrherh")
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the fields that we will add each task to
    val tvTaskName = view.tvTaskName
    val ivTaskColor = view.ivTaskColor
    val btnCompleteTask = view.btnCompleteTask
    val taskView = view
}