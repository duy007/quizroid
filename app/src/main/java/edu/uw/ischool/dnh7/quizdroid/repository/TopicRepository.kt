package edu.uw.ischool.dnh7.quizdroid.repository

import android.content.Context
import edu.uw.ischool.dnh7.quizdroid.model.Question
import edu.uw.ischool.dnh7.quizdroid.model.Topic

interface TopicRepository {

    fun loadTopics(context: Context): List<Topic>

    fun loadQuestions(context: Context, type: String): List<Question>

    fun updateQuestion(index: Int, question: Question) : List<Question>

    fun addQuestion(question: Question) : List<Question>

    fun addTopic(topic: Topic) : List<Topic>

    fun getTopics() : List<Topic>

    fun getQuestions() : List<Question>
    fun loadEmptyQuestion() : List<Question>

    fun loadEmptyTopics(): List<Topic>
}