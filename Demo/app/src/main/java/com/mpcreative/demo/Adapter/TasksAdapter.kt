package com.mpcreative.demo.Adapter

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.text.Editable
import android.view.*
import android.widget.*
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.mpcreative.demo.Model.Task_entity
import com.mpcreative.demo.Model.onClickListionar
import com.mpcreative.demo.R
import com.mpcreative.demo.Reset.DatabaseClient
import com.zerobranch.layout.SwipeLayout
import kotlinx.android.synthetic.main.dialog_data.*


class TasksAdapter(
    private val mCtx: Context, val listionar: onClickListionar,
    private val taskList: List<Task_entity>
) :
    RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    private val items: ArrayList<ViewModel>? = null
    var list: ArrayList<Task_entity> = ArrayList()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksViewHolder {
        val view: View =
            LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false)
        list = taskList as ArrayList<Task_entity>
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TasksViewHolder,
        position: Int
    ) {
        val t = list[position]
        holder.textViewTask.text = t.task
        holder.textViewDesc.text = t.desc

        holder.textViewFinishBy.text = t.finishBy
        if (t.finished) holder.textViewStatus.text =
            "Completed" else holder.textViewStatus.text =
            "Not Completed"

        holder.leftView!!.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                upload(mCtx, t.id, holder.adapterPosition)
            }
        }
        holder.rightView!!.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                remove(mCtx, t.id, holder.adapterPosition)
            }
        }
        holder.swipeLayout!!.setOnActionsListener(object :
            SwipeLayout.SwipeActionsListener {
            override fun onOpen(direction: Int, isContinuous: Boolean) {
                if (direction == SwipeLayout.LEFT && isContinuous) {
                    if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                        remove(mCtx, t.id, holder.adapterPosition)
                    }
                }
            }

            override fun onClose() {}
        })
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TasksViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textViewStatus: TextView
        var textViewTask: TextView
        var textViewDesc: TextView
        var textViewFinishBy: TextView
        var leftView: ImageView? = null
        var rightView: ImageView? = null
        var dragItem: LinearLayout? = null
        var swipeLayout: SwipeLayout? = null

        init {
            swipeLayout = itemView.findViewById(R.id.swipe_layout)

            textViewStatus = itemView.findViewById(R.id.textViewStatus)
            textViewTask = itemView.findViewById(R.id.textViewTask)
            textViewDesc = itemView.findViewById(R.id.textViewDesc)
            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy)
            leftView = itemView.findViewById(R.id.left_view)
            rightView = itemView.findViewById(R.id.right_view)
        }
    }


    fun getData(): List<Task_entity>? {
        return list
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun remove(
        context: Context,
        id: Int,
        position: Int
    ) {
        Toast.makeText(
            context,
            "removed item $position",
            Toast.LENGTH_SHORT
        ).show()

        deleteData(id)
        notifyDataSetChanged()
        removeItem(position)
    }

    private fun upload(
        context: Context,
        id: Int,
        position: Int
    ) {
        Toast.makeText(
            context,
            "upload item $position",
            Toast.LENGTH_SHORT
        ).show()
        editData(id, position)
        //Room_Activity.getTask()
    }

    fun deleteData(position: Int) {
        val task = Task_entity()
        task.id = position
        //adding to database

        //adding to database
        DatabaseClient.getInstance(mCtx).appDatabase
            .taskDao()
            .delete(task)

    }


    fun editData(id: Int, position: Int) {
        val dialog = Dialog(mCtx)
        dialog.setContentView(R.layout.dialog_data)

        val window: Window = dialog.window!!
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        val taskList: Task_entity = DatabaseClient
            .getInstance(mCtx)
            .appDatabase
            .taskDao()
            .getUpdate(id)

        val editTextTask = dialog.findViewById(R.id.editTextTask) as EditText
        val editTextDesc = dialog.findViewById(R.id.editTextDesc) as EditText
        val editTextFinishBy = dialog.findViewById(R.id.editTextFinishBy) as EditText

        editTextTask.text = Editable.Factory.getInstance().newEditable(taskList.task)
        editTextDesc.text = Editable.Factory.getInstance().newEditable(taskList.desc)
        editTextFinishBy.text = Editable.Factory.getInstance().newEditable(taskList.finishBy)

        dialog.button_save.setOnClickListener {
            val task = Task_entity()
            task.id = id
            task.task = editTextTask.text.toString()
            task.desc = editTextDesc.text.toString()
            task.finishBy = editTextFinishBy.text.toString()
            task.finished = false

            //adding to database

            //adding to database
            DatabaseClient.getInstance(mCtx).appDatabase
                .taskDao()
                .update(task)

            listionar.onClick(position)

            dialog.dismiss()
        }


        //creating a task

        //creating a task

        dialog.show();

    }

    fun updateData(viewModels: ArrayList<ViewModel>) {
        items!!.clear()
        items.addAll(viewModels)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, viewModel: ViewModel?)
    }


}