package com.itis.taskmanager

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itis.taskmanager.databinding.FragmentCreateOrEditTaskBinding
import com.itis.taskmanager.databinding.FragmentTaskExplorerBinding
import java.time.LocalDateTime

class TaskExplorerFragment : Fragment(R.layout.fragment_task_explorer) {
    private var binding: FragmentTaskExplorerBinding? = null
    private var id: Int = 0
    private lateinit var name: String
    private lateinit var description: String
    private lateinit var deadline: LocalDateTime
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTaskExplorerBinding.bind(view)

        binding?.run {
            if (arguments?.getBoolean("ADDING") == false) {
                id = arguments?.getInt("ID").toString().toInt()
                val taskList = TaskRepository.list
                val currentTask = taskList.find { task -> task.id == id }

                name = currentTask!!.name
                description = currentTask.desc
                deadline = currentTask.deadline

                tvName.text = name
                tvDescription.text = description
                tvDeadline.text = deadline.toLocalDate().toString() + "  " + deadline.toLocalTime().toString()
            }

            editButton.setOnClickListener {
                var bundle = Bundle()
                bundle.putBoolean("ADDING", false)
                bundle.putInt("ID", id)

                findNavController().navigate(R.id.action_taskExplorerFragment_to_createOrEditTaskFragment,
                bundle)
            }

            deleteButton.setOnClickListener {
                findNavController().navigate(R.id.action_taskExplorerFragment_to_taskListFragment)
                TaskRepository.list.remove(TaskRepository.list.find { it.id == id })
                var db = DataBaseHandler(requireContext())
                db.deleteData(id)
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}