package com.padcmyanmar.ttm.customviewassignmentapp.views.components

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.withStyledAttributes
import com.padcmyanmar.ttm.customviewassignmentapp.R


class CustomLineProgressView (context: Context, attrs: AttributeSet) : View(context, attrs) {


    companion object{
        private const val DEFAULT_INDICATOR_HEIGHT = 10f
        private const val DEFAULT_INDICATOR_THICKNESS= 5f
        private const val DEFAULT_REACHED_COLOR = Color.BLUE
        private const val DEFAULT_NOT_REACHED_COLOR = Color.BLACK
        private const val DEFAULT_UNFILLED_SELECTION_COLOR = Color.RED
        private const val DEFAULT_BAR_THICKNESS = 4f
      //  private const val DEFAULT_PROGRESS = 100
      //  private const val DEFAULT_GOAL = 70
    }
    private var progressPaint: Paint = Paint()

    private var goal = 0
    private var progress = 0

    private var indicatorHeight = 0f
    private var indicatorThickness = 0f
    private var reachedColor = 0
    private var notReachedColor = 0
    private var unfilledSectionColor = 0
    private var barThickness = 0f
    private var barAnimator: ValueAnimator? = null
    private var progressBarType: ProgressBarType? = null
    private var indicatorType: IndicatorType? = null

    init {

        progressPaint!!.style = Paint.Style.FILL_AND_STROKE
        context.withStyledAttributes(attrs, R.styleable.CustomLineProgressView){
            indicatorHeight = getDimension(R.styleable.CustomLineProgressView_indicatorHeight,DEFAULT_INDICATOR_HEIGHT)
            indicatorThickness = getDimension(R.styleable.CustomLineProgressView_indicatorThickness,DEFAULT_INDICATOR_THICKNESS)
            reachedColor = getColor(R.styleable.CustomLineProgressView_reachedColor,
                DEFAULT_REACHED_COLOR)
            notReachedColor = getColor(
                R.styleable.CustomLineProgressView_notReachedColor,
                DEFAULT_NOT_REACHED_COLOR)
            unfilledSectionColor = getColor(R.styleable.CustomLineProgressView_unfilledSectionColor,
                DEFAULT_UNFILLED_SELECTION_COLOR)
            barThickness = getDimension(R.styleable.CustomLineProgressView_barThickness,
                DEFAULT_BAR_THICKNESS)
            val progressBarTypeIndex = getInt(R.styleable.CustomLineProgressView_progressBarType,0)
            setProgressBarType(ProgressBarType.values()[progressBarTypeIndex])

            val indicatorTypeIndex = getInt(R.styleable.CustomLineProgressView_indicatorType,0)
            setIndicatorType(IndicatorType.values()[indicatorTypeIndex])

        }
    }

    enum class ProgressBarType {
        Simple, Goal
    }

    enum class IndicatorType {
        Line, Circle, Square
    }

//    override fun onSaveInstanceState(): Parcelable? {
//        val bundle = Bundle()
//
//        // save our added state - progress and goal
//        bundle.putInt("progress", progress)
//        bundle.putInt("goal", goal)
//
//        // save super state
//        bundle.putParcelable("superState", super.onSaveInstanceState())
//        return bundle
//    }
//
//    override fun onRestoreInstanceState(state: Parcelable?) {
//        var state = state
//        if (state is Bundle) {
//            val bundle = state
//
//            // restore our added state - progress and goal
//            setProgress(bundle.getInt("progress"))
//            setGoal(bundle.getInt("goal"))
//
//            // restore super state
//            state = bundle.getParcelable("superState")
//        }
//        super.onRestoreInstanceState(state)
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        when(progressBarType)
        {
            ProgressBarType.Simple -> simpleProgressBar(canvas)
            ProgressBarType.Goal -> goalProgressBar(canvas)
            else -> {simpleProgressBar(canvas)}
        }



    }
    private fun simpleProgressBar(canvas: Canvas){
        val halfHeight = height / 2
        val progressEndX = (width * progress / 100f)

        // draw the filled portion of the bar
        progressPaint.strokeWidth = barThickness
        progressPaint.color = reachedColor

        canvas.drawLine(
            0f,
            halfHeight.toFloat(),
            progressEndX,
            halfHeight.toFloat(),
            progressPaint
        )

        // draw the unfilled portion of the bar
        progressPaint.color = unfilledSectionColor
        canvas.drawLine(
            progressEndX.toFloat(),
            halfHeight.toFloat(),
            width.toFloat(),
            halfHeight.toFloat(),
            progressPaint
        )

    }
    private fun goalProgressBar(canvas: Canvas){
        val halfHeight = height / 2
        val progressEndX = (width * progress / 100f)

        // draw the filled portion of the bar
        progressPaint.strokeWidth = barThickness
        val color: Int = if (progress >= goal) reachedColor else notReachedColor
        progressPaint.color = color

        canvas.drawLine(
            0f,
            halfHeight.toFloat(),
            progressEndX,
            halfHeight.toFloat(),
            progressPaint
        )

        // draw the unfilled portion of the bar
        progressPaint.color = unfilledSectionColor
        Log.d("customline","check x,y data part2 = ${progressEndX.toFloat()} ,${halfHeight.toFloat()},${width.toFloat()},${ halfHeight.toFloat()}")

        canvas.drawLine(
            progressEndX.toFloat(),
            halfHeight.toFloat(),
            width.toFloat(),
            halfHeight.toFloat(),
            progressPaint
        )

        // draw goal indicator
        val indicatorPosition = (width * goal / 100f)
        progressPaint.color = reachedColor
        progressPaint.strokeWidth = indicatorThickness
        when (indicatorType) {
            IndicatorType.Line -> canvas.drawLine(
                indicatorPosition,
                halfHeight - indicatorHeight / 2,
                indicatorPosition,
                halfHeight + indicatorHeight / 2,
                progressPaint
            )
            IndicatorType.Square -> canvas.drawRect(
                indicatorPosition - indicatorHeight / 2,
                0f,
                indicatorPosition + indicatorHeight / 2,
                indicatorHeight,
                progressPaint
            )
            IndicatorType.Circle -> canvas.drawCircle(
                indicatorPosition, halfHeight.toFloat(),
                halfHeight.toFloat(), progressPaint
            )
            else -> {
                canvas.drawLine(
                    indicatorPosition,
                    halfHeight - indicatorHeight / 2,
                    indicatorPosition,
                    halfHeight + indicatorHeight / 2,
                    progressPaint
                )
            }
        }
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var specHeight = MeasureSpec.getSize(heightMeasureSpec)

      var  height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.EXACTLY -> specHeight
            MeasureSpec.AT_MOST -> indicatorHeight.coerceAtMost(specHeight.toFloat()).toInt()
            MeasureSpec.UNSPECIFIED -> specHeight
            else -> specHeight
        }

        // must call this, otherwise the app will crash
        setMeasuredDimension(width, height)
    }

    fun setProgress(progress: Int) {
        setProgress(progress, true)
    }

    private fun setProgress(progress: Int, animate: Boolean) {
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
            if (!barAnimator?.isStarted()!!) {
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

    fun setProgressBarType(progressBarType: ProgressBarType) {
        this.progressBarType = progressBarType
        postInvalidate()
    }
    fun setIndicatorType(indicatorType: IndicatorType) {
        this.indicatorType = indicatorType
        postInvalidate()
    }
}