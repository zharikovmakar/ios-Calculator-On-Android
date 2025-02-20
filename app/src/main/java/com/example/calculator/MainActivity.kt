package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

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
                    if (button.text != "=") {
                        val resButton = button.text.toString()
                        resultText += if (resButton in listOf("+", "-")) " $resButton " else resButton
                        binding.resultTextView.text = resultText
                    }
                    else calculateResultInTextView(resultText)
                }
            }
    }
    private fun calculateResultInTextView(resultText: String){
        val elements = resultText.split(" ")
        var result = elements[0].toInt()
        for (i in 1 until elements.size-1 step 2){
            val operator = elements[i]
            val num = elements[i+1].toInt()
            when (operator){
                "+" -> result += num
                "-" -> result -= num
            }
        }
        binding.resultTextView.text = result.toString()
    }
}
