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
    )