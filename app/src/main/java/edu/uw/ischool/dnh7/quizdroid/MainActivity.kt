package edu.uw.ischool.dnh7.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uw.ischool.dnh7.quizdroid.adapters.TopicAdapter
import edu.uw.ischool.dnh7.quizdroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var topicRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        topicRecyclerView = binding.topicRecyclerView
        topicRecyclerView.layoutManager = LinearLayoutManager(this)
        topicRecyclerView.adapter = TopicAdapter(this,(this.application as QuizApp).getTopicRepository().loadTopics(this))
    }
}