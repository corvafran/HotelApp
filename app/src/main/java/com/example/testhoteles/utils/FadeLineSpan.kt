package com.example.testhoteles.utils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.text.Layout
import android.text.style.LeadingMarginSpan

class FadeLineSpan(val fadingLine: Int, color: Int) : LeadingMarginSpan.LeadingMarginSpan2 {

    val tops = linkedSetOf<Int>()
    val gradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, arrayOf(Color.TRANSPARENT, color).toIntArray())

    override fun getLeadingMargin(first: Boolean): Int {
        return 0
    }

    override fun drawLeadingMargin(canvas: Canvas?, p: Paint?, x: Int, dir: Int, top: Int, baseline: Int, bottom: Int, text: CharSequence?, start: Int, end: Int, first: Boolean, layout: Layout?) {
        tops.add(top)
        if (tops.size >= fadingLine && tops.elementAt(fadingLine - 1) == top) {
            canvas?.drawText(text, start, end, x.toFloat(), baseline.toFloat(), p)
            gradient.bounds = Rect(x, top, canvas?.width!!, bottom)
            gradient.draw(canvas)
            canvas?.clipRect(x, 0, canvas.width, top)
        }
    }

    override fun getLeadingMarginLineCount(): Int {
        return 0
    }

}