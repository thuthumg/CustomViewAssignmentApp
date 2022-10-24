package com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.TaskItemViewPod
import kotlinx.android.synthetic.main.view_holder_tasks_list.view.*


class TasksItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var mTaskListItemViewPod : TaskItemViewPod

    init {

        setUpViewPods()
    }

    private fun setUpViewPods() {
        mTaskListItemViewPod = itemView.viewPodTaskItem as TaskItemViewPod

    }
}