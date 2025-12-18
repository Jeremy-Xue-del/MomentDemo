package com.jeremy.momentdemo

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeremy.momentdemo.databinding.MomentItemBinding
import com.jeremy.momentdemo.databinding.UserHeaderBinding
import com.jeremy.momentdemo.model.MomentModel
import com.jeremy.momentdemo.util.DateTimeFormat

class MomentAdapter(private val momentList: List<MomentModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_MOMENT = 1
    }

    class HeaderViewHolder(binding: UserHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    class MomentViewHolder(val binding: MomentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = UserHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                HeaderViewHolder(binding)
            }

            TYPE_MOMENT -> {
                val binding = MomentItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MomentViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, position: Int
    ) {
        when (holder) {
            is HeaderViewHolder -> {
                // 头部视图不需要额外绑定数据
            }

            is MomentViewHolder -> {
                val momentPosition = position - 1 // 减去头部位置
                val moment = momentList[momentPosition]
                holder.binding.apply {
                    moment.name?.let {
                        tvName.text = it
                    }
                    moment.avatar?.let {
                        ivAvatar.setImageResource(it)
                    }
                    moment.content?.let {
                        tvContext.text = it
                    }
                    moment.time?.let { tvTime.text = DateTimeFormat.formatMomentTime(it) }

                    rvDetail.layoutManager =
                        GridLayoutManager(holder.itemView.context, getCount(momentPosition))
                    rvDetail.addItemDecoration(GridItemDecoration(5, 5, getCount(momentPosition)))
                    val adapter = ImageAdapter(moment.photos)
                    rvDetail.adapter = adapter

                    if (moment.comment.isNotEmpty()) {
                        rvComment.visibility = VISIBLE
                        rvComment.layoutManager = LinearLayoutManager(holder.itemView.context)
                        rvComment.adapter = CommentAdapter(moment.comment)
                    } else {
                        rvComment.visibility = GONE
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_MOMENT
    }

    override fun getItemCount() = momentList.size + 1 // 加上头部视图

    fun getCount(position: Int): Int {
        val moment = momentList[position]
        return when (moment.photos.size) {
            1 -> 1
            2, 4 -> 2
            else -> 3
        }
    }
}
