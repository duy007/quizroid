package edu.uw.ischool.dnh7.quizdroid.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uw.ischool.dnh7.quizdroid.R
import edu.uw.ischool.dnh7.quizdroid.TopicOverviewActivity
import edu.uw.ischool.dnh7.quizdroid.model.Topic

class TopicAdapter (
    val context: Context,
    private val data: List<Topic>
) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    class TopicViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.topic_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.topic_list_item, parent, false)
        return TopicViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = data[position].type
        holder.textView.text = topic

        holder.textView.setOnClickListener {
            holder.view.context
            val intent = Intent(holder.view.context, TopicOverviewActivity::class.java)
            intent.putExtra(TopicOverviewActivity.TYPE, topic)
            intent.putExtra(TopicOverviewActivity.DESCRIPTION, data[position].description)
            intent.putExtra(TopicOverviewActivity.TOTAL_QUESTIONS, data[position].questionCount.toString())
            context.startActivity(intent)
        }
    }
}