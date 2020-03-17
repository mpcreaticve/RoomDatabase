package com.mpcreative.demo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mpcreative.demo.Model.Data
import com.mpcreative.demo.R

class EmployeeAdapter(internal var context: Context, var arrayList: ArrayList<Data>) :
    RecyclerView.Adapter<EmployeeAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeAdapter.viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)


        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: EmployeeAdapter.viewHolder, position: Int) {
        val data = arrayList[position]

        holder.tv_name.text = data.employeeName
        holder.tv_salary.text = data.employeeSalary
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_name: TextView
        val tv_salary: TextView

        init {
            tv_name = view.findViewById(R.id.tv_name)
            tv_salary = view.findViewById(R.id.tv_salary)
        }
    }
}