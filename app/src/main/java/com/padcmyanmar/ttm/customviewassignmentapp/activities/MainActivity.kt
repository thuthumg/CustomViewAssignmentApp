package com.padcmyanmar.ttm.customviewassignmentapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.customviewassignmentapp.ProfileScreenFragment
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.adapters.ProfileImageListAdapter
import com.padcmyanmar.ttm.customviewassignmentapp.adapters.TasksListAdapter
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.views.components.OverlapDecoration
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.TaskDataViewPod
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.TaskItemViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , ProfileImageDelegate {

    lateinit var mProfileImageListAdapter: ProfileImageListAdapter
    lateinit var mTaskDataViewPod: TaskDataViewPod


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()
        setUpProfileRecyclerView()
        setUpTasksViewPod()




    }


    private fun setUpTasksViewPod() {
//        mTasksListAdapter = TasksListAdapter(this)
//        rvTaskLists.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        rvTaskLists.adapter = mTasksListAdapter

        mTaskDataViewPod = vpTaskListData as TaskDataViewPod
        mTaskDataViewPod.mDelegate = this
    }

    private fun setUpProfileRecyclerView() {
        mProfileImageListAdapter = ProfileImageListAdapter(false,this)
        rvProfileImageList.addItemDecoration(OverlapDecoration())
     val  layoutManager =
         LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
       // layoutManager.orientation = HORIZONTAL
      //  layoutManager.reverseLayout = true
      //  layoutManager.stackFromEnd = true
        rvProfileImageList.layoutManager = layoutManager
        rvProfileImageList.adapter = mProfileImageListAdapter
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // to show leading icon
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24) // add leading icon
    }

    override fun goToProfilePage() {
      //  setUpBottomSheet()

        ProfileScreenFragment().apply {
            show(supportFragmentManager,tag)
        }
     }

}