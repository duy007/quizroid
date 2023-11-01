package edu.uw.ischool.dnh7.quizdroid

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import edu.uw.ischool.dnh7.quizdroid.data.DataSource
import edu.uw.ischool.dnh7.quizdroid.databinding.QuestionBinding
import kotlin.random.Random


class QuestionActivity : AppCompatActivity() {
    companion object {
        const val TYPE = "type"
        const val POSITION = "position"
        var currentAnswer = ""
    }
    private lateinit var binding: QuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = DataSource(this).loadQuestions(intent?.extras?.getString(TYPE).toString().lowercase())
        val position = intent?.extras?.getString(POSITION).toString().toInt()
        binding.questionTitle.text = data[position].question
        binding.submitButton.isEnabled = false
        val answers = data[position].falseAnswer.toMutableList()
        answers.add(Random.nextInt(0, data[position].falseAnswer.size),data[position].correctAnswer)

        val layoutParam = RadioGroup.LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        )
        layoutParam.setMargins(8, 32, 8, 32)
        answers.forEachIndexed { index, answer ->
            val btn = RadioButton(this)
            btn.text = answer
            btn.layoutParams = layoutParam
            btn.setTextAppearance(androidx.appcompat.R.style.Base_TextAppearance_AppCompat_Body1)
            btn.textSize = 32F
            btn.setPadding(32, 32, 32,32)
            btn.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    currentAnswer = answer
                    binding.submitButton.isEnabled = true
                    Log.v("currentAnswer", currentAnswer)
                }
            }
            binding.answerRadioGroup.addView(btn)
        }
//        Log.v("answers", answers.toString())
    }
}