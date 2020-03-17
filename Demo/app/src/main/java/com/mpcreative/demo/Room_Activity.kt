package com.mpcreative.demo

import android.R.attr.name
import android.app.Dialog
import android.content.ClipData
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.Snackbar
import com.mpcreative.demo.Adapter.TasksAdapter
import com.mpcreative.demo.Model.Task_entity
import com.mpcreative.demo.Model.onClickListionar
import com.mpcreative.demo.Reset.DatabaseClient
import com.mpcreative.demo.Util.SwipeToDeleteCallback
import com.mpcreative.demo.horizontal.HorizontalAdapter
import kotlinx.android.synthetic.main.activity_room_.*
import kotlinx.android.synthetic.main.dialog_data.*


class Room_Activity : AppCompatActivity(), onClickListionar {

    lateinit var adapter: TasksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_)

        floating_button_add.setOnClickListener {
            addData()
        }

        recyclerview_tasks.layoutManager = LinearLayoutManager(this);
        recyclerview_tasks.itemAnimator = DefaultItemAnimator()
        recyclerview_tasks.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        getTask()


    }

    fun addData() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_data)

        val window: Window = dialog.window!!
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val editTextTask = dialog.findViewById(R.id.editTextTask) as EditText
        val editTextDesc = dialog.findViewById(R.id.editTextDesc) as EditText
        val editTextFinishBy = dialog.findViewById(R.id.editTextFinishBy) as EditText


        dialog.button_save.setOnClickListener {
            val task = Task_entity()
            task.task = editTextTask.text.toString()
            task.desc = editTextDesc.text.toString()
            task.finishBy = editTextFinishBy.text.toString()
            task.finished = false

            //adding to database

            //adding to database
            DatabaseClient.getInstance(applicationContext).appDatabase
                .taskDao()
                .insert(task)
            adapter.notifyDataSetChanged()
            dialog.dismiss()

        }


        //creating a task

        //creating a task

        dialog.show();

    }

    fun getTask() {

        val taskList: List<Task_entity> = DatabaseClient
            .getInstance(applicationContext)
            .appDatabase
            .taskDao()
            .getAll()

        adapter = TasksAdapter(this, this, taskList)
        recyclerview_tasks.setAdapter(adapter)

        //recyclerview_tasks.setAdapter(new HorizontalAdapter(getItems()));
        // recyclerview_tasks.setLayoutManager(new LinearLayoutManager(this));
    }


    override fun onClick(pos: Int) {
        adapter.notifyItemChanged(pos)
        adapter.notifyDataSetChanged()
        getTask()
    }


}
