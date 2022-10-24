package com.padcmyanmar.ttm.customviewassignmentapp.adapters

import android.icu.text.LocaleDisplayNames.UiListItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders.ProfileImageViewHolder

class ProfileImageListAdapter(private var checkTaskListItem: Boolean) : RecyclerView.Adapter<ProfileImageViewHolder>() {


   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_profile_image, parent, false)
        return ProfileImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileImageViewHolder, position: Int) {

        holder.bindViewData(position,itemCount,checkTaskListItem)

    }

    override fun getItemCount(): Int {

        if(checkTaskListItem)
        return 2
        else return 3
    }
}