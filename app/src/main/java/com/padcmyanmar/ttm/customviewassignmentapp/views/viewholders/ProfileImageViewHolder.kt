package com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import kotlinx.android.synthetic.main.view_holder_profile_image.view.*

class ProfileImageViewHolder(itemView: View,var mDelegate: ProfileImageDelegate) : RecyclerView.ViewHolder(itemView) {


    init {
        itemView.setOnClickListener {
            mDelegate.goToProfilePage()
        }
    }

   fun bindViewData(
       positionId: Int,
       itemCountData: Int,
       checkTaskListItem: Boolean
   ){

       if(!checkTaskListItem)
       {
           if(positionId == itemCountData-1)
           {
               itemView.civProfile.setImageDrawable(itemView.resources
                   .getDrawable(R.drawable.ic_baseline_add_24))
           }else{
               itemView.civProfile.setImageDrawable(itemView.resources
                   .getDrawable(R.drawable.profile_sample))
           }
       }


   }
}