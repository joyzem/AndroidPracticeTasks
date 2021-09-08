package com.example.practicetask1.view.adapters

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.example.practicetask1.R

class ItemDivider(
    private val divider: Drawable,
    private val orientation: Int,
    private val context: Context
) :
    RecyclerView.ItemDecoration() {

    private val rect = Rect()

    init {
        if (orientation != VERTICAL && orientation != HORIZONTAL) {
            throw IllegalStateException("Invalid orientation")
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (orientation == HORIZONTAL) {
            drawHorizontal(canvas, parent)
        } else if (orientation == VERTICAL) {
            drawVertical(canvas, parent)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = context.resources.getDimension(R.dimen.search_results_start_padding).toInt()
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, rect)
            val bottom: Int = rect.bottom + Math.round(child.translationY)
            val top: Int = bottom - divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(
                parent.paddingLeft, top,
                parent.width - parent.paddingRight, bottom
            )
        } else {
            top = 0
            bottom = parent.height
        }

        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, rect)
            val right: Int = rect.right + Math.round(child.translationX)
            val left: Int = right - divider.intrinsicWidth
            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }
        canvas.restore()
    }

    companion object {
        const val VERTICAL = 0
        const val HORIZONTAL = 1
    }
}
