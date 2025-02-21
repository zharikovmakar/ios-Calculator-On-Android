package com.example.calculator

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import android.graphics.drawable.ColorDrawable
import android.graphics.Color


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buttons = listOf(
            binding.butC,
            binding.butDel,
            binding.butDot,
            binding.butOne,
            binding.butTwo,
            binding.butThree,
            binding.butFive,
            binding.butFour,
            binding.butSix,
            binding.butSeven,
            binding.butEight,
            binding.butNine,
            binding.butZero,
            binding.butMult,
            binding.butPlus,
            binding.butMinus,
            binding.butRavno,
        )
        textFromButtonsToResultTextView(buttons)
    }
    private fun textFromButtonsToResultTextView(buttons:List<Button>){
            var resultText = ""
            for (button in buttons){
                button.setOnClickListener{
                    var resButton = button.text.toString()
                    if (resButton in listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+", ",")) {
                        if (resButton == ",") {
                            resButton = "."
                        }
                        resultText += if (resButton in listOf("+", "-")) " $resButton " else resButton
                        binding.resultTextView.text = resultText
                    }
                    else if (resButton == "×" || resButton == "÷"){
                        showDevelopmentDialog()
                    }
                    else if (resButton == "=") {
                        calculateResultInTextView(resultText)
                    }
                    else if (resButton == "C"){
                        binding.resultTextView.text = ""
                        resultText = ""
                        textFromButtonsToResultTextView(buttons)
                    }
                }
            }

    }
    private fun calculateResultInTextView(resultText: String){
        val elements = resultText.split(" ")
        var result = elements[0].toDouble()
        for (i in 1 until elements.size-1 step 2){
            val operator = elements[i]
            val num = elements[i+1].toDouble()
            when (operator){
                "+" -> result += num
                "-" -> result -= num
            }
        }
        if (result % 1.0 == 0.0){
            binding.resultTextView.text = result.toInt().toString()
        }
        else{
            binding.resultTextView.text = result.toString()
        }
    }
    private fun showDevelopmentDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.development_dialog)
        val buttonContiniue = dialog.findViewById<TextView>(R.id.continiueButtonTextView)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        buttonContiniue.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
}
