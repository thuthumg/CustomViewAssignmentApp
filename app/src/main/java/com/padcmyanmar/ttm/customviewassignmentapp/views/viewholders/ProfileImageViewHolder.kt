package com.padcmyanmar.ttm.customviewassignmentapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import kotlinx.android.synthetic.main.view_holder_profile_image.view.*

class ProfileImageViewHolder(itemView: View,var mDelegate: ProfileImageDelegate) : BaseViewHolder(itemView,mDelegate) {


    init {
        itemView.setOnClickListener {
            mDelegate.goToProfilePage()
        }
    }

}