package com.jeremy.momentdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jeremy.momentdemo.viewHolder.MoreImage
import com.jeremy.momentdemo.viewHolder.SingleImage

class ImageAdapter(private val pictures: List<Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemViewType(position: Int): Int {
        return when (pictures.size) {
            1 -> SINGLE_IMAGE
            else -> MORE_IMAGE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            SINGLE_IMAGE -> ViewHolder(inflater.inflate(R.layout.single_image, parent, false))
            MORE_IMAGE -> ViewHolder(inflater.inflate(R.layout.square_image, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val picture = pictures[position]
        when (holder.itemViewType) {
            SINGLE_IMAGE -> {
                val singleImage =
                    SingleImage(holder.itemView as androidx.appcompat.widget.AppCompatImageView)
                singleImage.bind(picture)
            }

            MORE_IMAGE -> {
                val mediumImage =
                    MoreImage(holder.itemView as ConstraintLayout)
                mediumImage.bind(picture)
            }

        }
    }

    override fun getItemCount() = pictures.size

    companion object {
        const val SINGLE_IMAGE = 1
        const val MORE_IMAGE = 2
    }
}