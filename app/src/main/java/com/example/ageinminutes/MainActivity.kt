package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener{
            view->clickDatePicker(view)
            Toast.makeText(this,"See In Minutes", Toast.LENGTH_LONG).show()}

    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View){
        val myCalander=Calendar.getInstance()
        val year= myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DATE)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            /*Toast.makeText(this,
                "The chosen year is $selectedYear, the month is $selectedMonth, the day is $selectedDayOfMonth " + year
                , Toast.LENGTH_LONG).show()*/

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelctedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            SelectDateInMinutes.setText(differenceInMinutes.toString())

        }
            ,year
            ,month
            ,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }

}