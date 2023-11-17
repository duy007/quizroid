package edu.uw.ischool.dnh7.quizdroid.repository.implement

import android.content.Context
import edu.uw.ischool.dnh7.quizdroid.R
import edu.uw.ischool.dnh7.quizdroid.model.Question
import edu.uw.ischool.dnh7.quizdroid.model.Topic
import edu.uw.ischool.dnh7.quizdroid.repository.TopicRepository
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileReader

class TopicRepositoryImpl () : TopicRepository {

    private lateinit var topicList: List<Topic>
    private lateinit var questionList: List<Question>
    private lateinit var jsonMap: MutableMap<String, JSONObject>
    private lateinit var type: String

    override fun loadTopics(context: Context): List<Topic> {
        if (this::topicList.isInitialized) return topicList
        val topics = mutableListOf<String>()
        val desc = mutableListOf<String>()
        val questionCount = mutableListOf<Int>()
        val file = File(context.filesDir, "./questions.json")
        val inputStream = FileReader(file)
        var data: JSONArray
        inputStream.use {
            data = JSONArray(it.readText())
        }
        inputStream.close()
        for (index in 0 until data.length()) {
            val value = data.getJSONObject(index)
            topics.add(value.getString("title"))
            desc.add(value.getString("desc"))
            questionCount.add(value.getJSONArray("questions").length())
        }

         topicList = topics.mapIndexed { index, topic ->
            Topic(
                type = topic, short_description = desc[index],
                long_description = desc[index], questionCount = questionCount[index])
        }
        return topicList
    }
    override fun loadQuestions(context: Context, type: String): List<Question> {
        if (this::questionList.isInitialized && this.type == type) return questionList
        val file = File(context.filesDir, "./questions.json")
        // load data from file
        val inputStream = FileReader(file)
        var data: JSONArray
        inputStream.use {
            data = JSONArray(it.readText())
        }
        inputStream.close()
        // loop through JSONArray from file
        for (index in 0 until data.length()) {
            val value = data.getJSONObject(index)
            // check if type are question type is the same
            if (value.getString("title") == type) {
                this.type = type
                // load the question list, and loop through it
                val questionArray = value.getJSONArray("questions")
                val tmpList = mutableListOf<Question>()
                for (index in 0 until questionArray.length()) {
                    // generate Json object
                    val questionObj = questionArray.getJSONObject(index)
                    // generate answerArray for a question
                    val answerArray = questionObj.getJSONArray("answers")
                    val answerList = mutableListOf<String>()
                    // generate a list<String> for Question Model
                    for (index in 0 until answerArray.length()) {
                        answerList.add(answerArray.getString(index))
                    }
                    // correctAnswer - 1 b/c of JSON is 1-based index, app is not
                    //
                    tmpList.add(
                        Question(
                            question = questionObj.getString("text"),
                            correctAnswer = questionObj.getInt("answer") - 1,
                            answers = answerList.toList()
                        )
                    )
                }
                // set tmpList to questionList
                questionList = tmpList.toList()
            }
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