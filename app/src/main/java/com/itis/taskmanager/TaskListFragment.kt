package com.itis.taskmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.itis.taskmanager.databinding.FragmentCreateOrEditTaskBinding
import com.itis.taskmanager.databinding.FragmentTaskListBinding


class TaskListFragment : Fragment(R.layout.fragment_task_list) {

    private var binding: FragmentTaskListBinding? = null
    private var adapter: TaskAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaskListBinding.bind(view)

        initAdapter()

        binding?.run {
            addButton.setOnClickListener {
                var bundle = Bundle()
                bundle.putBoolean("ADDING", true)
                findNavController().navigate(R.id.action_taskListFragment_to_createOrEditTaskFragment, bundle)
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initAdapter() {
        adapter = TaskAdapter(
            list = TaskRepository.list,
            onItemClick = {task ->
                var bundle = Bundle()
                bundle.putInt("ID", task.id)
                findNavController().navigate(R.id.action_taskListFragment_to_taskExplorerFragment,
                bundle)
            }
        )
        binding?.rvTask?.adapter = adapter
    }
}













