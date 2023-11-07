package edu.uw.ischool.dnh7.quizdroid

import android.app.Application
import android.util.Log
import edu.uw.ischool.dnh7.quizdroid.repository.TopicRepository
import edu.uw.ischool.dnh7.quizdroid.repository.implement.TopicRepositoryImpl

class QuizApp : Application() {

    private lateinit var topicRepository: TopicRepository
    override fun onCreate() {
        Log.i("Application", "QuizApp Init")
        topicRepository = TopicRepositoryImpl(this)
        super.onCreate()
    }

    fun getTopicRepository() : TopicRepository {
        return topicRepository
    }
}