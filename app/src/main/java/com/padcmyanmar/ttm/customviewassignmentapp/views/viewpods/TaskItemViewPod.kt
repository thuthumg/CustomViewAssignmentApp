package com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.padcmyanmar.ttm.customviewassignmentapp.adapters.ProfileImageListAdapter
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.views.components.OverlapDecoration
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.view_pod_task_item.view.*

class TaskItemViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs){

    lateinit var mProfileImageListAdapter:ProfileImageListAdapter
    lateinit var mDelegate: ProfileImageDelegate
    override fun onFinishInflate() {
      //  setUpProfileRecyclerView()
        super.onFinishInflate()
    }
    fun setDelegate(delegate: ProfileImageDelegate) {
        this.mDelegate = delegate
        setUpProfileRecyclerView()
    }
    fun setUpProfileRecyclerView() {
        mProfileImageListAdapter = ProfileImageListAdapter(true,mDelegate)
        rvProfileImage.addItemDecoration(OverlapDecoration())
        val  layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        // layoutManager.orientation = HORIZONTAL
        //  layoutManager.reverseLayout = true
        //  layoutManager.stackFromEnd = true
        rvProfileImage.layoutManager = layoutManager
        rvProfileImage.adapter = mProfileImageListAdapter
    }


}