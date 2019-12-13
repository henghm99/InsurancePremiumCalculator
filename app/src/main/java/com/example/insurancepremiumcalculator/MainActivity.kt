package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: InsuranceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(InsuranceViewModel::class.java)
        display()

        button_calculate.setOnClickListener {
            if (radioGroup.checkedRadioButtonId != -1) {
                viewModel.premiumAmount = getPremium()
                textView_insurance_premium.text = String.format("RM %.2f", viewModel.premiumAmount)
            } else {
                val toast: Toast = Toast.makeText(this, "Please select gender!", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }

        button_reset.setOnClickListener {
            resetOnclick()
        }
    }

    private fun display() {
        textView_insurance_premium.text = String.format("RM %.2f", viewModel.premiumAmount)
    }

    private fun getPremium(): Double {
        return when(spinner_age.selectedItemPosition) {
            0 -> 60.00
            1 -> 70.00 +
                    (if(radioButton_male.isChecked) 50.00 else 0.0) +
                    (if(checkBox_smoker.isChecked) 100.00 else 0.0)
            2 -> 90.00 +
                    (if(radioButton_male.isChecked) 100.00 else 0.0) +
                    (if(checkBox_smoker.isChecked) 150.00 else 0.0)
            3 -> 120.00 +
                    (if(radioButton_male.isChecked) 150.00 else 0.0) +
                    (if(checkBox_smoker.isChecked) 200.00 else 0.0)
            4 -> 150.00 +
                    (if(radioButton_male.isChecked) 200.00 else 0.0) +
                    (if(checkBox_smoker.isChecked) 250.00 else 0.0)
            else -> 150.00 +
                    (if(radioButton_male.isChecked) 200.00 else 0.0) +
                    (if(checkBox_smoker.isChecked) 300.00 else 0.0)
        }
    }

    private fun resetOnclick() {
        spinner_age.setSelection(0)
        radioGroup.clearCheck()
        checkBox_smoker.isChecked = false
        viewModel.premiumAmount = 0.00
        textView_insurance_premium.text = "RM 0.00"
    }
}
