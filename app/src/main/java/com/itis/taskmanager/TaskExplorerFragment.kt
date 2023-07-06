package com.itis.taskmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itis.taskmanager.databinding.FragmentCreateOrEditTaskBinding
import com.itis.taskmanager.databinding.FragmentTaskExplorerBinding

class TaskExplorerFragment : Fragment(R.layout.fragment_task_explorer) {
    private var binding: FragmentTaskExplorerBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTaskExplorerBinding.bind(view)

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}