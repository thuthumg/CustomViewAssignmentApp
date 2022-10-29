package com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.TaskScreenView

class TaskScreenPresenterImpl: ViewModel(),TaskScreenPresenter {

  //view
    var mView: TaskScreenView? = null


    override fun initView(view: TaskScreenView) {
        mView = view
    }

    override fun goToMainScreen() {
        mView?.navigateToMainScreenPage()
    }

    override fun onUiReady() {

    }

    override fun goToProfilePage() {

        mView?.navigateToProfileScreen()

    }

    override fun goToCreateTaskPage() {
        mView?.navigateToCreateTaskPage()
    }




}