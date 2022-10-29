package com.padcmyanmar.ttm.customviewassignmentapp.views.components

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.withStyledAttributes
import com.padcmyanmar.ttm.customviewassignmentapp.R


class CustomLineProgressView (context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var progressPaint: Paint? = null
    private var goal = 0
    private var progress = 0

    private var indicatorHeight = 0f
    private var indicatorThickness = 0f
    private var reachedColor = 0
    private var notReachedColor = 0
    private var unfilledSectionColor = 0
    private var barThickness = 0f
    private var barAnimator: ValueAnimator? = null

    init {


        context.withStyledAttributes(attrs, R.styleable.CustomLineProgressView){
            indicatorHeight = getDimension(R.styleable.CustomLineProgressView_indicatorHeight,10f)
            indicatorThickness = getDimension(R.styleable.CustomLineProgressView_indicatorThickness,5f)
            reachedColor = getColor(R.styleable.CustomLineProgressView_reachedColor,
                Color.BLUE)
            notReachedColor = getColor(
                R.styleable.CustomLineProgressView_notReachedColor,
                Color.BLACK)
            unfilledSectionColor = getColor(R.styleable.CustomLineProgressView_unfilledSectionColor,
                Color.RED)
            barThickness = getDimension(R.styleable.CustomLineProgressView_barThickness,
                4f)
        }
    }
    override fun onDraw(canvas: Canvas) {
        val halfHeight = height / 2
        val progressEndX = (width * progress / 100f).toInt()

        // draw the filled portion of the bar
        progressPaint!!.strokeWidth = barThickness
        val color = if (progress >= goal) reachedColor else notReachedColor
        progressPaint!!.color = color
        canvas.drawLine(
            0F, halfHeight.toFloat(), progressEndX.toFloat(),
            halfHeight.toFloat(), progressPaint!!
        )

        // draw the unfilled portion of the bar
        progressPaint!!.color = unfilledSectionColor
        canvas.drawLine(
            progressEndX.toFloat(),
            halfHeight.toFloat(), width.toFloat(), halfHeight.toFloat(), progressPaint!!
        )

        // draw goal indicator
        val indicatorPosition = (width * goal / 100f).toInt()
        progressPaint!!.color = reachedColor
        progressPaint!!.strokeWidth = indicatorThickness

        canvas.drawLine(
            indicatorPosition.toFloat(),
            halfHeight - indicatorHeight / 2,
            indicatorPosition.toFloat(),
            halfHeight + indicatorHeight / 2,
            progressPaint!!
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val specHeight = MeasureSpec.getSize(heightMeasureSpec)
        val height: Int
        height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.EXACTLY -> specHeight
            MeasureSpec.AT_MOST -> Math.min(indicatorHeight, specHeight.toFloat()).toInt()
            MeasureSpec.UNSPECIFIED -> specHeight
            else -> specHeight
        }

        // must call this, otherwise the app will crash
        setMeasuredDimension(width, height)
    }

    fun setProgress(progress: Int) {
        setProgress(progress, true)
    }

    fun setProgress(progress: Int, animate: Boolean) {
        if (animate) {
            barAnimator = ValueAnimator.ofFloat(0f, 1f)
            barAnimator?.setDuration(700)

            // reset progress without animating
            setProgress(0, false)
            barAnimator?.setInterpolator(DecelerateInterpolator())
            barAnimator?.addUpdateListener(AnimatorUpdateListener { animation ->
                val interpolation = animation.animatedValue as Float
                setProgress((interpolation * progress).toInt(), false)
            })
            if (!(barAnimator?.isStarted())!!) {
                barAnimator?.start()
            }
        } else {
            this.progress = progress
            postInvalidate()
        }
    }

    fun setGoal(goal: Int) {
        this.goal = goal
        postInvalidate()
    }


}