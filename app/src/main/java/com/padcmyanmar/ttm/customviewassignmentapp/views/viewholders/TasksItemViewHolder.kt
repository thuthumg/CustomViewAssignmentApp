package com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.TaskItemViewPod
import kotlinx.android.synthetic.main.view_holder_tasks_list.view.*


class TasksItemViewHolder (itemView: View, private val mDelegate: ProfileImageDelegate) : RecyclerView.ViewHolder(itemView) {

    private lateinit var mTaskListItemViewPod : TaskItemViewPod
  //  private lateinit var mParamDelegate: ProfileImageDelegate

    init {
//
//        itemView.viewPodTaskItem.setOnClickListener {
//            mParamDelegate = mDelegate
//        }

        setUpViewPods()
    }

    private fun setUpViewPods() {
        mTaskListItemViewPod = itemView.viewPodTaskItem as TaskItemViewPod
        mTaskListItemViewPod.setDelegate(mDelegate)
      //  mTaskListItemViewPod.setUpProfileRecyclerView()

    }
}