package com.itis.taskmanager

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import com.itis.taskmanager.databinding.FragmentTestBinding
import java.util.Calendar
import java.util.Date


class test : Fragment(R.layout.fragment_test) {
    private var binding: FragmentTestBinding? = null
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var dateButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestBinding.bind(view)

        dateButton = binding?.datePickerButton!!

    dateButton.setOnClickListener(View.OnClickListener() {
        fun onClick(view: View) {
            var cal = Calendar.getInstance()
            var year = cal.get(Calendar.YEAR)
            var month = cal.get(Calendar.YEAR)
            var day = cal.get(Calendar.DAY_OF_MONTH)

            var dialog = DatePickerDialog(
                requireContext().applicationContext,
                mDateSetListener,
                year, month, day
            )
            dialog.show()
        }
    })

    }


    fun makeDateString(day: Int, month: Int, year: Int): String {
        return getMonthForman(month) + " " + day + " " + year
    }

    private fun getMonthForman(month: Int): String {
        return when (month) {
            1 -> "JAN"
            2 -> "FEB"
            3 -> "MAR"
            4 -> "APR"
            5 -> "MAY"
            6 -> "JUN"
            7 -> "JUL"
            8 -> "AUG"
            9 -> "SEP"
            10 -> "OCT"
            11 -> "NOV"
            12 -> "DEC"
            else -> "JAN"
        }
    }
}