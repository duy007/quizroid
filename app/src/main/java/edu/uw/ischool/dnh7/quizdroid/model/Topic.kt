package edu.uw.ischool.dnh7.quizdroid.model

import androidx.annotation.StringRes

data class Topic(
    val type: String,
    val description: String,
    val questionCount: Int)