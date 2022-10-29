package com.padcmyanmar.ttm.customviewassignmentapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.fragments.ProfileScreenFragment
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters.TaskScreenPresenter
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters.TaskScreenPresenterImpl
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.TaskScreenView
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.ProfileListViewPod
import kotlinx.android.synthetic.main.activity_task_screen.*
import kotlin.random.Random


class TaskScreenActivity : AppCompatActivity(),TaskScreenView {

   //View Pod
    private lateinit var mProfileListViewPod:ProfileListViewPod

   //Presenter
    private lateinit var mPresenter: TaskScreenPresenter

    companion object{
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, TaskScreenActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_screen)
        setUpPresenter()

        setUpToolbar()
        setUpProfileListViewPod()
        setUpClientData()
        setUpProgressBar()
        mPresenter.onUiReady()



//        if (savedInstanceState == null) {
//            resetProgress();
//        }
//        ivAttachment.setOnClickListener {
//            resetProgress()
//        }

    }

    private fun setUpProgressBar() {
        progressBar.setGoal(70)
        resetProgress();
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[TaskScreenPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    private fun resetProgress() {
        val prog: Int = Random.nextInt(100)
        progressBar.setProgress(prog)
    }
    private fun setUpClientData() {
        val tasksData = resources.getStringArray(R.array.tasks)

        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.spinner_list, tasksData)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        spinner.adapter = arrayAdapter
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // to show leading icon
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_white_24) // add leading icon
    }

    private fun setUpProfileListViewPod() {
        mProfileListViewPod = vpProfileListsData as ProfileListViewPod
        mProfileListViewPod.setProfileListViewPod(mPresenter,false)
    }


    override fun navigateToProfileScreen() {
        ProfileScreenFragment().apply {
            show(supportFragmentManager,tag)
        }
    }

    override fun navigateToCreateTaskPage() {
       startActivity(newIntent(this))
        finish()
    }

    override fun navigateToMainScreenPage() {
        finish()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // todo: goto back activity from here

               mPresenter.goToMainScreen()


                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}