package com.test.b2schoolarithmetic.ui.account.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.data.remote.dto.ClassGroupsDto

class GroupViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val tvName: TextView = itemView.findViewById(R.id.name_text_view)
    private val tvCity: TextView = itemView.findViewById(R.id.city_text_view)
    private val tvSchool: TextView = itemView.findViewById(R.id.school_text_view)
    private val tvGroupCount: TextView = itemView.findViewById(R.id.group_count_text_view)

    fun bind(item: ClassGroupsDto) {
        tvName.text = "${item.name} (${item.classNumber} ${item.literal})"
        tvCity.text = item.city
        tvSchool.text = item.educationalInstitution
        tvGroupCount.text = item.users.size.toString()
    }

    companion object {
        fun from(parent: ViewGroup) : GroupViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return GroupViewHolder(layoutInflater.inflate(R.layout.group_list_item, parent, false))
        }
    }
}