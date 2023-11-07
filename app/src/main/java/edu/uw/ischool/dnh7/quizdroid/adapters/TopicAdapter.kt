package edu.uw.ischool.dnh7.quizdroid.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import edu.uw.ischool.dnh7.quizdroid.R
import edu.uw.ischool.dnh7.quizdroid.TopicOverviewActivity
import edu.uw.ischool.dnh7.quizdroid.model.Topic

class TopicAdapter (
    val context: Context,
    private val data: List<Topic>
) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    class TopicViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val card = view.findViewById<MaterialCardView>(R.id.topic_overview_wrapper)
        val title = view.findViewById<TextView>(R.id.topic_title)
        val short_desc = view.findViewById<TextView>(R.id.topic_description_short)
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
        val topicShortDesc = data[position].short_description
        holder.title.text = topic
        holder.short_desc.text = topicShortDesc

        holder.card.setOnClickListener {
            holder.view.context
            val intent = Intent(holder.view.context, TopicOverviewActivity::class.java)
            intent.putExtra(TopicOverviewActivity.TYPE, topic)
            intent.putExtra(TopicOverviewActivity.DESCRIPTION, data[position].long_description)
            intent.putExtra(TopicOverviewActivity.TOTAL_QUESTIONS, data[position].questionCount.toString())
            context.startActivity(intent)
        }
    }
}