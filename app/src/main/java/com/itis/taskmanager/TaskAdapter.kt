package com.itis.taskmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.taskmanager.databinding.ItemTaskBinding

class TaskAdapter (
    private var list: List<Task>,
    private val onItemClick: (Task) -> Unit
    ) : RecyclerView.Adapter<TaskItem>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): TaskItem = TaskItem(
            binding = ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick = onItemClick
        )

        override fun onBindViewHolder(holder: TaskItem, position: Int) {
            holder.onBind(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }
}