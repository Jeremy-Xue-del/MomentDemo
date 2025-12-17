package com.jeremy.momentdemo.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.jeremy.momentdemo.R
import com.jeremy.momentdemo.databinding.SingleImageBinding

class SingleImageHolder(val binding: SingleImageBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(picture: Int) {
        binding.ivSingleImage.setImageResource(R.mipmap.ic_launcher)
    }

}