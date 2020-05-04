package com.test.b2schoolarithmetic.ui.main.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.ui.main.adapter.viewholder.ListItemViewHolder
import com.test.b2schoolarithmetic.ui.main.vo.ListItem
import com.test.b2schoolarithmetic.ui.main.vo.ThemeHeader

class ThemeViewHolder(parent: ViewGroup) : ListItemViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.theme_header_list_item, parent, false)
) {

    private val tvThemeName: TextView = itemView.findViewById(R.id.theme_name_text_view)
    private val thThemeDescription: TextView = itemView.findViewById(R.id.theme_description_text_view)

    override fun bind(item: ListItem) {
        val themeHeader = item as ThemeHeader
        tvThemeName.text = themeHeader.name
        thThemeDescription.text = themeHeader.description
    }
}