package com.test.b2schoolarithmetic.ui.main.vo

abstract class ListItem {

    abstract val getType: Int

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_EVENT = 1
    }
}