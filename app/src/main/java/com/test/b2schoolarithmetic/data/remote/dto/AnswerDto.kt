package com.test.b2schoolarithmetic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AnswerDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("text")
    val text: String,

    @SerializedName("isCorrect")
    val isCorrect: Boolean
)