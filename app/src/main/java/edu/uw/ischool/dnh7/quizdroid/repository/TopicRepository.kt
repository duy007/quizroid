package edu.uw.ischool.dnh7.quizdroid.repository

import edu.uw.ischool.dnh7.quizdroid.model.Question
import edu.uw.ischool.dnh7.quizdroid.model.Topic

interface TopicRepository {

    fun loadTopics(): List<Topic>

    fun loadQuestions(type: String): List<Question>
}