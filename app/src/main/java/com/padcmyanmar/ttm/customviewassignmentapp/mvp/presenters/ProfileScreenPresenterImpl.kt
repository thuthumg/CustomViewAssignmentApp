package com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.ProfileScreenView

class ProfileScreenPresenterImpl : ViewModel(),ProfileScreenPresenter {

   //View
    var mView: ProfileScreenView? = null
    override fun initView(view: ProfileScreenView) {
        mView = view
    }

    override fun closeProfilePage() {
        mView?.closeProfileScreen()
    }

    override fun onUiReady() {

    }

    override fun goToProfilePage() {
       mView?.navigateToProfileScreen()
    }

    override fun goToCreateTaskPage() {

    }
}