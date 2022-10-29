package com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters

import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.MainView
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.TaskScreenView

interface TaskScreenPresenter:IBasePresenter, ProfileImageDelegate {
    fun initView(view: TaskScreenView)
    fun goToMainScreen()

}