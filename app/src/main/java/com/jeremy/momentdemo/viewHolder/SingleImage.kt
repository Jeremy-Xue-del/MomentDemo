package com.jeremy.momentdemo.viewHolder

import androidx.appcompat.widget.AppCompatImageView
import com.jeremy.momentdemo.R

class SingleImage(itemView: AppCompatImageView) {
    private val image: AppCompatImageView = itemView.findViewById(R.id.singleImage)
    fun bind(picture: Int) {
        image.setImageResource(picture)
    }
}