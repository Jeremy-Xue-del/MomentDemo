package com.jeremy.momentdemo.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class NestedChildRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private var startY = 0f

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        when (e.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                startY = e.y
                // 先让自己接管事件
                parent.requestDisallowInterceptTouchEvent(true)
            }

            MotionEvent.ACTION_MOVE -> {
                val dy = e.y - startY

                val canScrollUp = canScrollVertically(-1)
                val canScrollDown = canScrollVertically(1)

                val scrollingUp = dy > 0
                val scrollingDown = dy < 0

                val shouldChildHandle =
                    (scrollingUp && canScrollUp) ||
                            (scrollingDown && canScrollDown)

                // ⭐ 关键：只有子不能滚时，才交给父
                parent.requestDisallowInterceptTouchEvent(shouldChildHandle)
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.onInterceptTouchEvent(e)
    }
}
