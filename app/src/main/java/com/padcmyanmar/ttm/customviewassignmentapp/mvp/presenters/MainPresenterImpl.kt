package com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.MainView

class MainPresenterImpl : ViewModel(),MainPresenter {

    //View
    var mView: MainView? = null


    override fun initView(view: MainView) {
        mView = view
    }

    override fun onUiReady() {

    }

    override fun goToProfilePage() {
        mView?.navigateToProfileScreen()
    }

    override fun goToCreateTaskPage() {
        mView?.navigateToCreateTaskScreen()
    }
}