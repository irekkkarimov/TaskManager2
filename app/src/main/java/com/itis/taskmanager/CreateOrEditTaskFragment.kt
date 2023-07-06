package com.itis.taskmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itis.taskmanager.databinding.FragmentCreateOrEditTaskBinding

class CreateOrEditTaskFragment : Fragment(R.layout.fragment_create_or_edit_task) {

    private var binding: FragmentCreateOrEditTaskBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCreateOrEditTaskBinding.bind(view)

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}