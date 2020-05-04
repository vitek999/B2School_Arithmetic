package com.test.b2schoolarithmetic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserLevelDto(
    @SerializedName("successfullyPassed")
    val successfullyPassed: Boolean,

    @SerializedName("exerciseCount")
    val exerciseCount: Int,

    @SerializedName("successExerciseCount")
    val successExerciseCount: Int
)