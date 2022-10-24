package com.padcmyanmar.ttm.customviewassignmentapp.views.components

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OverlapDecoration : RecyclerView.ItemDecoration() {

    val vertOverlap = -30

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        outRect.set(0, 0, vertOverlap, 0);
    }

}