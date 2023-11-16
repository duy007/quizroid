package edu.uw.ischool.dnh7.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStoragePublicDirectory
import android.util.JsonReader
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uw.ischool.dnh7.quizdroid.adapters.TopicAdapter
import edu.uw.ischool.dnh7.quizdroid.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.FileReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var topicRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Environment.getExternalStoragePublicDirectory()
        val file = File(filesDir, "./questions.json")
        val inputStream = FileReader(file)
        inputStream.use {
            val text = it.readText()
            val jsonArray = JSONArray(text)
            val jsonObjectZero = JSONObject(jsonArray.get(0).toString())
            Log.d("jsonObjectZeroProperties", jsonObjectZero.toString())
            Log.d("jsonObjectZeroProperties", jsonObjectZero.getString("title"))
            Log.d("jsonObjectZeroProperties", jsonObjectZero.getString("desc"))
            Log.d("jsonObjectZeroProperties", jsonObjectZero.getJSONArray("questions").toString())
            Log.d("jsonObjectZeroProperties", JSONObject(jsonObjectZero.getJSONArray("questions").get(0).toString()).getString("text"))
            Log.d("jsonObjectZeroProperties", JSONObject(jsonObjectZero.getJSONArray("questions").get(0).toString()).getString("answer"))
            Log.d("jsonObjectZeroProperties", JSONObject(jsonObjectZero.getJSONArray("questions").get(0).toString()).getJSONArray("answers")[0].toString())
            Log.d("jsonObjectZeroProperties", JSONObject(jsonObjectZero.getJSONArray("questions").get(0).toString()).getJSONArray("answers")[1].toString())
            Log.d("jsonObjectZeroProperties", JSONObject(jsonObjectZero.getJSONArray("questions").get(0).toString()).getJSONArray("answers")[2].toString())
            Log.d("jsonObjectZeroProperties", JSONObject(jsonObjectZero.getJSONArray("questions").get(0).toString()).getJSONArray("answers")[3].toString())
        }
        topicRecyclerView = binding.topicRecyclerView
        topicRecyclerView.layoutManager = LinearLayoutManager(this)
        topicRecyclerView.adapter = TopicAdapter(this,(this.application as QuizApp).getTopicRepository().loadTopics(this))
    }
}