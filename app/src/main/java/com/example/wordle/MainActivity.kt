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
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.wordle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnKeyListener, View.OnClickListener {
    var answer = "WORDLE"
    var tryCount = 6
    var count = 0
    var answerLength = answer.length

    lateinit var word1 : TextView; lateinit var word2 : TextView; lateinit var word3 : TextView; lateinit var word4 : TextView
    lateinit var word5 : TextView; lateinit var word6 : TextView; lateinit var word7 : TextView; lateinit var word8 : TextView
    lateinit var word9 : TextView; lateinit var word10 : TextView; lateinit var word11 : TextView; lateinit var word12 : TextView

    lateinit var inputWord1 : EditText; lateinit var inputWord2 : EditText; lateinit var inputWord3 : EditText
    lateinit var inputWord4 : EditText; lateinit var inputWord5 : EditText; lateinit var inputWord6 : EditText
    lateinit var inputWord7 : EditText; lateinit var inputWord8 : EditText; lateinit var inputWord9 : EditText
    lateinit var inputWord10 : EditText; lateinit var inputWord11 : EditText; lateinit var inputWord12 : EditText

    lateinit var toggle : ActionBarDrawerToggle

    lateinit var layout1 : LinearLayout; lateinit var layout2 : LinearLayout; lateinit var layout3: LinearLayout; lateinit var layout4 : LinearLayout
    lateinit var layout5 : LinearLayout; lateinit var layout6 : LinearLayout; lateinit var layout7 : LinearLayout; lateinit var layout8 : LinearLayout
    lateinit var layout9 : LinearLayout; lateinit var layout10 : LinearLayout; lateinit var layout11 : LinearLayout; lateinit var layout12 : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        layout1 = findViewById(R.id.layout1); layout2 = findViewById(R.id.layout2); layout3 = findViewById(R.id.layout3); layout4 = findViewById(R.id.layout4)
        layout5 = findViewById(R.id.layout5); layout6 = findViewById(R.id.layout6); layout7 = findViewById(R.id.layout7); layout8 = findViewById(R.id.layout8)
        layout9 = findViewById(R.id.layout9); layout10 = findViewById(R.id.layout10); layout11 = findViewById(R.id.layout11); layout12 = findViewById(R.id.layout12)

        //filter를 통하여 입력이 정답의 길이인 answer_length를 넘기지 못하게 조정한다.
        inputWord1 = findViewById(R.id.Input1); inputWord1.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord2 = findViewById(R.id.Input2); inputWord2.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord3 = findViewById(R.id.Input3); inputWord3.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord4 = findViewById(R.id.Input4); inputWord4.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord5 = findViewById(R.id.Input5); inputWord5.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord6 = findViewById(R.id.Input6); inputWord6.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord7 = findViewById(R.id.Input7); inputWord7.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord8 = findViewById(R.id.Input8); inputWord8.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord9 = findViewById(R.id.Input9); inputWord9.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord10 = findViewById(R.id.Input10); inputWord10.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord11 = findViewById(R.id.Input11); inputWord11.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))
        inputWord12 = findViewById(R.id.Input12); inputWord12.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength))

        word1 = findViewById(R.id.word1); word2 = findViewById(R.id.word2); word3 = findViewById(R.id.word3)
        word4 = findViewById(R.id.word4); word5 = findViewById(R.id.word5); word6 = findViewById(R.id.word6)
        word7 = findViewById(R.id.word7); word8 = findViewById(R.id.word8); word9 = findViewById(R.id.word9)
        word10 = findViewById(R.id.word10); word11 = findViewById(R.id.word11); word12 = findViewById(R.id.word12)

        //setOnKeyListener는 외부 함수에 구현하였다.
        inputWord1.setOnKeyListener(this); inputWord2.setOnKeyListener(this); inputWord3.setOnKeyListener(this)
        inputWord4.setOnKeyListener(this); inputWord5.setOnKeyListener(this); inputWord6.setOnKeyListener(this)
        inputWord7.setOnKeyListener(this); inputWord8.setOnKeyListener(this); inputWord9.setOnKeyListener(this)
        inputWord10.setOnKeyListener(this); inputWord11.setOnKeyListener(this); inputWord12.setOnKeyListener(this)

        //사이드바 구현
        toggle = ActionBarDrawerToggle(this, binding.MainView, R.string.side_bar_opened, R.string.side_bar_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        //정답 단어 길이 선택 함수 구현
        val seekBarAnswer : SeekBar = findViewById(R.id.LengthSetting)
        seekBarAnswer.progress = answerLength-2 //정답 단어의 길이로 progress 조정

        val lengthTextView : TextView = findViewById(R.id.LengthTextView)
        lengthTextView.text = "정답 단어 길이 : $answerLength"

        seekBarAnswer.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) { //view, 진행 값, 사용자/코드에 의한 변경 값 True/False
                answerLength = progress+2 //정답 길이 조절
                lengthTextView.text = "정답 단어 길이 : $answerLength"

                //입력 길이 변경, 입력 초기화 및 입력 가능하게 변경, textView 초기화
                answerLengthChange(inputWord1, word1); answerLengthChange(inputWord2, word2); answerLengthChange(inputWord3, word3)
                answerLengthChange(inputWord4, word4); answerLengthChange(inputWord5, word5); answerLengthChange(inputWord6, word6)
                answerLengthChange(inputWord7, word7); answerLengthChange(inputWord8, word8); answerLengthChange(inputWord9, word9)
                answerLengthChange(inputWord10, word10); answerLengthChange(inputWord11, word11); answerLengthChange(inputWord11, word11)
                Toast.makeText(applicationContext, "단어 길이 변경에 따라 문제를 초기화합니다.", Toast.LENGTH_SHORT).show()
                //정답 새로 받아오기 TODO()

            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        //시도 횟수에 따라 단어 입력 칸의 높이 적절히 조절
        val seekBarTry : SeekBar = findViewById(R.id.TrySetting)
        seekBarTry.progress = tryCount-4 //정답 단어의 길이로 progress 조정

        val tryTextView : TextView = findViewById(R.id.TryTextView)
        tryTextView.text = "시도 횟수 길이 : $tryCount"

        seekBarTry.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) { //view, 진행 값, 사용자/코드에 의한 변경 값 True/False
                tryCount = progress+4 //정답 길이 조절
                tryTextView.text = "시도 횟수 길이 : $tryCount"

                progressTryCountChange()

                //입력 길이 변경, 입력 초기화 및 입력 가능하게 변경, textView 초기화
                Toast.makeText(applicationContext, "시도 횟수 변경에 따라 오답 처리 되었습니다.", Toast.LENGTH_SHORT).show()
                //정답 새로 받아오기 TODO()

            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){return true}
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) { //버튼 클릭시 작동
        when(v?.id){ //n번째 시도에 따라 분류
            R.id.BtnInfo -> { //WORDLE 앱의 정보를 출력하는 버튼

            }
        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean { //View.OnKeyListener를 상속받는 함수
        if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) { //Enter키 입력시 작동
            ++count
            when(v?.id){ //n번째 시도에 따라 분류
                R.id.Input1 -> { setTextView(inputWord1, inputWord2, word1) }
                R.id.Input2 -> { setTextView(inputWord2, inputWord3, word2) }
                R.id.Input3 -> { setTextView(inputWord3, inputWord4, word3) }
                R.id.Input4 -> { setTextView(inputWord4, inputWord5, word4) }
                R.id.Input5 -> { setTextView(inputWord5, inputWord6, word5) }
                R.id.Input6 -> { setTextView(inputWord6, inputWord7, word6) }
                R.id.Input7 -> { setTextView(inputWord7, inputWord8, word7) }
                R.id.Input8 -> { setTextView(inputWord8, inputWord9, word8) }
                R.id.Input9 -> { setTextView(inputWord9, inputWord10, word9) }
                R.id.Input10 -> { setTextView(inputWord10, inputWord11, word10) }
                R.id.Input11 -> { setTextView(inputWord11, inputWord12, word11) }
                R.id.Input12 -> { setTextView(inputWord12, inputWord12, word12) }
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
                count == tryCount -> { Toast.makeText(this.applicationContext, "실패하셨습니다..", Toast.LENGTH_SHORT).show() }
                else -> edit2.isEnabled = true
            }
            edit1.isEnabled = false //입력되었으므로 사용 불가능하게 변경
        }
    }

    fun answerLengthChange(edit: EditText, tv: TextView){
        edit.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(answerLength)) //입력 길이 조정
        edit.isEnabled = true //입력 가능하게 변경
        edit.setText("") //입력 값 초기화
        tv.text="" //출력 값 초기화
    }

    fun layoutVisibilityChange(lay: LinearLayout, value : Int){
        lay.visibility = value
    }

    fun progressTryCountChange(){
        layoutVisibilityChange(layout5, View.GONE); layoutVisibilityChange(layout6, View.GONE); layoutVisibilityChange(layout7, View.GONE)
        layoutVisibilityChange(layout8, View.GONE); layoutVisibilityChange(layout9, View.GONE); layoutVisibilityChange(layout10, View.GONE)
        layoutVisibilityChange(layout11, View.GONE); layoutVisibilityChange(layout12, View.GONE)

        if(tryCount >= 5){ layoutVisibilityChange(layout5, View.VISIBLE) }
        if(tryCount >= 6){ layoutVisibilityChange(layout6, View.VISIBLE) }
        if(tryCount >= 7){ layoutVisibilityChange(layout7, View.VISIBLE) }
        if(tryCount >= 8){ layoutVisibilityChange(layout8, View.VISIBLE) }
        if(tryCount >= 9){ layoutVisibilityChange(layout9, View.VISIBLE) }
        if(tryCount >= 10){ layoutVisibilityChange(layout10, View.VISIBLE) }
        if(tryCount >= 11){ layoutVisibilityChange(layout11, View.VISIBLE) }
        if(tryCount >= 12){ layoutVisibilityChange(layout12, View.VISIBLE) }

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
