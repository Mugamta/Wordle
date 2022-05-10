package com.example.wordle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnKeyListener {
    var answer = "WORDLE"
    var answerLength = answer.length

    lateinit var word1 : TextView; lateinit var word2 : TextView; lateinit var word3 : TextView
    lateinit var word4 : TextView; lateinit var word5 : TextView; lateinit var word6 : TextView

    lateinit var inputWord1 : EditText; lateinit var inputWord2 : EditText; lateinit var inputWord3 : EditText
    lateinit var inputWord4 : EditText; lateinit var inputWord5 : EditText; lateinit var inputWord6 : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //filter를 통하여 입력이 정답의 길이인 answer_length를 넘기지 못하게 조정한다.
        inputWord1 = findViewById(R.id.Input1); inputWord1.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord2 = findViewById(R.id.Input2); inputWord2.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord3 = findViewById(R.id.Input3); inputWord3.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord4 = findViewById(R.id.Input4); inputWord4.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord5 = findViewById(R.id.Input5); inputWord5.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord6 = findViewById(R.id.Input6); inputWord6.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))

        word1 = findViewById(R.id.word1); word2 = findViewById(R.id.word2); word3 = findViewById(R.id.word3)
        word4 = findViewById(R.id.word4); word5 = findViewById(R.id.word5); word6 = findViewById(R.id.word6)

        //setOnKeyListener는 외부 함수에 구현하였다.
        inputWord1.setOnKeyListener(this); inputWord2.setOnKeyListener(this); inputWord3.setOnKeyListener(this)
        inputWord4.setOnKeyListener(this); inputWord5.setOnKeyListener(this); inputWord6.setOnKeyListener(this)
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean { //View.OnKeyListener를 상속받는 함수
        if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) { //Enter키 입력시 작동
            when(v?.id){ //n번째 시도에 따라 분류
                R.id.Input1 -> { setTextView(inputWord1, inputWord2, word1) }
                R.id.Input2 -> { setTextView(inputWord2, inputWord3, word2) }
                R.id.Input3 -> { setTextView(inputWord3, inputWord4, word3) }
                R.id.Input4 -> { setTextView(inputWord4, inputWord5, word4) }
                R.id.Input5 -> { setTextView(inputWord5, inputWord6, word5) }
                R.id.Input6 -> { setTextView(inputWord6, inputWord6, word6) }
            }
        }
        return false
    }

    fun setTextView(edit1: EditText, edit2: EditText, tv: TextView){ //입력을 계산하여 textview에 대입하는 함수
        if(edit1.text.length == answerLength){
            val str = edit1.text.toString() //입력된 문자열

            val spannableStringBuilder = correctAnswer(answer, str) //정답과 문자열 대조하여 색칠된 문자열 반환

            tv.text = spannableStringBuilder // 초록/노란색으로 칠해진 결과를 TextView대입

            edit1.setText("") //입력한 edittext는 textview로 전달되었으므로 비움

            when { //정답일때는 메세지를 출력, edit1 == edit2일때는 시도가 모두 실패한 경우이므로 실패 메세지를 출력, 이외에는 다음 입력 활성화
                str == answer -> { Toast.makeText(this.applicationContext, "단어 ${str}은 정답입니다!", Toast.LENGTH_SHORT).show() }
                edit1 == edit2 -> { Toast.makeText(this.applicationContext, "실패하셨습니다..", Toast.LENGTH_SHORT).show() }
                else -> edit2.isEnabled = true
            }
            edit1.isEnabled = false //입력되었으므로 사용 불가능하게 변경
        }
    }

    fun correctAnswer(answer: String, str: String): SpannableStringBuilder { //입력과 정답을 비교하여 문자열을 색칠하는 함수
        val spannableStringBuilder = SpannableStringBuilder(str)

        for(i in 0 until answerLength){ //answer의 문자를 하나씩 순회하며 위치와 일치하면 녹색, 아니면 나머지 글자에서 존재하는지 탐색
            if(answer[i] == str[i]){ //같은 경우 녹색으로 처리
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GREEN),
                    i,
                    i+1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)  //색, 시작 구간, 끝 구간, 삽입된 구간을 제외하고 이를 삽입
            }
            else{
                for(j in 0 until answerLength){
                    if(i != j && answer[i] == str[j]){ //다른 경우 노란색으로 처리
                        spannableStringBuilder.setSpan(
                            ForegroundColorSpan(Color.YELLOW),
                            j,
                            j+1,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                        break //같은 글자가 여러개 있을 수 있으므로 현재 answer[i]에 대해 찾아냈으면 종료
                    }
                }
            }
        }
        return spannableStringBuilder
    }
}