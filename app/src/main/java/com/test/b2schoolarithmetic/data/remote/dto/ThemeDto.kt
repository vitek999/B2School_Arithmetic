package com.test.b2schoolarithmetic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ThemeDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("levels")
    val levels: List<LevelDto>
)