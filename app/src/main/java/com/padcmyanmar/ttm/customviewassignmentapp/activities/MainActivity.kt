package com.padcmyanmar.ttm.customviewassignmentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.adapters.ProfileImageListAdapter
import com.padcmyanmar.ttm.customviewassignmentapp.views.components.OverlapDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mProfileImageListAdapter: ProfileImageListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()
        setUpProfileRecyclerView()

    }
    private fun setUpProfileRecyclerView() {
        mProfileImageListAdapter = ProfileImageListAdapter()
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
}