package com.jeremy.momentdemo.model

import com.jeremy.momentdemo.R
import java.util.Date

class MomentData {
    val moments = listOf(
        MomentModel(
            null,
            "Jeremy",
            "刚刚发的动态",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 10_000)
        ),
        MomentModel(
            null,
            "Alice",
            "今天的天气真不错！",
            listOf(R.drawable.background, R.drawable.background),
            Date(System.currentTimeMillis() - 3 * 60_000)
        ),
        MomentModel(
            null,
            "Bob",
            "喝杯咖啡，继续干活 ☕️",
            listOf(R.drawable.background, R.drawable.background, R.drawable.background),
            Date(System.currentTimeMillis() - 8 * 60_000)
        ),
        MomentModel(
            null,
            "Cindy",
            "午餐好好吃！",
            listOf(
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background
            ),
            Date(System.currentTimeMillis() - 40 * 60_000)
        ),
        MomentModel(
            null,
            "David",
            "跑步 3 公里完成！",
            listOf(
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background
            ),
            Date(System.currentTimeMillis() - 2 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Eric",
            "开始学习 Android Jetpack",
            listOf(
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
            ),
            Date(System.currentTimeMillis() - 5 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Fiona",
            "写代码写到怀疑人生",
            listOf(
                R.drawable.background, R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
            ),
            Date(System.currentTimeMillis() - 10 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "George",
            "今天的云好好看 ☁️",
            listOf(
                R.drawable.background, R.drawable.background, R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
            ),
            Date(System.currentTimeMillis() - 20 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Helen",
            "昨天出去散步了",
            listOf(
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
            ),
            Date(System.currentTimeMillis() - 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Ian",
            "昨天看了一部电影，很治愈",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - (24 + 5) * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Jack",
            "前天吃了很好吃的面",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 2L * 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Kathy",
            "最近在学 Kotlin，很有意思",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 3L * 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Leo",
            "坚持每天阅读 30 分钟",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 5L * 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Mary",
            "去看了一场演唱会！",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 7L * 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Nick",
            "搬家累死了 😭",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 10L * 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Olivia",
            "开始健身打卡",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 20L * 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Peter",
            "工作顺利完成一个大任务",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 30L * 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Queen",
            "去爬山拍了很多照片",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 60L * 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Rick",
            "去年这个时候我在旅游",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 365L * 24 * 60 * 60_000)
        ),
        MomentModel(
            null,
            "Sunny",
            "两年前的回忆又浮现了",
            listOf(R.drawable.background),
            Date(System.currentTimeMillis() - 2L * 365 * 24 * 60 * 60_000)
        ),
    )
}