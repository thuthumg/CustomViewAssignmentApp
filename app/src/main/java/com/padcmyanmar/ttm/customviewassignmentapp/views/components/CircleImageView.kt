package com.padcmyanmar.ttm.customviewassignmentapp.views.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import com.padcmyanmar.ttm.customviewassignmentapp.R
import kotlin.math.min


class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    companion object{
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f
    }
    //Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var cornerRadius = 100f

    private val path = Path()

    private var borderWidth = DEFAULT_BORDER_WIDTH

    private var strokeColor = DEFAULT_BORDER_COLOR

    private var size = 0f


    init {
        context.withStyledAttributes(attrs, R.styleable.CircleImageView){
            cornerRadius = getDimension(R.styleable.CircleImageView_cornerRadius,0f)
            borderWidth = getDimension(R.styleable.CircleImageView_borderWidth, DEFAULT_BORDER_WIDTH)
            strokeColor = getColor(R.styleable.CircleImageView_strokeColor, DEFAULT_BORDER_COLOR)

        }
    }
    override fun onDraw(canvas: Canvas?) {

        val rectangle = RectF(0f,0f,width.toFloat(),height.toFloat())

        // Prepare paints beforehand to prevent allocations when drawing.
        val borderPaint = Paint()
        borderPaint.setStyle(Paint.Style.FILL)
        borderPaint.setColor(Color.WHITE)
        borderPaint.setAntiAlias(true)
        borderPaint.setDither(true)

        val fillPaint = Paint()
        fillPaint.setStyle(Paint.Style.FILL)
        fillPaint.setColor(Color.WHITE)
        fillPaint.setAntiAlias(true)
        fillPaint.setDither(true)


// Draw a rounded rectangle with a dark color that will serve as the border.
        canvas?.drawRoundRect(rectangle, cornerRadius, cornerRadius, borderPaint)

// Then draw a smaller rounded rectangle with a lighter color that will serve as the background.
        rectangle.inset(borderWidth, borderWidth)
        if (rectangle.width() > 0 && rectangle.height() > 0) {
            canvas?.drawRoundRect(rectangle, cornerRadius, cornerRadius, fillPaint)
        }

        path.addRoundRect(rectangle,cornerRadius,cornerRadius,Path.Direction.CCW)

        canvas?.clipPath(path)






        super.onDraw(canvas)
    }
}