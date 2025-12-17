package com.jeremy.momentdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeremy.momentdemo.databinding.SingleImageBinding
import com.jeremy.momentdemo.databinding.SquareImageBinding
import com.jeremy.momentdemo.viewHolder.SquareImageHolder
import com.jeremy.momentdemo.viewHolder.SingleImageHolder

class ImageAdapter(private val pictures: List<Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val SINGLE_IMAGE = 1
        const val MORE_IMAGE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (pictures.size) {
            1 -> SINGLE_IMAGE
            else -> MORE_IMAGE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            SINGLE_IMAGE -> {
                val binding = SingleImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SingleImageHolder(binding)
            }

            MORE_IMAGE -> {
                val binding = SquareImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SquareImageHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val picture = pictures[position]
        when (holder) {
            is SingleImageHolder -> {
                holder.binding.apply {
                    holder.bind(picture)
                }
            }

            is SquareImageHolder -> {
                holder.binding.apply {
                    holder.bind(picture)
                }
            }

        }
    }

    override fun getItemCount() = pictures.size

}