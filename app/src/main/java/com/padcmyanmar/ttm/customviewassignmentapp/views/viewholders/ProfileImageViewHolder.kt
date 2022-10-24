package com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.customviewassignmentapp.R
import kotlinx.android.synthetic.main.view_holder_profile_image.view.*

class ProfileImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


   fun bindViewData(positionId: Int, itemCountData: Int){
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