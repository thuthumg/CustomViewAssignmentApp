package com.padcmyanmar.ttm.customviewassignmentapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.padcmyanmar.ttm.customviewassignmentapp.fragments.ProfileScreenFragment
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters.MainPresenter
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters.MainPresenterImpl
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.MainView
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.ProfileListViewPod
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.TaskDataViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , MainView {

    //View Pods
    lateinit var mTaskDataViewPod: TaskDataViewPod
    lateinit var mProfileListViewPod: ProfileListViewPod


    //Presenter
    private lateinit var mPresenter: MainPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpPresenter()

        setUpToolbar()
        setUpProfileListViewPod()
        setUpTasksViewPod()

        mPresenter.onUiReady()
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpTasksViewPod() {
        mTaskDataViewPod = vpTaskListData as TaskDataViewPod
        mTaskDataViewPod.setUpTaskListViewPod(mPresenter)
    }

    private fun setUpProfileListViewPod() {
        mProfileListViewPod = vpProfileLists as ProfileListViewPod
        mProfileListViewPod.setProfileListViewPod(mPresenter,false)
       }

    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // to show leading icon
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_gray_24) // add leading icon
    }

    override fun navigateToProfileScreen() {
        ProfileScreenFragment().apply {
            show(supportFragmentManager,tag)
        }
    }

    override fun navigateToCreateTaskScreen() {
        startActivity(TaskScreenActivity.newIntent(this))
    }

}