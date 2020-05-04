package com.test.b2schoolarithmetic.ui.main.adapter.viewholder

import android.view.ViewGroup

interface ListItemViewHolderFactory {
    fun createViewHolder(parent: ViewGroup): ListItemViewHolder
}

class LevelItemViewHolderFactory :
    ListItemViewHolderFactory {
    override fun createViewHolder(parent: ViewGroup): ListItemViewHolder {
        return LevelItemViewHolder(
            parent
        )
    }
}

class ThemeViewHolderFactory :
    ListItemViewHolderFactory {
    override fun createViewHolder(parent: ViewGroup): ListItemViewHolder {
        return ThemeViewHolder(
            parent
        )
    }
}