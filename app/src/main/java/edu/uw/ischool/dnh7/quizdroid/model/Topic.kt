package edu.uw.ischool.dnh7.quizdroid.model

import androidx.annotation.StringRes
import edu.uw.ischool.dnh7.quizdroid.R

data class Topic(
    val icon: Int = R.drawable.ic_launcher_foreground,
    val type: String,
    val short_description: String,
    val long_description: String,
    val questionCount: Int)