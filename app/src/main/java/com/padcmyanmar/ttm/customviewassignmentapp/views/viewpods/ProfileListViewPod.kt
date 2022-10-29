package com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.customviewassignmentapp.adapters.ProfileImageListAdapter
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.views.components.OverlapDecoration
import kotlinx.android.synthetic.main.view_pod_profile_list.view.*

class ProfileListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mDelegate: ProfileImageDelegate
    private lateinit var  mProfileImageListAdapter : ProfileImageListAdapter
    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    private fun setDelegate(delegate: ProfileImageDelegate){
        this.mDelegate = delegate
    }

    fun setProfileListViewPod(delegate: ProfileImageDelegate,checkTaskListItemFlag:Boolean){
        setDelegate(delegate)
        setUpProfileListRecyclerView(checkTaskListItemFlag = checkTaskListItemFlag)
    }

    private fun setUpProfileListRecyclerView(checkTaskListItemFlag:Boolean) {
        mProfileImageListAdapter = ProfileImageListAdapter(checkTaskListItem = checkTaskListItemFlag,
            mDelegate = mDelegate)
        rvProfileImageList.addItemDecoration(OverlapDecoration())
        val  layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        // layoutManager.orientation = HORIZONTAL
        //  layoutManager.reverseLayout = true
        //  layoutManager.stackFromEnd = true
        rvProfileImageList.layoutManager = layoutManager
        rvProfileImageList.adapter = mProfileImageListAdapter

    }
}