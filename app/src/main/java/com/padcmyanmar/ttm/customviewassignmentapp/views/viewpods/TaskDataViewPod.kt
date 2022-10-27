package com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.customviewassignmentapp.adapters.TasksListAdapter
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import kotlinx.android.synthetic.main.view_pod_task_data.view.*

class TaskDataViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {


    lateinit var mTasksListAdapter: TasksListAdapter
    lateinit var mDelegate: ProfileImageDelegate
    override fun onFinishInflate() {
        // setUpMovieRecyclerView()
        super.onFinishInflate()
    }

    fun setUpTaskListViewPod(delegate: ProfileImageDelegate) {
        setDelegate(delegate)
        setUpTaskDataRecyclerView()
    }

    private fun setDelegate(delegate: ProfileImageDelegate) {
        this.mDelegate = delegate
    }

    private fun setUpTaskDataRecyclerView() {

    mTasksListAdapter = TasksListAdapter(mDelegate)
    rvTaskLists.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    rvTaskLists.adapter = mTasksListAdapter


    }

}