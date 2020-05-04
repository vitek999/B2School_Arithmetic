package com.test.b2schoolarithmetic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserTestResultDto(
    @SerializedName("answers")
    val answers: List<Long>,

    @SerializedName("dateFinish")
    val dateFinish: String,

    @SerializedName("dateStart")
    val dateStart: String,

    @SerializedName("levelId")
    val levelId: Long
)