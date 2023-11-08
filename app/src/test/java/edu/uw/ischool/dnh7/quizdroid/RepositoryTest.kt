package edu.uw.ischool.dnh7.quizdroid

import edu.uw.ischool.dnh7.quizdroid.model.Question
import edu.uw.ischool.dnh7.quizdroid.model.Topic
import edu.uw.ischool.dnh7.quizdroid.repository.implement.TopicRepositoryImpl
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryTest {
    @Test
    fun repository_question_isEmpty() {
        val repository = TopicRepositoryImpl()
        repository.loadEmptyQuestion()
        assertEquals(listOf<Question>(), repository.getQuestions())
    }

    @Test
    fun repository_question_saveQuestion() {
        val repository = TopicRepositoryImpl()
        repository.loadEmptyQuestion()
        assertEquals(listOf<Question>(), repository.getQuestions())
        val question = Question(
            "This is a test question",
            0,
            listOf<String>("Hello 1", "Hello 2", "Hello 3")
        )
        repository.addQuestion(question)
        assertEquals(listOf(question), repository.getQuestions())
    }

    @Test
    fun repository_topic_isEmpty() {
        val repository = TopicRepositoryImpl()
        repository.loadEmptyTopics()
        assertEquals(listOf<Topic>(), repository.getTopics())
    }

    @Test
    fun repository_topic_saveTopic() {
        val repository = TopicRepositoryImpl()
        repository.loadEmptyTopics()
        assertEquals(listOf<Topic>(), repository.getTopics())
        val topic = Topic(
            type = "test",
            short_description = "short_desc",
            long_description = "long_Desc",
            questionCount =  0
        )
        repository.addTopic(topic)
        assertEquals(listOf(topic), repository.getTopics())
    }
}