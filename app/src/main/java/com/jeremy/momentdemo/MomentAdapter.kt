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
import com.jeremy.momentdemo.model.Comment
import com.jeremy.momentdemo.model.MomentModel
import com.jeremy.momentdemo.util.DateTimeFormat

class MomentAdapter(
    private val momentList: List<MomentModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_MOMENT = 1
    }

    /* ---------------- ViewHolder ---------------- */

    class HeaderViewHolder(binding: UserHeaderBinding) :
        RecyclerView.ViewHolder(binding.root)

    class MomentViewHolder(
        val binding: MomentItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.rvComment.isNestedScrollingEnabled = false
            val rv = binding.rvDetail
            rv.isNestedScrollingEnabled = false
            rv.addItemDecoration(GridItemDecoration())
        }
    }

    /* ---------------- Adapter ---------------- */

    override fun getItemCount(): Int = momentList.size + 1

    override fun getItemViewType(position: Int): Int =
        if (position == 0) TYPE_HEADER else TYPE_MOMENT

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = UserHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(binding)
            }

            TYPE_MOMENT -> {
                val binding = MomentItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MomentViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is HeaderViewHolder -> {
                // header 无逻辑
            }

            is MomentViewHolder -> {
                val moment = momentList[position - 1]
                bindMoment(holder, moment)
            }
        }
    }

    /* ---------------- Bind ---------------- */

    private fun bindMoment(
        holder: MomentViewHolder,
        moment: MomentModel
    ) {
        holder.binding.apply {

            tvName.text = moment.name
            tvContext.text = moment.content
            moment.time?.let { tvTime.text = DateTimeFormat.formatMomentTime(it) }

            moment.avatar?.let {
                ivAvatar.setImageResource(it)
            }

            bindImages(rvDetail, moment)
            bindComments(rvComment, moment.comments)
        }
    }

    /* ---------------- Images ---------------- */

    private fun bindImages(
        rvDetail: RecyclerView,
        moment: MomentModel
    ) {
        val count = when (moment.photos.size) {
            1 -> 1
            2, 4 -> 2
            else -> 3
        }

        rvDetail.layoutManager = GridLayoutManager(rvDetail.context, count)

        rvDetail.adapter = ImageAdapter(moment.photos)
    }

    /* ---------------- Comments ---------------- */

    private fun bindComments(
        rvComment: RecyclerView,
        comments: List<Comment>
    ) {
        if (comments.isEmpty()) {
            rvComment.visibility = GONE
            return
        }

        rvComment.visibility = VISIBLE

        rvComment.layoutManager =
            LinearLayoutManager(rvComment.context)

        rvComment.adapter = CommentAdapter(comments)

        rvComment.setHasFixedSize(false)

        // 限制高度 = 前 2 条评论高度
        if (comments.size > 2) {
            rvComment.post {
                val first = rvComment.findViewHolderForAdapterPosition(0)?.itemView
                val second = rvComment.findViewHolderForAdapterPosition(1)?.itemView

                if (first != null && second != null) {
                    val padding = rvComment.paddingTop + rvComment.paddingBottom
                    rvComment.layoutParams = rvComment.layoutParams.apply {
                        height = first.measuredHeight + second.measuredHeight + padding
                    }
                }
            }
        } else {
            rvComment.layoutParams = rvComment.layoutParams.apply {
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
    }
}
