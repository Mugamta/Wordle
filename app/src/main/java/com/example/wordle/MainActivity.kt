package com.example.wordle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputWord = findViewById<EditText>(R.id.InputWord)
        var answer : String = "WORDLE"

        val word1_1 = findViewById<TextView>(R.id.word1_1)
        val word1_2 = findViewById<TextView>(R.id.word1_2)
        val word1_3 = findViewById<TextView>(R.id.word1_3)
        val word1_4 = findViewById<TextView>(R.id.word1_4)
        val word1_5 = findViewById<TextView>(R.id.word1_5)
        val word1_6 = findViewById<TextView>(R.id.word1_6)

        inputWord.setOnKeyListener{ v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER){
                if(inputWord.text.length == 6){
                    val str = inputWord.text.toString().uppercase(Locale.getDefault()) //대문자 변환

                    val strList : MutableList<Char> = str.toMutableList()

                    word1_1.text = str[0].toString(); word1_2.text = str[1].toString()
                    word1_3.text = str[2].toString(); word1_4.text = str[3].toString()
                    word1_5.text = str[4].toString(); word1_6.text = str[5].toString()

                    word1_1.setTextColor(Color.WHITE); word1_2.setTextColor(Color.WHITE)
                    word1_3.setTextColor(Color.WHITE); word1_4.setTextColor(Color.WHITE)
                    word1_5.setTextColor(Color.WHITE); word1_6.setTextColor(Color.WHITE)

                    when {
                        answer[0] == strList[0] -> { word1_1.setTextColor(Color.GREEN)}
                        answer[0] == strList[1] -> { word1_2.setTextColor(Color.YELLOW)}
                        answer[0] == strList[2] -> { word1_3.setTextColor(Color.YELLOW)}
                        answer[0] == strList[3] -> { word1_4.setTextColor(Color.YELLOW)}
                        answer[0] == strList[4] -> { word1_5.setTextColor(Color.YELLOW)}
                        answer[0] == strList[5] -> { word1_6.setTextColor(Color.YELLOW)}
                    }

                    when {
                        answer[1] == strList[0] -> { word1_1.setTextColor(Color.YELLOW)}
                        answer[1] == strList[1] -> { word1_2.setTextColor(Color.GREEN)}
                        answer[1] == strList[2] -> { word1_3.setTextColor(Color.YELLOW)}
                        answer[1] == strList[3] -> { word1_4.setTextColor(Color.YELLOW)}
                        answer[1] == strList[4] -> { word1_5.setTextColor(Color.YELLOW)}
                        answer[1] == strList[5] -> { word1_6.setTextColor(Color.YELLOW)}
                    }

                    when {
                        answer[2] == strList[0] -> { word1_1.setTextColor(Color.YELLOW)}
                        answer[2] == strList[1] -> { word1_2.setTextColor(Color.YELLOW)}
                        answer[2] == strList[2] -> { word1_3.setTextColor(Color.GREEN)}
                        answer[2] == strList[3] -> { word1_4.setTextColor(Color.YELLOW)}
                        answer[2] == strList[4] -> { word1_5.setTextColor(Color.YELLOW)}
                        answer[2] == strList[5] -> { word1_6.setTextColor(Color.YELLOW)}
                    }

                    when {
                        answer[3] == strList[0] -> { word1_1.setTextColor(Color.YELLOW)}
                        answer[3] == strList[1] -> { word1_2.setTextColor(Color.YELLOW)}
                        answer[3] == strList[2] -> { word1_3.setTextColor(Color.YELLOW)}
                        answer[3] == strList[3] -> { word1_4.setTextColor(Color.GREEN)}
                        answer[3] == strList[4] -> { word1_5.setTextColor(Color.YELLOW)}
                        answer[3] == strList[5] -> { word1_6.setTextColor(Color.YELLOW)}
                    }

                    when {
                        answer[4] == strList[0] -> { word1_1.setTextColor(Color.YELLOW)}
                        answer[4] == strList[1] -> { word1_2.setTextColor(Color.YELLOW)}
                        answer[4] == strList[2] -> { word1_3.setTextColor(Color.YELLOW)}
                        answer[4] == strList[3] -> { word1_4.setTextColor(Color.YELLOW)}
                        answer[4] == strList[4] -> { word1_5.setTextColor(Color.GREEN)}
                        answer[4] == strList[5] -> { word1_6.setTextColor(Color.YELLOW)}
                    }

                    when {
                        answer[5] == strList[0] -> { word1_1.setTextColor(Color.YELLOW)}
                        answer[5] == strList[1] -> { word1_2.setTextColor(Color.YELLOW)}
                        answer[5] == strList[2] -> { word1_3.setTextColor(Color.YELLOW)}
                        answer[5] == strList[3] -> { word1_4.setTextColor(Color.YELLOW)}
                        answer[5] == strList[4] -> { word1_5.setTextColor(Color.YELLOW)}
                        answer[5] == strList[5] -> { word1_6.setTextColor(Color.GREEN)}
                    }

                    inputWord.setText("")
                }
            }
            false
        }
    }
}