package com.padcmyanmar.ttm.customviewassignmentapp.fragments

import android.app.DialogFragment.STYLE_NORMAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.TaskDataViewPod
import kotlinx.android.synthetic.main.bottom_sheet_fragment_profile_screen.view.*


class ProfileScreenFragment  : BottomSheetDialogFragment() , ProfileImageDelegate {

    private lateinit var bottomSheet: ViewGroup
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    private lateinit var mViewPodTaskData: TaskDataViewPod

    override fun onStart() {
        super.onStart()
        bottomSheet =
            dialog!!.findViewById(com.google.android.material.R.id.design_bottom_sheet) as ViewGroup // notice the R root package
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        // SETUP YOUR BEHAVIOR HERE
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myview: View = inflater.inflate(R.layout.bottom_sheet_fragment_profile_screen, container, false)

        // SETUP THE VIEWPAGER AND THE TABLAYOUT HERE

        val tabLayout = myview.findViewById<TabLayout>(R.id.tabLayout)
        val ivClose = myview.findViewById<AppCompatImageView>(R.id.ivClose)

        ivClose.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
        setUpTasksViewPod(myview)
        setUpTabLayout(tabLayout = tabLayout)

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        setStyle(STYLE_NORMAL,R.style.MyTransparentBottomSheetDialogTheme)
        return myview
    }

    private fun setUpTabLayout(tabLayout: TabLayout) {

        val taskListArray: Array<String> = arrayOf("Project Tasks", "Contacts", "About you")

        taskListArray.forEach {
            tabLayout.newTab().apply {
                text = it
                tabLayout.addTab(this)

            }

        }

    }

    private fun setUpTasksViewPod(myview: View) {
        mViewPodTaskData = myview.vpTaskList as TaskDataViewPod
        mViewPodTaskData.setUpTaskListViewPod(this)
    }

    override fun goToProfilePage() {
       onStart()
    }

    override fun goToCreateTaskPage() {

    }
}
