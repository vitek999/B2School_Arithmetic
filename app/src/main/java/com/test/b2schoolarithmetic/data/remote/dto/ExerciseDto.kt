package com.test.b2schoolarithmetic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExerciseDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("complexity")
    val complexity: Int,

    @SerializedName("answers")
    val answers: List<AnswerDto>
)