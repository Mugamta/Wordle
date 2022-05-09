package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var inputWord = findViewById<EditText>(R.id.InputWord)

        var word1_1 = findViewById<TextView>(R.id.word1_1)
        var word1_2 = findViewById<TextView>(R.id.word1_2)
        var word1_3 = findViewById<TextView>(R.id.word1_3)
        var word1_4 = findViewById<TextView>(R.id.word1_4)
        var word1_5 = findViewById<TextView>(R.id.word1_5)
        var word1_6 = findViewById<TextView>(R.id.word1_6)

        inputWord.setOnKeyListener{ v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER){
                if(inputWord.text.length == 6){
                    var str = inputWord.text.toString()
                    word1_1.text = str[0].toString()
                    word1_2.text = str[1].toString()
                    word1_3.text = str[2].toString()
                    word1_4.text = str[3].toString()
                    word1_5.text = str[4].toString()
                    word1_6.text = str[5].toString()
                }
                inputWord.setText("")
            }
            false
        }


    }
}