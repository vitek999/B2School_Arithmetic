package com.test.b2schoolarithmetic.ui.main.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.ui.main.vo.LevelListItem
import com.test.b2schoolarithmetic.ui.main.vo.ListItem

class LevelItemViewHolder(parent: ViewGroup, private val onItemClick: (Long, String) -> Unit) : ListItemViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.theme_level_list_item, parent, false)
) {

    private val rootLayout = itemView.findViewById<ConstraintLayout>(R.id.rootLayout)
    private val tvLevelName: TextView = itemView.findViewById(R.id.level_name_text_view)
    private val tvLevelDescription: TextView =
        itemView.findViewById(R.id.level_description_text_view)
    private val ivTestQuality: ImageView = itemView.findViewById(R.id.test_quality_image_view)
    private val ivTestComplete: ImageView = itemView.findViewById(R.id.test_complete_image_view)

    override fun bind(item: ListItem) {
        val levelListItem = item as LevelListItem
        val levelName = "Уровень ${item.levelNumber}. "
        tvLevelName.text = levelName
        tvLevelDescription.text = levelListItem.name
        levelListItem.isGood?.let {ivTestQuality.setImageResource(if (it) R.drawable.ic_good_test else R.drawable.ic_bad_test)}
        if(levelListItem.isGood == null) ivTestQuality.setImageDrawable(null)
        ivTestComplete.setImageResource(if (levelListItem.isDone) R.drawable.ic_autorenew else R.drawable.ic_play_arrow)
        rootLayout.setOnClickListener { onItemClick(item.levelId, levelName + levelListItem.name) }
    }

}