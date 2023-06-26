package com.example.bmicalculator

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.R
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val imageBoy = findViewById<ImageView>(R.id.imgMale)
        val imageGirl = findViewById<ImageView>(R.id.imgFemale)
        val weight = findViewById<EditText>(R.id.edWeightValue)
        val height = findViewById<EditText>(R.id.edHeightValue)
        val calculateButton = findViewById<Button>(R.id.btnCalculate)
        val bmi = findViewById<TextView>(R.id.txtBMI)
        val bmiStatus = findViewById<TextView>(R.id.statusBMI)
        val bmiView = findViewById<LinearLayout>(R.id.llResult)
        val calculateAgainButton = findViewById<TextView>(R.id.txtAgain)

        imageBoy.setOnClickListener {
            imageBoy.setImageResource(R.drawable.man_svgrepo_com)
            imageGirl.setImageResource(R.drawable.female_logo_blur)
        }

        imageGirl.setOnClickListener {
            imageBoy.setImageResource(R.drawable.male_blur)
            imageGirl.setImageResource(R.drawable.femalelogo)
        }

        calculateButton.setOnClickListener {
            var weightValue = 0.0
            var heightValue = 0.0
            if (weight.text.toString().isNotEmpty()) {
                weightValue = weight.text.toString().toDouble()
            }
            if (height.text.toString().isNotEmpty()) {
                heightValue = (height.text.toString().toDouble() / 100)
            }
            if (weightValue > 0.0 && heightValue > 0.0) {
                val bmiValue = String.format("%.2f", weightValue / heightValue.pow(2))
                bmi.text = bmiValue
                bmiStatus.text = bmiStatusValue(weightValue / heightValue.pow(2))
                bmiView.visibility = VISIBLE
                calculateButton.visibility = GONE
            } else
                Toast.makeText(this, "Please Input Weight and Height Values greater than 0", Toast.LENGTH_LONG).show()
        }

        calculateAgainButton.setOnClickListener {
            bmiView.visibility = GONE
            calculateButton.visibility = VISIBLE
            weight.text.clear()
            height.text.clear()
            weight.requestFocus()
        }
    }

    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if (bmi < 18.5)
            bmiStatus = "Underweight"
        else if (bmi >= 18.5 && bmi < 25)
            bmiStatus = "Normal"
        else if (bmi >= 25 && bmi < 30)
            bmiStatus = "Overweight"
        else if (bmi > 30)
            bmiStatus = "Obese"
        return bmiStatus
    }
}