package com.itis.taskmanager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.itis.taskmanager.databinding.FragmentCreateOrEditTaskBinding
import java.time.LocalDateTime

class CreateOrEditTaskFragment : Fragment(R.layout.fragment_create_or_edit_task) {
    private var binding: FragmentCreateOrEditTaskBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var db = DataBaseHandler(requireContext())

        binding = FragmentCreateOrEditTaskBinding.bind(view)

        binding?.run {
            if (arguments?.getBoolean("ADDING") == false) {
                val id = arguments?.getInt("ID").toString().toInt()
                val currentTask = TaskRepository.list.find { task -> task.id == id }

                val name = currentTask!!.name
                val description = currentTask.desc
                val deadline = currentTask.deadline

                val day = deadline.dayOfMonth
                val month = deadline.month
                val year = deadline.year
                val hours = deadline.hour
                val minutes = deadline.minute

                (etNameInput as TextView).text = name
                (etDescriptionInput as TextView).text = description
                (etDay as TextView).text = day.toString()
                (etMonth as TextView).text = month.value.toString()
                (etYear as TextView).text = year.toString()
                (etTime1 as TextView).text = hours.toString()
                (etTime2 as TextView).text = minutesFiller(minutes)
            }
            submitButton.setOnClickListener {
                var time1 = 0
                var time2 = 0
                if (!etTime1.text.isEmpty())
                {
                    time1 = etTime1.text.toString().toInt()
                    if (etTime1.text.toString().toInt() < 0) {
                        etTime1.setText("")
                        etTime1.setHint("!!")
                    }
                }
                if (!etTime2.text.isEmpty()) {
                    time2 = etTime2.text.toString().toInt()
                    if (etTime2.text.toString().toInt() < 0) {
                        etTime2.setText("")
                        etTime2.setHint("!!")
                    }
                }
                if (etNameInput.text == null || etNameInput.text.length == 0)
                    etNameInput.setHint("Wrong Input")
                if (etDescriptionInput.text == null || etDescriptionInput.text.length == 0)
                    etDescriptionInput.setHint("Wrong Input")
                if (etDay.text == null || etDay.text.length == 0 || etDay.text.toString().toInt() <= 0) {
                    etDay.setText("")
                    etDay.setHint("!!")
                }
                if (etMonth.text == null || etMonth.text.length == 0 || etMonth.text.toString().toInt() <= 0) {
                    etMonth.setText("")
                    etMonth.setHint("!!")
                }
                if (etYear.text == null || etYear.text.length == 0 || etYear.text.toString().toInt() <= 0) {
                    etYear.setText("")
                    etYear.setHint("!!!!")
                }

                if (inputsChecker()) {
                    var date = LocalDateTime.of(
                        etYear.text.toString().toInt(),
                        etMonth.text.toString().toInt(),
                        etDay.text.toString().toInt(),
                        time1,
                        time2
                    )

                    if (date < LocalDateTime.now())
                        Snackbar.make(binding!!.root,"Date is earlier than now" , Snackbar.LENGTH_LONG).show()
                    else {
                        var newId = findNewIndex()
                        if (arguments?.getBoolean("ADDING") == false)
                            newId = id
                        var newTask = Task(
                            newId,
                            etNameInput.text.toString(),
                            getShortDesc(etDescriptionInput.text.toString()),
                            etDescriptionInput.text.toString(),
                            date
                        )

                        if (arguments?.getBoolean("ADDING") == false) {
                            val id = arguments?.getInt("ID").toString().toInt()
                            val currentTask = TaskRepository.list.find { task -> task.id == id }
                            if (currentTask != null)
                                db.editData(newTask)

                            for (i in 0 until TaskRepository.list.size) {
                                if (TaskRepository.list[i].id == id)
                                    TaskRepository.list[i] = newTask
                            }
                        } else {
                            db.insertData(newTask)
                            TaskRepository.list.add(newTask)
                        }
                    }
                    findNavController().navigate(R.id.action_createOrEditTaskFragment_to_taskListFragment)
                }
            }
        }
    }

    private fun getShortDesc(desc: String): String {
        if (desc.length > 20)
            return desc.substring(0, 20)
        else
            return desc
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun minutesFiller(minutes: Int): String {
        if (minutes.toString().length < 2)
            return "0" + minutes.toString()
        else
            return minutes.toString()
    }

    fun inputsChecker(): Boolean {
        binding?.run {
            if (etNameInput.text.isEmpty())
                return false
            if (etDescriptionInput.text.isEmpty())
                return false
            if (etDay.text.isEmpty() || etDay.text.toString().toInt() <= 0)
                return false
            if (etMonth.text.isEmpty() || etMonth.text.toString().toInt() <= 0)
                return false
            if (etYear.text.isEmpty() || etYear.text.toString().toInt() <= 0)
                return false
            if (etTime1.text.isEmpty()) {}
            else if (etTime1.text.toString().toInt() < 0)
                return false
            if (etTime2.text.isEmpty()) {}
            else if (etTime2.text.toString().toInt() < 0)
                return false
            return true
        }

        return false
    }

    fun findNewIndex(): Int {
        var indexes = mutableListOf<Int>()
        TaskRepository.list.forEach { task ->
                indexes.add(task.id)
        }

        var max = 0
        if (!indexes.isEmpty())
            max = indexes.max()

        return max + 1
    }
}