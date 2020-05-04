package com.test.b2schoolarithmetic.ui.main.vo

data class LevelListItem(
    val levelId: Long,
    val name: String,
    val levelNumber: Int,
    val description: String,
    val isGood: Boolean?,
    val isDone: Boolean
) : ListItem() {
    override val getType: Int = TYPE_EVENT
}