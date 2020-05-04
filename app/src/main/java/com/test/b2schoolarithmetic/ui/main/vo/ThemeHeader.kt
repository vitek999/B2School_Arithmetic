package com.test.b2schoolarithmetic.ui.main.vo

data class ThemeHeader(val name: String, val description: String) : ListItem() {
    override val getType: Int = TYPE_HEADER
}