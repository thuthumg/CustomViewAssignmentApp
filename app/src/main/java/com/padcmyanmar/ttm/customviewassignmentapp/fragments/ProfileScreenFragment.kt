package com.padcmyanmar.ttm.customviewassignmentapp.fragments

import android.app.DialogFragment.STYLE_NORMAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.ttm.customviewassignmentapp.R
import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters.ProfileScreenPresenter
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters.ProfileScreenPresenterImpl
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.ProfileScreenView
import com.padcmyanmar.ttm.customviewassignmentapp.views.viewpods.TaskDataViewPod
import kotlinx.android.synthetic.main.bottom_sheet_fragment_profile_screen.view.*


class ProfileScreenFragment  : BottomSheetDialogFragment() , ProfileScreenView {

    private lateinit var bottomSheet: ViewGroup
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private var tabLayout:TabLayout? = null
    private var  ivClose:AppCompatImageView? = null

    private lateinit var mViewPodTaskData: TaskDataViewPod

    //Presenter
    private lateinit var mPresenter: ProfileScreenPresenter

    override fun onStart() {
        super.onStart()
        bottomSheet =
            dialog!!.findViewById(com.google.android.material.R.id.design_bottom_sheet) as ViewGroup // notice the R root package
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        // SETUP YOUR BEHAVIOR HERE
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myview: View = inflater.inflate(R.layout.bottom_sheet_fragment_profile_screen, container, false)
        tabLayout = myview.findViewById(R.id.tabLayout)
        ivClose = myview.findViewById(R.id.ivClose)

        setUpPresenter()


        setUpTasksViewPod(myview)
        setUpTabLayout(tabLayout = tabLayout)
        setUpClickListener()


        mPresenter.onUiReady()

        return myview
    }

    private fun setUpClickListener() {
        ivClose?.setOnClickListener {
            mPresenter.closeProfilePage()
        }
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
       // setStyle(STYLE_NORMAL,R.style.MyTransparentBottomSheetDialogTheme)
    }

    private fun setUpPresenter() {
       mPresenter = ViewModelProvider(this)[ProfileScreenPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpTabLayout(tabLayout: TabLayout?) {

        val taskListArray: Array<String> = arrayOf("Project Tasks", "Contacts", "About you")

        taskListArray.forEach {
            tabLayout?.newTab()?.apply {
                text = it
                tabLayout?.addTab(this)

            }

        }

    }

    private fun setUpTasksViewPod(myview: View) {
        mViewPodTaskData = myview.vpTaskList as TaskDataViewPod
        mViewPodTaskData.setUpTaskListViewPod(mPresenter)
    }



    override fun navigateToProfileScreen() {
        onStart()
    }

    override fun closeProfileScreen() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN;

    }
}
