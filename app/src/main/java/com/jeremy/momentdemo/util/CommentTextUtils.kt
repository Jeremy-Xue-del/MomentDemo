package com.jeremy.momentdemo.util

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View

class CommentTextUtils {
    // 默认颜色配置
    private val DEFAULT_REPLY_NAME_COLOR = 0xFF4A86E8.toInt()
    private val DEFAULT_CONTENT_COLOR = 0xFF1E1E1E.toInt()

    /**
     * 构建评论文字
     * @param fromName 回复人（比如“张三”）
     * @param toName 被回复人（比如“李四”，可为null，null则显示“张三: 内容”）
     * @param content 评论内容
     * @param nameColor 名字颜色（默认蓝色）
     * @param contentColor 内容颜色（默认黑色）
     */
    fun buildCommentText(
        fromName: String,
        toName: String?,
        content: String,
        clickListener: OnCommentClickListener,
        nameColor: Int = DEFAULT_REPLY_NAME_COLOR,
        contentColor: Int = DEFAULT_CONTENT_COLOR
    ): SpannableString {
        // 拼接基础字符串
        val baseText = if (toName.isNullOrEmpty()) {
            "$fromName: $content"
        } else {
            "${fromName}回复$toName: $content"
        }

        // 创建SpannableString，设置名字颜色
        val spannable = SpannableString(baseText)

        // 修改评论人字色
        val fromNameEnd = fromName.length
        spannable.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    clickListener.onNameClick(fromName)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = nameColor
                    ds.isUnderlineText = false
                }
            },
            0,
            fromNameEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        if (!toName.isNullOrEmpty()) {
            val toNameStart = fromNameEnd + 2
            val toNameEnd = toNameStart + toName.length
            spannable.setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        clickListener.onNameClick(toName)
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.color = nameColor
                        ds.isUnderlineText = false
                    }
                },
                toNameStart,
                toNameEnd,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        // 步骤4：给内容设置默认颜色（可选，避免继承名字颜色）
        val contentStart = baseText.indexOf(": ") + 2 // 找到“: ”后的起始位置
        spannable.setSpan(
            ForegroundColorSpan(contentColor),
            contentStart,
            spannable.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }

    // 定义点击监听器接口
    interface OnCommentClickListener {
        fun onNameClick(name: String)
    }
}