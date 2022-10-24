package com.padcmyanmar.ttm.customviewassignmentapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders.TasksItemViewHolder

class TasksListAdapter : RecyclerView.Adapter<TasksItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_tasks_list, parent, false)
        return TasksItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 5
    }
}