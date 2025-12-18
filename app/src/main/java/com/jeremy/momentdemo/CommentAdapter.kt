package com.jeremy.momentdemo

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jeremy.momentdemo.databinding.MomentCommentItemBinding
import com.jeremy.momentdemo.model.Comment
import com.jeremy.momentdemo.util.CommentTextUtils

class CommentAdapter(private val comments: List<Comment>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class MomentCommentItemAdapter(val binding: MomentCommentItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = MomentCommentItemBinding.inflate(LayoutInflater.from(parent.context))
        return MomentCommentItemAdapter(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val comment = comments[position]
        if (holder is MomentCommentItemAdapter) {
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
                tvMomentComment.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

    override fun getItemCount() = comments.size
}