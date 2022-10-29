package com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.ProfileListViewPod
import kotlinx.android.synthetic.main.view_holder_tasks_list.view.*


class TasksItemViewHolder (itemView: View, private val mDelegate: ProfileImageDelegate) : RecyclerView.ViewHolder(itemView) {

    private lateinit var mProfileListViewPod : ProfileListViewPod

    init {

        itemView.vpProfileLists.setOnClickListener {
             mDelegate.goToProfilePage()
        }

        setUpViewPods()
    }

    private fun setUpViewPods() {

        mProfileListViewPod = itemView.vpProfileLists as ProfileListViewPod
        mProfileListViewPod.setProfileListViewPod(mDelegate,true)


    }
}