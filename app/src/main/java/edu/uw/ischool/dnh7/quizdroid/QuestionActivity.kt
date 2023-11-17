package edu.uw.ischool.dnh7.quizdroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.LayoutParams
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.dnh7.quizdroid.databinding.QuestionBinding
import kotlin.random.Random

data class QuestionState(
    var type: String = "marvel",
    var position: Int = 0,
    var currentAnswer: Int = 0
)
class QuestionActivity : AppCompatActivity() {
    companion object {
        const val TYPE = "type"
        const val POSITION = "position"
        var currState = QuestionState()
    }
    private lateinit var binding: QuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = (this.application as QuizApp).getTopicRepository().loadQuestions(this, intent?.extras?.getString(TYPE).toString())
        Log.d("type", intent?.extras?.getString(TYPE).toString())
        currState.position = intent?.extras?.getString(POSITION).toString().toInt()
        currState.type = this.intent?.extras?.getString(TYPE).toString()
        binding.questionTitle.text = data[currState.position].question
        binding.submitButton.isEnabled = false
        val answers = data[currState.position].answers

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
                    currState.currentAnswer = index
                    binding.submitButton.isEnabled = true
                    Log.v("currentAnswer", currState.currentAnswer.toString() )
                }
            }
            binding.answerRadioGroup.addView(btn)
        }
        binding.submitButton.setOnClickListener {
            val intent = Intent(this, AnswerActivity::class.java)
            intent.putExtra(AnswerActivity.TYPE, currState.type)
            intent.putExtra(AnswerActivity.POSITION, currState.position.toString())
            intent.putExtra(AnswerActivity.CORRECT_ANSWER, data[currState.position].correctAnswer.toString())
            intent.putExtra(AnswerActivity.USER_ANSWER, currState.currentAnswer.toString())
            intent.putExtra(AnswerActivity.LENGTH, data.size.toString())
            intent.putExtra(AnswerActivity.CORRECT, isCorrect(data[currState.position].correctAnswer, currState.currentAnswer).toString())
            this.startActivity(intent)
        }

        val callback = HandleBackPress(this, true)
        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private class HandleBackPress(val context: Context, enable: Boolean) : OnBackPressedCallback(enable) {
        override fun handleOnBackPressed() {
            if (currState.position == 0) {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            } else {
                val intent = Intent(context, QuestionActivity::class.java)
                AnswerActivity.revertState()
                intent.putExtra(TYPE, currState.type)
                intent.putExtra(POSITION, (currState.position-1).toString())
                context.startActivity(intent)
            }
        }
    }
    private fun isCorrect(correctAnswer: Int, userAnswer: Int) : Boolean {
        return correctAnswer == userAnswer
    }
}