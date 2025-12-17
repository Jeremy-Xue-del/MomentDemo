package com.jeremy.momentdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremy.momentdemo.databinding.ActivityMainBinding
import com.jeremy.momentdemo.model.MomentData

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMoment()
    }


    private fun getMoment() {
        val moments = MomentData().moments
        val momentLayout = binding.rvMoments
        momentLayout.layoutManager = LinearLayoutManager(this)
        momentLayout.setItemViewCacheSize(20)
        val adapter = MomentAdapter(moments)
        momentLayout.adapter = adapter
    }
}