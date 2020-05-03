package com.test.b2schoolarithmetic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LevelDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("levelNumber")
    val levelNumber: Int
)