package com.felix.lib_tools.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.felix.lib_tools.R

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: RoundImageView
 */
class RoundImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatImageView(context, attrs, defStyleAttr) {
    var round: Float = 0f
    val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)

    init {
        context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0).let {
            round = it.getDimension(R.styleable.RoundImageView_radius, 0f)
        }
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    }


    val path = Path()
    val rectF = RectF()
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        rectF.set(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())

        path.reset()
        path.addRoundRect(rectF, round, round, Path.Direction.CW)
        path.addRect(rectF, Path.Direction.CCW)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.saveLayer(rectF, null).let {
            super.onDraw(canvas)
            canvas.drawPath(path, paint)
            canvas.restoreToCount(it)
        }
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)
}