package edu.uw.ischool.dnh7.quizdroid.repository.implement

import android.content.Context
import edu.uw.ischool.dnh7.quizdroid.R
import edu.uw.ischool.dnh7.quizdroid.model.Question
import edu.uw.ischool.dnh7.quizdroid.model.Topic
import edu.uw.ischool.dnh7.quizdroid.repository.TopicRepository

class TopicRepositoryImpl (private val context: Context) : TopicRepository {
    override fun loadTopics(): List<Topic> {
        val topics = context.resources.getStringArray(R.array.topic)
        val topicDescriptionShort = context.resources.getStringArray(R.array.topic_description_short)
        val topicDescriptionLong = context.resources.getStringArray(R.array.topic_description_long)

        return topics.mapIndexed { index, topic ->
            Topic(topic, topicDescriptionShort[index], topicDescriptionLong[index],3)
        }
    }
    override fun loadQuestions(type: String): List<Question> {
        val resourceArray = mutableListOf<Array<String>>()
        when (type) {
            "math" ->
            {
                resourceArray.add(context.resources.getStringArray(R.array.math_questions))
                resourceArray.add(context.resources.getStringArray(R.array.math_correct_answer))
                resourceArray.add(context.resources.getStringArray(R.array.math_false_answer_1))
                resourceArray.add(context.resources.getStringArray(R.array.math_false_answer_2))
                resourceArray.add(context.resources.getStringArray(R.array.math_false_answer_3))
            }
            "marvel" -> {
                resourceArray.add(context.resources.getStringArray(R.array.marvel_questions))
                resourceArray.add(context.resources.getStringArray(R.array.marvel_correct_answer))
                resourceArray.add(context.resources.getStringArray(R.array.marvel_false_answer_1))
                resourceArray.add(context.resources.getStringArray(R.array.marvel_false_answer_2))
                resourceArray.add(context.resources.getStringArray(R.array.marvel_false_answer_3))
            }
            else -> {
                resourceArray.add(context.resources.getStringArray(R.array.physics_questions))
                resourceArray.add(context.resources.getStringArray(R.array.physics_correct_answer))
                resourceArray.add(context.resources.getStringArray(R.array.physics_false_answer_1))
                resourceArray.add(context.resources.getStringArray(R.array.physics_false_answer_2))
                resourceArray.add(context.resources.getStringArray(R.array.physics_false_answer_3))
            }
        }
        val questions = resourceArray[0]
        val correctAnswer = resourceArray[1]
        val answers = listOf(resourceArray[2].toList(), resourceArray[3].toList(), resourceArray[4].toList())
        return questions.mapIndexed { index, questionString ->
            Question(
                question = questionString,
                correctAnswer = correctAnswer[index].toInt(),
                answers = answers[index]
            )
        }
    }
}