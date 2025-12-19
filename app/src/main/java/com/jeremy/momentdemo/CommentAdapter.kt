package com.jeremy.momentdemo

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jeremy.momentdemo.databinding.MomentCommentItemBinding
import com.jeremy.momentdemo.model.Comment
import com.jeremy.momentdemo.util.CommentTextUtils

class CommentAdapter(
    private val comments: List<Comment>
) : RecyclerView.Adapter<CommentAdapter.MomentCommentItemAdapter>() {

    class MomentCommentItemAdapter(
        val binding: MomentCommentItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MomentCommentItemAdapter {
        val binding = MomentCommentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MomentCommentItemAdapter(binding)
    }

    override fun onBindViewHolder(
        holder: MomentCommentItemAdapter,
        position: Int
    ) {
        val comment = comments[position]

        holder.binding.apply {

            val commentText = CommentTextUtils().buildCommentText(
                fromName = comment.fromName,
                toName = comment.toName,
                content = comment.comment,
                clickListener = object : CommentTextUtils.OnCommentClickListener {
                    override fun onNameClick(name: String) {
                        Toast.makeText(
                            tvMomentComment.context,
                            "点击了用户: $name",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )

            tvMomentComment.text = commentText

            // ⭐ 关键：使用「可滑动友好」的 MovementMethod
            tvMomentComment.movementMethod =
                LinkMovementMethod.getInstance()

            // ⭐ 关键：不要设置 OnTouchListener
            tvMomentComment.isClickable = true
            tvMomentComment.isFocusable = false
        }
    }

    override fun getItemCount(): Int = comments.size
}

