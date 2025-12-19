package com.jeremy.momentdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremy.momentdemo.databinding.ActivityMainBinding
import com.jeremy.momentdemo.model.MomentData
import com.jeremy.momentdemo.util.ImmersiveUtils

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ImmersiveUtils().setupImmersive(
            this,
            topView = binding.clAppBar
        )
        getMoment()
    }


    private fun getMoment() {
        val moments = MomentData().moments
        val momentLayout = binding.rvMoments
        momentLayout.layoutManager = LinearLayoutManager(this)
        momentLayout.setItemViewCacheSize(10)
        // 启用嵌套滚动
        momentLayout.isNestedScrollingEnabled = true
        // 添加优化设置
        momentLayout.setHasFixedSize(false)
        momentLayout.isFocusable = false

        // 设置装饰器
        momentLayout.addItemDecoration(MomentDecoration())
        val adapter = MomentAdapter(moments)
        momentLayout.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersiveUtils().removeInsetsListener(binding.clAppBar)
    }
}