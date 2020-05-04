package com.test.b2schoolarithmetic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(

    @SerializedName("userName")
    val userName: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("points")
    val points: Int,

    @SerializedName("classGroupsDto")
    val classGroupsDto: List<ClassGroupsDto>?
)