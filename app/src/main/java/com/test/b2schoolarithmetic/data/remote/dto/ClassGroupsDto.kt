package com.test.b2schoolarithmetic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ClassGroupsDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("educationalInstitution")
    val educationalInstitution: String,
    @SerializedName("classNumber")
    val classNumber: Int,
    @SerializedName("literal")
    val literal: String,
    @SerializedName("users")
    val users: List<UserDto>
)