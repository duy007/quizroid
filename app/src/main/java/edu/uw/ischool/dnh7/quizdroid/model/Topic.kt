package edu.uw.ischool.dnh7.quizdroid.model

import androidx.annotation.StringRes

data class Topic(
    val type: String,
    val short_description: String,
    val long_description: String,
    val questionCount: Int)