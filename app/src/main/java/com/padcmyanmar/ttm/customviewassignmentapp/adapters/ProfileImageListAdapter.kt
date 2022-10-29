package com.padcmyanmar.ttm.customviewassignmentapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders.BaseViewHolder
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders.CreateTaskViewHolder
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders.ProfileImageViewHolder

class ProfileImageListAdapter(private var checkTaskListItem: Boolean,val mDelegate:ProfileImageDelegate) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object{
        val VIEW_TYPE_CREATE_TASK = 1
        val VIEW_TYPE_PROFILR_IMAGE = 2
    }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

       when(viewType)
       {
           VIEW_TYPE_CREATE_TASK ->{
               val view =
                   LayoutInflater.from(parent.context).inflate(R.layout.view_holder_create_task, parent, false)
               return CreateTaskViewHolder(view,mDelegate)
           }
           VIEW_TYPE_PROFILR_IMAGE->{
               val view =
                   LayoutInflater.from(parent.context).inflate(R.layout.view_holder_profile_image, parent, false)
               return ProfileImageViewHolder(view,mDelegate)
           }
           else->{
               val view =
                   LayoutInflater.from(parent.context).inflate(R.layout.view_holder_profile_image, parent, false)
               return ProfileImageViewHolder(view,mDelegate)
           }
       }



    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
       // holder.onBindData(checkTaskListItem)
    }

    override fun getItemCount(): Int {

        return if(checkTaskListItem)
            3
        else 5
    }

    override fun getItemViewType(position: Int): Int {

        return if(checkTaskListItem)
            VIEW_TYPE_PROFILR_IMAGE
        else {


            when (position) {
                itemCount - 1 -> {
                    VIEW_TYPE_CREATE_TASK
                }
                else -> {
                    VIEW_TYPE_PROFILR_IMAGE
                }
            }
        }
    }
}