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
//    override fun onDraw(canvas: Canvas?) {
//
////        val rectangle = RectF(0f,0f,width.toFloat(),height.toFloat())
////
////        paint.color = strokeColor
////        paint.style = Paint.Style.STROKE
////        paint.strokeWidth = borderWidth
////
////
////
////      //  val borderWidth = 5f
////        canvas?.drawCircle(
////            rectangle.centerX(),
////            rectangle.centerY(),
////            rectangle.height() / 2 ,
////            paint
////        )
////
////
////        paint.color = Color.WHITE
////        paint.style = Paint.Style.FILL
////
////        canvas?.drawCircle(
////            rectangle.centerX(),
////            rectangle.centerY(),
////            rectangle.height() / 2  ,
////            paint
////        )
////        path.addCircle(rectangle.centerX(), rectangle.centerY(), rectangle.height() / 2 - borderWidth,
////            Path.Direction.CCW)
////        canvas?.clipPath(path)
//
//
//
//
//
////
////        path.addRoundRect(rectangle,cornerRadius,cornerRadius, Path.Direction.CCW)
////
////        canvas?.clipPath(path)
////
//
//
//
//      //  super.onDraw(canvas)
//        val rect = RectF(0f,0f,width.toFloat(),height.toFloat())
//
//        // Prepare paints beforehand to prevent allocations when drawing.
//        val borderPaint = Paint()
//        borderPaint.setStyle(Paint.Style.FILL)
//        borderPaint.setColor(Color.BLACK)
//        borderPaint.setAntiAlias(true)
//        borderPaint.setDither(true)
//
//        val fillPaint = Paint()
//        fillPaint.setStyle(Paint.Style.FILL)
//        fillPaint.setColor(Color.RED)
//        fillPaint.setAntiAlias(true)
//        fillPaint.setDither(true)
//
//
//// Draw a rounded rectangle with a dark color that will serve as the border.
////        canvas?.drawRoundRect(rect, cornerRadius, cornerRadius, borderPaint)
////
////// Then draw a smaller rounded rectangle with a lighter color that will serve as the background.
////        rect.inset(borderWidth, borderWidth)
////        if (rect.width() > 0 && rect.height() > 0) {
////            canvas?.drawRoundRect(rect, cornerRadius, cornerRadius, fillPaint)
////        }
//
//
//        val rectangle = RectF(0f,0f,width.toFloat(),height.toFloat())
//
//        path.addRoundRect(rectangle,160f,160f,Path.Direction.CCW)
//
//        canvas?.clipPath(path)
//
//    }





//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        size = (min(widthMeasureSpec, heightMeasureSpec) / 2.0).toFloat()
//
//      //  val screenWidth = MeasureSpec.getSize(widthMeasureSpec)
//      //  val screenHeight = MeasureSpec.getSize(heightMeasureSpec)
//     ///   mRect.set(0, 0, screenWidth, screenHeight)
//
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//    }

//
//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//        size = w.coerceAtMost(h) / 2f
//    }
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        val w = MeasureSpec.getSize(widthMeasureSpec)
//        val h = MeasureSpec.getSize(heightMeasureSpec)
//        val size = w.coerceAtMost(h)
//        setMeasuredDimension(size, size)
//    }

}