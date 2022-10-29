package com.padcmyanmar.ttm.customviewassignmentapp.mvp.presenters

import com.padcmyanmar.ttm.customviewassignmentapp.delegates.ProfileImageDelegate
import com.padcmyanmar.ttm.customviewassignmentapp.mvp.views.MainView

interface MainPresenter:IBasePresenter,ProfileImageDelegate  {

    fun initView(view: MainView)
}