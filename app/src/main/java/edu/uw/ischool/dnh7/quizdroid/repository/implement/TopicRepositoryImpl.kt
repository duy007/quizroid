package edu.uw.ischool.dnh7.quizdroid.repository.implement

import android.content.Context
import edu.uw.ischool.dnh7.quizdroid.R
import edu.uw.ischool.dnh7.quizdroid.model.Question
import edu.uw.ischool.dnh7.quizdroid.model.Topic
import edu.uw.ischool.dnh7.quizdroid.repository.TopicRepository

class TopicRepositoryImpl () : TopicRepository {

    private lateinit var topicList: List<Topic>
    private lateinit var questionList: List<Question>
    private lateinit var type: String

    override fun loadTopics(context: Context): List<Topic> {
        if (this::topicList.isInitialized) return topicList
        val topics = context.resources.getStringArray(R.array.topic)
        val topicDescriptionShort = context.resources.getStringArray(R.array.topic_description_short)
        val topicDescriptionLong = context.resources.getStringArray(R.array.topic_description_long)

         topicList = topics.mapIndexed { index, topic ->
            Topic(
                type = topic, short_description = topicDescriptionShort[index],
                long_description = topicDescriptionLong[index], questionCount = 3)
        }
        return topicList
    }
    override fun loadQuestions(context: Context, type: String): List<Question> {
        if (this::questionList.isInitialized && this.type == type) return questionList
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
        this.type = type
        val questions = resourceArray[0]
        val correctAnswer = resourceArray[1]
        val answers = listOf(resourceArray[2].toList(), resourceArray[3].toList(), resourceArray[4].toList())
        questionList = questions.mapIndexed { index, questionString ->
            Question(
                question = questionString,
                correctAnswer = correctAnswer[index].toInt(),
                answers = answers[index]
            )
        }
        return questionList
    }

    override fun updateQuestion(index: Int, question: Question): List<Question> {
        var tmp = questionList.toMutableList()
        tmp[index] = question
        questionList = tmp.toList()
        return questionList
    }

    override fun addQuestion(question: Question): List<Question> {
        var tmp = questionList.toMutableList()
        tmp.add(question)
        questionList = tmp.toList()
        return questionList
    }

    override fun addTopic(topic: Topic): List<Topic> {
        var tmp = topicList.toMutableList()
        tmp.add(topic)
        topicList = tmp.toList()
        return topicList
    }

    override fun getTopics(): List<Topic> {
        return topicList
    }

    override fun getQuestions(): List<Question> {
        return questionList
    }

    override fun loadEmptyQuestion(): List<Question> {
        questionList = listOf<Question> ()
        return questionList
    }

    override fun loadEmptyTopics(): List<Topic> {
        topicList = listOf<Topic> ()
        return topicList
    }


}