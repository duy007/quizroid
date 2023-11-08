package edu.uw.ischool.dnh7.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.dnh7.quizdroid.databinding.TopicOverviewBinding

class TopicOverviewActivity : AppCompatActivity() {

    companion object {
        const val TYPE = "type"
        const val DESCRIPTION = "description"
        const val TOTAL_QUESTIONS = "total_questions"
        const val ICON = "icon"
        const val STARTING_POSITION = 0
    }
    private lateinit var binding: TopicOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TopicOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topicDescriptionLong.text = intent?.extras?.getString(DESCRIPTION).toString()
        binding.iconOverview.setImageResource(intent?.extras?.getString(ICON).toString().toInt())
        binding.totalQuestions.text = getString(R.string.total_questions,
            intent?.extras?.getString(TOTAL_QUESTIONS)?.toInt() ?: 0
        )
        binding.beginButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            Log.d("type", this.intent?.extras?.getString(TYPE).toString())
            intent.putExtra(QuestionActivity.TYPE, this.intent?.extras?.getString(TYPE).toString())
            intent.putExtra(QuestionActivity.POSITION, STARTING_POSITION.toString())
            this.startActivity(intent)
        }
    }
}