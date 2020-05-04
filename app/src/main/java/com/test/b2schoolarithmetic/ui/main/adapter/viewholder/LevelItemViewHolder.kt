package com.test.b2schoolarithmetic.ui.main.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.ui.main.vo.LevelListItem
import com.test.b2schoolarithmetic.ui.main.vo.ListItem

class LevelItemViewHolder(parent: ViewGroup) : ListItemViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.theme_level_list_item, parent, false)
) {

    private val tvLevelName: TextView = itemView.findViewById(R.id.level_name_text_view)
    private val tvLevelDescription: TextView =
        itemView.findViewById(R.id.level_description_text_view)
    private val ivTestQuality: ImageView = itemView.findViewById(R.id.test_quality_image_view)
    private val ivTestComplete: ImageView = itemView.findViewById(R.id.test_complete_image_view)

    override fun bind(item: ListItem) {
        val levelListItem = item as LevelListItem
        tvLevelName.text = levelListItem.name
        tvLevelDescription.text = levelListItem.description
        ivTestQuality.setImageResource(if (levelListItem.isGood) R.drawable.ic_good_test else R.drawable.ic_bad_test)
        ivTestComplete.setImageResource(if (levelListItem.isDone) R.drawable.ic_autorenew else R.drawable.ic_play_arrow)
    }

}