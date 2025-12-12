package com.jeremy.momentdemo.viewHolder

import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.jeremy.momentdemo.R

class MoreImage(itemView: ConstraintLayout) {
    private val image: AppCompatImageView = itemView.findViewById(R.id.squareImage)
    fun bind(picture: Int) {
        image.setImageResource(picture)
    }
}