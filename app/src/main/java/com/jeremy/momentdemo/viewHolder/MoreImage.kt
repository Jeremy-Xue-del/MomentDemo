package com.jeremy.momentdemo.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.jeremy.momentdemo.databinding.SquareImageBinding

class SquareImageHolder(val binding: SquareImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(picture: Int) {
        binding.ivSquareImage.setImageResource(picture)
    }
}