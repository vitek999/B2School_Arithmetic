package com.test.b2schoolarithmetic.data.domain.user

enum class UserType {
    PARENT, TEACHER, STUDENT;

    override fun toString(): String = when (this) {
        TEACHER -> "Учитель"
        PARENT -> "Родитель"
        STUDENT -> "Ученик"
    }
}