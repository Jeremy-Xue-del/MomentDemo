package com.jeremy.momentdemo.util

import java.util.Calendar
import java.util.Date

class DateTimeFormat {
    companion object {
        fun formatMomentTime(date: Date): String {
            val now = Date()
            val diff = now.time - date.time

            val minute = 60 * 1000
            val hour = 60 * minute
            val day = 24 * hour

            return when {
                diff < minute -> {
                    "刚刚"
                }

                diff < hour -> {
                    val m = diff / minute
                    "${m}分钟前"
                }

                diff < day -> {
                    val h = diff / hour
                    "${h}小时前"
                }
                // 昨天
                isYesterday(date) -> {
                    "昨天"
                }
                // 前天
                isTheDayBeforeYesterday(date) -> {
                    "前天"
                }
                // 三天以上且不到一年
                diff < 365L * day -> {
                    val d = diff / day
                    "${d}天前"
                }

                else -> {
                    val years = diff / (365L * day)
                    "${years}年前"
                }
            }
        }

        fun isYesterday(date: Date): Boolean {
            val cal = Calendar.getInstance()
            cal.time = Date()
            cal.add(Calendar.DAY_OF_YEAR, -1)

            val target = Calendar.getInstance()
            target.time = date

            return cal.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                    && cal.get(Calendar.DAY_OF_YEAR) == target.get(Calendar.DAY_OF_YEAR)
        }

        fun isTheDayBeforeYesterday(date: Date): Boolean {
            val cal = Calendar.getInstance()
            cal.time = Date()
            cal.add(Calendar.DAY_OF_YEAR, -2)

            val target = Calendar.getInstance()
            target.time = date

            return cal.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                    && cal.get(Calendar.DAY_OF_YEAR) == target.get(Calendar.DAY_OF_YEAR)
        }
    }


}