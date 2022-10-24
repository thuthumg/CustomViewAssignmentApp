package com.padcmyanmar.ttm.customviewassignmentapp.views.components


import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.text.TextUtils

import android.util.AttributeSet

import android.view.View
import androidx.core.content.withStyledAttributes
import com.padcmyanmar.ttm.customviewassignmentapp.R


class CustomCircularProgressView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private const val DEFAULT_PROGRESS_COLOR = Color.GREEN
        private const val DEFAULT_BACKGROUND_COLOR = Color.LTGRAY
        private const val DEFAULT_STROKE_WIDTH_DIMENSION = 10f
        private const val DEFAULT_BACKGROUND_WIDTH = 10f
        private const val DEFAULT_MAX_VALUE = 100f
        private const val DEFAULT_PROGRESS_TEXT_COLOR = Color.BLACK
        private const val DEFAULT_PROGRESS_TEXT_SIZE = 18f
        private const val DEFAULT_PROGRESS = 30

    }


    private var progressBarPaint: Paint? = null
    private var bacgroundPaint: Paint? = null
    private var textPaint: Paint? = null

    private var mRadius = 0f
    private val mArcBounds = RectF()
    var drawUpto = 0f


    private var progressColor = 0
    private var progressBgColor = 0
    private var strokeWidthDimension = 0f
    private var backgroundWidth = 0f
    private var roundedCorners = false
    private var maxValue = 0f

    private var progressTextColor: Int = Color.BLACK
    private var textSize = 18f
    private var text: String? = ""
    private var suffix: String? = ""
    private var prefix: String? = ""


    init {
        context.withStyledAttributes(attrs, R.styleable.CustomCircularProgressView) {
            progressColor =
                getColor(
                    R.styleable.CustomCircularProgressView_progressColor,
                    DEFAULT_PROGRESS_COLOR
                )
            progressBgColor =
                getColor(
                    R.styleable.CustomCircularProgressView_backgroundColor,
                    DEFAULT_BACKGROUND_COLOR
                )
            strokeWidthDimension =
                getDimension(
                    R.styleable.CustomCircularProgressView_strokeWidthDimension,
                    DEFAULT_STROKE_WIDTH_DIMENSION
                )

            backgroundWidth = getFloat(
                R.styleable.CustomCircularProgressView_backgroundWidth,
                DEFAULT_BACKGROUND_WIDTH
            )
            roundedCorners =
                getBoolean(R.styleable.CustomCircularProgressView_roundedCorners, false)
            maxValue = getFloat(R.styleable.CustomCircularProgressView_maxValue, DEFAULT_MAX_VALUE)
            progressTextColor =
                getColor(
                    R.styleable.CustomCircularProgressView_progressTextColor,
                    DEFAULT_PROGRESS_TEXT_COLOR
                )

            textSize = getDimension(
                R.styleable.CustomCircularProgressView_textSize,
                DEFAULT_PROGRESS_TEXT_SIZE
            )
            suffix = getString(R.styleable.CustomCircularProgressView_suffix)
            prefix = getString(R.styleable.CustomCircularProgressView_prefix)
            text = getString(R.styleable.CustomCircularProgressView_progressText)

            drawUpto =
                getInt(R.styleable.CustomCircularProgressView_progress, DEFAULT_PROGRESS).toFloat()


        }
        setUpProgressBarPaint()
        setUpProgressBarBackgroundPaint()
        setUpProgressBarTextPaint()
    }

    private fun setUpProgressBarPaint() {
        progressBarPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        progressBarPaint?.style = Paint.Style.FILL
        progressBarPaint?.color = progressColor
        progressBarPaint?.style = Paint.Style.STROKE
        progressBarPaint?.strokeWidth = strokeWidthDimension * resources.displayMetrics.density
        if (roundedCorners) {
            progressBarPaint?.strokeCap = Paint.Cap.ROUND
        } else {
            progressBarPaint?.strokeCap = Paint.Cap.BUTT
        }
//        val pc = String.format("#%06X", 0xFFFFFF and progressColor)
//        progressBarPaint?.color = Color.parseColor(pc)
    }

    private fun setUpProgressBarBackgroundPaint() {
        bacgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        bacgroundPaint?.style = Paint.Style.FILL
        bacgroundPaint?.color = progressBgColor
        bacgroundPaint?.style = Paint.Style.STROKE
        bacgroundPaint?.strokeWidth = backgroundWidth * resources.displayMetrics.density
        bacgroundPaint?.strokeCap = Paint.Cap.SQUARE
//        val bc = String.format("#%06X", 0xFFFFFF and backgroundColor)
//        bacgroundPaint?.color = Color.parseColor(bc)
    }

    private fun setUpProgressBarTextPaint() {

        textPaint = TextPaint()
        textPaint?.color = progressTextColor
//        val c = String.format("#%06X", 0xFFFFFF and progressTextColor)
//        textPaint?.color = Color.parseColor(c)
        textPaint?.textSize = textSize
        textPaint?.typeface = Typeface.DEFAULT_BOLD
        textPaint?.isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mRadius = w.coerceAtMost(h) / 2f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        val size = w.coerceAtMost(h)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val progressRadius = mRadius / 3
        mArcBounds.set(
            progressRadius,
            progressRadius,
            mRadius * 2 - progressRadius,
            mRadius * 2 - progressRadius
        )

        //progress bar background
        canvas?.drawArc(mArcBounds, 0f, 360f, false, bacgroundPaint!!)

        //progress bar
        // canvas?.drawArc(mArcBounds, 270f, drawUpto / getMaxValue() * 360, false, progressBarPaint!!)
        canvas?.drawArc(mArcBounds, 270f, drawUpto / maxValue * 360, false, progressBarPaint!!)


        if (TextUtils.isEmpty(suffix)) {
            suffix = ""
        }
        if (TextUtils.isEmpty(prefix)) {
            prefix = ""
        }
        val drawnText = prefix + text + suffix
        if (!TextUtils.isEmpty(text)) {
            val textHeight: Float? = textPaint?.descent()?.plus(textPaint?.ascent()!!)

            //progress bar text
            canvas?.drawText(
                drawnText,
                (width - textPaint?.measureText(drawnText)!!) / 2.0f,
                (width - textHeight!!) / 2.0f,
                textPaint!!
            )
        }
    }

}