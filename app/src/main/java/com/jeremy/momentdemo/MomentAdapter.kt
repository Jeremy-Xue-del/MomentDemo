package com.jeremy.momentdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeremy.momentdemo.databinding.MomentItemBinding
import com.jeremy.momentdemo.model.MomentModel
import com.jeremy.momentdemo.util.DateTimeFormat

class MomentAdapter(private val momentList: List<MomentModel>) :
    RecyclerView.Adapter<MomentAdapter.ViewHolder>() {
    class ViewHolder(val binding: MomentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = MomentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val moment = momentList[position]
        holder.binding.apply {
            moment.name?.let {
                name.text = it
            }
            moment.avatar?.let {
                avatar.setImageResource(it)
            }
            moment.content?.let {
                context.text = it
            }

            detail.layoutManager =
                GridLayoutManager(holder.itemView.context, getCount(position))
            if (getCount(position) > 1) {
                detail.addItemDecoration(GridItemDecoration(5, 5, getCount(position)))
            } else {
                detail.removeItemDecoration(GridItemDecoration(5, 5, getCount(position)))
            }
            val adapter = ImageAdapter(moment.photos)
            detail.adapter = adapter
            moment.time?.let { time.text = DateTimeFormat.formatMomentTime(it) }
        }
    }

    override fun getItemCount() = momentList.size

    fun getCount(position: Int): Int {
        val moment = momentList[position]
        return when (moment.photos.size) {
            1 -> 1
            2, 4 -> 2
            else -> 3
        }
    }


}