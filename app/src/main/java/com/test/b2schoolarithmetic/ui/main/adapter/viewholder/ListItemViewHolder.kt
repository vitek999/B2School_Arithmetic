package com.test.b2schoolarithmetic.ui.main.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.b2schoolarithmetic.ui.main.vo.ListItem

abstract class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: ListItem)
}