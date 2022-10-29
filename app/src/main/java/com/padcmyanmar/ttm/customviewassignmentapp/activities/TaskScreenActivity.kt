package com.padcmyanmar.ttm.customviewassignmentapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.fragments.ProfileScreenFragment
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.ProfileListViewPod
import kotlinx.android.synthetic.main.activity_task_screen.*


class TaskScreenActivity : AppCompatActivity(),ProfileImageDelegate {
    private lateinit var mProfileListViewPod:ProfileListViewPod
    companion object{
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, TaskScreenActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_screen)

        setUpToolbar()
        setUpProfileListViewPod()
        setUpClientData()
        progressBar.setProgress(50)
        progressBar.setGoal(70)

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
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24) // add leading icon
    }

    private fun setUpProfileListViewPod() {
        mProfileListViewPod = vpProfileListsData as ProfileListViewPod
        mProfileListViewPod.setProfileListViewPod(this,false)
    }

    override fun goToProfilePage() {
        ProfileScreenFragment().apply {
            show(supportFragmentManager,tag)
        }
    }

    override fun goToCreateTaskPage() {
    }

}