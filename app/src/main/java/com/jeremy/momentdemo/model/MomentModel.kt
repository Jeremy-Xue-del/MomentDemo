package com.jeremy.momentdemo.model

import java.util.Date

data class MomentModel(
    /// 头像
    val avatar: Int?,

    /// 名字
    val name: String?,

    /// 内容
    val content: String?,

    /// 图片
    val photos: List<Int>,

    /// 时间
    val time: Date?,

    val comments: List<Comment> = emptyList()
)

data class Comment(
    /// 评论者名字
    val fromName: String,

    /// 评论内容
    val comment: String,

    /// 被回复人名字
    val toName: String? = null,
)