package edu.uw.ischool.dnh7.quizdroid.model

import androidx.annotation.StringRes

data class Question (
    val question: String,
    val correctAnswer: Int,
    val answers: List<String>
)