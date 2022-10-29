package com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders

import android.view.View

import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate

class CreateTaskViewHolder (itemView: View, var mDelegate: ProfileImageDelegate)  : BaseViewHolder(itemView,mDelegate)  {

    init {
        itemView.setOnClickListener {
            mDelegate.goToCreateTaskPage()
        }
    }

}