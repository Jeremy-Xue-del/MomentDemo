package com.jeremy.momentdemo

import android.view.View
import android.content.Context
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(
    private val horizontalSpacingDp: Int,
    private val verticalSpacingDp: Int,
    private val spanCount: Int,
): RecyclerView.ItemDecoration(){
    // dp转px工具方法
    private fun dp2px(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density + 0.5f).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // 当前Item的位置
        val position = parent.getChildAdapterPosition(view)
        // 没有找到
        if(position == RecyclerView.NO_POSITION) return

        // 当前Item所在列(从0开始)
        val column = position % spanCount
        // 列间距
        val horizontalSpacing = dp2px(parent.context, horizontalSpacingDp)
        // 垂直间距
        val verticalSpacing = dp2px(parent.context, verticalSpacingDp)

        // 左侧间距 = 当前列占的横向间距
        outRect.left = column * horizontalSpacing / spanCount
        // 右侧间距 = 横向总间距 - (当前列+1) 占的横向间距
        outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount
        // 仅非第一行的Item顶部加纵向间距（行间距）
        if (position >= spanCount) { // 不是第一行的Item
            outRect.top = verticalSpacing
        }
        // 所有Item底部可选加间距（如果需要行底间距，保留；否则注释）
        outRect.bottom = verticalSpacing
    }
}