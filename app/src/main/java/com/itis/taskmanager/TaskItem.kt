package com.itis.taskmanager

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.itis.taskmanager.databinding.ItemTaskBinding

class TaskItem(
    private val binding: ItemTaskBinding,
    private val onItemClick: (Task) -> Unit
) : ViewHolder(binding.root) {

    private val context: Context
        get() = itemView.context
    fun onBind(task: Task) {
        binding.run {
            tvName.text = task.name
            tvShortDesc.text = task.short_desc

            root.setOnClickListener {
                onItemClick(task)
            }
        }
    }
}