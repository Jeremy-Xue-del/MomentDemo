package com.jeremy.momentdemo.util

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class ImmersiveUtils {
    /**
     * 初始化沉浸式（边到边）模式
     * @param activity 目标Activity
     * @param topView 需要规避顶部状态栏的View（比如顶部导航栏，可为null）
     * @param bottomView 需要规避底部导航栏的View（比如底部按钮/列表，可为null）
     * @param isLightStatusBar 是否设置状态栏文字为深色（true=深色，false=白色，默认true）
     * @param adaptNavigationBar 是否适配底部导航栏（true=给bottomView加padding，false=仅适配状态栏，默认true）
     */
    @SuppressLint("ObsoleteSdkInt")
    fun setupImmersive(
        activity: Activity,
        topView: View? = null,
        bottomView: View? = null,
        isLightStatusBar: Boolean = true,
        adaptNavigationBar: Boolean = true
    ) {
        // 开启边到边模式（兼容所有版本）
        enableEdgeToEdgeCompat(activity)

        // 设置状态栏文字颜色（API 23+）
        setStatusBarTextColor(activity, isLightStatusBar)

        // 刘海屏/挖孔屏适配（API 28+）
        adaptDisplayCutout(activity)

        // 给View设置padding，规避状态栏/导航栏
        adaptViewToSystemBars(topView, bottomView, adaptNavigationBar)
    }

    /**
     * 兼容版enableEdgeToEdge（向下适配到API 21+）
     * 替代原生API 33+的enableEdgeToEdge，避免版本兼容问题
     */
    private fun enableEdgeToEdgeCompat(activity: Activity) {
        val window = activity.window
        window.apply {
            // 状态栏设为透明色
            statusBarColor = android.graphics.Color.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                navigationBarColor = android.graphics.Color.TRANSPARENT
            }
            // 布局延伸到状态栏区域
            decorView.systemUiVisibility =
                decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                } else {
                    0
                }
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }

    /**
     * 设置状态栏文字颜色（API 23+）
     */
    private fun setStatusBarTextColor(activity: Activity, isLight: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val controller =
                WindowInsetsControllerCompat(activity.window, activity.window.decorView)
            controller.isAppearanceLightStatusBars = isLight
        }
    }

    /**
     * 刘海屏/挖孔屏适配（API 28+）
     */
    private fun adaptDisplayCutout(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // 检索与该面板关联的当前窗口属性
            val layoutParams = activity.window.attributes
            // 允许布局延伸到刘海/挖孔区域
            layoutParams.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            activity.window.attributes = layoutParams
        }
    }

    private fun adaptViewToSystemBars(
        topView: View?,
        bottomView: View?,
        adaptNavigationBar: Boolean
    ) {
        // 处理顶部View：规避状态栏
        topView?.let { view ->
            ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
                val statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
                // 叠加原有padding，避免覆盖
                v.setPadding(
                    v.paddingLeft,
                    statusBarInsets.top + v.paddingTop,
                    v.paddingRight,
                    v.paddingBottom
                )
                // 返回insets，让子View也能处理
                insets
            }
            // 触发insets重新计算
            ViewCompat.requestApplyInsets(view)
        }

        // 处理底部View：规避导航栏（如果需要）
        if (adaptNavigationBar) {
            bottomView?.let { view ->
                ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
                    val navBarInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
                    // 叠加原有padding
                    v.setPadding(
                        v.paddingLeft,
                        v.paddingTop,
                        v.paddingRight,
                        navBarInsets.bottom + v.paddingBottom
                    )
                    insets
                }
                ViewCompat.requestApplyInsets(view)
            }
        }
    }

    /**
     * 移除View的Insets监听（页面销毁时调用，避免内存泄漏）
     */
    fun removeInsetsListener(vararg views: View?) {
        views.forEach { view ->
            view?.let {
                ViewCompat.setOnApplyWindowInsetsListener(it, null)
            }
        }
    }
}