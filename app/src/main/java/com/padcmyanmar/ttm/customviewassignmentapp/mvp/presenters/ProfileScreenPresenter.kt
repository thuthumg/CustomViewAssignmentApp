package com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters

import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.ProfileScreenView

interface ProfileScreenPresenter:IBasePresenter, ProfileImageDelegate {

    fun initView(view:ProfileScreenView)
    fun closeProfilePage()
}