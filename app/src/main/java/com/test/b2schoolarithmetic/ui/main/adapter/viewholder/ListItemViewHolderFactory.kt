package com.test.b2schoolarithmetic.ui.main.adapter.viewholder

import android.view.ViewGroup

interface ListItemViewHolderFactory {
    fun createViewHolder(parent: ViewGroup, onItemClick: (Long) -> Unit): ListItemViewHolder
}

class LevelItemViewHolderFactory :
    ListItemViewHolderFactory {
    override fun createViewHolder(parent: ViewGroup, onItemClick: (Long) -> Unit): ListItemViewHolder {
        return LevelItemViewHolder(
            parent,
            onItemClick
        )
    }
}

class ThemeViewHolderFactory :
    ListItemViewHolderFactory {
    override fun createViewHolder(parent: ViewGroup, onItemClick: (Long) -> Unit): ListItemViewHolder {
        return ThemeViewHolder(
            parent
        )
    }
}