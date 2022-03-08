package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.view.View
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var tvAgeInMinites: TextView? = null
    private var tvSelectedDate: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        tvAgeInMinites = findViewById(R.id.tvSelectedDateInMinutes)
        tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)


        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View) {


        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get((Calendar.MONTH))
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dp = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "The choses year is $selectedYear, the month is $selectedMonth, the day is $selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        tvAgeInMinites?.text = differenceInMinutes.toString()
                    }

                }


            },
            year, month, day
        )

        dp.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dp.show()


    }
}