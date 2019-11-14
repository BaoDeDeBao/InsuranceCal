package com.example.insurancecal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = spinnerAge.getItemAtPosition(position) // the way to show array index - selectedItemPosition
        Toast.makeText(this, "Selected Item = " + selectedItem, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling item selected listener for spinner
        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener {
            calculatePremium()
        }
    }

    private fun calculatePremium() {
        //Get the age group
        val age : Int = spinnerAge.selectedItemPosition
        var premium : Int

        if(age == 0) {
            premium = 60
        }
        else if(age == 1) {
            premium = 70
        }
        else if(age == 2) {
            premium = 90
        }
        else if(age == 3) {
            premium = 120
        }
        else {
            premium = 150
        }


        //Get the gender selection.ID of radio button
        val gender : Int = radioGroupGender.checkedRadioButtonId
        if(gender == R.id.radioButtonMale) {
            if(age == 1) {
                premium += 50
            }
            else if(age == 2) {
                premium += 100
            }
            else if(age == 3) {
                premium += 150
            }
            else if(age == 4 || age == 5){
                premium += 200
            }
        }

        //Determine smoker or non-smoker
        val smoker : Boolean = checkBoxSmoker.isChecked
        if(smoker) {
            if(age == 1) {
                premium += 100
            }
            else if(age == 2) {
                premium += 150
            }
            else if(age == 3) {
                premium += 200
            }
            else if(age == 4){
                premium += 250
            }
            else if(age == 5) {
                premium += 300
            }

        }
        val symbol = Currency.getInstance(Locale.getDefault()).symbol
        textViewPrice.text =  String.format("%s %d", symbol, premium)
    }

    fun reset(view: View) {
        spinnerAge.setSelection(0)
        radioButtonMale.isChecked = false
        radioButtonFemale.isChecked = false
        checkBoxSmoker.isChecked = false
        textViewPrice.setText("")
    }

}
