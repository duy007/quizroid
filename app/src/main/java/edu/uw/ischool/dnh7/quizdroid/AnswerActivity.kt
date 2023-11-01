package edu.uw.ischool.dnh7.quizdroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.dnh7.quizdroid.data.DataSource
import edu.uw.ischool.dnh7.quizdroid.databinding.AnswerBinding

data class AnswerState(
    var currCorrect: Int = 0,
    var currPosition: Int = 0,
    var correctValue: Boolean = false,
    var total: Int = 0,
    var type: String = "marvel",
    var correctAnswer: String = "n/a",
    var userAnswer: String = "n/a"
)
class AnswerActivity : AppCompatActivity()  {

    private lateinit var binding: AnswerBinding
    companion object {
        const val TYPE = "type"
        const val POSITION = "position"
        const val CORRECT_ANSWER = "correct_answer"
        const val USER_ANSWER = "user_answer"
        const val LENGTH = "length"
        const val CORRECT = "correct"
        private var prevState = AnswerState()
        private var currState = AnswerState()
        fun revertState() {
            Log.d("AnswerState", "CURR: " + currState.toString())
            Log.d("AnswerState", "PREV: " + prevState.toString())
            currState = prevState.copy()
        }

        fun resetValue() {
            currState = AnswerState()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AnswerBinding.inflate(layoutInflater)
        prevState = currState.copy()
        Log.d("AnswerState", "PREV: " + prevState.toString())
        setContentView(binding.root)
        currState.correctValue = intent?.extras?.getString(CORRECT).toString().toBooleanStrict()
        currState.total = intent?.extras?.getString(LENGTH).toString().toInt()
        currState.type = intent?.extras?.getString(TYPE).toString()
        currState.correctAnswer = intent?.extras?.getString(CORRECT_ANSWER).toString()
        currState.userAnswer = intent?.extras?.getString(USER_ANSWER).toString()
        currState.currPosition = intent?.extras?.getString(POSITION).toString().toInt()
        if (currState.correctValue) currState.currCorrect+=1
        Log.d("AnswerState", "CURR: " + currState.toString())
        binding.correctAnswer.text = currState.correctAnswer
        binding.userAnswer.text =  currState.userAnswer
        binding.answerResult.text = getString(R.string.you_have_1_d_out_of_2_d_correct, currState.currCorrect, currState.total)
        binding.nextButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra(QuestionActivity.TYPE, currState.type)
            intent.putExtra(QuestionActivity.POSITION, (currState.currPosition+1).toString())
            this.startActivity(intent)
        }
        if (currState.currPosition == currState.total - 1) {
            binding.nextButton.text = "Finish"
            binding.nextButton.setOnClickListener {
                resetValue()
                val intent = Intent(this, MainActivity::class.java)
                this.startActivity(intent)
            }
        }
        val callback = HandleBackPress(this, true)
        this.onBackPressedDispatcher.addCallback(this, callback)
    }
    private class HandleBackPress(val context: Context, enable: Boolean) : OnBackPressedCallback(enable) {
        override fun handleOnBackPressed() {
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra(QuestionActivity.TYPE, currState.type)
            intent.putExtra(QuestionActivity.POSITION, (currState.currPosition).toString())
            if (currState.currPosition == 0) resetValue()
            revertState()
            context.startActivity(intent)
        }
    }
}